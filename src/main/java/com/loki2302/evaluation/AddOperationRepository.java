package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.expression.Type;

public class AddOperationRepository {
	private final List<AddOperationDefinition> addOperationDefinitions = new ArrayList<AddOperationDefinition>();
	
	{		
		addOperationDefinitions.add(new IntAddOperationDefinition());
		addOperationDefinitions.add(new DoubleAddOperationDefinition());
	}
	
	public AddOperationDefinition findByLeftAndRightTypes(Type leftType, Type rightType) {
		for(AddOperationDefinition addOperationDefinition : addOperationDefinitions) {
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
	
	public AddOperationDefinition findByLeftType(Type leftType) {
		for(AddOperationDefinition addOperationDefinition : addOperationDefinitions) {
			if(addOperationDefinition.getLeftType() != leftType) {
				continue;
			}				
			
			return addOperationDefinition;
		}	
		
		return null;
	}
	
	public AddOperationDefinition findByRightType(Type rightType) {
		for(AddOperationDefinition addOperationDefinition : addOperationDefinitions) {				
			if(addOperationDefinition.getRightType() != rightType) {
				continue;
			}
			
			return addOperationDefinition;
		}	
		
		return null;
	}
}