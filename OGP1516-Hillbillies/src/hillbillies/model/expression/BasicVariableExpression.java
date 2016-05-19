package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class BasicVariableExpression<V extends Object> extends VariableExpression<V> {
	public BasicVariableExpression(String variableName){
		setName(variableName);
	}

	private void setName(String variableName) {
		this.name = variableName;
		
	}
	
	public String getName(){
		return name;
	}
	
	private String name;

	@Override
	public V evaluateExpression(ExecutionContext context) {
		System.out.println(" this variable " + context.getVariables().get(getName()));
		setValue((V) context.getVariables().get(getName()).getValue());
		
		return getValue();
	}
	
	public Expression<?> getAssociatedExpression(ExecutionContext context){
		return context.getVariables().get(getName());
	}
	
	
}
