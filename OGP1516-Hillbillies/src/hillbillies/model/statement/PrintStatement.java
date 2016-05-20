package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

public class PrintStatement<E extends Expression<?>> extends ExpressionStatement<E> {

	public PrintStatement(E e) {
		setExpression(e);
		//System.out.println(e);
	}

	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);
		getExpression().evaluateExpression(context);
		System.out.println(getExpression().toString());
		context.getExecutingUnit().stopExecutingStatement();
	}
	

}
