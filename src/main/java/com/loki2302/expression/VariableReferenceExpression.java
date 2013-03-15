package com.loki2302.expression;

public class VariableReferenceExpression implements Expression {
	private final Type variableType;
	
	public VariableReferenceExpression(Type vairableType) {
		this.variableType = vairableType;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitVariableReferenceExpression(this);
	}

	@Override
	public Type getResultType() {
		return variableType;
	}

	@Override
	public boolean isLvalue() {
		return true;
	}	
}