package hillbillies.model.expression;

public class NextToExpression extends ComposedPExpression {
	
	public NextToExpression(PositionExpression position){
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
