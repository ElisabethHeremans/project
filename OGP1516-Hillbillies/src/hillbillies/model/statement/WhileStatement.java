package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class WhileStatement<E extends BooleanExpression<?>, S extends Statement<?,?>>
extends ExpressionStatement<E,S> {
	
	public WhileStatement(E condition, S body){
		setExpression(condition);
		setStatement(body);
	}
	
	public E getCondition() {
		return getExpression();
	}
//	public void setConditie(Expression conditie) {
//		this.conditie = conditie;
//	}
	public S getBody() {
		return getStatement();
		
	}
	
	@Basic @Raw
	public final S getStatement() {
		return statement;
	}

	@Raw
	public final void setStatement(S statement) {
		this.statement = statement;
	}
	

	private S statement;
//	public void setBody(Statement body) {
//		this.body = body;
//	}
//
//	private Expression conditie;
//	private Statement body;
}
