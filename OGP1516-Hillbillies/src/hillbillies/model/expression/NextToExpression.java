package hillbillies.model.expression;

import java.util.List;

public class NextToExpression extends ComposedPExpression {
	
	public NextToExpression(PositionExpression position){
		setExpression(position);
		setValue(getNextTo());
	}

	private int[] getNextTo() {
		int[] pos = (int[]) getExpression().getValue();
		List<int[]> neighbouringPositions = this.getStatement().getTask().getExecutingUnit().getWorld().getNeighboringCubes(pos);
		if (neighbouringPositions.isEmpty())
			return null;
		else
			return neighbouringPositions.get(0);
	}
	

}
