package com.loki2302.evaluation.matcher;

import com.google.inject.Inject;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.evaluation.operations.CastOperationDefinition;
import com.loki2302.evaluation.operations.CastOperationRepository;
import com.loki2302.expression.BinaryOperationSemantics;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;
import static com.loki2302.evaluation.operations.BinaryOperationRepository.*;

public class ImplicitRightBinaryOperationMatcher implements BinaryOperationMatcher {	
	@Inject BinaryOperationRepository binaryOperationRepository;
	@Inject CastOperationRepository castOperationRepository;

	public ExpressionResult match(			
			BinaryOperationSemantics semantics,
			Expression leftExpression, 
			Expression rightExpression) {
		Type leftType = leftExpression.getResultType();		
		BinaryOperationDefinition binaryOperationDefinition = 
				binaryOperationRepository.firstWhere(
						semanticsIs(semantics),
						leftTypeIs(leftType));
		
		if(binaryOperationDefinition != null) {
			Type requiredRightType = binaryOperationDefinition.getRightType();
			Type rightType = rightExpression.getResultType();
			
			CastOperationDefinition castOperationDefinition = 
					castOperationRepository.findImplicitBySourceAndDestinationTypes(
							rightType, 
							requiredRightType);
			
			if(castOperationDefinition != null) {
				return ExpressionResult.ok(binaryOperationDefinition.makeExpression(						
						leftExpression,
						castOperationDefinition.makeExpression(rightExpression)));
			}
		}
		
		return ExpressionResult.fail();
	}
}