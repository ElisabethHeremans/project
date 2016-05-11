package hillbillies.model.expression;

public class BracketBooleanExpression<E extends BooleanExpression> extends UnaryBooleanExpression<E> {

	public BracketBooleanExpression(E e) {
		setExpression(e);
		setValue(e.getValue());
	}


	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
