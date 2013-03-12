package com.loki2302.dom;


public class DOMAddExpression implements DOMExpression {
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	
	public DOMAddExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public DOMExpression getLeftExpression() {
		return leftExpression;
	}
	
	public DOMExpression getRightExpression() {
		return rightExpression;
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMAddExpression(this);
	}
}