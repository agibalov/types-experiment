package com.loki2302;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMBinaryExpressionType;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.evaluation.DOMExpressionEvaluator;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.CastIntToDoubleExpression;
import com.loki2302.expression.DoubleAddExpression;
import com.loki2302.expression.DoubleLiteralExpression;
import com.loki2302.expression.Expression;
import com.loki2302.expression.IntAddExpression;
import com.loki2302.expression.IntLiteralExpression;

import static org.junit.Assert.*;

public class AppTest {
	@Test
	public void intLiteralTest() {		
		assertEquals(IntLiteralExpression.class, expression(i("123")).getClass());
	}	
	
	@Test
	public void doubleLiteralTest() {		
		assertEquals(DoubleLiteralExpression.class, expression(d("3.14")).getClass());
	}
	
	@Test
	public void add2Ints() {
		Expression e = expression(add(i("1"), i("2")));
		assertEquals(IntAddExpression.class, e.getClass());
		IntAddExpression intAddExpression = (IntAddExpression)e;
		assertEquals(intAddExpression.getLeftExpression().getClass(), IntLiteralExpression.class);
		assertEquals(intAddExpression.getRightExpression().getClass(), IntLiteralExpression.class);
	}
	
	@Test
	public void add2Doubles() {
		Expression e = expression(add(d("3.14"), d("2.78")));
		assertEquals(DoubleAddExpression.class, e.getClass());
		DoubleAddExpression doubleAddExpression = (DoubleAddExpression)e;
		assertEquals(doubleAddExpression.getLeftExpression().getClass(), DoubleLiteralExpression.class);
		assertEquals(doubleAddExpression.getRightExpression().getClass(), DoubleLiteralExpression.class);
	}
	
	@Test
	public void addDoubleAndInt() {
		Expression e = expression(add(d("3.14"), i("1")));
		assertEquals(DoubleAddExpression.class, e.getClass());
		DoubleAddExpression doubleAddExpression = (DoubleAddExpression)e;
		assertEquals(doubleAddExpression.getLeftExpression().getClass(), DoubleLiteralExpression.class);
		assertEquals(doubleAddExpression.getRightExpression().getClass(), CastIntToDoubleExpression.class);
		CastIntToDoubleExpression castIntToDoubleExpression = 
				(CastIntToDoubleExpression)doubleAddExpression.getRightExpression();
		assertEquals(castIntToDoubleExpression.getInnerExpression().getClass(), IntLiteralExpression.class);
	}
	
	@Test
	public void addIntAndDouble() {
		Expression e = expression(add(i("1"), d("3.14")));
		assertEquals(DoubleAddExpression.class, e.getClass());
		DoubleAddExpression doubleAddExpression = (DoubleAddExpression)e;
		assertEquals(doubleAddExpression.getLeftExpression().getClass(), CastIntToDoubleExpression.class);
		CastIntToDoubleExpression castIntToDoubleExpression = 
				(CastIntToDoubleExpression)doubleAddExpression.getLeftExpression();
		assertEquals(castIntToDoubleExpression.getInnerExpression().getClass(), IntLiteralExpression.class);
		assertEquals(doubleAddExpression.getRightExpression().getClass(), DoubleLiteralExpression.class);		
	}
		
	private static Expression expression(DOMExpression domExpression) {
		Injector injector = Guice.createInjector(new OperationsModule());		
		DOMExpressionEvaluator domExpressionEvaluator = injector.getInstance(DOMExpressionEvaluator.class);
		ExpressionResult result = domExpressionEvaluator.evaluateDOMExpression(domExpression);
		return result.getExpression();
	}
	
	private static DOMExpression add(DOMExpression left, DOMExpression right) {
		return new DOMBinaryExpression(DOMBinaryExpressionType.Add, left, right);
	}
	
	private static DOMExpression i(String stringValue) {
		return new DOMLiteralExpression(DOMLiteralType.Int, stringValue);
	}
	
	private static DOMExpression d(String stringValue) {
		return new DOMLiteralExpression(DOMLiteralType.Double, stringValue);
	}
}
