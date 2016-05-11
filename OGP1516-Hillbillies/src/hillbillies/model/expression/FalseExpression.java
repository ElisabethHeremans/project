package hillbillies.model.expression;

import hillbillies.model.expression.BooleanExpression;

public class FalseExpression extends BooleanExpression {
	public FalseExpression(){
		setValue(false);
	}


	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return false;
	}
}
