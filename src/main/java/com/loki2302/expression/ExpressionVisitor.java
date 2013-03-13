package com.loki2302.expression;

public interface ExpressionVisitor<T> {
	T visitIntLiteralExpression(IntLiteralExpression expression);
	T visitDoubleLiteralExpression(DoubleLiteralExpression expression);
	T visitCastExpression(CastExpression expression);
	T visitBinaryExpression(BinaryExpression expression);
}