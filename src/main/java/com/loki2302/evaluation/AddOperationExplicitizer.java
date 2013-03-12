package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;

public class AddOperationExplicitizer {	
	// TODO: get this stuff injected as Set<> of implementations
	@Inject ExactMatcher exactMatcher;
	@Inject ImplicitRightMatcher implicitRightMatcher;
	@Inject ImplicitLeftMatcher implicitLeftMatcher;
	//
	
	public ExpressionResult makeOperationExpression(
			Expression leftExpression, 
			Expression rightExpression) {
		
		// TODO: get this stuff injected as Set<> of implementations
		Matcher[] matchers = { 
				exactMatcher, 
				implicitRightMatcher, 
				implicitLeftMatcher 
		};
		//
		
		for(Matcher matcher : matchers) {
			ExpressionResult expressionResult = matcher.match(
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