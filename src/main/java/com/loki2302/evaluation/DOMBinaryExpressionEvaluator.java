package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.expression.BinaryOperationFamily;

public class DOMBinaryExpressionEvaluator {	
	@Inject @Named("addExpressionEvaluator") DOMMatchingBinaryExpressionEvaluator addExpressionEvaluator;
		
	public ExpressionResult evaluateDOMBinaryExpression(
			DOMBinaryExpression expression, 
			DOMExpressionEvaluator domExpressionEvaluator) {
		
		BinaryOperationFamily expressionType = expression.getBinaryOperationFamily();
		if(expressionType == BinaryOperationFamily.Add) {
			return addExpressionEvaluator.evaluateBinaryExpression(
					expression.getLeftExpression(),
					expression.getRightExpression(),
					domExpressionEvaluator);
		} else if(expressionType == BinaryOperationFamily.Sub) {
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.Mul) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.Div) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.Less) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.LessOrEqual) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.Greater) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.GreaterOrEqual) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.Equal) {			
			throw new RuntimeException();
		} else if(expressionType == BinaryOperationFamily.NotEqual) {			
			throw new RuntimeException();
		}
		
		throw new RuntimeException();
	}
}