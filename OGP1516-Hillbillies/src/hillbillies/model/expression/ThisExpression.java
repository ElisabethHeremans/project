package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class ThisExpression extends UnitExpression{
	
	public ThisExpression(){
	}

	@Override
	public Unit evaluateExpression(ExecutionContext context) {
		setValue(context.getExecutingUnit());
		return getValue();
	}

}
