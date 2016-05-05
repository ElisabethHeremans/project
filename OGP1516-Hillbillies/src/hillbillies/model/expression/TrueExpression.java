package hillbillies.model.expression;

public class TrueExpression extends BasicBExpression {
	public TrueExpression(){
		setValue(true);
	}

	@Override
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return true;
	}

}
