package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;

public class XYZExpression extends PositionExpression {

	
	public XYZExpression(Position pos){
		setValue(pos);
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		return getValue();
	}


}
