package com.loki2302.evaluation;

import com.loki2302.dom.DOMDoubleLiteralExpression;
import com.loki2302.expression.DoubleLiteralExpression;

public class DOMDoubleLiteralExpressionEvaluator {
	public ExpressionResult evaluateDOMDoubleLiteralExpression(DOMDoubleLiteralExpression expression) {
		String stringValue = expression.getStringValue();
		try {
			double value = Double.parseDouble(stringValue);
			return ExpressionResult.ok(new DoubleLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
}