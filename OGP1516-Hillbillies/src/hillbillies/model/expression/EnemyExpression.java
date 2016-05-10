package hillbillies.model.expression;

import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.types.EnemyType;

public class EnemyExpression<E> extends UnitExpression<EnemyType,E>{
	
	public EnemyExpression(){
		setValue(findEnemyUnit());
	}

	private EnemyType findEnemyUnit() {
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
		return new EnemyType(nearest);
	}

	@Override
	public EnemyType evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
