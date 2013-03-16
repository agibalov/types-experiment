package com.loki2302.evaluation.operations;

import java.util.List;

import com.loki2302.expression.Type;

public class AssignmentOperationRepository extends OperationRepository<AssignmentOperationDefinition> {
	public AssignmentOperationRepository(List<AssignmentOperationDefinition> operationDefinitions) {
		super(operationDefinitions);
	}
	
	public static Predicate<AssignmentOperationDefinition> leftTypeIs(Type leftType) {
		return new LeftTypeIsPredicate(leftType);
	}
	
	public static Predicate<AssignmentOperationDefinition> rightTypeIs(Type rightType) {
		return new RightTypeIsPredicate(rightType);
	}
	
	private static class LeftTypeIsPredicate implements Predicate<AssignmentOperationDefinition> {
		private final Type leftType;
		
		public LeftTypeIsPredicate(Type leftType) {
			this.leftType = leftType;
		}

		@Override
		public boolean match(AssignmentOperationDefinition operationDefinition) {
			return operationDefinition.getLeftType() == leftType;
		}		
	}
	
	private static class RightTypeIsPredicate implements Predicate<AssignmentOperationDefinition> {
		private final Type rightType;
		
		public RightTypeIsPredicate(Type rightType) {
			this.rightType = rightType;
		}

		@Override
		public boolean match(AssignmentOperationDefinition operationDefinition) {
			return operationDefinition.getRightType() == rightType;
		}		
	}
}