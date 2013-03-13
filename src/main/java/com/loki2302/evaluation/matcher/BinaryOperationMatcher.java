package com.loki2302.evaluation.matcher;

import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.expression.Expression;

public interface BinaryOperationMatcher {
	ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository, 
			Expression leftExpression, 
			Expression rightExpression);
}