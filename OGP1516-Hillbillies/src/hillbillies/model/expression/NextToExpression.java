package hillbillies.model.expression;

import java.util.List;

import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.ComposedPExpression;

public class NextToExpression<E extends PositionExpression> extends UnaryPositionExpression<E> {
	
	public NextToExpression(E position){
		setExpression(position);
		setValue(getNextTo());
	}

	private Position getNextTo() {
		int[] pos = getExpression().getValue().getCoords();
		List<int[]> neighbouringPositions = this.getStatement().getTask().getExecutingUnit().getWorld().getNeighboringCubes(pos);
		if (neighbouringPositions.isEmpty())
			return null;
		else
			return new Position(neighbouringPositions.get(0));
	}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
