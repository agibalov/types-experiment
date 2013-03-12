package com.loki2302.evaluation;

import java.util.ArrayList;
import java.util.List;

import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class CastOperationLogic {
	private final List<CastOperationDefinition> castOperationDefinitions = new ArrayList<CastOperationDefinition>();
	
	{
		castOperationDefinitions.add(new CastIntToDoubleOperationDefinition());
	}
	
	public ExpressionResult implicitlyCast(Expression expression, Type toType) {
		Type expressionType = expression.getType();
		for(CastOperationDefinition op : castOperationDefinitions) {
			if(!op.isImplicit()) {
				continue;
			}
			
			if(op.getSourceType() != expressionType) {
				continue;
			}
			
			if(op.getDestinationType() != toType) {
				continue;
			}
			
			return ExpressionResult.ok(op.makeExpression(expression));
		}
		
		return ExpressionResult.fail();
	}
}