package com.loki2302.expression;

public class DoubleLiteralExpression implements Expression {
	private final double value;
	
	public DoubleLiteralExpression(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}

	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitDoubleLiteralExpression(this);
	}

	@Override
	public Type getType() {
		return Type.Double;
	}
}