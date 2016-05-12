package hillbillies.model.expression;

import hillbillies.model.ExecutionContext;

public class BracketVariableExpression extends VariableExpression{
	public BracketVariableExpression(Expression v){
		setExpression(v);	
	}

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;

	@Override
	public Object evaluateExpression(ExecutionContext context) {
		setValue(getExpression().getValue());
		return getValue();
	}
}
