package hillbillies.model.expression;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.Task;
import hillbillies.model.statement.ExpressionStatement;
import hillbillies.model.statement.Statement;
import hillbillies.part3.programs.SourceLocation;

public class Expression {
	
	public void setValue(Object object){
		value = object;
	}
	
	
	private Object value;

	/**
	 * @return the value
	 */
	private final Object getValue() {
		return value;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
		
	}
	
	private SourceLocation sourceLocation;
	
	public boolean isValidExpression(){
		return true;
	}
	
	public void setStatement(@Raw ExpressionStatement statement){
		if (statement.getExpression() == this)
			this.statement = statement;
	}
	
	@Basic @Raw
	public final ExpressionStatement getStatement() {
		return statement;
	}

	private ExpressionStatement statement;
	

}
