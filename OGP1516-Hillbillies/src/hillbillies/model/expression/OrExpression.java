package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class OrExpression<E extends BooleanExpression> extends BinaryBooleanExpression<E> {
	public OrExpression(E left, E right) {
		super(left, right);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(getRightExpression().getValue() || getLeftExpression().getValue());
		return getValue();
	}


}
