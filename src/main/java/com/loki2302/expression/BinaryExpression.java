package com.loki2302.expression;

public abstract class BinaryExpression implements Expression {
	private final Expression leftExpression;
	private final Expression rightExpression;
	
	protected BinaryExpression(Expression leftExpression, Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public Expression getLeftExpression() {
		return leftExpression;
	}
	
	public Expression getRightExpression() {
		return rightExpression;
	}	
}