package com.loki2302.evaluation;

import com.loki2302.expression.Type;

public interface BinaryOperationRepository {
	BinaryOperationDefinition findByLeftAndRightTypes(Type leftType, Type rightType);	
	BinaryOperationDefinition findByLeftType(Type leftType);
	BinaryOperationDefinition findByRightType(Type rightType);
}