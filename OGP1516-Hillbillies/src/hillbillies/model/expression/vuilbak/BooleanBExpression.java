package hillbillies.model.expression.vuilbak;

import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.UnaryBooleanExpression;

public abstract class BooleanBExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends BooleanExpression<T,R>> extends UnaryBooleanExpression<T,R,E> {
	public BooleanBExpression(E e){
		setExpression(e);
	}
}
