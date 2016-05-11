package hillbillies.model.expression;

import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.types.EnemyType;

public class EnemyExpression extends UnitExpression{
	
	public EnemyExpression(){
		setValue(findEnemyUnit());
	}

	private Unit findEnemyUnit() {
		Unit unit = this.getStatement().getTask().getExecutingUnit();
		assert (unit!=null);
		Unit nearest = null;
		double nearestDist = 0;
		for (Unit other: unit.getWorld().listAllUnits()){
			if (other.getFaction()!=unit.getFaction()){
				double dist = Vector.getDistance(unit.getPosition(), other.getPosition());
				if (nearest == null || nearestDist > dist){
					nearestDist = dist;
					nearest = other;
				}
			}
		}
		return nearest;
	}


	public Unit evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
