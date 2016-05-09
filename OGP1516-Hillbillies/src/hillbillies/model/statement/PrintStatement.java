package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

public class PrintStatement<E extends Expression<?,?>,S> extends ExpressionStatement<E, S> {

	public PrintStatement(E e) {
		setExpression(e);
	}
	
	

}
