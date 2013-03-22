package com.loki2302.evaluation;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.loki2302.dom.DOMBinaryExpression;
import com.loki2302.expression.BinaryExpression;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.BoolLiteralExpression;
import com.loki2302.expression.CastExpression;
import com.loki2302.expression.DoubleLiteralExpression;
import com.loki2302.expression.Expression;
import com.loki2302.expression.ExpressionVisitor;
import com.loki2302.expression.GetVariableValueExpression;
import com.loki2302.expression.IntLiteralExpression;
import com.loki2302.expression.SetVariableValueExpression;
import com.loki2302.expression.VariableReferenceExpression;

public class DOMBinaryExpressionEvaluator {
    @Inject @Named("assignmentBinaryExpressionMatcher") BinaryExpressionMatcher assignmentBinaryExpressionMatcher;
    @Inject @Named("nonAssignmentBinaryExpressionMatcher") BinaryExpressionMatcher nonAssignmentBinaryExpressionMatcher;
		
	public ExpressionResult evaluateDOMBinaryExpression(
			DOMBinaryExpression expression, 
			DOMExpressionEvaluator domExpressionEvaluator) {
		
		ExpressionResult leftResult = domExpressionEvaluator.evaluateDOMExpression(expression.getLeftExpression());
		ExpressionResult rightResult = domExpressionEvaluator.evaluateDOMExpression(expression.getRightExpression());
		if(!leftResult.isOk() || !rightResult.isOk()) {
			return ExpressionResult.fail();
		}			
		
		Expression leftExpression = leftResult.getExpression();
		Expression rightExpression = rightResult.getExpression();
		BinaryOperationFamily binaryOperationFamily = expression.getBinaryOperationFamily();
				
		if(binaryOperationFamily == BinaryOperationFamily.Assignment) {
		    return evaluateAsAssignment(leftExpression, rightExpression);	        
		}
		
		return nonAssignmentBinaryExpressionMatcher.tryMatch(
		        leftExpression,
		        rightExpression,
		        binaryOperationFamily);
	}
	
	private ExpressionResult evaluateAsAssignment(Expression left, final Expression right) {
	    return left.accept(new ExpressionVisitor<ExpressionResult>() {
            @Override
            public ExpressionResult visitIntLiteralExpression(IntLiteralExpression expression) {
                return ExpressionResult.fail();
            }

            @Override
            public ExpressionResult visitDoubleLiteralExpression(DoubleLiteralExpression expression) {
                return ExpressionResult.fail();
            }

            @Override
            public ExpressionResult visitBoolLiteralExpression(BoolLiteralExpression expression) {
                return ExpressionResult.fail();
            }

            @Override
            public ExpressionResult visitCastExpression(CastExpression expression) {
                return ExpressionResult.fail();
            }

            @Override
            public ExpressionResult visitBinaryExpression(BinaryExpression expression) {
                return ExpressionResult.fail();
            }

            @Override
            public ExpressionResult visitVariableReferenceExpression(VariableReferenceExpression expression) {
                ExpressionResult expressionResult = assignmentBinaryExpressionMatcher.tryMatch(
                        expression, 
                        right,
                        BinaryOperationFamily.Assignment);
                if(!expressionResult.isOk()) {
                    return expressionResult;
                }
                
                return ExpressionResult.ok(new SetVariableValueExpression(expression, expressionResult.getExpression()));
            }

            @Override
            public ExpressionResult visitSetVariableValueExpression(SetVariableValueExpression expression) {
                return ExpressionResult.fail();
            }

            @Override
            public ExpressionResult visitGetVariableValueExpression(GetVariableValueExpression expression) {
                return ExpressionResult.fail();
            }	        
	    });
	}
}