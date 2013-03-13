package com.loki2302.evaluation.matcher;

import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.expression.Expression;

public class AlternativeBinaryOperationMatcher implements BinaryOperationMatcher {		
	private final BinaryOperationMatcher[] matchers;
	
	public AlternativeBinaryOperationMatcher(BinaryOperationMatcher... matchers) {
		this.matchers = matchers;
	}
	
	@Override
	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		
		for(BinaryOperationMatcher matcher : matchers) {
			ExpressionResult expressionResult = matcher.match(
					binaryOperationRepository,
					leftExpression, 
					rightExpression);
			
			if(!expressionResult.isOk()) {
				continue;
			}
			
			return expressionResult;
		}
		
		return ExpressionResult.fail();
	}		
}