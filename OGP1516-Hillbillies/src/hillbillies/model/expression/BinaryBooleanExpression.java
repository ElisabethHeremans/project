package hillbillies.model.expression;

public abstract class BinaryBooleanExpression<E extends BooleanExpression> 
extends BooleanExpression implements IComposedBinaryExpression<E> {
	
	public BinaryBooleanExpression(E right, E left){
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
