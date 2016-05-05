package hillbillies.model.expression;

import hillbillies.model.Unit;

public class IsAliveExpression extends UnitBExpression {

	public IsAliveExpression(UnitExpression e) {
		super(e);
		Unit thisUnit = this.getStatement().getTask().getExecutingUnit();
		setValue(!thisUnit.isTerminated());

	}
	
	public boolean evaluateExpression(Unit unit){
		return !unit.isTerminated();
	}

}
