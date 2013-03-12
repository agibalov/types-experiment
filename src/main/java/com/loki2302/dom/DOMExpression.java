package com.loki2302.dom;

public interface DOMExpression {		
	<T> T accept(DOMExpressionVisitor<T> visitor);
}