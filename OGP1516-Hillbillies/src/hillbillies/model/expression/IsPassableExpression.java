package hillbillies.model.expression;

public class IsPassableExpression<E extends PositionExpression<?>> extends UnaryBooleanExpression<E> {

	public IsPassableExpression(E e) {
		setExpression(e);
		setValue(this.getStatement().getTask().getExecutingUnit().
				getWorld().getTerrain(e.getValue().getCoords()).isPassable());

	}

	@Override
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return this.getStatement().getTask().getExecutingUnit().
				getWorld().getTerrain(getExpression().getValue().getCoords()).isPassable();
	}
	
}
