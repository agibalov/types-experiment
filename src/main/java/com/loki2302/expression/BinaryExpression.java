package com.loki2302.expression;

public class BinaryExpression implements Expression {
	private final BinaryOperationType expressionType;
	private final Expression leftExpression;
	private final Expression rightExpression;
	private final Type resultType;
	
	public BinaryExpression(
			BinaryOperationType expressionType,
			Expression leftExpression, 
			Expression rightExpression,
			Type resultType) {
		
		this.expressionType = expressionType;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.resultType = resultType;
	}
	
	public BinaryOperationType getOperationType() {
		return expressionType;
	}
	
	public Expression getLeftExpression() {
		return leftExpression;
	}
	
	public Expression getRightExpression() {
		return rightExpression;
	}
	
	public Type getResultType() {
		return resultType;
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitBinaryExpression(this);
	}

	@Override
	public boolean isLvalue() {
		return false;
	}
}