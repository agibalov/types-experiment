package com.loki2302;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.dom.DOMExpression;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Type;

@RunWith(Parameterized.class)
public class BinaryExpressionTest extends AbstractTypeSystemTest {	
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
		
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Add, ok(Type.Int) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Sub, ok(Type.Int) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Mul, ok(Type.Int) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Div, ok(Type.Int) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { IntLiteral(), IntLiteral(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Add, ok(Type.Double) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Sub, ok(Type.Double) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Mul, ok(Type.Double) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Div, ok(Type.Double) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { DoubleLiteral(), DoubleLiteral(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Add, ok(Type.Double) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Sub, ok(Type.Double) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Mul, ok(Type.Double) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Div, ok(Type.Double) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { IntLiteral(), DoubleLiteral(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Add, ok(Type.Double) });		
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Sub, ok(Type.Double) });		
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Mul, ok(Type.Double) });		
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Div, ok(Type.Double) });		
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Less, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.LessOrEqual, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Greater, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.GreaterOrEqual, ok(Type.Bool) });			
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { DoubleLiteral(), IntLiteral(), BinaryOperationFamily.Or, fail() });
		
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Equal, ok(Type.Bool) });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.NotEqual, ok(Type.Bool) });	
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.And, ok(Type.Bool) });
		parameters.add(new Object[] { BoolLiteral(), BoolLiteral(), BinaryOperationFamily.Or, ok(Type.Bool) });		
				
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.Equal, fail() });
		parameters.add(new Object[] { IntLiteral(), BoolLiteral(), BinaryOperationFamily.NotEqual, fail() });
		
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Less, fail() });		
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.Equal, fail() });
		parameters.add(new Object[] { DoubleLiteral(), BoolLiteral(), BinaryOperationFamily.NotEqual, fail() });
		
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Div, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.LessOrEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.NotEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), IntLiteral(), BinaryOperationFamily.Equal, fail() });
		
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.And, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Or, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Add, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Sub, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Mul, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Div, fail() });			
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Greater, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.GreaterOrEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Less, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.NotEqual, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.Equal, fail() });
		parameters.add(new Object[] { BoolLiteral(), DoubleLiteral(), BinaryOperationFamily.LessOrEqual, fail() });	
		
		parameters.add(new Object[] { IntVar(), IntLiteral(), BinaryOperationFamily.Assignment, ok(Type.Int) });
        parameters.add(new Object[] { IntVar(), DoubleLiteral(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { IntVar(), BoolLiteral(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { IntVar(), IntVar(), BinaryOperationFamily.Assignment, ok(Type.Int) });
        parameters.add(new Object[] { IntVar(), DoubleVar(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { IntVar(), BoolVar(), BinaryOperationFamily.Assignment, fail() });
        
        parameters.add(new Object[] { DoubleVar(), DoubleLiteral(), BinaryOperationFamily.Assignment, ok(Type.Double) });
        parameters.add(new Object[] { DoubleVar(), IntLiteral(), BinaryOperationFamily.Assignment, ok(Type.Double) });
        parameters.add(new Object[] { DoubleVar(), BoolLiteral(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { DoubleVar(), DoubleVar(), BinaryOperationFamily.Assignment, ok(Type.Double) });
        parameters.add(new Object[] { DoubleVar(), IntVar(), BinaryOperationFamily.Assignment, ok(Type.Double) });        
        parameters.add(new Object[] { DoubleVar(), BoolVar(), BinaryOperationFamily.Assignment, fail() });
        
        parameters.add(new Object[] { BoolVar(), BoolLiteral(), BinaryOperationFamily.Assignment, ok(Type.Bool) });
        parameters.add(new Object[] { BoolVar(), IntLiteral(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { BoolVar(), DoubleLiteral(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { BoolVar(), BoolVar(), BinaryOperationFamily.Assignment, ok(Type.Bool) });
        parameters.add(new Object[] { BoolVar(), IntVar(), BinaryOperationFamily.Assignment, fail() });
        parameters.add(new Object[] { BoolVar(), DoubleVar(), BinaryOperationFamily.Assignment, fail() });
		
		return parameters;
	}
	
	@Test
	public void testBinaryExpression() {
		DOMBinaryExpression binaryExpression = new DOMBinaryExpression(
				family, 
				leftExpression, 
				rightExpression);
		ExpressionResult expressionResult = compileExpression(binaryExpression);
		assertTrue(expressionResultChecker.check(expressionResult));
	}
}
