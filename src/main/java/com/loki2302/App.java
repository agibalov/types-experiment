package com.loki2302;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMBinaryExpressionType;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.evaluation.DOMExpressionEvaluator;
import com.loki2302.evaluation.ExpressionResult;

public class App {	
	
	// add: IntAdd, DoubleAdd
	// sub: IntSub, DoubleSub
	// mul: IntMul, DoubleMul
	// div: IntDiv, DoubleDiv
	// greater: IntGreater, DoubleGreater
	// greater-equal: IntGreaterOrEqual, DoubleGreaterOrEqual
	// less: IntLess, DoubleLess
	// less-equal: IntLessOrEqual, DoubleLessOrEqual
	// equal: IntEqual, DoubleEqual, BoolEqual
	// not-equal: IntNotEqual, DoubleNotEqual, BoolNotEqual
	
	// implicit-cast: int->double
	
	public static void main(String[] args) {		
		DOMExpression domExpression = new DOMBinaryExpression(
				DOMBinaryExpressionType.Add,
				new DOMLiteralExpression(DOMLiteralType.Int, "1"),
				new DOMLiteralExpression(DOMLiteralType.Double, "3.14"));
		
		Injector injector = Guice.createInjector(new OperationsModule());		
		DOMExpressionEvaluator domExpressionEvaluator = injector.getInstance(DOMExpressionEvaluator.class);
		ExpressionResult result = domExpressionEvaluator.evaluateDOMExpression(domExpression);
		System.out.println(result);
	}
}
