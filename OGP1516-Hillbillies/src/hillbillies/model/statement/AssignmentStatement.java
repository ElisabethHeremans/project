package hillbillies.model.statement;


import java.util.Arrays;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.BasicVariableExpression;
import hillbillies.model.expression.*;
import hillbillies.model.expression.VariableExpression;

public class AssignmentStatement<E extends Expression<?>> extends ExpressionStatement<E> {
	public AssignmentStatement(String variableName , E expression){
		setExpression(expression);
		setVariableName(variableName);
	}
	
	private String variableName;
	@Basic @Raw
	public String getVariableName() {
		return variableName;
	}
	@Raw
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);
		getExpression().evaluateExpression(context);
		context.addVariable(getVariableName(), getExpression());
		context.getExecutingUnit().stopExecutingStatement();
	}

	
}
