package com.loki2302.evaluation;

import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;

public interface BinaryExpressionMatcher {
    ExpressionResult tryMatch(
            Expression leftExpression, 
            Expression rightExpression, 
            BinaryOperationFamily binaryOperationFamily);
}