package com.loki2302.evaluation;

import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.expression.DoubleLiteralExpression;
import com.loki2302.expression.IntLiteralExpression;

public class DOMLiteralExpressionEvaluator {
	public ExpressionResult evaluateDOMLiteralExpression(DOMLiteralExpression expression) {
		DOMLiteralType literalType = expression.getLiteralType();
		if(literalType == DOMLiteralType.Int) {
			return evaluateDOMIntLiteralExpression(expression);
		} else if(literalType == DOMLiteralType.Double) {
			return evaluateDOMDoubleLiteralExpression(expression);
		}
		
		throw new RuntimeException();
	}
	
	private ExpressionResult evaluateDOMIntLiteralExpression(DOMLiteralExpression expression) {
		String stringValue = expression.getStringValue();
		try {
			int value = Integer.parseInt(stringValue);
			return ExpressionResult.ok(new IntLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
	
	private ExpressionResult evaluateDOMDoubleLiteralExpression(DOMLiteralExpression expression) {
		String stringValue = expression.getStringValue();
		try {
			double value = Double.parseDouble(stringValue);
			return ExpressionResult.ok(new DoubleLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
}