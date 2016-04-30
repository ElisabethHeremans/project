package hillbillies.model.expression;

public class BasicVExpression extends VariableExpression {
	public BasicVExpression(String variableName){
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
	
//??? is x een object of gewoon niets?	
	
//	/**
//	 * @return the variable
//	 */
//	public Object getVariable() {
//		return variable;
//	}
//
//
//
//	/**
//	 * @param variable the variable to set
//	 */
//	public void setVariable(Object variable) {
//		this.variable = variable;
//	}
//
//
//
//	private Object variable;
	
	
}
