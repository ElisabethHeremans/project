package hillbillies.model.expression;

public abstract class BooleanBExpression<T> extends UnaryBExpression<BooleanExpression<T>> {
	public BooleanBExpression(BooleanExpression e){
		setExpression(e);
	}
}
