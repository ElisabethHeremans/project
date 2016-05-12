package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.BooleanExpression;

public class WhileStatement<E extends BooleanExpression, S extends Statement>
extends ExpressionStatement<E> implements IComposedUnaryStatement<S> {
	
	public WhileStatement(E condition, S body){
		setExpression(condition);
		setStatement(body);
	}
	
	public E getCondition() {
		return getExpression();
	}

	public S getBody() {
		return getStatement();
		
	}
	
	@Basic @Raw @Override
	public final S getStatement() {
		return statement;
	}

	@Raw @Override
	public final void setStatement(S statement) {
		this.statement = statement;
	}
	

	private S statement;


	@Override
	public void executeStatement(ExecutionContext context) {
		while (getExpression().getValue()){
			getStatement().executeStatement(context);
		}
			
	}
}
