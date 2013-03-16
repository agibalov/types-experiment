package com.loki2302.evaluation.operations;

import com.loki2302.expression.AssignmentExpression;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class AssignmentOperationDefinition {
	private final AssignmentOperationType operationType;
	private final Type leftType;
	private final Type rightType;	
	private final Type resultType;
	
	public AssignmentOperationDefinition(
			AssignmentOperationType operationType,
			Type leftType,
			Type rightType,			
			Type resultType) {
		this.operationType = operationType;
		this.leftType = leftType;
		this.rightType = rightType;		
		this.resultType = resultType;
	}
	
	public AssignmentOperationType getOperationType() {
		return operationType;
	}
	
	public Type getLeftType() {
		return leftType;
	}
	
	public Type getRightType() {
		return rightType;
	}
	
	public Type getResultType() {
		return resultType;
	}
	
	public Expression makeExpression(Expression leftExpression, Expression rightExpression) {
		return new AssignmentExpression(operationType, leftExpression, rightExpression);
	}
}