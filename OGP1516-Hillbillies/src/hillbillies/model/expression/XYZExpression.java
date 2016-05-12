package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;

public class XYZExpression extends PositionExpression {
	
//	public XYZExpression(int x, int y, int z){
//		setValue(Position(new int[] {x,y,z}));
//	
//	}
	
	public XYZExpression(Position pos){
		setValue(pos);
	}

	@Override
	public Position evaluateExpression(ExecutionContext context) {
		return getValue();
	}


}
