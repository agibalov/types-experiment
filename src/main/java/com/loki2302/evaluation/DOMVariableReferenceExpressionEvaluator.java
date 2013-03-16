package com.loki2302.evaluation;

import com.loki2302.dom.DOMVariableReferenceExpression;
import com.loki2302.expression.Type;
import com.loki2302.expression.VariableReferenceExpression;

public class DOMVariableReferenceExpressionEvaluator {
	// DUMMY IMPLEMENTATION
	public ExpressionResult evaluateDOMVariableReferenceExpression(DOMVariableReferenceExpression expression) {
		String variableName = expression.getVariableName();
		if(variableName == "int") {
			return ExpressionResult.ok(new VariableReferenceExpression(Type.Int));
		} else if(variableName == "double") {
			return ExpressionResult.ok(new VariableReferenceExpression(Type.Double));
		} else if(variableName == "bool") {
			return ExpressionResult.ok(new VariableReferenceExpression(Type.Bool));
		}
		
		return ExpressionResult.fail();
	}
}