package hillbillies.model.expression;

import hillbillies.model.Unit;

public class IsEnemyExpression extends UnitBExpression {

	public IsEnemyExpression(UnitExpression e) {
		super(e);
		Unit thisUnit = this.getStatement().getTask().getExecutingUnit();
		Unit other = (Unit) e.getValue();
		setValue(thisUnit.getFaction() != other.getFaction());

	}
	

}
