package com.loki2302.expression;

public class IntAddExpression extends BinaryExpression {	
	public IntAddExpression(Expression leftExpression, Expression rightExpression) {
		super(leftExpression, rightExpression);
	}
	
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitIntAddExpression(this);
	}

	@Override
	public Type getType() {
		return Type.Int;
	}
}