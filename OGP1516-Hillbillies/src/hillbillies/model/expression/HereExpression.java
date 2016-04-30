package hillbillies.model.expression;

public class HereExpression extends BasicPExpression {
	public HereExpression(){
		setValue(here());
	}

	private int[] here() {
		return this.getStatement().getTask().getExecutingUnit().getWorld().
				getCubeCoordinate(this.getStatement().getTask().getExecutingUnit().getPosition());
		}
}
