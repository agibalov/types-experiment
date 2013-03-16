package com.loki2302;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.loki2302.dom.DOMExpression;
import com.loki2302.dom.DOMLiteralExpression;
import com.loki2302.dom.DOMLiteralType;
import com.loki2302.dom.DOMVariableReferenceExpression;
import com.loki2302.evaluation.DOMExpressionEvaluator;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.Type;

public abstract class AbstractTypeSystemTest {	
	public static ExpressionResult compileExpression(DOMExpression domExpression) {
		Injector injector = Guice.createInjector(new OperationsModule());		
		DOMExpressionEvaluator domExpressionEvaluator = injector.getInstance(DOMExpressionEvaluator.class);
		return domExpressionEvaluator.evaluateDOMExpression(domExpression);
	}
	
	public static DOMExpression IntLiteral() {
		return new DOMLiteralExpression(DOMLiteralType.Int, "1");
	}
	
	public static DOMExpression DoubleLiteral() {
		return new DOMLiteralExpression(DOMLiteralType.Double, "3.14");
	}
	
	public static DOMExpression BoolLiteral() {
		return new DOMLiteralExpression(DOMLiteralType.Bool, "true");
	}
	
	public static DOMExpression IntVar() {
		return new DOMVariableReferenceExpression("int");
	}
	
	public static DOMExpression DoubleVar() {
		return new DOMVariableReferenceExpression("double");
	}
	
	public static DOMExpression BoolVar() {
		return new DOMVariableReferenceExpression("bool");
	}
	
	public static ExpressionResultChecker ok(Type resultType) {
		return new SpecificTypeExpressionResultChecker(resultType);
	}
	
	public static ExpressionResultChecker fail() {
		return new FailureExpressionResultChecker();
	}
	
	public static interface ExpressionResultChecker {
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