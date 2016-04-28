package hillbillies.model.expression;

public class XYZExpression extends BasicPExpression {
	
	public XYZExpression(int x, int y, int z){
		setX(x);
		setY(y);
		setZ(z);
	}
	
	
	
	public int getX() {
		return x;
	}
	private void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	private void setY(int y) {
		this.y = y;
	}
	public int getZ() {
		return z;
	}
	private void setZ(int z) {
		this.z = z;
	}



	private int x;
	private int y;
	private int z;

}
