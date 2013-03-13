package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.loki2302.evaluation.AlternativeBinaryOperationMatcher;
import com.loki2302.evaluation.BinaryOperationDefinition;
import com.loki2302.evaluation.BinaryOperationRepository;
import com.loki2302.evaluation.CastIntToDoubleOperationDefinition;
import com.loki2302.evaluation.CastOperationRepository;
import com.loki2302.evaluation.DoubleAddOperationDefinition;
import com.loki2302.evaluation.ExactMatcher;
import com.loki2302.evaluation.ImplicitLeftBinaryOperationMatcher;
import com.loki2302.evaluation.ImplicitRightBinaryOperationMatcher;
import com.loki2302.evaluation.IntAddOperationDefinition;
import com.loki2302.evaluation.BinaryOperationMatcher;

public class OperationsModule extends AbstractModule {
	@Override
	protected void configure() {
	}
	
	@Provides
	@Named("addOperationRepository")
	BinaryOperationRepository provideAddOperationRepository(
			IntAddOperationDefinition intAddOperationDefinition,
			DoubleAddOperationDefinition doubleAddOperationDefinition) {
		List<BinaryOperationDefinition> addOperationDefinitions = new ArrayList<BinaryOperationDefinition>();
		addOperationDefinitions.add(intAddOperationDefinition);
		addOperationDefinitions.add(doubleAddOperationDefinition);		
		return new BinaryOperationRepository(addOperationDefinitions);
	}
	
	@Provides
	@Named("addOperationMatcher")
	BinaryOperationMatcher provideAddOperationMatcher(
			ExactMatcher exactMatcher, 
			ImplicitRightBinaryOperationMatcher implicitRightMatcher,
			ImplicitLeftBinaryOperationMatcher implicitLeftMatcher) {
		return new AlternativeBinaryOperationMatcher(
				exactMatcher, 
				implicitRightMatcher, 
				implicitLeftMatcher);
	}
	
	@Provides
	CastOperationRepository provideCastOperationRepository(
			CastIntToDoubleOperationDefinition castIntToDoubleOperationDefinition) {
		return new CastOperationRepository(castIntToDoubleOperationDefinition);
	}
}