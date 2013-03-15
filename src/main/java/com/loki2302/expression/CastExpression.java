package com.loki2302.expression;

public class CastExpression implements Expression {
	private final CastOperationType castType;
	private final Expression innerExpression;
	
	public CastExpression(
			CastOperationType castType, 
			Expression innerExpression) {
		
		this.castType = castType;
		this.innerExpression = innerExpression;
	}
	
	public CastOperationType getCastType() {
		return castType;
	}
	
	public Expression getInnerExpression() {
		return innerExpression;
	}

	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitCastExpression(this);
	}

	@Override
	public Type getResultType() {
		return Type.Double;
	}

	@Override
	public boolean isLvalue() {
		return false;
	}
}