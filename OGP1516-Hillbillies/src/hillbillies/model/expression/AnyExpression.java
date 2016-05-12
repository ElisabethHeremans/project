package hillbillies.model.expression;

import hillbillies.model.*;

public class AnyExpression extends UnitExpression{
	
	public AnyExpression(){
	}
	
	public Unit findNearestAnyUnit(Unit unit){
		//Unit unit = this.getStatement().getTask().getExecutingUnit();
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

	@Override
	public Unit evaluateExpression(ExecutionContext context) {
		setValue(findNearestAnyUnit(context.getExecutingUnit()));
		return getValue();
	}
	

	

}
