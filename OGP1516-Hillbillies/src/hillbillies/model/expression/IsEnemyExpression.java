package hillbillies.model.expression;

import hillbillies.model.Unit;

public class IsEnemyExpression<E extends UnitExpression> extends UnaryBooleanExpression<E> {

	public IsEnemyExpression(E e) {
		setExpression(e);
		Unit thisUnit = this.getStatement().getTask().getExecutingUnit();
		Unit other = (Unit) e.getValue();
		setValue(thisUnit.getFaction() != other.getFaction());

	}
	

	
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
