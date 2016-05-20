package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;

public class PositionOfExpression<E extends Expression<Unit>> extends UnaryPositionExpression<E> {

	public PositionOfExpression(E unit){
		setExpression(unit);
	}

	private int[] getPositionOfUnit() {
		return (getExpression().getValue().
				getWorld().getCubeCoordinate(getExpression().getValue().getPosition())) ;
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		try{
		getExpression().evaluateExpression(context);
		setValue(new Position(getPositionOfUnit()));
		return getValue();
		}
		catch(NullPointerException e){
			return null;
		}

	}

}
