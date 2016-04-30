package hillbillies.model.expression;

import hillbillies.model.*;

public class AnyExpression extends UnitExpression {
	
	public AnyExpression(){
		setValue(findNearestAnyUnit());
	}
	
//	/**
//	 * @return the anyUnit
//	 */
//	public Unit getAnyUnit() {
//		return anyUnit;
//	}
//	
//	/**
//	 * @param anyUnit the anyUnit to set
//	 */
//	public void setAnyUnit(Unit anyUnit) {
//		this.anyUnit = anyUnit;
//	}
//	
//	private Unit anyUnit;
	
	public Unit findNearestAnyUnit(){
		Unit unit = this.getStatement().getTask().getExecutingUnit();
		assert (unit!=null);
		Unit nearest = null;
		double nearestDist = 0;
		for (Unit other: unit.getWorld().listAllUnits()){
			if (other!=unit){
				double dist = Vector.getDistance(unit.getPosition(), other.getPosition());
				if (nearest == null || nearestDist > dist){
					nearestDist = dist;
					nearest = other;
				}
			}
		}
		return nearest;
	}
	

}
