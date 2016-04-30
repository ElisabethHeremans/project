package hillbillies.model.expression;

import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class LogExpression extends BasicPExpression {
	public LogExpression(){
		setValue(findNearestLog());
	}

	private int[] findNearestLog() {
		Unit unit = this.getStatement().getTask().getExecutingUnit();
		Log nearest = null;
		double nearestDist = 0;
		for (Log log: unit.getWorld().listAllLogs() ){
			double dist = Vector.getDistance(unit.getPosition(), log.getPosition());
			if (nearest == null || nearestDist > dist){
				nearestDist = dist;
				nearest = log;
			}
		}
		return nearest.getWorld().getCubeCoordinate(nearest.getPosition());
	}
}
