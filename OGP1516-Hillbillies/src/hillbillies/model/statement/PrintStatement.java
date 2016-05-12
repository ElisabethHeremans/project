package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

public class PrintStatement<E extends Expression> extends ExpressionStatement<E> {

	public PrintStatement(E e) {
		setExpression(e);
	}

	@Override
	public void executeStatement(ExecutionContext context) {
		System.out.println(getExpression().toString());
		
	}
	
	

}
