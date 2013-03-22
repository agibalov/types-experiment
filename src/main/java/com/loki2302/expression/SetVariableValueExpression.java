package com.loki2302.expression;

public class SetVariableValueExpression implements Expression {
    private final VariableReferenceExpression variableReference;
    private final Expression expression;
    
    public SetVariableValueExpression(
            VariableReferenceExpression variableReference, 
            Expression expression) {
        this.variableReference = variableReference;
        this.expression = expression;
    }
    
    public VariableReferenceExpression getVariableReferenceExpression() {
        return variableReference;
    }
    
    public Expression getExpression() {
        return expression;
    }
    
    @Override
    public <T> T accept(ExpressionVisitor<T> visitor) {
        return visitor.visitSetVariableValueExpression(this);
    }

    @Override
    public Type getResultType() {
        return variableReference.getResultType();
    }	    
}