package hillbillies.model.expression;

import hillbillies.model.expression.BooleanExpression;

public class FalseExpression<E> extends BooleanExpression<E> {
	public FalseExpression(){
		setValue(false);
	}

	@Override
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return false;
	}
}
