package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.expression.vuilbak.ComposedPExpression;

public class PositionOfExpression<E extends UnitExpression> extends UnaryPositionExpression<E> {

	public PositionOfExpression(E unit){
		setExpression(unit);
	}

	private int[] getPositionOfUnit() {
		return (getExpression().getValue().
				getWorld().getCubeCoordinate(getExpression().getValue().getPosition())) ;
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		setValue(new Position(getPositionOfUnit()));
		return getValue();
	}

}
