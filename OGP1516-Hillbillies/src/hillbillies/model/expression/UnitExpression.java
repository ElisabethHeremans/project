package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public abstract class UnitExpression extends Expression<Unit> {
	public UnitExpression(){
		
	}
	
	public void setValue(Unit object){
		value = object;
	}
	
	private Unit value;

	public Unit getValue() {
		return value;
	}
	@Override
	public abstract Unit evaluateExpression(ExecutionContext context);
}
