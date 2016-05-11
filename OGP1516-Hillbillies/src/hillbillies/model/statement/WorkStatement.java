package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class WorkStatement<E extends PositionExpression> extends ActionStatement<E>{
	
	public WorkStatement(E position){
		setExpression(position);
	}
	

}
