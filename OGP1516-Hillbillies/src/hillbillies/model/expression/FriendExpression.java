package hillbillies.model.expression;

import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.types.FriendType;

public class FriendExpression< E> extends UnitExpression<FriendType,E>{
	
	public FriendExpression(){
		setValue(findFriendUnit());
	}

	private FriendType findFriendUnit() {
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
		return new FriendType(nearest);
	}

	@Override
	public FriendType evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
