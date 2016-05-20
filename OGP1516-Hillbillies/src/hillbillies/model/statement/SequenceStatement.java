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
		for (E statement: statements){
			//System.out.println(statement);
			statement.setSuperStatement(this);
		}
		this.statements = statements;
	}

	private List<E> statements = new ArrayList <>();

	@Override
	public void executeStatement(ExecutionContext context) {
		System.out.println(" execute sequence statement ");
		
		context.getExecutingUnit().setCurrentStatement(this);
		for (int index = 0 ; index < getStatements().size() ; index++){
			getStatements().get(index).setIndex(index);
			getStatements().get(index).setLast(false);
		}
		this.getStatements().get(getStatements().size()-1).setLast(true);
		//context.getExecutingUnit().setLastStatementInSequence(getStatements().get(getStatements().size()-1));
		getStatements().get(0).executeStatement(context);
		
	}
	
	
	public void addStatement(Statement statement){
		List<E> list = getStatements();
		if (statement instanceof SequenceStatement){
			for (Statement stat: ((SequenceStatement<?>)statement).getStatements())
				list.add((E) stat);
		}
		else
			list.add((E) statement);
		setStatements(list);
		
	}
	
//	public Statement removeFirstStatement() {
//		E first = (E)getStatements().get(0);
//		//System.out.println(E);
//		System.out.println(getStatements());
//		List<E> statements = getStatements();
//		statements.remove(0);
//		setStatements(statements);
//		if (getStatements().size()==0){
//			setStatementExecuted(true);
//		}
//		return first;
//	}
	


}
