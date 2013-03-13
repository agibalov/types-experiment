package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ImplicitRightMatcher implements Matcher {	
	@Inject CastOperationRepository castOperationRepository;

	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		Type leftType = leftExpression.getType();		
		BinaryOperationDefinition addOperationDefinition = binaryOperationRepository.findByLeftType(leftType);
		if(addOperationDefinition != null) {
			Type requiredRightType = addOperationDefinition.getRightType();
			Type rightType = rightExpression.getType();
			
			CastOperationDefinition castOperationDefinition = 
					castOperationRepository.findImplicitBySourceAndDestinationTypes(
							rightType, 
							requiredRightType);
			
			if(castOperationDefinition != null) {
				return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(						
						leftExpression,
						castOperationDefinition.makeExpression(rightExpression)));
			}
		}
		
		return ExpressionResult.fail();
	}
}