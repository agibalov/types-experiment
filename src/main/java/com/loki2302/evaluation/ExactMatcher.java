package com.loki2302.evaluation;

import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ExactMatcher implements BinaryOperationMatcher {	
	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		Type leftType = leftExpression.getType();
		Type rightType = rightExpression.getType();					
		
		BinaryOperationDefinition addOperationDefinition = 
				binaryOperationRepository.findByLeftAndRightTypes(leftType, rightType);
		if(addOperationDefinition != null) {
			return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(
					leftExpression,
					rightExpression));
		}
		
		return ExpressionResult.fail();
	}	
}