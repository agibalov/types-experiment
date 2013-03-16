package com.loki2302.expression;

import com.loki2302.evaluation.operations.AssignmentOperationType;

public class AssignmentExpression implements Expression {
	private final AssignmentOperationType operationType;
	private final Expression leftExpression;
	private final Expression rightExpression;

	public AssignmentExpression(
			AssignmentOperationType operationType,
			Expression leftExpression, 
			Expression rightExpression) {	
		
		this.operationType = operationType;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public AssignmentOperationType getOperationType() {
		return operationType;
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