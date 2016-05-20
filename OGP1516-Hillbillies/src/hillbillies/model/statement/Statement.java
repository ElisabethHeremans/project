package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

public abstract class Statement{

	public abstract void executeStatement(ExecutionContext context);
	
	/**
	 * @return the superStatement
	 */
	public Statement getSuperStatement() {
		return superStatement;
	}

	/**
	 * @param superStatement the superStatement to set
	 */
	public void setSuperStatement(Statement superStatement) {
		this.superStatement = superStatement;
	}

	private Statement superStatement;
	
	
	public Statement getNextStatement(ExecutionContext context){
		if (! context.isBroken() &&!isLast() && this.getSuperStatement() != null){
			return (Statement) ((SequenceStatement<?>)getSuperStatement()).getStatements().get(this.getIndex()+1);
		}
		if (this.getSuperStatement() != null){
			return getSuperStatement().getNextStatement(context);
		}
		else
			return null;
	
	}


//	public abstract void removeFirstStatement();
//	
//	public abstract void stopExecutingStatement();
	
	private boolean statementExecuted = false;

	/**
	 * @return the statementExecuted
	 */
	public boolean isStatementExecuted() {
		return statementExecuted;
	}

	/**
	 * @param statementExecuted the statementExecuted to set
	 */
	public void setStatementExecuted(boolean statementExecuted) {
		this.statementExecuted = statementExecuted;
	}
	
	private int index;
	
	private boolean isLast = true;

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the isLast
	 */
	public boolean isLast() {
		return isLast;
	}

	/**
	 * @param isLast the isLast to set
	 */
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}


}
