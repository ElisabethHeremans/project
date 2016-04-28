package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class MoveToStatement extends ActionStatement {
	
	public MoveToStatement( PositionExpression position){
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
