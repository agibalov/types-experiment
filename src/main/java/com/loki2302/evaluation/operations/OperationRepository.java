package com.loki2302.evaluation.operations;

import java.util.List;

public class OperationRepository<TOperationDefinition> {
	private final List<TOperationDefinition> operationDefinitions;
	
	protected OperationRepository(List<TOperationDefinition> operationDefinitions) {
		this.operationDefinitions = operationDefinitions;
	}
	
	public TOperationDefinition firstWhere(Predicate<TOperationDefinition>... predicates) {
		for(TOperationDefinition operationDefinition : operationDefinitions) {
			boolean found = true;
			for(OperationRepository.Predicate<TOperationDefinition> predicate : predicates) {
				if(!predicate.match(operationDefinition)) {
					found = false;
					break;
				}
			}
			
			if(found) {
				return operationDefinition;
			}
		}
		
		return null;
	}
	
	public static interface Predicate<TOperationDefinition> {
		boolean match(TOperationDefinition operationDefinition);
	}
}