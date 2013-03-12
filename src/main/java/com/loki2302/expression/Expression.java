package com.loki2302.expression;

public interface Expression {		
	<T> T accept(ExpressionVisitor<T> visitor);
	Type getType();
}