package com.loki2302;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.evaluation.operations.CastOperationDefinition;
import com.loki2302.evaluation.operations.CastOperationRepository;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.BinaryOperationType;
import com.loki2302.expression.CastOperationType;
import com.loki2302.expression.Type;

public class OperationsModule extends AbstractModule {
	@Override
	protected void configure() {
	}
	
	@Provides	
	BinaryOperationRepository provideAddOperationRepository() {
		List<BinaryOperationDefinition> definitions = new ArrayList<BinaryOperationDefinition>();
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Add, BinaryOperationType.IntAdd, Type.Int, Type.Int, Type.Int));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Add, BinaryOperationType.DoubleAdd, Type.Double, Type.Double, Type.Double));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Sub, BinaryOperationType.IntSub, Type.Int, Type.Int, Type.Int));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Sub, BinaryOperationType.DoubleSub, Type.Double, Type.Double, Type.Double));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Mul, BinaryOperationType.IntMul, Type.Int, Type.Int, Type.Int));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Mul, BinaryOperationType.DoubleMul, Type.Double, Type.Double, Type.Double));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Div, BinaryOperationType.IntDiv, Type.Int, Type.Int, Type.Int));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Div, BinaryOperationType.DoubleDiv, Type.Double, Type.Double, Type.Double));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Less, BinaryOperationType.IntLess, Type.Int, Type.Int, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Less, BinaryOperationType.DoubleLess, Type.Double, Type.Double, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.LessOrEqual, BinaryOperationType.IntLessOrEqual, Type.Int, Type.Int, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.LessOrEqual, BinaryOperationType.DoubleLessOrEqual, Type.Double, Type.Double, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Greater, BinaryOperationType.IntGreater, Type.Int, Type.Int, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Greater, BinaryOperationType.DoubleGreater, Type.Double, Type.Double, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.GreaterOrEqual, BinaryOperationType.IntGreaterOrEqual, Type.Int, Type.Int, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.GreaterOrEqual, BinaryOperationType.DoubleGreaterOrEqual, Type.Double, Type.Double, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Equal, BinaryOperationType.IntEqual, Type.Int, Type.Int, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Equal, BinaryOperationType.DoubleEqual, Type.Double, Type.Double, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Equal, BinaryOperationType.BoolEqual, Type.Bool, Type.Bool, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.NotEqual, BinaryOperationType.IntNotEqual, Type.Int, Type.Int, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.NotEqual, BinaryOperationType.DoubleNotEqual, Type.Double, Type.Double, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.NotEqual, BinaryOperationType.BoolNotEqual, Type.Bool, Type.Bool, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.And, BinaryOperationType.BoolAnd, Type.Bool, Type.Bool, Type.Bool));
		definitions.add(new BinaryOperationDefinition(BinaryOperationFamily.Or, BinaryOperationType.BoolOr, Type.Bool, Type.Bool, Type.Bool));
		return new BinaryOperationRepository(definitions);
	}	
	
	@Provides
	CastOperationRepository provideCastOperationRepository() {
		List<CastOperationDefinition> definitions = new ArrayList<CastOperationDefinition>();
		definitions.add(new CastOperationDefinition(Type.Int, Type.Double, true, CastOperationType.IntToDouble));		
		return new CastOperationRepository(definitions);
	}
}