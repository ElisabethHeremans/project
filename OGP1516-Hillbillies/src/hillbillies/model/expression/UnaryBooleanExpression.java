package hillbillies.model.expression;

public abstract class UnaryBooleanExpression<E extends Expression<?,?>> 
extends BooleanExpression<E> implements IComposedUnaryExpression<E> {
	
	public UnaryBooleanExpression(){
	}
	
	
	
	private E expression;



	/**
	 * @return the expression
	 */
	public E getExpression() {
		return expression;
	}



	/**
	 * @param expression the expression to set
	 */
	public void setExpression(E expression) {
		this.expression = expression;
	}
	
	



}
