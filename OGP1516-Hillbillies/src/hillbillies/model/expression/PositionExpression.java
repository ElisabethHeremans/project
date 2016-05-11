package hillbillies.model.expression;

import hillbillies.model.Position;

public abstract class PositionExpression extends Expression {
	public PositionExpression(){
		
	}
	
	public void setValue(Position object){
		value = object;
	}
	
	private Position value;

	public Position getValue() {
		return value;
	}
}
