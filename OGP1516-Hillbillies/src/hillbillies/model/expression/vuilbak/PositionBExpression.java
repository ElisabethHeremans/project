package hillbillies.model.expression.vuilbak;

import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.PositionExpression;
import hillbillies.model.expression.UnaryBooleanExpression;

public abstract class PositionBExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends PositionExpression> extends UnaryBooleanExpression<T,R,E> {
	
	public PositionBExpression(E e){
		setExpression(e);
		
	}

}
