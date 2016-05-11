package hillbillies.model.expression;

import hillbillies.model.Boulder;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class BoulderExpression extends PositionExpression {
		public BoulderExpression(){
			setValue(new Position(findNearestBoulder()));
		}

		private int[] findNearestBoulder() {
			Unit unit = this.getStatement().getTask().getExecutingUnit();
			Boulder nearest = null;
			double nearestDist = 0;
			for (Boulder boulder: unit.getWorld().listAllBoulders() ){
				double dist = Vector.getDistance(unit.getPosition(), boulder.getPosition());
				if (nearest == null || nearestDist > dist){
					nearestDist = dist;
					nearest = boulder;
				}
			}
			return nearest.getWorld().getCubeCoordinate(nearest.getPosition());
		}

	
		public Position evaluateExpression() {
			// TODO Auto-generated method stub
			return null;
		}


}
