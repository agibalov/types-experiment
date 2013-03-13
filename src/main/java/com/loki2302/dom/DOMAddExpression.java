package com.loki2302.dom;

public class DOMAddExpression extends DOMBinaryExpression {
	public DOMAddExpression(DOMExpression leftExpression, DOMExpression rightExpression) {
		super(leftExpression, rightExpression);
	}

	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMAddExpression(this);
	}
}