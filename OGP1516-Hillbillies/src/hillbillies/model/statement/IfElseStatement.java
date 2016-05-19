package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class IfElseStatement<E extends Expression<Boolean>, S extends Statement,T extends Statement>
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
		statement.setSuperStatement(this);
		this.thenBody = statement;
		
	}

	@Override
	public S getFirstStatement() {
		
		return thenBody;
	}

	@Override
	public void setSecondStatement(T statement) {
		if (statement != null)
			statement.setSuperStatement(this);
		this.elseBody = statement;
		
	}

	@Override
	public T getSecondStatement() {
		// TODO Auto-generated method stub
		return elseBody;
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);
		System.out.println(" executing if else ");
		getExpression().evaluateExpression(context);
		System.out.println(getExpression().getValue());
		if (getExpression().getValue()){
			getFirstStatement().executeStatement(context);
			System.out.println("  if  ");
		}
		else if (getSecondStatement()!= null){
			getSecondStatement().executeStatement(context);
			System.out.println(" if else bla");
			System.out.println(getSecondStatement());
		}
		else{
			context.getExecutingUnit().stopExecutingStatement();
			System.out.println(" if else bla2");
		}
	}

}
