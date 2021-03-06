package hillbillies.model.statement;

import java.util.Arrays;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.expression.BasicVariableExpression;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.PositionExpression;

public class MoveToStatement<E extends Expression<Position>> extends ActionStatement<E> {
	
	public MoveToStatement( E position){
		setExpression(position);
	}

	

	@Override
	public void executeStatement(ExecutionContext context) {
		try{
		context.getExecutingUnit().setCurrentStatement(this);
		getExpression().evaluateExpression(context);
		context.getExecutingUnit().moveTo1(getExpression().getValue().getCoords());
		}
		catch(NullPointerException e){
			context.getExecutingUnit().stopExecutingTask();
		}

	}
	

}
