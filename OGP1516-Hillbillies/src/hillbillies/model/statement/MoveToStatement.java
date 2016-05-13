package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class MoveToStatement<E extends PositionExpression> extends ActionStatement<E> {
	
	public MoveToStatement( E position){
		setExpression(position);
	}

	

	@Override
	public void executeStatement(ExecutionContext context) {
		getExpression().evaluateExpression(context);
		context.getExecutingUnit().moveTo1(getExpression().getValue().getCoords());
		
	}
	

}
