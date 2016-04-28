package hillbillies.model.statement;

import hillbillies.model.expression.Expression;

public class WhileStatement extends ComposedStatement {
	
	public WhileStatement( Expression conditie, Statement body){
		setConditie(conditie);
		setBody(body);
	}
	
	public Expression getConditie() {
		return conditie;
	}
	public void setConditie(Expression conditie) {
		this.conditie = conditie;
	}
	public Statement getBody() {
		return body;
	}
	public void setBody(Statement body) {
		this.body = body;
	}

	private Expression conditie;
	private Statement body;
}
