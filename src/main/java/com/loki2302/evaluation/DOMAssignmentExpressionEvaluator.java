package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.dom.DOMAssignmentExpression;
import com.loki2302.evaluation.operations.AssignmentOperationDefinition;
import com.loki2302.evaluation.operations.AssignmentOperationRepository;
import com.loki2302.evaluation.operations.CastOperationDefinition;
import com.loki2302.evaluation.operations.CastOperationRepository;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;
import static com.loki2302.evaluation.operations.AssignmentOperationRepository.*;
import static com.loki2302.evaluation.operations.CastOperationRepository.*;

public class DOMAssignmentExpressionEvaluator {
	@Inject CastOperationRepository castOperationRepository;
	@Inject AssignmentOperationRepository assignmentOperationRepository;
	
	public ExpressionResult evaluateDOMAssignmentExpression(
			DOMAssignmentExpression expression, 
			DOMExpressionEvaluator domExpressionEvaluator) {
		
		ExpressionResult leftResult = domExpressionEvaluator.evaluateDOMExpression(expression.getLeftExpression());
		ExpressionResult rightResult = domExpressionEvaluator.evaluateDOMExpression(expression.getRightExpression());
		if(!leftResult.isOk() || !rightResult.isOk()) {
			return ExpressionResult.fail();
		}			
		
		Expression leftExpression = leftResult.getExpression();
		Expression rightExpression = rightResult.getExpression();
		
		if(!leftExpression.isLvalue()) {
			return ExpressionResult.fail();
		}
		
		ExpressionResult expressionResult = null;
		
		expressionResult = tryExactMatch(leftExpression, rightExpression);
		if(expressionResult.isOk()) {
			return expressionResult;
		}
		
		expressionResult = tryImplicitRightMatch(leftExpression, rightExpression);
		if(expressionResult.isOk()) {
			return expressionResult;
		}
		
		return ExpressionResult.fail();
	}
	
	private ExpressionResult tryExactMatch(
			Expression leftExpression, 
			Expression rightExpression) {
		
		AssignmentOperationDefinition assignmentOperationDefinition = 
				assignmentOperationRepository.firstWhere(
						leftTypeIs(leftExpression.getResultType()),
						rightTypeIs(rightExpression.getResultType()));		
		if(assignmentOperationDefinition == null) {
			return ExpressionResult.fail();
		}
	
		Expression assignmentExpression = 
				assignmentOperationDefinition.makeExpression(
						leftExpression, 
						rightExpression);
		
		return ExpressionResult.ok(assignmentExpression);
	}
	
	private ExpressionResult tryImplicitRightMatch(
			Expression leftExpression, 
			Expression rightExpression) {
		
		AssignmentOperationDefinition assignmentOperationDefinition = 
				assignmentOperationRepository.firstWhere(
						leftTypeIs(leftExpression.getResultType()));
		if(assignmentOperationDefinition == null) {
			return ExpressionResult.fail();
		}
		
		Type requiredRightType = assignmentOperationDefinition.getRightType();
		CastOperationDefinition castOperationDefinition = castOperationRepository.firstWhere(
				sourceTypeIs(rightExpression.getResultType()),
				destinationTypeIs(requiredRightType));
		if(castOperationDefinition == null) {
			return ExpressionResult.fail();
		}	
		
		return ExpressionResult.ok(assignmentOperationDefinition.makeExpression(
				leftExpression, 
				castOperationDefinition.makeExpression(rightExpression))); 
	}
}