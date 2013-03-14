package com.loki2302;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.evaluation.DOMExpressionEvaluator;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.BinaryOperationFamily;

public class App {
	public static void main(String[] args) {		
		DOMExpression domExpression = new DOMBinaryExpression(
				BinaryOperationFamily.Add,
				new DOMLiteralExpression(DOMLiteralType.Int, "1"),
				new DOMLiteralExpression(DOMLiteralType.Double, "3.14"));
		
		Injector injector = Guice.createInjector(new OperationsModule());		
		DOMExpressionEvaluator domExpressionEvaluator = injector.getInstance(DOMExpressionEvaluator.class);
		ExpressionResult result = domExpressionEvaluator.evaluateDOMExpression(domExpression);
		System.out.println(result);
	}
}
