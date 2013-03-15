package com.loki2302.expression;

public class BoolLiteralExpression implements Expression {
	private final boolean value;
	
	public BoolLiteralExpression(boolean value) {
		this.value = value;
	}
	
	public boolean getValue() {
		return value;
	}

	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitBoolLiteralExpression(this);
	}

	@Override
	public Type getResultType() {
		return Type.Bool;
	}

	@Override
	public boolean isLvalue() {
		return false;
	}
}