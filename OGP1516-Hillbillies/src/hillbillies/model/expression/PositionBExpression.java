package hillbillies.model.expression;

public abstract class PositionBExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends PositionExpression> extends UnaryBExpression<T,R,E> {
	public PositionBExpression(E e){
		setExpression(e);
		
	}

}
