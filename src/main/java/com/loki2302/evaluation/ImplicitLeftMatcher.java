package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ImplicitLeftMatcher implements Matcher {
	@Inject CastOperationLogic castOperationLogic;
	
	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		Type rightType = rightExpression.getType();		
		BinaryOperationDefinition addOperationDefinition = binaryOperationRepository.findByRightType(rightType);
		if(addOperationDefinition != null) {
			Type requiredLeftType = addOperationDefinition.getLeftType();
			ExpressionResult castResult = castOperationLogic.implicitlyCast(
					leftExpression, 
					requiredLeftType);
			if(castResult.isOk()) {
				return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(
						castResult.getExpression(),
						rightExpression));
			}
		}
		
		return ExpressionResult.fail();
	}	
}