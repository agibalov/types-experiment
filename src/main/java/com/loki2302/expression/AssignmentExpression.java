package com.loki2302.expression;

public class AssignmentExpression implements Expression {
	private final Expression leftExpression;
	private final Expression rightExpression;

	public AssignmentExpression(
			Expression leftExpression, 
			Expression rightExpression) {	
		
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public Expression getLeftExpression() {
		return leftExpression;
	}
	
	public Expression getRightExpression() {
		return rightExpression;
	}
	
	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		return visitor.visitAssignmentExpression(this);
	}

	@Override
	public Type getResultType() {
		return leftExpression.getResultType();
	}

	@Override
	public boolean isLvalue() {
		return false;
	}	
}