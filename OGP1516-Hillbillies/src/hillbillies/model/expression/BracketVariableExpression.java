package hillbillies.model.expression;

public class BracketVariableExpression extends VariableExpression{
	public BracketVariableExpression(Expression v){
		setExpression(v);
		
	}
	
	/**
	 * @return the expression
	 */
	public Expression getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(Expression expression) {
		this.expression = expression;
	}

	private Expression expression;

	@Override
	public Object evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
