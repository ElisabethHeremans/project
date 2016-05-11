package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class IfElseStatement<E extends BooleanExpression, S extends ComposedStatement>
extends ExpressionStatement<E> implements IComposedStatement<S>{
	
	public IfElseStatement(E condition, S thenBody, S elseBody){
		setExpression(condition);
		setStatement(thenBody);
		setElseBody(elseBody);
	}
	
	public IfElseStatement(E condition, S thenBody){
		setExpression(condition);
		setStatement(thenBody);
	}
	
	public E getCondition() {
		return getExpression();
	}
	//mag je casten naar een boolean? en dan BooleanExpression teruggeven?
	
//	public void setCondition(BooleanExpression condition) {
//		this.condition = condition;
//	}
	public S getThenBody() {
		return getStatement();
	}
//	public void setIfBody(Statement ifBody) {
//		this.ifBody = ifBody;
//	}
	public S getElseBody() {
		return elseBody;
	}
	public void setElseBody(S elseBody) {
		this.elseBody = elseBody;
	}
//
//	private BooleanExpression condition;
//	private Statement ifBody;
	private S elseBody;
	
	@Basic @Raw @Override
	public final S getStatement() {
		return statement;
	}

	@Raw @Override
	public final void setStatement(S statement) {
		this.statement = statement;
	}
	

	private S statement;

}
