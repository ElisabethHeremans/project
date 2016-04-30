package hillbillies.model.expression;

public class IsPassableExpression extends PositionBExpression {

	public IsPassableExpression(PositionExpression e) {
		super(e);
		setValue(this.getStatement().getTask().getExecutingUnit().
				getWorld().getTerrain((int[]) e.getValue()).isPassable());

	}
	
}
