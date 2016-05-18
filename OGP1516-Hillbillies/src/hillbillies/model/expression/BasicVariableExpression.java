package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class BasicVariableExpression<V extends Object> extends VariableExpression<V> {
	public BasicVariableExpression(String variableName){
		setName(variableName);
		//setValue in AssignmentStatement gedaan
		//?? setVariable(variable);
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
		setValue((V) context.getVariables().get(getName()).getValue());
		return getValue();
	}
	
	public Expression<?> getAssociatedExpression(ExecutionContext context){
		return context.getVariables().get(getName());
	}
	
	
}
