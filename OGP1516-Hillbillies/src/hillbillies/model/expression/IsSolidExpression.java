package hillbillies.model.expression;

public class IsSolidExpression extends PositionBExpression {

	public IsSolidExpression(PositionExpression e) {
		super(e);
		setValue(!this.getStatement().getTask().getExecutingUnit().
				getWorld().getTerrain((int[]) e.getValue()).isPassable());
	}
	
	
}
