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
		context.getExecutingUnit().setCurrentStatement(this);
		getExpression().evaluateExpression(context);
		//System.out.println(" move to stat " + Arrays.toString(getExpression().getValue().getCoords()));
		context.getExecutingUnit().moveTo1(getExpression().getValue().getCoords());
		
	}
	

}
