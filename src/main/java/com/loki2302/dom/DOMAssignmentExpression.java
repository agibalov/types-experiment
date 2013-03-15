package com.loki2302.dom;

public class DOMAssignmentExpression implements DOMExpression {
	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMAssignmentExpression(this);
	}		
}