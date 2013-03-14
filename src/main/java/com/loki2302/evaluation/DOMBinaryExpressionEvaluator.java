package com.loki2302.evaluation;

import static com.loki2302.evaluation.operations.BinaryOperationRepository.*;
import static com.loki2302.evaluation.operations.CastOperationRepository.*;

import com.google.inject.Inject;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.evaluation.operations.CastOperationDefinition;
import com.loki2302.evaluation.operations.CastOperationRepository;
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
		
		Type leftType = leftExpression.getResultType();
		Type rightType = rightExpression.getResultType();					
				
		// try exact match
		BinaryOperationDefinition binaryOperationDefinition =
				binaryOperationRepository.firstWhere(
						familyIs(expression.getBinaryOperationFamily()), 
						leftTypeIs(leftType), 
						rightTypeIs(rightType));
		
		if(binaryOperationDefinition != null) {
			return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
					leftExpression,
					rightExpression));
		}
		
		// try right cast
		binaryOperationDefinition = 
				binaryOperationRepository.firstWhere(
						familyIs(expression.getBinaryOperationFamily()),
						leftTypeIs(leftType));
		
		if(binaryOperationDefinition != null) {
			Type requiredRightType = binaryOperationDefinition.getRightType();			
			CastOperationDefinition castOperationDefinition = 
					castOperationRepository.firstWhere(
							isImplicit(),
							sourceTypeIs(rightType),
							destinationTypeIs(requiredRightType));
			
			if(castOperationDefinition != null) {
				return ExpressionResult.ok(binaryOperationDefinition.makeExpression(						
						leftExpression,
						castOperationDefinition.makeExpression(rightExpression)));
			}
		}
		
		// try left cast
		binaryOperationDefinition = 
				binaryOperationRepository.firstWhere(
						familyIs(expression.getBinaryOperationFamily()), 
						rightTypeIs(rightType));
		
		if(binaryOperationDefinition != null) {
			Type requiredLeftType = binaryOperationDefinition.getLeftType();
			CastOperationDefinition castOperationDefinition = 
					castOperationRepository.firstWhere(
							isImplicit(),
							sourceTypeIs(leftType), 
							destinationTypeIs(requiredLeftType));
			
			if(castOperationDefinition != null) {
				return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
						castOperationDefinition.makeExpression(leftExpression),
						rightExpression));
			}
		}
		
		return ExpressionResult.fail();
	}
}