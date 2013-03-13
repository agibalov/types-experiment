package com.loki2302.evaluation.operations;

import com.loki2302.expression.BinaryExpression;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.BinaryOperationType;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class BinaryOperationDefinition {
	private final BinaryOperationFamily binaryOperationFamily;
	private final BinaryOperationType operationType;
	private final Type leftType;
	private final Type rightType;	
	private final Type resultType;
	
	public BinaryOperationDefinition(
			BinaryOperationFamily binaryOperationFamily,
			BinaryOperationType operationType,
			Type leftType,
			Type rightType,			
			Type resultType) {
		
		this.binaryOperationFamily = binaryOperationFamily;
		this.operationType = operationType;
		this.leftType = leftType;
		this.rightType = rightType;		
		this.resultType = resultType;
	}
	
	public BinaryOperationFamily getBinaryOperationFamily() {
		return binaryOperationFamily;
	}
	
	public BinaryOperationType getOperationType() {
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
		return new BinaryExpression(operationType, leftExpression, rightExpression, resultType);
	}
}