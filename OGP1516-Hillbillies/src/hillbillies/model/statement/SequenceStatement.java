package hillbillies.model.statement;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;

public class SequenceStatement extends Statement {
	
	public SequenceStatement(List<Statement> statements){
		setStatements(statements);
	}
	@Basic @Raw
	public List<Statement> getStatements() {
		return statements;
	}
	@Basic
	public void setStatements(List<Statement> statements) {
		this.statements = statements;
	}

	private List<Statement> statements = new ArrayList <>();
}
