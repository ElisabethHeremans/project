package hillbillies.model.expression;

public abstract class VariableExpression extends Expression {
	
	public void setValue(Object object){
		value = object;
	}
	
	private Object value;
	
	@Override
	public final Object getValue() {
		return value;
	}
	
}
