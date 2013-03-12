package com.loki2302.dom;


public class DOMIntLiteralExpression implements DOMExpression {
	private final String stringValue;
	
	public DOMIntLiteralExpression(String stringValue) {
		this.stringValue = stringValue;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMIntLiteralExpression(this);
	}	
}