package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;

public abstract class PositionExpression extends Expression<Position> {
	public PositionExpression(){
		
	}
	
	public void setValue(Position object){
		value = object;
	}
	
	private Position value;

	public Position getValue() {
		return value;
	}
	
	@Override
	public abstract Position evaluateExpression(ExecutionContext context);
}
