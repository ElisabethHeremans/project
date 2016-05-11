package hillbillies.model.statement;

import hillbillies.model.expression.UnitExpression;

public class FollowStatement<E extends UnitExpression> extends ActionStatement<E> {
	
	public FollowStatement(E unit){
		setExpression(unit);
	}
	

}
