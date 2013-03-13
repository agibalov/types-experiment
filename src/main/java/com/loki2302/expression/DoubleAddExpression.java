package com.loki2302.expression;

public class DoubleAddExpression extends BinaryExpression {	
	public DoubleAddExpression(Expression leftExpression, Expression rightExpression) {
		super(leftExpression, rightExpression);
	}	
	
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitDoubleAddExpression(this);
	}

	@Override
	public Type getType() {
		return Type.Double;
	}
}