package com.loki2302.dom;

public class DOMLiteralExpression implements DOMExpression {
	private final DOMLiteralType literalType;
	private final String stringValue;
	
	public DOMLiteralExpression(
			DOMLiteralType domLiteralType, 
			String stringValue) {
		this.literalType = domLiteralType;
		this.stringValue = stringValue;
	}
	
	public DOMLiteralType getLiteralType() {
		return literalType;
	}
	
	public String getStringValue() {
		return stringValue;
	}
	
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMLiteralExpression(this);
	}
}