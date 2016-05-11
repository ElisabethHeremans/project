package hillbillies.model.expression;

import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.Vector;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class WorkshopExpression extends PositionExpression {
	public WorkshopExpression(){
		setValue(new Position(findNearestWorkshop()));
	}

	private int[] findNearestWorkshop() {
		Unit unit = this.getStatement().getTask().getExecutingUnit();
		int[] nearest = null;
		double nearestDist = 0;
		for (int i=0; i< unit.getWorld().getTerrainTypes().length; i++){
			for (int j=0; j< unit.getWorld().getTerrainTypes()[0].length; j++){
				for (int k=0; k< unit.getWorld().getTerrainTypes()[0][0].length; k++){
					if (unit.getWorld().getTerrainTypes()[i][j][k]==3){
						double dist = Vector.getDistance(unit.getPosition(), new double[] {i+0.5,j+0.5,k+0.5});
						if (nearest == null || nearestDist > dist){
							nearestDist = dist;
							nearest = new int[] {i,j,k};
						}

					}
				}
			}
		}
					
					
	
						
		return nearest;
	}

	
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
