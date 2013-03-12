package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.loki2302.dom.DOMAddExpression;
import com.loki2302.dom.DOMDoubleLiteralExpression;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMExpressionVisitor;
import com.loki2302.dom.DOMIntLiteralExpression;

public class DOMExpressionEvaluator {	
	@Inject DOMIntLiteralExpressionEvaluator domIntLiteralExpressionEvaluator;
	@Inject DOMDoubleLiteralExpressionEvaluator domDoubleLiteralExpressionEvaluator;
	@Inject DOMAddExpressionEvaluator domAddExpressionEvaluator;
	
	public ExpressionResult evaluateDOMExpression(DOMExpression domExpression) {
		return domExpression.accept(new DOMExpressionVisitor<ExpressionResult>() {
			public ExpressionResult visitDOMIntLiteralExpression(DOMIntLiteralExpression expression) {				
				return domIntLiteralExpressionEvaluator.evaluateDOMIntLiteralExpression(expression);
			}

			public ExpressionResult visitDOMDoubleLiteralExpression(DOMDoubleLiteralExpression expression) {
				return domDoubleLiteralExpressionEvaluator.evaluateDOMDoubleLiteralExpression(expression);
			}

			public ExpressionResult visitDOMAddExpression(DOMAddExpression expression) {
				return domAddExpressionEvaluator.evaluateDOMAddExpression(expression, DOMExpressionEvaluator.this);
			}			
		});
	}
}