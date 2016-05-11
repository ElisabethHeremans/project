package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.UnitExpression;

public class FollowStatement<E extends UnitExpression> extends ActionStatement<E> {
	
	public FollowStatement(E unit){
		setExpression(unit);
	}

	@Override
	public void executeStatement(Unit executingUnit) {
		executingUnit.startFollowing(getExpression().getValue());
		
	}
	

}
