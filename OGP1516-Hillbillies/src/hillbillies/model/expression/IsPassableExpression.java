package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class IsPassableExpression<E extends PositionExpression> extends UnaryBooleanExpression<E> {

	public IsPassableExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(context.getExecutingUnit().getWorld().getTerrain(getExpression().getValue().getCoords()).isPassable());
		return getValue();
	}
	
}
