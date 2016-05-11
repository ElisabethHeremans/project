package hillbillies.model.expression;

public class NegationExpression<E extends BooleanExpression> extends UnaryBooleanExpression<E> {

	public NegationExpression(E e) {
		setExpression(e);
		setValue(! (Boolean)e.getValue());
	}

	
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
