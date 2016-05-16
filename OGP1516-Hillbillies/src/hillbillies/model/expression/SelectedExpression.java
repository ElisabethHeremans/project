package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;

public class SelectedExpression extends PositionExpression {
	public SelectedExpression(){
		
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		setValue(new Position(context.getSelectedCube()));
		return getValue();
	}
}
