package com.loki2302.evaluation;

import static com.loki2302.evaluation.operations.BinaryOperationRepository.*;
import static com.loki2302.evaluation.operations.CastOperationRepository.*;

import com.google.inject.Inject;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.evaluation.operations.CastOperationDefinition;
import com.loki2302.evaluation.operations.CastOperationRepository;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class DOMBinaryExpressionEvaluator {	
	@Inject CastOperationRepository castOperationRepository;
	@Inject BinaryOperationRepository binaryOperationRepository;
		
	public ExpressionResult evaluateDOMBinaryExpression(
			DOMBinaryExpression expression, 
			DOMExpressionEvaluator domExpressionEvaluator) {
		
		ExpressionResult leftResult = domExpressionEvaluator.evaluateDOMExpression(expression.getLeftExpression());
		ExpressionResult rightResult = domExpressionEvaluator.evaluateDOMExpression(expression.getRightExpression());
		if(!leftResult.isOk() || !rightResult.isOk()) {
			return ExpressionResult.fail();
		}			
		
		Expression leftExpression = leftResult.getExpression();
		Expression rightExpression = rightResult.getExpression();
		BinaryOperationFamily binaryOperationFamily = expression.getBinaryOperationFamily();
		
		ExpressionResult expressionResult;
		
		if(binaryOperationFamily == BinaryOperationFamily.Assignment) {
		    if(!leftExpression.isLvalue()) {
		        return ExpressionResult.fail();
		    }
		    
		    expressionResult = tryExactMatch(
		            leftExpression, 
		            rightExpression,
		            binaryOperationFamily);
	        if(expressionResult.isOk()) {
	            return expressionResult;
	        }
	        
	        expressionResult = tryImplicitRightMatch(
	                leftExpression, 
	                rightExpression,
	                binaryOperationFamily);
	        if(expressionResult.isOk()) {
	            return expressionResult;
	        }
		} else {
    		expressionResult = tryExactMatch(
    				leftExpression, 
    				rightExpression, 
    				binaryOperationFamily);
    		if(expressionResult.isOk()) {
    			return expressionResult;
    		}
    		
    		expressionResult = tryImplicitRightMatch(
    				leftExpression, 
    				rightExpression, 
    				binaryOperationFamily);
    		if(expressionResult.isOk()) {
    			return expressionResult;
    		}
    		
    		expressionResult = tryImplicitLeftMatch(
    				leftExpression, 
    				rightExpression, 
    				binaryOperationFamily);
    		if(expressionResult.isOk()) {
    			return expressionResult;
    		}
		}
		
		return ExpressionResult.fail();
	}
	
	private ExpressionResult tryExactMatch(
			Expression leftExpression, 
			Expression rightExpression, 
			BinaryOperationFamily binaryOperationFamily) {
		
		Type leftType = leftExpression.getResultType();
		Type rightType = rightExpression.getResultType();					
				
		BinaryOperationDefinition binaryOperationDefinition =
				binaryOperationRepository.firstWhere(
						familyIs(binaryOperationFamily), 
						leftTypeIs(leftType), 
						rightTypeIs(rightType));
		
		if(binaryOperationDefinition == null) {
			return ExpressionResult.fail();
		}
		
		return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
				leftExpression,
				rightExpression));
	}
	
	private ExpressionResult tryImplicitRightMatch(
			Expression leftExpression, 
			Expression rightExpression, 
			BinaryOperationFamily binaryOperationFamily) {
		
		Type leftType = leftExpression.getResultType();
		Type rightType = rightExpression.getResultType();
		
		BinaryOperationDefinition binaryOperationDefinition = 
				binaryOperationRepository.firstWhere(
						familyIs(binaryOperationFamily),
						leftTypeIs(leftType));
		
		if(binaryOperationDefinition == null) {
			return ExpressionResult.fail();
		}
		
		Type requiredRightType = binaryOperationDefinition.getRightType();			
		CastOperationDefinition castOperationDefinition = 
				castOperationRepository.firstWhere(
						isImplicit(),
						sourceTypeIs(rightType),
						destinationTypeIs(requiredRightType));
			
		if(castOperationDefinition == null) {
			return ExpressionResult.fail();
		}
		
		return ExpressionResult.ok(binaryOperationDefinition.makeExpression(						
				leftExpression,
				castOperationDefinition.makeExpression(rightExpression)));
	}
	
	private ExpressionResult tryImplicitLeftMatch(
			Expression leftExpression, 
			Expression rightExpression, 
			BinaryOperationFamily binaryOperationFamily) {
		
		Type leftType = leftExpression.getResultType();
		Type rightType = rightExpression.getResultType();
		
		BinaryOperationDefinition binaryOperationDefinition = 
				binaryOperationRepository.firstWhere(
						familyIs(binaryOperationFamily), 
						rightTypeIs(rightType));
		
		if(binaryOperationDefinition == null) {
			return ExpressionResult.fail();
		}
		
		Type requiredLeftType = binaryOperationDefinition.getLeftType();
		CastOperationDefinition castOperationDefinition = 
				castOperationRepository.firstWhere(
						isImplicit(),
						sourceTypeIs(leftType), 
						destinationTypeIs(requiredLeftType));
			
		if(castOperationDefinition == null) {
			return ExpressionResult.fail();
		}
		
		return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
				castOperationDefinition.makeExpression(leftExpression),
				rightExpression));
	}
	
}