package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class IsFriendExpression<E extends Expression<Unit>> extends UnaryBooleanExpression<E> {

	public IsFriendExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		getExpression().evaluateExpression(context);
		setValue(context.getExecutingUnit().getFaction() == getExpression().getValue().getFaction());
		return getValue();
	}
	

}
