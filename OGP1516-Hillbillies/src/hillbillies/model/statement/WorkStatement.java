package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class WorkStatement<E extends PositionExpression> extends ActionStatement<E>{
	
	public WorkStatement(E position){
		setExpression(position);
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().work(getExpression().getValue().getCoords());		
	}
	

}
