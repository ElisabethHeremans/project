package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.BasicVariableExpression;
import hillbillies.model.expression.Expression;
import hillbillies.model.expression.VariableExpression;

public class AssignmentStatement<E extends Expression<?,?>,S> extends ExpressionStatement<E,S> {
	public AssignmentStatement(String variableName , E expression){
		setExpression(expression);
		BasicVariableExpression<E> variable = new BasicVariableExpression<E>(variableName);
		setVariable(variable);
		variable.setValue(expression.getValue());
	}
	@Basic @Raw
	public VariableExpression<E> getVariable() {
		return variable;
	}
	
	@Raw
	public void setVariable(VariableExpression<E> name) {
		this.variable = name;
	}
	
	private VariableExpression<E> variable;
	
	

	
}
