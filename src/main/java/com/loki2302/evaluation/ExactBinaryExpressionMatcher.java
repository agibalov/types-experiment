package com.loki2302.evaluation;

import static com.loki2302.evaluation.operations.BinaryOperationRepository.familyIs;
import static com.loki2302.evaluation.operations.BinaryOperationRepository.leftTypeIs;
import static com.loki2302.evaluation.operations.BinaryOperationRepository.rightTypeIs;

import com.google.inject.Inject;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ExactBinaryExpressionMatcher implements BinaryExpressionMatcher {	    
    @Inject BinaryOperationRepository binaryOperationRepository;
    
    public ExpressionResult tryMatch(
            Expression leftExpression, 
            Expression rightExpression, 
            BinaryOperationFamily binaryOperationFamily) {
        
        Type leftType = leftExpression.getResultType();
        Type rightType = rightExpression.getResultType();                   
                
        BinaryOperationDefinition binaryOperationDefinition =
                binaryOperationRepository.firstWhere(
                        familyIs(binaryOperationFamily), 
                        leftTypeIs(leftType), 
                        rightTypeIs(rightType));
        
        if(binaryOperationDefinition == null) {
            return ExpressionResult.fail();
        }
        
        return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
                leftExpression,
                rightExpression));
    }    
}