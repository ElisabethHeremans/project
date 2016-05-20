package hillbillies.model.expression;

import java.util.Comparator;
import java.util.Optional;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.Vector;

public class FriendExpression extends UnitExpression{
	
	public FriendExpression(){
		
	}
	
	private Unit findFriendUnitStream(Unit unit){

		final Comparator<Unit> comp = (u1, u2) -> Integer.compare( (int) Vector.getDistance(u1.getPosition(), unit.getPosition()), 
				(int) Vector.getDistance(u2.getPosition(), unit.getPosition()));
		
		Optional<Unit> nearest = unit.getWorld().listAllUnits().stream()
				.filter(n -> n.getFaction() == unit.getFaction() && n != unit)
				.min(comp);
		if(nearest.isPresent()){
			return nearest.get();
		}
		else 
			return null;
	}


	@Override
	public Unit evaluateExpression(ExecutionContext context) {
		setValue(findFriendUnitStream(context.getExecutingUnit()));
		return getValue();
	}
}
