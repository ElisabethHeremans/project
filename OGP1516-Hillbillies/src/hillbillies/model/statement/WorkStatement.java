package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class WorkStatement extends ActionStatement {
	
	public WorkStatement(PositionExpression position){
		setExpression(position);
	}
	

}
