package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMBinaryExpressionType;

public class DOMBinaryExpressionEvaluator {	
	@Inject @Named("addExpressionEvaluator") DOMMatchingBinaryExpressionEvaluator addExpressionEvaluator;
		
	public ExpressionResult evaluateDOMBinaryExpression(
			DOMBinaryExpression expression, 
			DOMExpressionEvaluator domExpressionEvaluator) {
		
		DOMBinaryExpressionType expressionType = expression.getExpressionType();
		if(expressionType == DOMBinaryExpressionType.Add) {
			return addExpressionEvaluator.evaluateBinaryExpression(
					expression.getLeftExpression(),
					expression.getRightExpression(),
					domExpressionEvaluator);
		} else if(expressionType == DOMBinaryExpressionType.Sub) {
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.Mul) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.Div) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.Less) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.LessOrEqual) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.Greater) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.GreaterOrEqual) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.Equal) {			
			throw new RuntimeException();
		} else if(expressionType == DOMBinaryExpressionType.NotEqual) {			
			throw new RuntimeException();
		}
		
		throw new RuntimeException();
	}
}