package hillbillies.model.statement;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class SequenceStatement<E extends ComposedStatement> extends ComposedStatement  
	implements ISequenceStatement<E>{
	
	public SequenceStatement(List<E> statements){
		setStatements(statements);
	}
	@Basic @Raw
	public List<E> getStatements() {
		return statements;
	}
	@Basic @Override
	public void setStatements(List<E> statements) {
		this.statements = statements;
	}

	private List<E> statements = new ArrayList <>();

	@Override
	public void executeStatement(ExecutionContext context) {
		getStatements().get(0).executeStatement(context);
		
		
	}
	
	public void removeFirstStatement() {
		List<E> statements = (List<E>) getStatements().remove(0);
		setStatements(statements);
		if (getStatements().size()==0){
			setStatementExecuted(true);
		}
	}
	
	public void stopExecutingStatement() {
		// TODO Auto-generated method stub
		
	}


}
