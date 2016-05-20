package hillbillies.model.expression;

import hillbillies.model.Position;

public abstract class UnaryPositionExpression<E extends Expression<?>> 
extends PositionExpression implements IComposedUnaryExpression<E> {
	
	private E expression;

	public E getExpression() {
		return expression;
	}



	public void setExpression(E expression) {
		this.expression = expression;
	}





}
