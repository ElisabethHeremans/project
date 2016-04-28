package hillbillies.model.expression;

public class IsSolidExpression extends PositionBExpression {
	
	public IsSolidExpression(PositionExpression position){
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
