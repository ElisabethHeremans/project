package hillbillies.model.expression;

import java.util.Arrays;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class WorkshopExpression extends PositionExpression {
	public WorkshopExpression(){
	
	}

	private Position findNearestWorkshop(Unit unit) {
		int[] nearest = null;
		double nearestDist = 0;
		//System.out.println(unit.getWorld().getTerrainTypes());
		for (int i=0; i< unit.getWorld().getTerrainTypes().length; i++){
			for (int j=0; j< unit.getWorld().getTerrainTypes()[0].length; j++){
				for (int k=0; k< unit.getWorld().getTerrainTypes()[0][0].length; k++){
					System.out.println(unit.getWorld().getTerrainTypes()[i][j][k]);
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
		if(nearest != null){
			System.out.println("WORKSHOP" +Arrays.toString(nearest));
			return new Position(nearest);
		}
		else{
			System.out.println("WORKSHOP null" );
			return null;
		}
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		setValue(findNearestWorkshop(context.getExecutingUnit()));
		return getValue();
	}
}
