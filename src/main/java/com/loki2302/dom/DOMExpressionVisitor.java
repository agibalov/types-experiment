package com.loki2302.dom;

public interface DOMExpressionVisitor<T> {
	T visitDOMBinaryExpression(DOMBinaryExpression expression);
	T visitDOMLiteralExpression(DOMLiteralExpression expression);
}