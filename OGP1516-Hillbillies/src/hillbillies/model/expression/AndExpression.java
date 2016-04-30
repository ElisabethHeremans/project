package hillbillies.model.expression;

public class AndExpression extends BinaryBExpression {

	public AndExpression(BooleanExpression right, BooleanExpression left) {
		super(right, left);
		setValue((boolean)right.getValue()&&(boolean)left.getValue());
	}

}
