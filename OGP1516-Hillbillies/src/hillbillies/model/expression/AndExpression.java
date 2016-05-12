package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class AndExpression<E extends BooleanExpression> extends BinaryBooleanExpression<E> {

	public AndExpression(E right, E left) {
		super(right, left);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(getRightExpression().getValue() && getLeftExpression().getValue());
		return getValue();
	}

}
