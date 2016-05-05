package hillbillies.model.expression;

public abstract class UnaryBExpression<T extends Expression<?>> 
extends ComposedBExpression /*implements ComposedUnaryExpression<T>*/ {
	public UnaryBExpression(){
	}
	

	public Expression<?> getExpression() {
		return expression;
	}

	public void setExpression(Expression<?> e) {
		this.expression = e;
	}
	
	private Expression<?> expression;
	//??

}
