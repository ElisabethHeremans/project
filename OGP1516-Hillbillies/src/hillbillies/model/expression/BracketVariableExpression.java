package hillbillies.model.expression;

public class BracketVariableExpression<E extends VariableExpression<?>> extends VariableExpression<E> {
	public BracketVariableExpression(E v){
		setExpression(v);
		
	}
	
	/**
	 * @return the expression
	 */
	public E getExpression() {
		return expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(E expression) {
		this.expression = expression;
	}

	private E expression;

	@Override
	public Object evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
