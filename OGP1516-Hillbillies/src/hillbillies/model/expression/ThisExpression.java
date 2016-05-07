package hillbillies.model.expression;

import hillbillies.model.Unit;

public class ThisExpression<E> extends UnitExpression<E>{
	
	public ThisExpression(){
		setValue(thisUnit());
	}

	private Unit thisUnit() {
		return this.getStatement().getTask().getExecutingUnit();
	}

	@Override
	public Unit evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

}
