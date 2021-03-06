package hillbillies.model.expression;

import java.util.List;
import java.util.Random;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;

public class NextToExpression<E extends Expression<Position>> extends UnaryPositionExpression<E> {
	
	public NextToExpression(E position){
		setExpression(position);
	}

	private Position getNextTo(ExecutionContext context) {
		getExpression().evaluateExpression(context);
		int[] pos = getExpression().getValue().getCoords();
		List<int[]> neighbouringPositions = context.getExecutingUnit().getWorld().getNeighboringCubes(pos);
		if (neighbouringPositions.isEmpty())
			return null;
		int i = new Random().nextInt(neighbouringPositions.size());
		return new Position(neighbouringPositions.get(i));
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		try{
		setValue(getNextTo(context));
		return getValue();
		}
		catch(NullPointerException e){
			return null;
		}

	}
	

}
