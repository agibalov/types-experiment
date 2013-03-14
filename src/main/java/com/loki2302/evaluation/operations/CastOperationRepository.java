package com.loki2302.evaluation.operations;

import java.util.List;

import com.loki2302.expression.Type;

public class CastOperationRepository extends OperationRepository<CastOperationDefinition> {	
	public CastOperationRepository(List<CastOperationDefinition> definitions) {
		super(definitions);
	}
	
	public static Predicate<CastOperationDefinition> isImplicit() {
		return new IsImplicitPredicate();
	}
	
	public static Predicate<CastOperationDefinition> sourceTypeIs(Type sourceType) {
		return new SourceTypeIsPredicate(sourceType);
	}
	
	public static Predicate<CastOperationDefinition> destinationTypeIs(Type destinationType) {
		return new DestinationTypeIsPredicate(destinationType);
	}
	
	private static class IsImplicitPredicate implements Predicate<CastOperationDefinition> {
		@Override
		public boolean match(CastOperationDefinition operationDefinition) {
			return operationDefinition.isImplicit();
		}		
	}
	
	private static class SourceTypeIsPredicate implements Predicate<CastOperationDefinition> {
		private final Type sourceType;
		
		public SourceTypeIsPredicate(Type sourceType) {
			this.sourceType = sourceType;
		}

		@Override
		public boolean match(CastOperationDefinition operationDefinition) {
			return operationDefinition.getSourceType() == sourceType;
		}	
	}
	
	private static class DestinationTypeIsPredicate implements Predicate<CastOperationDefinition> {
		private final Type destinationType;
		
		public DestinationTypeIsPredicate(Type destinationType) {
			this.destinationType = destinationType;
		}

		@Override
		public boolean match(CastOperationDefinition operationDefinition) {
			return operationDefinition.getDestinationType() == destinationType;
		}	 
	}		
}