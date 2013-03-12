package com.loki2302.evaluation;

import com.loki2302.expression.CastIntToDoubleExpression;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class CastIntToDoubleOperationDefinition implements CastOperationDefinition {
	public Type getSourceType() {
		return Type.Int;
	}

	public Type getDestinationType() {
		return Type.Double;
	}

	public Expression makeExpression(Expression sourceExpression) {
		return new CastIntToDoubleExpression(sourceExpression);
	}

	public boolean isImplicit() {
		return true;
	}		
}