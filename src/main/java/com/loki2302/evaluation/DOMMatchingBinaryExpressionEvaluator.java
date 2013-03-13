package com.loki2302.evaluation;

import com.loki2302.dom.DOMExpression;
import com.loki2302.evaluation.matcher.BinaryOperationMatcher;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.expression.BinaryOperationSemantics;
import com.loki2302.expression.Expression;

public class DOMMatchingBinaryExpressionEvaluator {
	private final BinaryOperationSemantics semantics;
	private final BinaryOperationRepository operationRepository;
	private final BinaryOperationMatcher operationMatcher;
	
	public DOMMatchingBinaryExpressionEvaluator(
			BinaryOperationSemantics semantics,
			BinaryOperationRepository operationRepository,
			BinaryOperationMatcher operationMatcher) {
		this.semantics = semantics;
		this.operationRepository = operationRepository;
		this.operationMatcher = operationMatcher;
	}
	
	public ExpressionResult evaluateBinaryExpression(
			DOMExpression leftDOMExpression, 
			DOMExpression rightDOMExpression,
			DOMExpressionEvaluator domExpressionEvaluator) {
		
		ExpressionResult leftResult = domExpressionEvaluator.evaluateDOMExpression(leftDOMExpression);
		ExpressionResult rightResult = domExpressionEvaluator.evaluateDOMExpression(rightDOMExpression);
		if(!leftResult.isOk() || !rightResult.isOk()) {
			return ExpressionResult.fail();
		}			
		
		Expression leftExpression = leftResult.getExpression();
		Expression rightExpression = rightResult.getExpression();
		
		return operationMatcher.match(
				operationRepository, 
				semantics,
				leftExpression, 
				rightExpression);
	}
}