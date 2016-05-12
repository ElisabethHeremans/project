package hillbillies.model.expression;

import java.util.List;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;

public class NextToExpression<E extends PositionExpression> extends UnaryPositionExpression<E> {
	
	public NextToExpression(E position){
		setExpression(position);
	}

	private Position getNextTo(Unit unit) {
		int[] pos = getExpression().getValue().getCoords();
		List<int[]> neighbouringPositions = unit.getWorld().getNeighboringCubes(pos);
		if (neighbouringPositions.isEmpty())
			return null;
		else
			return new Position(neighbouringPositions.get(0));
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		setValue(getNextTo(context.getExecutingUnit()));
		return getValue();
	}
	

}
