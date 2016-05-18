package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;

public class IsSolidExpression<E extends Expression<Position>> extends UnaryBooleanExpression<E> {

	public IsSolidExpression(E e) {
		setExpression(e);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(context.getExecutingUnit().getWorld().getTerrain(getExpression().getValue().getCoords()).isPassable());
		return getValue();
	}
	
	
}
