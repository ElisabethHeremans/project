package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.Expression;

public abstract class ExpressionStatement<E extends Expression > 
extends ComposedStatement implements IExpressionStatement<E>{
	
	
	public ExpressionStatement(){
		//setExpression(e);
	}
	

	
	/**
	 * @return the expression
	 */
	@Basic @Raw @Override
	public final E getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	@Raw @Override
	public final void setExpression(E expression) {
		this.expression = expression;
	}
	

	private E expression;
	
	
}
