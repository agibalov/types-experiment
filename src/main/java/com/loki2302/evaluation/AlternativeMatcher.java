package com.loki2302.evaluation;

import com.loki2302.expression.Expression;

public class AlternativeMatcher implements Matcher {		
	private final Matcher[] matchers;
	
	public AlternativeMatcher(Matcher... matchers) {
		this.matchers = matchers;
	}
	
	@Override
	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		
		for(Matcher matcher : matchers) {
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