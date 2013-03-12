package com.loki2302.evaluation;

import com.loki2302.dom.DOMIntLiteralExpression;
import com.loki2302.expression.IntLiteralExpression;

public class DOMIntLiteralExpressionEvaluator {
	public ExpressionResult evaluateDOMIntLiteralExpression(DOMIntLiteralExpression expression) {
		String stringValue = expression.getStringValue();
		try {
			int value = Integer.parseInt(stringValue);
			return ExpressionResult.ok(new IntLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
}