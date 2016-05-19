package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class NegationExpression<E extends Expression<Boolean>> extends UnaryBooleanExpression<E> {

	public NegationExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		getExpression().evaluateExpression(context);
		setValue(!getExpression().getValue());
		return getValue();
	}
}
