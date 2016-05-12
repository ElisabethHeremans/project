package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class BracketBooleanExpression<E extends BooleanExpression> extends UnaryBooleanExpression<E> {

	public BracketBooleanExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(getExpression().getValue());
		return getValue();
	}

}
