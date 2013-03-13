package com.loki2302.dom;

import com.loki2302.expression.BinaryOperationFamily;

public class DOMBinaryExpression implements DOMExpression {
	private final BinaryOperationFamily binaryOperationFamily;
	private final DOMExpression leftExpression;
	private final DOMExpression rightExpression;
	
	public DOMBinaryExpression(
			BinaryOperationFamily binaryOperationFamily, 
			DOMExpression leftExpression, 
			DOMExpression rightExpression) {
		
		this.binaryOperationFamily = binaryOperationFamily;
		this.leftExpression = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	public BinaryOperationFamily getBinaryOperationFamily() {
		return binaryOperationFamily;
	}
	
	public DOMExpression getLeftExpression() {
		return leftExpression;
	}
	
	public DOMExpression getRightExpression() {
		return rightExpression;
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMBinaryExpression(this);
	}
}