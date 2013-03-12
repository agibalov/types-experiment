package com.loki2302.dom;


public class DOMDoubleLiteralExpression implements DOMExpression {
	private final String stringValue;
	
	public DOMDoubleLiteralExpression(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMDoubleLiteralExpression(this);
	}
}