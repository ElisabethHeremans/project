package hillbillies.model.expression;

public abstract class BinaryBExpression extends ComposedBExpression  {
	
	public BinaryBExpression(BooleanExpression right, BooleanExpression left){
		setRightExpression(right);
		setLeftExpression(left);
				
	}
	
	
	public BooleanExpression getRightExpression(){
		return rightExp;
	}
	
	private BooleanExpression rightExp;
	

	public void setRightExpression(BooleanExpression rightExp) {
		this.rightExp = rightExp;
	}

	public BooleanExpression getLeftExpression(){
		return leftExp;
	}

	public void setLeftExpression(BooleanExpression leftExp) {
		this.leftExp = leftExp;
	}

	private BooleanExpression leftExp;
	

}
