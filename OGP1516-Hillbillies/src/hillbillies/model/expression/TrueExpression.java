package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class TrueExpression extends BooleanExpression {
	public TrueExpression(){
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(true);
		return getValue();
	}

}
