package hillbillies.model.expression;

public class IsSolidExpression<E extends PositionExpression> extends UnaryBooleanExpression<E> {

	public IsSolidExpression(E e) {
		setExpression(e);
		setValue(!this.getStatement().getTask().getExecutingUnit().
				getWorld().getTerrain(e.getValue().getCoords()).isPassable());
	}

	
	public Boolean evaluateExpression() {
		return !this.getStatement().getTask().getExecutingUnit().
				getWorld().getTerrain(getExpression().getValue().getCoords()).isPassable();
	}
	
	
}
