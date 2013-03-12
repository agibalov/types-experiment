package com.loki2302.evaluation;

import com.loki2302.expression.DoubleAddExpression;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class DoubleAddOperationDefinition implements AddOperationDefinition {
	public Type getLeftType() {
		return Type.Double;
	}

	public Type getRightType() {
		return Type.Double;
	}

	public Expression makeOperationExpression(Expression leftExpression,	Expression rightExpression) {
		return new DoubleAddExpression(leftExpression, rightExpression);
	}		
}