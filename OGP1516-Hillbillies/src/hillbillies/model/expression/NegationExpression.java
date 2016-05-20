package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class NegationExpression<E extends Expression<Boolean>> extends UnaryBooleanExpression<E> {

	public NegationExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		try{
		getExpression().evaluateExpression(context);
		setValue(!getExpression().getValue());
		return getValue();
		}
		catch(NullPointerException e){
			return null;
		}

	}
}
