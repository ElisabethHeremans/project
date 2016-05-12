package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.UnitExpression;

public class FollowStatement<E extends UnitExpression> extends ActionStatement<E> {
	
	public FollowStatement(E unit){
		setExpression(unit);
	}

	

	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().startFollowing(getExpression().getValue());
		
	}
	

}
