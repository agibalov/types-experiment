package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class AddOperationExplicitizer {	
	@Inject AddOperationRepository addOperationRepository;
	@Inject CastOperationLogic castOperationLogic;
		
	public ExpressionResult makeOperationExpression(Expression leftExpression, Expression rightExpression) {
		Type leftType = leftExpression.getType();
		Type rightType = rightExpression.getType();					
		
		AddOperationDefinition addOperationDefinition = 
				addOperationRepository.findByLeftAndRightTypes(leftType, rightType);
		if(addOperationDefinition != null) {
			return ExpressionResult.ok(addOperationDefinition.makeOperationExpression(
					leftExpression,
					rightExpression));
		}
		
		addOperationDefinition = addOperationRepository.findByLeftType(leftType);
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
		
		addOperationDefinition = addOperationRepository.findByRightType(rightType);
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