package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Unit;
import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class IfElseStatement<E extends BooleanExpression, S extends ComposedStatement,T extends ComposedStatement>
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
	public void executeStatement(Unit executingUnit) {
		if (getExpression().getValue())
			getFirstStatement().executeStatement(executingUnit);
		else
			getSecondStatement().executeStatement(executingUnit);
		
	}

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

}
