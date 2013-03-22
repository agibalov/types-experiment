package com.loki2302.evaluation;

import static com.loki2302.evaluation.operations.BinaryOperationRepository.familyIs;
import static com.loki2302.evaluation.operations.BinaryOperationRepository.leftTypeIs;
import static com.loki2302.evaluation.operations.CastOperationRepository.destinationTypeIs;
import static com.loki2302.evaluation.operations.CastOperationRepository.isImplicit;
import static com.loki2302.evaluation.operations.CastOperationRepository.sourceTypeIs;

import com.google.inject.Inject;
import com.loki2302.evaluation.operations.BinaryOperationDefinition;
import com.loki2302.evaluation.operations.BinaryOperationRepository;
import com.loki2302.evaluation.operations.CastOperationDefinition;
import com.loki2302.evaluation.operations.CastOperationRepository;
import com.loki2302.expression.BinaryOperationFamily;
import com.loki2302.expression.Expression;
import com.loki2302.expression.Type;

public class ImplicitRightBinaryExpressionMatcher implements BinaryExpressionMatcher {
    @Inject CastOperationRepository castOperationRepository;
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
                        leftTypeIs(leftType));
        
        if(binaryOperationDefinition == null) {
            return ExpressionResult.fail();
        }
        
        Type requiredRightType = binaryOperationDefinition.getRightType();          
        CastOperationDefinition castOperationDefinition = 
                castOperationRepository.firstWhere(
                        isImplicit(),
                        sourceTypeIs(rightType),
                        destinationTypeIs(requiredRightType));
            
        if(castOperationDefinition == null) {
            return ExpressionResult.fail();
        }
        
        return ExpressionResult.ok(binaryOperationDefinition.makeExpression(                        
                leftExpression,
                castOperationDefinition.makeExpression(rightExpression)));
    }
}