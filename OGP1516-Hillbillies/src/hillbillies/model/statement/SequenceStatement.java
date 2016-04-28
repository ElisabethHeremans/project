package hillbillies.model.statement;

import java.util.ArrayList;
import java.util.List;

public class SequenceStatement extends ComposedStatement {
	
	public SequenceStatement(List<Statement> statements){
		setStatements(statements);
	}
	
	public List<Statement> getStatements() {
		return statements;
	}

	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	private List<Statement> statements = new ArrayList <>();
}
