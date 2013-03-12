package com.loki2302.dom;

public interface DOMExpressionVisitor<T> {
	T visitDOMIntLiteralExpression(DOMIntLiteralExpression expression);
	T visitDOMDoubleLiteralExpression(DOMDoubleLiteralExpression expression);
	T visitDOMAddExpression(DOMAddExpression expression);
}