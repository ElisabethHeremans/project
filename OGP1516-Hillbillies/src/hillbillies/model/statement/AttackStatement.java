package hillbillies.model.statement;

import java.util.List;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.UnitExpression;

public class AttackStatement<E extends Expression<Unit>> extends ActionStatement<E> {

	public AttackStatement(E e) {
		setExpression(e);
	}

	@Override
	public void executeStatement(ExecutionContext context) {
		try{
		context.getExecutingUnit().setCurrentStatement(this);
		getExpression().evaluateExpression(context);
		context.getExecutingUnit().attack(getExpression().getValue());
		}
		catch(NullPointerException e){
			context.getExecutingUnit().stopExecutingTask();
		}
		
	}


}
