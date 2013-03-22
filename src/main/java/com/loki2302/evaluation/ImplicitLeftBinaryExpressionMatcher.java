package com.loki2302.evaluation;

import static com.loki2302.evaluation.operations.BinaryOperationRepository.familyIs;
import static com.loki2302.evaluation.operations.BinaryOperationRepository.rightTypeIs;
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

public class ImplicitLeftBinaryExpressionMatcher implements BinaryExpressionMatcher {
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
                        rightTypeIs(rightType));
        
        if(binaryOperationDefinition == null) {
            return ExpressionResult.fail();
        }
        
        Type requiredLeftType = binaryOperationDefinition.getLeftType();
        CastOperationDefinition castOperationDefinition = 
                castOperationRepository.firstWhere(
                        isImplicit(),
                        sourceTypeIs(leftType), 
                        destinationTypeIs(requiredLeftType));
            
        if(castOperationDefinition == null) {
            return ExpressionResult.fail();
        }
        
        return ExpressionResult.ok(binaryOperationDefinition.makeExpression(
                castOperationDefinition.makeExpression(leftExpression),
                rightExpression));
    }
}