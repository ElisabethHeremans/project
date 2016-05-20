package hillbillies.model.expression;

public abstract class UnaryBooleanExpression<E extends Expression<?>> 
extends BooleanExpression implements IComposedUnaryExpression<E> {
	
	public UnaryBooleanExpression(){
	}
	
	
	
	private E expression;


	public E getExpression() {
		return expression;
	}


	public void setExpression(E expression) {
		this.expression = expression;
	}
	
	



}
