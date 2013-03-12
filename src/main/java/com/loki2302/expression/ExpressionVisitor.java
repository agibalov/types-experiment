package com.loki2302.expression;


public interface ExpressionVisitor<T> {
	T visitIntLiteralExpression(IntLiteralExpression expression);
	T visitDoubleLiteralExpression(DoubleLiteralExpression expression);
	T visitCastIntToDoubleExpression(CastIntToDoubleExpression expression);
	T visitIntAddExpression(IntAddExpression expression);
	T visitDoubleAddExpression(DoubleAddExpression expression);
}