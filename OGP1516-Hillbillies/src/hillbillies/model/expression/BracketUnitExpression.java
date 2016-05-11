package hillbillies.model.expression;

import hillbillies.model.Unit;
import hillbillies.model.types.UnitType;

public class BracketUnitExpression<E extends UnitExpression> extends UnitExpression implements IComposedUnaryExpression<E>{
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


	public Unit evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
