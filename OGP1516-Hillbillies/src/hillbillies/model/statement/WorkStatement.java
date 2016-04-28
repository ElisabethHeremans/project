package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class WorkStatement extends ActionStatement {
	
	public WorkStatement(PositionExpression position){
		setPosition(position);
	}
	
	private PositionExpression position;

	public PositionExpression getPosition() {
		return position;
	}

	public void setPosition(PositionExpression position) {
		this.position = position;
	}

}
