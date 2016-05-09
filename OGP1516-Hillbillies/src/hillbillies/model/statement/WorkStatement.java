package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class WorkStatement<E extends PositionExpression<?>,S> extends ActionStatement<E,S>{
	
	public WorkStatement(E position){
		setExpression(position);
	}
	

}
