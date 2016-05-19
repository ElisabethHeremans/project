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
		System.out.println(getExpression());
		setVariableName(variableName);
		System.out.println(getVariableName());
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
		System.out.println(" execute assignment statemnet ");
		getExpression().evaluateExpression(context);
		System.out.println(getExpression().getValue());
		//System.out.println(getVariableName());
		//System.out.println(getExpression());
		context.addVariable(getVariableName(), getExpression());
		System.out.println(" variables "+(context.getVariables()));
		context.getExecutingUnit().stopExecutingStatement();
	}

	
}
