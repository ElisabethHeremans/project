package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Log;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class LogExpression extends PositionExpression {
	public LogExpression(){
		
	}

	private Position findNearestLog(Unit unit) {
		Log nearest = null;
		double nearestDist = 0;
		for (Log log: unit.getWorld().listAllLogs() ){
			double dist = Vector.getDistance(unit.getPosition(), log.getPosition());
			if (nearest == null || nearestDist > dist){
				nearestDist = dist;
				nearest = log;
			}
		}
		return new Position(nearest.getWorld().getCubeCoordinate(nearest.getPosition()));
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		setValue(findNearestLog(context.getExecutingUnit()));
		return getValue();
	}
}
