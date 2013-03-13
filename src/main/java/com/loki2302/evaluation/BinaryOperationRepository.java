package com.loki2302.evaluation;

import java.util.List;

import com.loki2302.expression.Type;

public abstract class BinaryOperationRepository {
	public BinaryOperationDefinition findByLeftAndRightTypes(Type leftType, Type rightType) {
		for(BinaryOperationDefinition addOperationDefinition : getDefinitions()) {
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
		for(BinaryOperationDefinition addOperationDefinition : getDefinitions()) {
			if(addOperationDefinition.getLeftType() != leftType) {
				continue;
			}				
			
			return addOperationDefinition;
		}	
		
		return null;
	}
	
	public BinaryOperationDefinition findByRightType(Type rightType) {
		for(BinaryOperationDefinition addOperationDefinition : getDefinitions()) {				
			if(addOperationDefinition.getRightType() != rightType) {
				continue;
			}
			
			return addOperationDefinition;
		}	
		
		return null;
	}
	
	protected abstract List<BinaryOperationDefinition> getDefinitions();
}