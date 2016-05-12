package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.ComposedPExpression;

public class BracketPositionExpression<E extends PositionExpression> extends UnaryPositionExpression<E> {
	public BracketPositionExpression(E e){
		setExpression(e);
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		setValue(getExpression().getValue());
		return getValue();
	}
}
