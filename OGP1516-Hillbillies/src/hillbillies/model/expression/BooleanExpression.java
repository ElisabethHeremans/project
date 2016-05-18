package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public abstract class BooleanExpression extends Expression<Boolean> {
	
	
	public BooleanExpression(){
		
	}
	
	public void setValue(boolean object){
		value = object;
	}
	
	private boolean value;
	
	@Override
	public final Boolean getValue() {
		return value;
	}
	
	@Override
	public abstract Boolean evaluateExpression(ExecutionContext context);

}
