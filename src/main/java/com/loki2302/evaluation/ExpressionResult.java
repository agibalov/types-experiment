package com.loki2302.evaluation;

import com.loki2302.expression.Expression;

public class ExpressionResult {
	private boolean ok;
	private Expression expression;
	
	private ExpressionResult() {			
	}
	
	public boolean isOk() {
		return ok;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public static ExpressionResult ok(Expression expression) {
		ExpressionResult result = new ExpressionResult();
		result.ok = true;
		result.expression = expression;
		return result;
	}
	
	public static ExpressionResult fail() {
		ExpressionResult result = new ExpressionResult();
		result.ok = false;
		return result;
	}	
	
	public String toString() {
		if(ok) {
			return String.format("ExpressionResult[%s]", expression);
		}
		
		return "ExpressionResult[error]";
	}
}