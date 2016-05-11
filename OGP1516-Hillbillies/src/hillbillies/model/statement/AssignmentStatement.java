package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.BasicVariableExpression;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.VariableExpression;

public class AssignmentStatement<E extends Expression> extends ExpressionStatement<E> {
	public AssignmentStatement(String variableName , E expression){
		setExpression(expression);
		BasicVariableExpression variable = new BasicVariableExpression(variableName);
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
	
	

	
}
