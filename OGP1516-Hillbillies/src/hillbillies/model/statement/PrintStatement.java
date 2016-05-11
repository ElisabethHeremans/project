package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

public class PrintStatement<E extends Expression> extends ExpressionStatement<E> {

	public PrintStatement(E e) {
		setExpression(e);
	}
	
	

}
