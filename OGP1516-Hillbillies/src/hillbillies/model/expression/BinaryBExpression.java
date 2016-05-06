package hillbillies.model.expression;

public abstract class BinaryBExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends BooleanExpression<T,R>> 
extends BooleanExpression<T,R> implements IComposedBinaryExpression<E> {
	
	public BinaryBExpression(E right, E left){
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
