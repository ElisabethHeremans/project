package hillbillies.model.expression;

import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class FriendExpression<E> extends UnitExpression<E>{
	
	public FriendExpression(){
		setValue(findFriendUnit());
	}

	private Unit findFriendUnit() {
		Unit unit = this.getStatement().getTask().getExecutingUnit();
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
	public Unit evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
