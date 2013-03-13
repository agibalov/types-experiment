package com.loki2302.evaluation.operations;

import java.util.List;

import com.loki2302.expression.BinaryOperationSemantics;
import com.loki2302.expression.Type;

public class BinaryOperationRepository {
	private final List<BinaryOperationDefinition> definitions;
	
	public BinaryOperationRepository(List<BinaryOperationDefinition> definitions) {
		this.definitions = definitions;
	}

	public BinaryOperationDefinition findByLeftAndRightTypes(
			BinaryOperationSemantics semantics, 
			Type leftType, 
			Type rightType) {
		
		for(BinaryOperationDefinition operationDefinition : definitions) {
			if(operationDefinition.getSemantics() != semantics) {
				continue;
			}
			
			if(operationDefinition.getLeftType() != leftType) {
				continue;
			}
			
			if(operationDefinition.getRightType() != rightType) {
				continue;
			}
			
			return operationDefinition;
		}	
		
		return null;
	}
	
	public BinaryOperationDefinition findByLeftType(
			BinaryOperationSemantics semantics, 
			Type leftType) {
		
		for(BinaryOperationDefinition operationDefinition : definitions) {
			if(operationDefinition.getSemantics() != semantics) {
				continue;
			}
			
			if(operationDefinition.getLeftType() != leftType) {
				continue;
			}				
			
			return operationDefinition;
		}	
		
		return null;
	}
	
	public BinaryOperationDefinition findByRightType(
			BinaryOperationSemantics semantics,
			Type rightType) {
		
		for(BinaryOperationDefinition operationDefinition : definitions) {
			if(operationDefinition.getSemantics() != semantics) {
				continue;
			}
			
			if(operationDefinition.getRightType() != rightType) {
				continue;
			}
			
			return operationDefinition;
		}	
		
		return null;
	}
}