package hillbillies.model.expression;

public class IsPassableExpression extends PositionBExpression {
	
	public IsPassableExpression(PositionExpression position){
		setPosition(position);
	}
	
	public PositionExpression getPosition() {
		return position;
	}

	public void setPosition(PositionExpression position) {
		this.position = position;
	}

	private PositionExpression position;

}
