package com.loki2302.evaluation.operations;

import java.util.List;

import com.loki2302.expression.BinaryOperationSemantics;
import com.loki2302.expression.Type;

public class BinaryOperationRepository {
	private final List<BinaryOperationDefinition> definitions;
	
	public BinaryOperationRepository(List<BinaryOperationDefinition> definitions) {
		this.definitions = definitions;
	}
	
	public BinaryOperationDefinition firstWhere(BinaryOperationDefinitionPredicate... predicates) {
		for(BinaryOperationDefinition operationDefinition : definitions) {
			boolean found = true;
			for(BinaryOperationDefinitionPredicate predicate : predicates) {
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

	public static BinaryOperationDefinitionPredicate semanticsIs(BinaryOperationSemantics semantics) {
		return new SemanticsIsPredicate(semantics);
	}
	
	public static BinaryOperationDefinitionPredicate leftTypeIs(Type leftType) {
		return new LeftTypeIsPredicate(leftType);
	}
	
	public static BinaryOperationDefinitionPredicate rightTypeIs(Type rightType) {
		return new RightTypeIsPredicate(rightType);
	}
	
	public static interface BinaryOperationDefinitionPredicate {
		boolean match(BinaryOperationDefinition item);
	}
	
	private static class SemanticsIsPredicate implements BinaryOperationDefinitionPredicate {
		private final BinaryOperationSemantics semantics;
		
		public SemanticsIsPredicate(BinaryOperationSemantics semantics) {
			this.semantics = semantics;
		}
		
		@Override
		public boolean match(BinaryOperationDefinition item) {
			return item.getSemantics() == semantics;
		}		
	}
	
	private static class LeftTypeIsPredicate implements BinaryOperationDefinitionPredicate {
		private final Type type;
		
		public LeftTypeIsPredicate(Type type) {
			this.type = type;
		}
		
		@Override
		public boolean match(BinaryOperationDefinition item) {
			return item.getLeftType() == type;
		}		
	}
	
	private static class RightTypeIsPredicate implements BinaryOperationDefinitionPredicate {
		private final Type type;
		
		public RightTypeIsPredicate(Type type) {
			this.type = type;
		}
		
		@Override
		public boolean match(BinaryOperationDefinition item) {
			return item.getRightType() == type;
		}		
	}
}