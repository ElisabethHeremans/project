package hillbillies.model.expression;

import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.ComposedPExpression;

public class BracketPositionExpression<E extends PositionExpression<?>> extends UnaryPositionExpression<E> {
	public BracketPositionExpression(E e){
		setExpression(e);
		setValue(e.getValue());
	}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
