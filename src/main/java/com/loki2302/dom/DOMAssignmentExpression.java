package com.loki2302.dom;

public class DOMAssignmentExpression implements DOMExpression {
	private final DOMExpression leftExpression; 
	private final DOMExpression rightExpression;
	
	public DOMAssignmentExpression(
			DOMExpression leftExpression, 
			DOMExpression rightExpression) {
		
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public DOMExpression getLeftExpression() {
		return leftExpression;
	}
	
	public DOMExpression getRightExpression() {
		return rightExpression;
	}
	
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMAssignmentExpression(this);
	}		
}