package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ExactMatcher implements Matcher {
	@Inject AddOperationRepository addOperationRepository;
	
	public ExpressionResult match(Expression leftExpression, Expression rightExpression) {
		Type leftType = leftExpression.getType();
		Type rightType = rightExpression.getType();					
		
		AddOperationDefinition addOperationDefinition = 
				addOperationRepository.findByLeftAndRightTypes(leftType, rightType);
		if(addOperationDefinition != null) {
			return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(
					leftExpression,
					rightExpression));
		}
		
		return ExpressionResult.fail();
	}	
}