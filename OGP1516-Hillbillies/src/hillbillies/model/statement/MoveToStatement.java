package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class MoveToStatement<E extends PositionExpression<?>,S> extends ActionStatement<E,S> {
	
	public MoveToStatement( E position){
		setExpression(position);
	}
	

}
