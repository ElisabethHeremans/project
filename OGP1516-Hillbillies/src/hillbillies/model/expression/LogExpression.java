package hillbillies.model.expression;

import hillbillies.model.Log;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class LogExpression extends PositionExpression {
	public LogExpression(){
		setValue(findNearestLog());
	}

	private Position findNearestLog() {
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
		return new Position(nearest.getWorld().getCubeCoordinate(nearest.getPosition()));
	}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
