package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

public class WorkStatement<E extends Expression<Position>> extends ActionStatement<E>{
	
	public WorkStatement(E position){
		setExpression(position);
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		try{
		context.getExecutingUnit().setCurrentStatement(this);
		super.executeStatement(context);
		getExpression().evaluateExpression(context);
		context.getExecutingUnit().work(getExpression().getValue().getCoords());	
		}
		catch(NullPointerException e){
			context.getExecutingUnit().stopExecutingTask();
		}

	}
	

}
