package com.loki2302.dom;

public abstract class DOMBinaryExpression implements DOMExpression {
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	
	protected DOMBinaryExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public DOMExpression getLeftExpression() {
		return leftExpression;
	}
	
	public DOMExpression getRightExpression() {
		return rightExpression;
	}	
}