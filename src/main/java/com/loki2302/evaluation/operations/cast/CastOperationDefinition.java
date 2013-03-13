package com.loki2302.evaluation.operations.cast;

import com.loki2302.expression.CastExpression;
import com.loki2302.expression.CastOperationType;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class CastOperationDefinition {
	private final Type sourceType; 
	private final Type destinationType; 
	private final boolean isImplicit;
	private final CastOperationType castType;
	
	public CastOperationDefinition(
			Type sourceType, 
			Type destinationType, 
			boolean isImplicit, 
			CastOperationType castType) {
		
		this.sourceType = sourceType;
		this.destinationType = destinationType;
		this.isImplicit = isImplicit;
		this.castType = castType;
	}
	
	public Type getSourceType() {
		return sourceType;
	}

	public Type getDestinationType() {
		return destinationType;
	}

	public boolean isImplicit() {
		return isImplicit;
	}

	public CastOperationType getCastType() {
		return castType;
	}
	
	public Expression makeExpression(Expression expression) {
		return new CastExpression(castType, expression);
	}
}