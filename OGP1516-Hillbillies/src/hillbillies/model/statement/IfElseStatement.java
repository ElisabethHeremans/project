package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class IfElseStatement<E extends Expression<Boolean>, S extends Statement,T extends ComposedStatement>
extends ExpressionStatement<E> implements IComposedBinaryStatement<S,T>{
	
	public IfElseStatement(E condition, S thenBody, T elseBody){
		setExpression(condition);
		setFirstStatement(thenBody);
		setSecondStatement(elseBody);
	}
	
	public IfElseStatement(E condition, S thenBody){
		this(condition,thenBody,null);
	}
	
	public E getCondition() {
		return getExpression();
	}

	private T elseBody;
	

	private S thenBody;

	@Override
	public void setFirstStatement(S statement) {
		this.thenBody = statement;
		
	}

	@Override
	public S getFirstStatement() {
		// TODO Auto-generated method stub
		return thenBody;
	}

	@Override
	public void setSecondStatement(T statement) {
		this.elseBody = statement;
		
	}

	@Override
	public T getSecondStatement() {
		// TODO Auto-generated method stub
		return elseBody;
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		super.executeStatement(context);
		if (getExpression().getValue())
			getFirstStatement().executeStatement(context);
		else if (getSecondStatement()!= null)
			getSecondStatement().executeStatement(context);
		else
			context.getExecutingUnit().stopExecutingStatement();
	}

}
