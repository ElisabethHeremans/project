package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public abstract class BinaryBooleanExpression<E extends Expression<Boolean>,F extends Expression<Boolean>> 
extends Expression<Boolean> implements IComposedBinaryExpression<E,F> {
	
	public BinaryBooleanExpression(E left, F right){
		setRightExpression(right);
		setLeftExpression(left);
				
	}
	
	@Override
	public F getRightExpression(){
		return rightExp;
	}
	
	private F rightExp;
	
	@Override
	public void setRightExpression(F rightExp) {
		this.rightExp = rightExp;
	}
	@Override
	public E getLeftExpression(){
		return leftExp;
	}
	@Override
	public void setLeftExpression(E leftExp) {
		this.leftExp = leftExp;
	}

	private E leftExp;
	
	

}
