package hillbillies.model.expression;

public class AndExpression<T extends BooleanExpression<T,R>,R extends Boolean, E extends BooleanExpression<T,R>> extends BinaryBExpression<T,R,E> {

	public AndExpression(E right, E left) {
		super(right, left);
		setValue(right.getValue()&& left.getValue());
	}

	@Override
	public R evaluateExpression() {
		// TODO Auto-generated method stub
		return getRightExpression().getValue()&& getLeftExpression().getValue();
	}

}
