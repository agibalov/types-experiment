package com.loki2302.evaluation;

import com.loki2302.expression.Expression;

public interface BinaryOperationMatcher {
	ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository, 
			Expression leftExpression, 
			Expression rightExpression);
}