package hillbillies.model.expression;

public class BracketVariableExpression extends VariableExpression {
	public BracketVariableExpression(VariableExpression v){
		setExpression(v);
		
	}
	
	/**
	 * @return the expression
	 */
	public VariableExpression getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(VariableExpression expression) {
		this.expression = expression;
	}

	private VariableExpression expression;
}
