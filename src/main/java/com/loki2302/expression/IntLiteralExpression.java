package com.loki2302.expression;

public class IntLiteralExpression implements Expression {
	private final int value;
	
	public IntLiteralExpression(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitIntLiteralExpression(this);
	}

	@Override
	public Type getResultType() {
		return Type.Int;
	}
}