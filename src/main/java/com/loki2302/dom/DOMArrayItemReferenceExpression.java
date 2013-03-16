package com.loki2302.dom;

public class DOMArrayItemReferenceExpression implements DOMExpression {
	private final DOMExpression arrayReferenceExpression; 
	private final DOMExpression indexExpression;
	
	public DOMArrayItemReferenceExpression(
			DOMExpression arrayReferenceExpression, 
			DOMExpression indexExpression) {
		
		this.arrayReferenceExpression = arrayReferenceExpression;
		this.indexExpression = indexExpression;
	}
	
	public DOMExpression getArrayReferenceExpression() {
		return arrayReferenceExpression;
	}
	
	public DOMExpression getIndexExpression() {
		return indexExpression;
	}

	@Override
	public <T> T accept(DOMExpressionVisitor<T> visitor) {
		return visitor.visitDOMArrayItemReferenceExpression(this);			
	}
}