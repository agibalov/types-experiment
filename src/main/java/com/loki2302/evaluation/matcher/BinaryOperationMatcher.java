package com.loki2302.evaluation.matcher;

import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;

public interface BinaryOperationMatcher {
	ExpressionResult match(
			BinaryOperationFamily semantics,
			Expression leftExpression, 
			Expression rightExpression);
}