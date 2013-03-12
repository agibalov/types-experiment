package com.loki2302.expression;

public class DoubleAddExpression implements Expression {
	private final Expression leftExpression;
	private final Expression rightExpression;
	
	public DoubleAddExpression(Expression leftExpression, Expression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public Expression getLeftExpression() {
		return leftExpression;
	}
	
	public Expression getRightExpression() {
		return rightExpression;
	}
	
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitDoubleAddExpression(this);
	}

	@Override
	public Type getType() {
		return Type.Double;
	}
}