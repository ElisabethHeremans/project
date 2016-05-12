package hillbillies.model.expression;

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


	public Object evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Expression get
	
	
}
