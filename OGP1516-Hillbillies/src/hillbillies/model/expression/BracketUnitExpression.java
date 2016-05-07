package hillbillies.model.expression;

import hillbillies.model.Unit;

public class BracketUnitExpression<E extends UnitExpression<?>> extends UnitExpression<E> implements IComposedUnaryExpression<E>{
	public BracketUnitExpression(E e){
		setExpression(e);
		setValue(e.getValue());
	}
	
	public E getExpression() {
		return expression;
	}

	public void setExpression(E e) {
		this.expression = e;
	}
	
	private E expression;

	@Override
	public Unit evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
