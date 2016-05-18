package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class AndExpression<E extends Expression<Boolean>> extends BinaryBooleanExpression<E> {

	public AndExpression(E left, E right) {
		super(left, right);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(getRightExpression().getValue() && getLeftExpression().getValue());
		return getValue();
	}

}
