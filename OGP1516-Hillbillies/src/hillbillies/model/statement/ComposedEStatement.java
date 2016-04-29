package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.expression.Expression;

public abstract class ComposedEStatement extends ExpressionStatement{

	public ComposedEStatement() {
	}
	
	@Basic @Raw
	public final Statement getStatement() {
		return statement;
	}

	@Raw
	public final void setStatement(Statement s) {
		this.statement = s;
	}
	
	
	private Statement statement;
	

}
