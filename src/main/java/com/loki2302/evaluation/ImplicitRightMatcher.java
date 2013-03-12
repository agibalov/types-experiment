package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ImplicitRightMatcher implements Matcher {
	@Inject AddOperationRepository addOperationRepository;
	@Inject CastOperationLogic castOperationLogic;

	public ExpressionResult match(Expression leftExpression, Expression rightExpression) {
		Type leftType = leftExpression.getType();		
		AddOperationDefinition addOperationDefinition = addOperationRepository.findByLeftType(leftType);
		if(addOperationDefinition != null) {
			Type requiredRightType = addOperationDefinition.getRightType();
			ExpressionResult castResult = castOperationLogic.implicitlyCast(
					rightExpression, 
					requiredRightType);
			if(castResult.isOk()) {
				return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(
						leftExpression, 
						castResult.getExpression()));
			}					
		}
		
		return ExpressionResult.fail();
	}
}