package com.loki2302.expression;

public class IntAddExpression implements Expression {
	private final Expression leftExpression;
	private final Expression rightExpression;
	
	public IntAddExpression(Expression leftExpression, Expression rightExpression) {
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
		return visitor.visitIntAddExpression(this);
	}

	@Override
	public Type getType() {
		return Type.Int;
	}
}