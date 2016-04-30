package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.VariableExpression;

public class AssignmentStatement extends BasicEStatement {
	public AssignmentStatement(VariableExpression variable , Expression expression){
		setExpression(expression);
		setVariable(variable);
		//moet je dat hier ook toekennen?
		variable.setValue(expression.getValue());
	}
	@Basic @Raw
	public VariableExpression getVariable() {
		return variable;
	}
	
	@Raw
	public void setVariable(VariableExpression name) {
		this.variable = name;
	}
	
	private VariableExpression variable;
	
	

	
}
