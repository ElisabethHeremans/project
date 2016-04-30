package hillbillies.model.expression;

import hillbillies.model.Unit;

public class PositionOfExpression extends ComposedPExpression {
//	public PositionOfExpression(PositionExpression position){
//		setExpression(position);
//		setValue(getPositionOfPosition());
//	}
	
	public PositionOfExpression(UnitExpression unit){
		setExpression(unit);
		setValue(getPositionOfUnit());

	}

	private int[] getPositionOfUnit() {
		return ((Unit) getExpression().getValue()).
				getWorld().getCubeCoordinate(((Unit) getExpression().getValue()).getPosition()) ;
	}

//	private int[] getPositionOfPosition() {
//		return (int[]) getExpression().getValue();
//	}

}
