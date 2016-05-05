package hillbillies.model.expression;


import hillbillies.model.Position;

public class HereExpression extends BasicPExpression {
	public HereExpression(){
		setValue(here());
	}

	private Position here() {
		int[] pos= this.getStatement().getTask().getExecutingUnit().getWorld().
				getCubeCoordinate(this.getStatement().getTask().getExecutingUnit().getPosition());
		return new Position(pos);
		}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return here();
	}
}
