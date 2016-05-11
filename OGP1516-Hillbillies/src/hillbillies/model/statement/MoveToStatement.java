package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class MoveToStatement<E extends PositionExpression> extends ActionStatement<E> {
	
	public MoveToStatement( E position){
		setExpression(position);
	}

	@Override
	public void executeStatement(Unit executingUnit) {
		executingUnit.moveTo1(getExpression().getValue().getCoords());
		
	}
	

}
