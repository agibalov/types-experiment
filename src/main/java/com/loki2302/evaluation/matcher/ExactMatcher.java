package com.loki2302.evaluation.matcher;

import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ExactMatcher implements BinaryOperationMatcher {	
	public ExpressionResult match(
			BinaryOperationRepository binaryOperationRepository,
			Expression leftExpression, 
			Expression rightExpression) {
		Type leftType = leftExpression.getResultType();
		Type rightType = rightExpression.getResultType();					
		
		BinaryOperationDefinition addOperationDefinition = 
				binaryOperationRepository.findByLeftAndRightTypes(leftType, rightType);
		if(addOperationDefinition != null) {
			return ExpressionResult.ok(addOperationDefinition.makeExpression(
					leftExpression,
					rightExpression));
		}
		
		return ExpressionResult.fail();
	}	
}