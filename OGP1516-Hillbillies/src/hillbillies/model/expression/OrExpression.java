package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class OrExpression<E extends Expression<Boolean>,F extends Expression<Boolean>> extends BinaryBooleanExpression<E,F> {
	public OrExpression(E left, F right) {
		super(left, right);
	}

	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		try{
		this.getLeftExpression().evaluateExpression(context);
		this.getRightExpression().evaluateExpression(context);

		setValue(getRightExpression().getValue() || getLeftExpression().getValue());
		return getValue();
		}
		catch(NullPointerException e){
			return null;
		}

	}

	


}
