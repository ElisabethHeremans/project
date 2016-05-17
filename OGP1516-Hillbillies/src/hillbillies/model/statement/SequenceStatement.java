package hillbillies.model.statement;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class SequenceStatement<E extends Statement> extends ComposedStatement  
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
	
	public void addStatement(E statement){
		List<E> list = getStatements();
		if (statement instanceof SequenceStatement){
			for (Statement stat: ((SequenceStatement<E>)statement).getStatements())
				list.add((E) stat);
		}
		else
			list.add(statement);
		setStatements(list);
		
	}
	
	public Statement removeFirstStatement() {
		Statement first = (Statement)getStatements().get(0);
		List<E> statements = (List<E>) getStatements().remove(0);
		setStatements(statements);
		if (getStatements().size()==0){
			setStatementExecuted(true);
		}
		return first;
	}
	


}
