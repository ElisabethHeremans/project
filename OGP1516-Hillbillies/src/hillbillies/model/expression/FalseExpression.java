package hillbillies.model.expression;

public class FalseExpression extends BasicBExpression {
	public FalseExpression(){
		setValue(false);
	}

	@Override
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return false;
	}
}
