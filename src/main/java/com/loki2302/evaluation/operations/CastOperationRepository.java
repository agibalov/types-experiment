package com.loki2302.evaluation.operations;

import com.loki2302.expression.Type;

public class CastOperationRepository {
	private final CastOperationDefinition[] castOperationDefinitions;
	
	public CastOperationRepository(CastOperationDefinition... castOperationDefinitions) {
		this.castOperationDefinitions = castOperationDefinitions;
	}
	
	public CastOperationDefinition findImplicitBySourceAndDestinationTypes(Type sourceType, Type destinationType) {
		for(CastOperationDefinition op : castOperationDefinitions) {
			if(!op.isImplicit()) {
				continue;
			}
			
			if(op.getSourceType() != sourceType) {
				continue;
			}
			
			if(op.getDestinationType() != destinationType) {
				continue;
			}
			
			return op;
		}
		
		return null;
	}		
}