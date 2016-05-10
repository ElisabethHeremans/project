package hillbillies.model.expression;

import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.expression.vuilbak.ComposedPExpression;

public class PositionOfExpression<E extends UnitExpression> extends UnaryPositionExpression<E> {

	public PositionOfExpression(E unit){
		setExpression(unit);
		setValue(new Position(getPositionOfUnit()));

	}

	private int[] getPositionOfUnit() {
		return (((Unit) getExpression().getValue()).
				getWorld().getCubeCoordinate(((Unit) getExpression().getValue()).getPosition())) ;
	}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

//	private int[] getPositionOfPosition() {
//		return (int[]) getExpression().getValue();
//	}

}
