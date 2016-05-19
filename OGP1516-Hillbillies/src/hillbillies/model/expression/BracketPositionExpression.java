package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;

public class BracketPositionExpression<E extends Expression<Position>> extends UnaryPositionExpression<E> {
	public BracketPositionExpression(E e){
		setExpression(e);
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		this.getExpression().evaluateExpression(context);
		setValue(getExpression().getValue());
		return getValue();
	}
}
