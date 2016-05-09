package hillbillies.model.statement;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;

public class SequenceStatement<E, S extends ComposedStatement<?, ?>> extends ComposedStatement<E, S> {
	
	public SequenceStatement(List<S> statements){
		setStatements(statements);
	}
	@Basic @Raw
	public List<S> getStatements() {
		return statements;
	}
	@Basic
	public void setStatements(List<S> statements) {
		this.statements = statements;
	}

	private List<S> statements = new ArrayList <>();
}
