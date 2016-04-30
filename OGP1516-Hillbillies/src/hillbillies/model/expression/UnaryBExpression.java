package hillbillies.model.expression;

public abstract class UnaryBExpression extends ComposedBExpression {
	public UnaryBExpression(){
	}
	

	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression e) {
		this.expression = e;
	}
	
	private Expression expression;

}
