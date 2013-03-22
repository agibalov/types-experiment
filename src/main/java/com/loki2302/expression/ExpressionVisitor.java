package com.loki2302.expression;

public interface ExpressionVisitor<T> {
	T visitIntLiteralExpression(IntLiteralExpression expression);
	T visitDoubleLiteralExpression(DoubleLiteralExpression expression);
	T visitBoolLiteralExpression(BoolLiteralExpression expression);
	T visitCastExpression(CastExpression expression);
	T visitBinaryExpression(BinaryExpression expression);
	T visitVariableReferenceExpression(VariableReferenceExpression expression);
	T visitSetVariableValueExpression(SetVariableValueExpression expression);
	T visitGetVariableValueExpression(GetVariableValueExpression expression);
}