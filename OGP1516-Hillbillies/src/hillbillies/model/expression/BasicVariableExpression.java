package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class BasicVariableExpression extends VariableExpression {
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
	public Object evaluateExpression(ExecutionContext context) {
		return context.getVariables().get(getName()).getValue();
	}
	
	public Expression getAssociatedExpression(ExecutionContext context){
		return context.getVariables().get(getName());
	}
	
	
}
