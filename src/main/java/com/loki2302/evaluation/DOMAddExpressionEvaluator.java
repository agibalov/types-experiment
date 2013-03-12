package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.dom.DOMAddExpression;
import com.loki2302.dom.DOMExpression;
import com.loki2302.expression.Expression;

public class DOMAddExpressionEvaluator {
	@Inject AddOperationExplicitizer addOperationExplicitizer;
	
	public ExpressionResult evaluateDOMAddExpression(DOMAddExpression expression, DOMExpressionEvaluator domExpressionEvaluator) {			
		DOMExpression leftDOMExpression = expression.getLeftExpression();
		DOMExpression rightDOMExpression = expression.getRightExpression();
		
		ExpressionResult leftResult = domExpressionEvaluator.evaluateDOMExpression(leftDOMExpression);
		ExpressionResult rightResult = domExpressionEvaluator.evaluateDOMExpression(rightDOMExpression);
		if(!leftResult.isOk() || !rightResult.isOk()) {
			return ExpressionResult.fail();
		}			
		
		Expression leftExpression = leftResult.getExpression();
		Expression rightExpression = rightResult.getExpression();
		return addOperationExplicitizer.makeOperationExpression(leftExpression, rightExpression);
	}
}