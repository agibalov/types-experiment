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
import com.loki2302.expression.BinaryOperationSemantics;
import com.loki2302.expression.BinaryOperationType;
import com.loki2302.expression.CastOperationType;
import com.loki2302.expression.Type;

public class OperationsModule extends AbstractModule {
	@Override
	protected void configure() {
	}
	
	@Provides
	@Named("binaryOperationRepository")
	BinaryOperationRepository provideAddOperationRepository() {
		List<BinaryOperationDefinition> addOperationDefinitions = new ArrayList<BinaryOperationDefinition>();
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Add, BinaryOperationType.IntAdd, Type.Int, Type.Int, Type.Int));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Add, BinaryOperationType.DoubleAdd, Type.Double, Type.Double, Type.Double));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Sub, BinaryOperationType.IntSub, Type.Int, Type.Int, Type.Int));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Sub, BinaryOperationType.DoubleSub, Type.Double, Type.Double, Type.Double));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Mul, BinaryOperationType.IntMul, Type.Int, Type.Int, Type.Int));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Mul, BinaryOperationType.DoubleMul, Type.Double, Type.Double, Type.Double));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Div, BinaryOperationType.IntDiv, Type.Int, Type.Int, Type.Int));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Div, BinaryOperationType.DoubleDiv, Type.Double, Type.Double, Type.Double));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Less, BinaryOperationType.IntLess, Type.Int, Type.Int, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Less, BinaryOperationType.DoubleLess, Type.Double, Type.Double, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.LessOrEqual, BinaryOperationType.IntLessOrEqual, Type.Int, Type.Int, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.LessOrEqual, BinaryOperationType.DoubleLessOrEqual, Type.Double, Type.Double, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Greater, BinaryOperationType.IntGreater, Type.Int, Type.Int, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Greater, BinaryOperationType.DoubleGreater, Type.Double, Type.Double, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.GreaterOrEqual, BinaryOperationType.IntGreaterOrEqual, Type.Int, Type.Int, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.GreaterOrEqual, BinaryOperationType.DoubleGreaterOrEqual, Type.Double, Type.Double, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Equal, BinaryOperationType.IntEqual, Type.Int, Type.Int, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Equal, BinaryOperationType.DoubleEqual, Type.Double, Type.Double, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Equal, BinaryOperationType.BoolEqual, Type.Bool, Type.Bool, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.NotEqual, BinaryOperationType.IntNotEqual, Type.Int, Type.Int, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.NotEqual, BinaryOperationType.DoubleNotEqual, Type.Double, Type.Double, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.NotEqual, BinaryOperationType.BoolNotEqual, Type.Bool, Type.Bool, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.And, BinaryOperationType.BoolAnd, Type.Bool, Type.Bool, Type.Bool));
		addOperationDefinitions.add(new BinaryOperationDefinition(BinaryOperationSemantics.Or, BinaryOperationType.BoolOr, Type.Bool, Type.Bool, Type.Bool));
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
			@Named("binaryOperationRepository") BinaryOperationRepository binaryOperationRepository,
			@Named("addOperationMatcher") BinaryOperationMatcher operationMatcher) {
		return new DOMMatchingBinaryExpressionEvaluator(
				BinaryOperationSemantics.Add,
				binaryOperationRepository,
				operationMatcher);
	}
	
	@Provides
	CastOperationRepository provideCastOperationRepository() {
		return new CastOperationRepository(new CastOperationDefinition(Type.Int, Type.Double, true, CastOperationType.IntToDouble));
	}
}