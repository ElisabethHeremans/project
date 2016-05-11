package hillbillies.model.expression;

import hillbillies.model.Unit;

public abstract class UnitExpression extends Expression {
	public UnitExpression(){
		
	}
	
	public void setValue(Unit object){
		value = object;
	}
	
	private Unit value;

	public Unit getValue() {
		return value;
	}
}
