package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class OrExpression<E extends BooleanExpression> extends BinaryBooleanExpression<E> {
	public OrExpression(E right, E left) {
		super(right, left);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(getRightExpression().getValue() || getLeftExpression().getValue());
		return getValue();
	}


}
