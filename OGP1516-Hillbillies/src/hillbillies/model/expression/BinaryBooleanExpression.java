package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public abstract class BinaryBooleanExpression<E extends Expression<Boolean>> 
extends Expression<Boolean> implements IComposedBinaryExpression<E> {
	
	public BinaryBooleanExpression(E left, E right){
		setRightExpression(right);
		setLeftExpression(left);
				
	}
	
	
	public E getRightExpression(){
		return rightExp;
	}
	
	private E rightExp;
	

	public void setRightExpression(E rightExp) {
		this.rightExp = rightExp;
	}

	public E getLeftExpression(){
		return leftExp;
	}

	public void setLeftExpression(E leftExp) {
		this.leftExp = leftExp;
	}

	private E leftExp;
	
	

}
