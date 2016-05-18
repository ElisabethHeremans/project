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
//		if (expression.getValue() instanceof Boolean)
//			BooleanExpression expr = (BooleanExpression)expression;
//			setExpression(expr)
		setExpression(expression);
		System.out.println(getExpression());
		setVariableName(variableName);
		System.out.println(getVariableName());
//		BasicVariableExpression variable = new BasicVariableExpression(getVariableName());
//		setVariable(variable);
//		variable.setValue(expression.getValue());
	}
//	@Basic @Raw
//	public VariableExpression getVariable() {
//		return variable;
//	}
//	
//	@Raw
//	public void setVariable(VariableExpression name) {
//		this.variable = name;
//	}
//	
//	private VariableExpression variable;
	
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
		System.out.println(" execute assignment statemnet ");
		getExpression().evaluateExpression(context);
		//System.out.println(getExpression().getValue());
		//System.out.println(getVariableName());
		//System.out.println(getExpression());
		context.addVariable(getVariableName(), getExpression());
		System.out.println(" variables "+(context.getVariables()));
		context.getExecutingUnit().stopExecutingStatement();
//		Object value = getExpression().evaluateExpression(context);
//		if (value==Boolean.TRUE){
//			setExpression(new TrueExpression());
//		}
//		BasicVariableExpression variable = new BasicVariableExpression(getVariableName());
//		variable.setValue(getExpression().getValue());
	}
	
}
