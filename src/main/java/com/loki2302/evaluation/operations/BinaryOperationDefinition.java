package com.loki2302.evaluation.operations;

import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public interface BinaryOperationDefinition {
	Type getLeftType();
	Type getRightType();		
	Expression makeOperationExpression(Expression leftExpression, Expression rightExpression);
}