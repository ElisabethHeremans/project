package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class IsAliveExpression<E extends UnitExpression> extends UnaryBooleanExpression<E> {

	public IsAliveExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(!context.getExecutingUnit().isTerminated());
		return getValue();
	}

}
