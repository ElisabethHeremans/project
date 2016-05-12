package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.types.FriendType;

public class FriendExpression extends UnitExpression{
	
	public FriendExpression(){
	}

	private Unit findFriendUnit(Unit unit) {
		//Unit unit = this.getStatement().getTask().getExecutingUnit();
		assert (unit!=null);
		Unit nearest = null;
		double nearestDist = 0;
		for (Unit other: unit.getWorld().listAllUnitsOfFaction(unit.getFaction())){
			if (other !=unit){
				double dist = Vector.getDistance(unit.getPosition(), other.getPosition());
				if (nearest == null || nearestDist > dist){
					nearestDist = dist;
					nearest = other;
				}
			}
		}
		return nearest;
	}

	@Override
	public Unit evaluateExpression(ExecutionContext context) {
		setValue(findFriendUnit(context.getExecutingUnit()));
		return getValue();
	}
}
