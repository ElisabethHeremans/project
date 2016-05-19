package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.UnitExpression;

public class FollowStatement<E extends Expression<Unit>> extends ActionStatement<E> {
	
	public FollowStatement(E unit){
		setExpression(unit);
	}

	

	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);

		getExpression().evaluateExpression(context);
		context.getExecutingUnit().startFollowing(getExpression().getValue());
		
	}
	

}
