package hillbillies.model.expression;

import hillbillies.model.Boulder;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class BoulderExpression extends PositionExpression {
		public BoulderExpression(){
		
		}

		private Position findNearestBoulder(Unit unit) {
			Boulder nearest = null;
			double nearestDist = 0;
			for (Boulder boulder: unit.getWorld().listAllBoulders() ){
				double dist = Vector.getDistance(unit.getPosition(), boulder.getPosition());
				if (nearest == null || nearestDist > dist){
					nearestDist = dist;
					nearest = boulder;
				}
			}
			if(nearest != null)
				return new Position(nearest.getWorld().getCubeCoordinate(nearest.getPosition()));
			else
				return null;
		}

		@Override
		public Position evaluateExpression(ExecutionContext context) {
			setValue(findNearestBoulder(context.getExecutingUnit()));
			return getValue();
		}


}
