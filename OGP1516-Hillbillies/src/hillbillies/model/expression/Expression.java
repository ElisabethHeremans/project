package hillbillies.model.expression;

import java.util.List;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Task;
import hillbillies.model.statement.ExpressionStatement;
import hillbillies.model.statement.Statement;
import hillbillies.part3.programs.SourceLocation;

public abstract class Expression<V>{
	
	public V getValue() {
		return value;
	}
	private V value;
	public void setValue(V value){
		this.value = value;
	}
	
	public abstract V evaluateExpression(ExecutionContext context);

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
		
	}
	
	public SourceLocation getSourceLocation(){
		return sourceLocation;
	}
	
	private SourceLocation sourceLocation;
	
	
	public void setStatement(Statement statement){
		if (((ExpressionStatement) statement).getExpression() == this)
			this.statement = statement;
	}
	
	@Basic @Raw
	public final Statement getStatement() {
		return statement;
	}

	private Statement statement;
	

}
