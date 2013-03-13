package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ImplicitLeftBinaryOperationMatcher implements BinaryOperationMatcher {
	@Inject CastOperationRepository castOperationRepository;
	
	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		Type rightType = rightExpression.getType();		
		BinaryOperationDefinition addOperationDefinition = binaryOperationRepository.findByRightType(rightType);
		if(addOperationDefinition != null) {
			Type requiredLeftType = addOperationDefinition.getLeftType();
			Type leftType = leftExpression.getType();
			
			CastOperationDefinition castOperationDefinition = 
					castOperationRepository.findImplicitBySourceAndDestinationTypes(
							leftType, 
							requiredLeftType);
			
			if(castOperationDefinition != null) {
				return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(
						castOperationDefinition.makeExpression(leftExpression),
						rightExpression));
			}
		}
		
		return ExpressionResult.fail();
	}	
}