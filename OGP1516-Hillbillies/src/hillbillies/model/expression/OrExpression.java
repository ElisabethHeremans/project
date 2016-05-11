package hillbillies.model.expression;

public class OrExpression<E extends BooleanExpression> extends BinaryBooleanExpression<E> {
	public OrExpression(E right, E left) {
		super(right, left);
		setValue(right.getValue()|| left.getValue());
	}

	
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return getRightExpression().getValue()||getLeftExpression().getValue();
	}


}
