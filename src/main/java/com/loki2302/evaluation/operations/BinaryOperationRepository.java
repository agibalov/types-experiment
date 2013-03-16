package com.loki2302.evaluation.operations;

import java.util.List;

import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Type;

public class BinaryOperationRepository extends OperationRepository<BinaryOperationDefinition> {
	public BinaryOperationRepository(List<BinaryOperationDefinition> definitions) {
		super(definitions);
	}

	public static Predicate<BinaryOperationDefinition> familyIs(BinaryOperationFamily family) {
		return new FamilyIsPredicate(family);
	}
	
	public static Predicate<BinaryOperationDefinition> leftTypeIs(Type leftType) {
		return new LeftTypeIsPredicate(leftType);
	}
	
	public static Predicate<BinaryOperationDefinition> rightTypeIs(Type rightType) {
		return new RightTypeIsPredicate(rightType);
	}
		
	private static class FamilyIsPredicate implements Predicate<BinaryOperationDefinition> {
		private final BinaryOperationFamily family;
		
		public FamilyIsPredicate(BinaryOperationFamily family) {
			this.family = family;
		}
		
		@Override
		public boolean match(BinaryOperationDefinition item) {
			return item.getBinaryOperationFamily() == family;
		}		
	}
	
	private static class LeftTypeIsPredicate implements Predicate<BinaryOperationDefinition> {
		private final Type type;
		
		public LeftTypeIsPredicate(Type type) {
			this.type = type;
		}
		
		@Override
		public boolean match(BinaryOperationDefinition item) {
			return item.getLeftType() == type;
		}		
	}
	
	private static class RightTypeIsPredicate implements Predicate<BinaryOperationDefinition> {
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