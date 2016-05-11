package hillbillies.model.expression;

import hillbillies.model.Unit;

public class IsFriendExpression<E extends UnitExpression> extends UnaryBooleanExpression<E> {

	public IsFriendExpression(E e) {
		setExpression(e);
		Unit thisUnit = this.getStatement().getTask().getExecutingUnit();
		Unit other = (Unit) e.getValue();
		setValue(thisUnit.getFaction() == other.getFaction());
	}

	
	public Boolean evaluateExpression() {
		Unit thisUnit = this.getStatement().getTask().getExecutingUnit();
		Unit other = (Unit) getExpression().getValue();
		return (thisUnit.getFaction() == other.getFaction());
	}
	

}
