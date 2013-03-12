package com.loki2302.evaluation;

import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public interface CastOperationDefinition {
	Type getSourceType();
	Type getDestinationType();
	boolean isImplicit();
	Expression makeExpression(Expression sourceExpression);
}