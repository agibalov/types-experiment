package com.loki2302;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.evaluation.DOMExpressionEvaluator;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Type;

@RunWith(Parameterized.class)
public class BinaryExpressionTest {	
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	private final BinaryOperationFamily family;
	private final ExpressionResultChecker expressionResultChecker;
	
	public BinaryExpressionTest(
			DOMExpression leftExpression, 
			DOMExpression rightExpression, 
			BinaryOperationFamily family,
			ExpressionResultChecker expressionResultChecker) {
		
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.family = family;
		this.expressionResultChecker = expressionResultChecker;
	}
	
	@Parameters(name = "#{index}: {0} {2} {1} -> {3}")
	public static Collection<Object[]> generateData() {
		List<Object[]> parameters = new ArrayList<Object[]>();
		
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Add, ok(Type.Int) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Sub, ok(Type.Int) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Mul, ok(Type.Int) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Div, ok(Type.Int) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Int(), Int(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Add, ok(Type.Double) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Sub, ok(Type.Double) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Mul, ok(Type.Double) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Div, ok(Type.Double) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Dbl(), Dbl(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Add, ok(Type.Double) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Sub, ok(Type.Double) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Mul, ok(Type.Double) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Div, ok(Type.Double) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Int(), Dbl(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Add, ok(Type.Double) });		
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Sub, ok(Type.Double) });		
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Mul, ok(Type.Double) });		
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Div, ok(Type.Double) });		
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });			
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Dbl(), Int(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });	
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.And, ok(Type.Bool) });
		parameters.add(new Object[] { Bool(), Bool(), BinaryOperationFamily.Or, ok(Type.Bool) });		
				
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.Equal, fail() });
		parameters.add(new Object[] { Int(), Bool(), BinaryOperationFamily.NotEqual, fail() });
		
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Less, fail() });		
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.Equal, fail() });
		parameters.add(new Object[] { Dbl(), Bool(), BinaryOperationFamily.NotEqual, fail() });
		
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.NotEqual, fail() });
		parameters.add(new Object[] { Bool(), Int(), BinaryOperationFamily.Equal, fail() });
		
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Div, fail() });			
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.NotEqual, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.Equal, fail() });
		parameters.add(new Object[] { Bool(), Dbl(), BinaryOperationFamily.LessOrEqual, fail() });	
		
		return parameters;
	}
	
	@Test
	public void testOperation() {
		DOMBinaryExpression binaryExpression = new DOMBinaryExpression(
				family, 
				leftExpression, 
				rightExpression);
		ExpressionResult expressionResult = expression(binaryExpression);
		assertTrue(expressionResultChecker.check(expressionResult));
	}
	
	private static ExpressionResult expression(DOMExpression domExpression) {
		Injector injector = Guice.createInjector(new OperationsModule());		
		DOMExpressionEvaluator domExpressionEvaluator = injector.getInstance(DOMExpressionEvaluator.class);
		return domExpressionEvaluator.evaluateDOMExpression(domExpression);
	}
	
	private static DOMExpression Int() {
		return new DOMLiteralExpression(DOMLiteralType.Int, "1");
	}
	
	private static DOMExpression Dbl() {
		return new DOMLiteralExpression(DOMLiteralType.Double, "3.14");
	}
	
	private static DOMExpression Bool() {
		return new DOMLiteralExpression(DOMLiteralType.Bool, "true");
	}
	
	private static ExpressionResultChecker ok(Type resultType) {
		return new SpecificTypeExpressionResultChecker(resultType);
	}
	
	private static ExpressionResultChecker fail() {
		return new FailureExpressionResultChecker();
	}
	
	private static interface ExpressionResultChecker {
		boolean check(ExpressionResult expressionResult);
	}
	
	private static class SpecificTypeExpressionResultChecker implements ExpressionResultChecker {
		private final Type resultType;
		
		public SpecificTypeExpressionResultChecker(Type resultType) {
			this.resultType = resultType;
		}
		
		@Override
		public boolean check(ExpressionResult expressionResult) {
			return expressionResult.isOk() && 
					expressionResult.getExpression().getResultType() == resultType;
		}		
	}
	
	private static class FailureExpressionResultChecker implements ExpressionResultChecker {
		@Override
		public boolean check(ExpressionResult expressionResult) {
			return !expressionResult.isOk();
		}		
	}
}
