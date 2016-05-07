package hillbillies.model.expression.vuilbak;

import hillbillies.model.expression.Expression;
import hillbillies.model.expression.PositionExpression;

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
