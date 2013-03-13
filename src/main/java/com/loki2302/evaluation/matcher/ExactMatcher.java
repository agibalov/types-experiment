package com.loki2302.evaluation.matcher;

import com.google.inject.Inject;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import static com.loki2302.evaluation.operations.BinaryOperationRepository.*;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ExactMatcher implements BinaryOperationMatcher {	
	@Inject BinaryOperationRepository binaryOperationRepository;
	
	public ExpressionResult match(
			BinaryOperationFamily family,
			Expression leftExpression, 
			Expression rightExpression) {
		
		Type leftType = leftExpression.getResultType();
		Type rightType = rightExpression.getResultType();					
				
		BinaryOperationDefinition binaryOperationDefinition =
				binaryOperationRepository.firstWhere(
						familyIs(family), 
						leftTypeIs(leftType), 
						rightTypeIs(rightType));
		
		if(binaryOperationDefinition != null) {
			return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
					leftExpression,
					rightExpression));
		}
		
		return ExpressionResult.fail();
	}	
}