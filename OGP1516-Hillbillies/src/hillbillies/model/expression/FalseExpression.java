package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.expression.BooleanExpression;

public class FalseExpression extends BooleanExpression {
	public FalseExpression(){
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		setValue(false);
		return getValue();
	}
}
