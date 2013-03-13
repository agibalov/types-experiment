package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;


public class AddOperationRepository extends BinaryOperationRepository {	
	private final List<BinaryOperationDefinition> addOperationDefinitions = new ArrayList<BinaryOperationDefinition>();
	
	{		
		addOperationDefinitions.add(new IntAddOperationDefinition());
		addOperationDefinitions.add(new DoubleAddOperationDefinition());
	}

	@Override
	protected List<BinaryOperationDefinition> getDefinitions() {
		return addOperationDefinitions;
	}
}