package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class MoveToStatement extends ActionStatement {
	
	public MoveToStatement( PositionExpression position){
		setExpression(position);
	}
	

}
