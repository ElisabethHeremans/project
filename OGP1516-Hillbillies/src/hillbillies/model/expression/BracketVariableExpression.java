package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class BracketVariableExpression<V extends Object, E extends VariableExpression<?>> extends VariableExpression<V> implements IComposedUnaryExpression<E>{
	public BracketVariableExpression(E v){
		setExpression(v);	
	}


	@Override
	public V evaluateExpression(ExecutionContext context) {
		getExpression().evaluateExpression(context);
		setValue((V)getExpression().getValue());
		return getValue();
	}

	@Override
	public E getExpression() {
		return expression;
	}

	@Override
	public void setExpression(E e) {
		this.expression = e;
		
	}
	private E expression;
}
