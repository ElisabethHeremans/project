package hillbillies.model.expression;

import hillbillies.model.Boulder;
import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class BoulderExpression extends BasicPExpression {
		public BoulderExpression(){
			setValue(findNearestBoulder());
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


}
