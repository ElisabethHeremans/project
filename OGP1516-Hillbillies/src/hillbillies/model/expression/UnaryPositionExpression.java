package hillbillies.model.expression;

public abstract class UnaryPositionExpression<E extends Expression> 
extends PositionExpression implements IComposedUnaryExpression<E> {
	
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
