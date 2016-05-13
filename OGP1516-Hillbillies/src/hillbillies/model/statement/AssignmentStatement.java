package hillbillies.model.statement;


import java.util.List;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.BasicVariableExpression;
import hillbillies.model.expression.*;
import hillbillies.model.expression.VariableExpression;

public class AssignmentStatement<E extends Expression> extends ExpressionStatement<E> {
	public AssignmentStatement(String variableName , E expression){
//		if (expression.getValue() instanceof Boolean)
//			BooleanExpression expr = (BooleanExpression)expression;
//			setExpression(expr)
		setExpression(expression);
//		BasicVariableExpression variable = new BasicVariableExpression(getVariableName());
//		setVariable(variable);
//		variable.setValue(expression.getValue());
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
	public void executeStatement(ExecutionContext context) {
		context.addVariable(getVariableName(), getExpression());
		
//		Object value = getExpression().evaluateExpression(context);
//		if (value==Boolean.TRUE){
//			setExpression(new TrueExpression());
//		}
//		BasicVariableExpression variable = new BasicVariableExpression(getVariableName());
//		variable.setValue(getExpression().getValue());
	}
	
}
