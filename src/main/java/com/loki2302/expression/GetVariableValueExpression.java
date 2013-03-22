package com.loki2302.expression;

public class GetVariableValueExpression implements Expression {
    private final VariableReferenceExpression variableReference;
    
    public GetVariableValueExpression(VariableReferenceExpression variableReference) {
        this.variableReference = variableReference;
    }
    
    public VariableReferenceExpression getVariableReferenceExpression() {
        return variableReference;
    }
    
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitGetVariableValueExpression(this);
    }

    @Override
    public Type getResultType() {
        return variableReference.getResultType();
    }       
}