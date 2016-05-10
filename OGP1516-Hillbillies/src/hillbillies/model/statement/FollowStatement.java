package hillbillies.model.statement;

import hillbillies.model.expression.UnitExpression;

public class FollowStatement<E extends UnitExpression<?>,S> extends ActionStatement<E,S> {
	
	public FollowStatement(E unit){
		setExpression(unit);
	}
	

}
