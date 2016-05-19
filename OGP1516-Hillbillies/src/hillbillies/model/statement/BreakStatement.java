package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class BreakStatement extends Statement {
	public BreakStatement(){
		
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);

		context.getExecutingUnit().stopExecutingStatement();

		
	}
}
