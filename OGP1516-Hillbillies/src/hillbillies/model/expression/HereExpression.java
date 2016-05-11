package hillbillies.model.expression;


import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class HereExpression extends PositionExpression {
	public HereExpression(){
		setValue(here());
	}

	private Position here() {
		int[] pos= this.getStatement().getTask().getExecutingUnit().getWorld().
				getCubeCoordinate(this.getStatement().getTask().getExecutingUnit().getPosition());
		return new Position(pos);
		}

	
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return here();
	}
}
