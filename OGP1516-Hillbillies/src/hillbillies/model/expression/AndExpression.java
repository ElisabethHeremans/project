package hillbillies.model.expression;

public class AndExpression<E extends BooleanExpression<?>> extends BinaryBooleanExpression<E> {

	public AndExpression(E right, E left) {
		super(right, left);
		setValue(right.getValue()&& left.getValue());
	}

	@Override
	public Boolean evaluateExpression() {
		return getRightExpression().getValue()&& getLeftExpression().getValue();
	}

}
