package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMBinaryExpressionType;
import com.loki2302.dom.DOMExpression;
import com.loki2302.evaluation.matcher.BinaryOperationMatcher;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.expression.Expression;

public class DOMBinaryExpressionEvaluator {	
	@Inject @Named("addOperationRepository") BinaryOperationRepository addOperationRepository;
	@Inject @Named("addOperationMatcher") BinaryOperationMatcher addOperationMatcher;
		
	public ExpressionResult evaluateDOMBinaryExpression(DOMBinaryExpression expression, DOMExpressionEvaluator domExpressionEvaluator) {
		if(expression.getExpressionType() != DOMBinaryExpressionType.Add) {
			throw new RuntimeException();
		}
		
		DOMExpression leftDOMExpression = expression.getLeftExpression();
		DOMExpression rightDOMExpression = expression.getRightExpression();
		
		ExpressionResult leftResult = domExpressionEvaluator.evaluateDOMExpression(leftDOMExpression);
		ExpressionResult rightResult = domExpressionEvaluator.evaluateDOMExpression(rightDOMExpression);
		if(!leftResult.isOk() || !rightResult.isOk()) {
			return ExpressionResult.fail();
		}			
		
		Expression leftExpression = leftResult.getExpression();
		Expression rightExpression = rightResult.getExpression();
		
		return addOperationMatcher.match(
				addOperationRepository, 
				leftExpression, 
				rightExpression);
	}
}