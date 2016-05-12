package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class IsEnemyExpression<E extends UnitExpression> extends UnaryBooleanExpression<E> {

	public IsEnemyExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(context.getExecutingUnit().getFaction() != getExpression().getValue().getFaction());
		return getValue();
	}
	

}
