package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Unit;
import hillbillies.model.expression.BasicVariableExpression;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.VariableExpression;

public class AssignmentStatement<E extends Expression> extends ExpressionStatement<E> {
	public AssignmentStatement(String variableName , E expression){
		setExpression(expression);
		
		setVariable(variable);
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
	
	private String variableName;

	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	@Override
	public void executeStatement(Unit executingUnit) {
		BasicVariableExpression variable = new BasicVariableExpression(getVariableName());
		variable.setValue(getExpression().getValue());
	}
	
	

	
}
