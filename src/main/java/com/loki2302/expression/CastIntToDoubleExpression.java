package com.loki2302.expression;

public class CastIntToDoubleExpression implements Expression {
	private final Expression innerExpression;
	
	public CastIntToDoubleExpression(Expression innerExpression) {
		this.innerExpression = innerExpression;
	}
	
	public Expression getInnerExpression() {
		return innerExpression;
	}

	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitCastIntToDoubleExpression(this);
	}

	@Override
	public Type getType() {
		return Type.Double;
	}
}