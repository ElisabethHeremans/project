package hillbillies.model.expression;


import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class HereExpression extends PositionExpression {
	public HereExpression(){
		
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		Position here = new Position(context.getExecutingUnit().getCubeCoordinate());
		setValue(here);
		return getValue();
	}
}
