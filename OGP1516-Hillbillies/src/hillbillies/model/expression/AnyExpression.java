package hillbillies.model.expression;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import hillbillies.model.*;
import ogp.framework.util.Util;

public class AnyExpression<E> extends UnitExpression<E>{
	
	public AnyExpression(){
		setValue(findNearestAnyUnit());
	}
	
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

	@Override
	public Unit evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
