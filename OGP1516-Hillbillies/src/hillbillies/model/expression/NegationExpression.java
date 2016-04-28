package hillbillies.model.expression;

public class NegationExpression extends BooleanBExpression {
	
	public NegationExpression(Expression expression){
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
