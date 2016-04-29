package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

public class WhileStatement extends ComposedEStatement {
	
	public WhileStatement(Expression condition, Statement body){
		setExpression(condition);
		setStatement(body);
	}
	
	public Expression getCondition() {
		return getExpression();
	}
//	public void setConditie(Expression conditie) {
//		this.conditie = conditie;
//	}
	public Statement getBody() {
		return getStatement();
	}
//	public void setBody(Statement body) {
//		this.body = body;
//	}
//
//	private Expression conditie;
//	private Statement body;
}
