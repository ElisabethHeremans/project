package hillbillies.model.statement;

import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class IfElseStatement extends ComposedEStatement {
	
	public IfElseStatement(BooleanExpression condition, Statement thenBody, Statement elseBody){
		setExpression(condition);
		setStatement(thenBody);
		setElseBody(elseBody);
	}
	
	public IfElseStatement(BooleanExpression condition, Statement thenBody){
		this(condition, thenBody, new BreakStatement());
	}
	
	public Expression getCondition() {
		return getExpression();
	}
	//mag je casten naar een boolean? en dan BooleanExpression teruggeven?
	
//	public void setCondition(BooleanExpression condition) {
//		this.condition = condition;
//	}
	public Statement getThenBody() {
		return getStatement();
	}
//	public void setIfBody(Statement ifBody) {
//		this.ifBody = ifBody;
//	}
	public Statement getElseBody() {
		return elseBody;
	}
	public void setElseBody(Statement elseBody) {
		this.elseBody = elseBody;
	}
//
//	private BooleanExpression condition;
//	private Statement ifBody;
	private Statement elseBody;

}
