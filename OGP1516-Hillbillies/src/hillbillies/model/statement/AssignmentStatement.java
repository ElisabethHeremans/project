package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

public class AssignmentStatement extends BasicEStatement {
	public AssignmentStatement(String name , Expression expression){
		setName(name);
		setExpression(expression);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String name;

	public Expression getExpression() {
		return expression;
	}
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	private Expression expression;
}
