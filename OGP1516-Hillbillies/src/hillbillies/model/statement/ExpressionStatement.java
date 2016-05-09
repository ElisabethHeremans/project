package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.expression.Expression;

public abstract class ExpressionStatement<E extends Expression<?,?>, S > 
extends ComposedStatement<E, S> implements IExpressionStatement<E>{
	
	
	public ExpressionStatement(){
		//setExpression(e);
	}
	

	
	/**
	 * @return the expression
	 */
	@Basic @Raw
	public final E getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	@Raw
	public final void setExpression(E expression) {
		this.expression = expression;
	}
	
	//public abstract boolean isValidExpression(Expression e);

	private E expression;
}
