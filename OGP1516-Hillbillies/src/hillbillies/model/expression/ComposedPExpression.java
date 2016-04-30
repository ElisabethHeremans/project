package hillbillies.model.expression;

public abstract class ComposedPExpression extends PositionExpression {
	public ComposedPExpression(){
		
	}
	
	public Expression getExpression() {
		return expression;
	}

	public void setExpression(Expression e) {
		this.expression = e;
	}
	
	private Expression expression;
}
