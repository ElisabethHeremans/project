package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class TrueExpression extends BooleanExpression {
	public TrueExpression(){
		setValue(true);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		
		return getValue();
	}

}
