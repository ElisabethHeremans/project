package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class BracketUnitExpression<E extends Expression<Unit>> extends UnitExpression implements IComposedUnaryExpression<E>{
	public BracketUnitExpression(E e){
		setExpression(e);
	}
	
	public E getExpression() {
		return expression;
	}

	public void setExpression(E e) {
		this.expression = e;
	}
	
	private E expression;

	@Override
	public Unit evaluateExpression(ExecutionContext context) {
		setValue(getExpression().getValue());
		return getValue();
	}
}
