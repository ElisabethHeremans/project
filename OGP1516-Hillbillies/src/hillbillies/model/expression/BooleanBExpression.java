package hillbillies.model.expression;

public abstract class BooleanBExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends BooleanExpression<T,R>> extends UnaryBExpression<T,R,E> {
	public BooleanBExpression(E e){
		setExpression(e);
	}
}
