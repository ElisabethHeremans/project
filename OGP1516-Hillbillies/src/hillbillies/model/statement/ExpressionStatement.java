package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.Expression;

public abstract class ExpressionStatement extends Statement {
	
	
	public ExpressionStatement(){
		//setExpression(e);
	}
	
	/**
	 * @return the expression
	 */
	@Basic @Raw
	public final Expression getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	@Raw
	public final void setExpression(Expression expression) {
		this.expression = expression;
	}
	
	//public abstract boolean isValidExpression(Expression e);

	private Expression expression;
}
