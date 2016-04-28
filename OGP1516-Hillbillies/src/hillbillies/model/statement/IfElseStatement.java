package hillbillies.model.statement;

import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class IfElseStatement extends ComposedEStatement {
	
	public IfElseStatement(BooleanExpression condition, Statement ifBody, Statement elseBody){
		setCondition(condition);
		setIfBody(ifBody);
		setElseBody(elseBody);
	}
	
	public BooleanExpression getCondition() {
		return condition;
	}
	public void setCondition(BooleanExpression condition) {
		this.condition = condition;
	}
	public Statement getIfBody() {
		return ifBody;
	}
	public void setIfBody(Statement ifBody) {
		this.ifBody = ifBody;
	}
	public Statement getElseBody() {
		return elseBody;
	}
	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}

	private BooleanExpression condition;
	private Statement ifBody;
	private Statement elseBody;

}
