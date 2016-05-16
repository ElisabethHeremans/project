package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.expression.BooleanExpression;

public class FalseExpression extends BooleanExpression {
	public FalseExpression(){
		setValue(false);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		
		return getValue();
	}
}
