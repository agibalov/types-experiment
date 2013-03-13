package com.loki2302.evaluation.operations;

import java.util.List;

import com.loki2302.expression.Type;

public class BinaryOperationRepository {
	private final List<BinaryOperationDefinition> definitions;
	
	public BinaryOperationRepository(List<BinaryOperationDefinition> definitions) {
		this.definitions = definitions;
	}

	public BinaryOperationDefinition findByLeftAndRightTypes(Type leftType, Type rightType) {
		for(BinaryOperationDefinition addOperationDefinition : definitions) {
			if(addOperationDefinition.getLeftType() != leftType) {
				continue;
			}
			
			if(addOperationDefinition.getRightType() != rightType) {
				continue;
			}
			
			return addOperationDefinition;
		}	
		
		return null;
	}
	
	public BinaryOperationDefinition findByLeftType(Type leftType) {
		for(BinaryOperationDefinition addOperationDefinition : definitions) {
			if(addOperationDefinition.getLeftType() != leftType) {
				continue;
			}				
			
			return addOperationDefinition;
		}	
		
		return null;
	}
	
	public BinaryOperationDefinition findByRightType(Type rightType) {
		for(BinaryOperationDefinition addOperationDefinition : definitions) {				
			if(addOperationDefinition.getRightType() != rightType) {
				continue;
			}
			
			return addOperationDefinition;
		}	
		
		return null;
	}
}