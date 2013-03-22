package com.loki2302.evaluation;

import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;

public class AlternativeBinaryExpressionMatcher implements BinaryExpressionMatcher {
    private final BinaryExpressionMatcher[] matchers;
    
    public AlternativeBinaryExpressionMatcher(BinaryExpressionMatcher[] matchers) {
        this.matchers = matchers;
    }

    @Override
    public ExpressionResult tryMatch(
            Expression leftExpression,
            Expression rightExpression,
            BinaryOperationFamily binaryOperationFamily) {
        
        for(BinaryExpressionMatcher matcher : matchers) {
            ExpressionResult result = matcher.tryMatch(
                    leftExpression, 
                    rightExpression, 
                    binaryOperationFamily);
            if(result.isOk()) {
                return result;
            }
        }
        
        return ExpressionResult.fail();
    }
}