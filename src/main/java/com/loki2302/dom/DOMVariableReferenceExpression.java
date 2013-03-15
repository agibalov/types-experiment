package com.loki2302.dom;

public class DOMVariableReferenceExpression implements DOMExpression {
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMVariableReferenceExpression(this);
	}
}