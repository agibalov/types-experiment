package com.loki2302;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.loki2302.dom.DOMAssignmentExpression;
import com.loki2302.dom.DOMExpression;
import com.loki2302.evaluation.ExpressionResult;
import com.loki2302.expression.Type;

@RunWith(Parameterized.class)
public class AssignmentExpressionTest extends AbstractTypeSystemTest {
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	private final ExpressionResultChecker expressionResultChecker;
	
	public AssignmentExpressionTest(
			DOMExpression leftExpression, 
			DOMExpression rightExpression, 
			ExpressionResultChecker expressionResultChecker) {
		
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
		this.expressionResultChecker = expressionResultChecker;
	}	
	
	@Parameters(name = "#{index}: {0} = {1}")
	public static Collection<Object[]> generateData() {
		List<Object[]> parameters = new ArrayList<Object[]>();			
		
		parameters.add(new Object[] { IntVar(), IntLiteral(), ok(Type.Int) });
		parameters.add(new Object[] { IntVar(), DoubleLiteral(), fail() });
		parameters.add(new Object[] { IntVar(), BoolLiteral(), fail() });
		parameters.add(new Object[] { IntVar(), IntVar(), ok(Type.Int) });
		parameters.add(new Object[] { IntVar(), DoubleVar(), fail() });
		parameters.add(new Object[] { IntVar(), BoolVar(), fail() });
		
		parameters.add(new Object[] { DoubleVar(), DoubleLiteral(), ok(Type.Double) });
		parameters.add(new Object[] { DoubleVar(), IntLiteral(), ok(Type.Double) });
		parameters.add(new Object[] { DoubleVar(), BoolLiteral(), fail() });
		parameters.add(new Object[] { DoubleVar(), DoubleVar(), ok(Type.Double) });
		parameters.add(new Object[] { DoubleVar(), IntVar(), ok(Type.Double) });		
		parameters.add(new Object[] { DoubleVar(), BoolVar(), fail() });
		
		parameters.add(new Object[] { BoolVar(), BoolLiteral(), ok(Type.Bool) });
		parameters.add(new Object[] { BoolVar(), IntLiteral(), fail() });
		parameters.add(new Object[] { BoolVar(), DoubleLiteral(), fail() });
		parameters.add(new Object[] { BoolVar(), BoolVar(), ok(Type.Bool) });
		parameters.add(new Object[] { BoolVar(), IntVar(), fail() });
		parameters.add(new Object[] { BoolVar(), DoubleVar(), fail() });
		
		return parameters;
	}
	
	@Test
	public void testAssignmentExpression() {
		DOMAssignmentExpression assignmentExpression = new DOMAssignmentExpression(
				leftExpression, 
				rightExpression);
		ExpressionResult expressionResult = compileExpression(assignmentExpression);
		assertTrue(expressionResultChecker.check(expressionResult));
	}	
}