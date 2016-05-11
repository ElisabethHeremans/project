package hillbillies.model.statement;

import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class WorkStatement<E extends PositionExpression> extends ActionStatement<E>{
	
	public WorkStatement(E position){
		setExpression(position);
	}

	@Override
	public void executeStatement(Unit executingUnit) {
		executingUnit.work(getExpression().getValue().getCoords());
		
	}
	

}
