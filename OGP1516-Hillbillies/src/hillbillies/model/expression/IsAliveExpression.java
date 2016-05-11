package hillbillies.model.expression;

import hillbillies.model.Unit;

public class IsAliveExpression<E extends UnitExpression> extends UnaryBooleanExpression<E> {

	public IsAliveExpression(E e) {
		setExpression(e);
		Unit thisUnit = this.getStatement().getTask().getExecutingUnit();
		setValue(!thisUnit.isTerminated());

	}
	

	
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
