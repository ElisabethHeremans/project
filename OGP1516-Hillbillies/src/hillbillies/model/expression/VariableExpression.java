package hillbillies.model.expression;

public abstract class VariableExpression<V extends Object> extends Expression<V> {
	
	public void setValue(V object){
		value = object;
	}
	
	private V value;
	
	@Override
	public final V getValue() {
		return value;
	}
	
}
