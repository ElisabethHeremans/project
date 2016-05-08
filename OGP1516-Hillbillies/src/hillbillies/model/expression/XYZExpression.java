package hillbillies.model.expression;

import hillbillies.model.Position;

public class XYZExpression extends PositionExpression<Position> {
	
//	public XYZExpression(int x, int y, int z){
//		setValue(Position(new int[] {x,y,z}));
//	
//	}
	
	public XYZExpression(Position pos){
		setValue(pos);
	}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
//	public int getX() {
//		return x;
//	}
//	private void setX(int x) {
//		this.x = x;
//	}
//	public int getY() {
//		return y;
//	}
//	private void setY(int y) {
//		this.y = y;
//	}
//	public int getZ() {
//		return z;
//	}
//	private void setZ(int z) {
//		this.z = z;
//	}
//
//
//
//	private int x;
//	private int y;
//	private int z;

}
