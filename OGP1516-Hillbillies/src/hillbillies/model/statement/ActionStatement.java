package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

public class ActionStatement extends BasicEStatement {

	public ActionStatement(Expression expression){
		setExpression(expression);
	}
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;
}
