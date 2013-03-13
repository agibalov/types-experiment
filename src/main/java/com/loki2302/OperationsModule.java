package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.loki2302.evaluation.DOMMatchingBinaryExpressionEvaluator;
import com.loki2302.evaluation.matcher.AlternativeBinaryOperationMatcher;
import com.loki2302.evaluation.matcher.BinaryOperationMatcher;
import com.loki2302.evaluation.matcher.ExactMatcher;
import com.loki2302.evaluation.matcher.ImplicitLeftBinaryOperationMatcher;
import com.loki2302.evaluation.matcher.ImplicitRightBinaryOperationMatcher;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.evaluation.operations.cast.CastOperationDefinition;
import com.loki2302.evaluation.operations.cast.CastOperationRepository;
import com.loki2302.expression.BinaryOperationType;
import com.loki2302.expression.CastOperationType;
import com.loki2302.expression.Type;

public class OperationsModule extends AbstractModule {
	@Override
	protected void configure() {
	}
	
	@Provides
	@Named("addOperationRepository")
	BinaryOperationRepository provideAddOperationRepository() {
		List<BinaryOperationDefinition> addOperationDefinitions = new ArrayList<BinaryOperationDefinition>();
		addOperationDefinitions.add(new BinaryOperationDefinition(Type.Int, Type.Int, BinaryOperationType.IntAdd, Type.Int));
		addOperationDefinitions.add(new BinaryOperationDefinition(Type.Double, Type.Double, BinaryOperationType.DoubleAdd, Type.Double));		
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
	@Named("addExpressionEvaluator")
	DOMMatchingBinaryExpressionEvaluator provideAddExpressionEvaluator(
			@Named("addOperationRepository") BinaryOperationRepository binaryOperationRepository,
			@Named("addOperationMatcher") BinaryOperationMatcher operationMatcher) {
		return new DOMMatchingBinaryExpressionEvaluator(
				binaryOperationRepository,
				operationMatcher);
	}
	
	@Provides
	CastOperationRepository provideCastOperationRepository() {
		return new CastOperationRepository(new CastOperationDefinition(Type.Int, Type.Double, true, CastOperationType.IntToDouble));
	}
}