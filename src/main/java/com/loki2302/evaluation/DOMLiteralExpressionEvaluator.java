package com.loki2302.evaluation;

import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.expression.BoolLiteralExpression;
import com.loki2302.expression.DoubleLiteralExpression;
import com.loki2302.expression.IntLiteralExpression;

public class DOMLiteralExpressionEvaluator {
	public ExpressionResult evaluateDOMLiteralExpression(DOMLiteralExpression expression) {
		DOMLiteralType literalType = expression.getLiteralType();
		String stringValue = expression.getStringValue();
		if(literalType == DOMLiteralType.Int) {
			return evaluateDOMIntLiteralExpression(stringValue);
		} else if(literalType == DOMLiteralType.Double) {
			return evaluateDOMDoubleLiteralExpression(stringValue);
		} else if(literalType == DOMLiteralType.Bool) {
			return evaluateDOMBoolLiteralExpression(stringValue);
		}
		
		throw new RuntimeException();
	}
	
	private ExpressionResult evaluateDOMIntLiteralExpression(String stringValue) {
		try {
			int value = Integer.parseInt(stringValue);
			return ExpressionResult.ok(new IntLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
	
	private ExpressionResult evaluateDOMDoubleLiteralExpression(String stringValue) {
		try {
			double value = Double.parseDouble(stringValue);
			return ExpressionResult.ok(new DoubleLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
	
	private ExpressionResult evaluateDOMBoolLiteralExpression(String stringValue) {
		try {
			boolean value = Boolean.parseBoolean(stringValue);
			return ExpressionResult.ok(new BoolLiteralExpression(value));
		} catch(NumberFormatException e) {}
		
		return ExpressionResult.fail();
	}
}