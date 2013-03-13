package com.loki2302.evaluation.operations;

import com.loki2302.expression.BinaryExpression;
import com.loki2302.expression.BinaryOperationType;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class BinaryOperationDefinition {
	private final Type leftType;
	private final Type rightType;
	private final BinaryOperationType expressionType;
	private final Type resultType;
	
	public BinaryOperationDefinition(
			Type leftType,
			Type rightType,
			BinaryOperationType expressionType,
			Type resultType) {
		
		this.leftType = leftType;
		this.rightType = rightType;
		this.expressionType = expressionType;
		this.resultType = resultType;
	}
	
	public Type getLeftType() {
		return leftType;
	}
	
	public Type getRightType() {
		return rightType;
	}
	
	public BinaryOperationType getExpressionType() {
		return expressionType;
	}
	
	public Type getResultType() {
		return resultType;
	}
	
	public Expression makeExpression(Expression leftExpression, Expression rightExpression) {
		return new BinaryExpression(expressionType, leftExpression, rightExpression, resultType);
	}
}