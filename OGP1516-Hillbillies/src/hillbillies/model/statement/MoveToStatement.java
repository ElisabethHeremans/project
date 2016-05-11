package hillbillies.model.statement;

import hillbillies.model.expression.PositionExpression;

public class MoveToStatement<E extends PositionExpression> extends ActionStatement<E> {
	
	public MoveToStatement( E position){
		setExpression(position);
	}
	

}
