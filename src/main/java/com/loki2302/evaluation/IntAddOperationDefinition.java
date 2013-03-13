package com.loki2302.evaluation;

import com.loki2302.expression.Expression;
import com.loki2302.expression.IntAddExpression;
import com.loki2302.expression.Type;


public class IntAddOperationDefinition implements BinaryOperationDefinition {
	public Type getLeftType() {
		return Type.Int;
	}

	public Type getRightType() {
		return Type.Int;
	}

	public Expression makeOperationExpression(Expression leftExpression,	Expression rightExpression) {
		return new IntAddExpression(leftExpression, rightExpression);
	}		
}