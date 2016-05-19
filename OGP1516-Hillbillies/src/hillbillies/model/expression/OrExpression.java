package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class OrExpression<E extends Expression<Boolean>> extends BinaryBooleanExpression<E> {
	public OrExpression(E left, E right) {
		super(left, right);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		this.getLeftExpression().evaluateExpression(context);
		this.getRightExpression().evaluateExpression(context);

		setValue(getRightExpression().getValue() || getLeftExpression().getValue());
		return getValue();
	}

	


}
