package hillbillies.model.expression;

public abstract class UnaryBExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends Expression<?,?>> 
extends BooleanExpression<T,R> implements IComposedUnaryExpression<E> {
	public UnaryBExpression(){
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
