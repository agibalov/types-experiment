package com.loki2302.evaluation;

import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public interface AddOperationDefinition {
	Type getLeftType();
	Type getRightType();		
	Expression makeOperationExpression(Expression leftExpression, Expression rightExpression);
}