package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class BreakStatement extends Statement {
	public BreakStatement(){
		
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		System.out.println("break uitgevoerd");
		context.getExecutingUnit().setCurrentStatement(this);
		context.setBroken(true);
		context.getExecutingUnit().stopExecutingStatement();
	}
	
	@Override
	public Statement getNextStatement(ExecutionContext context){
		Statement sup = this.getSuperStatement();
		while (!(sup instanceof WhileStatement)){
			System.out.println(sup);
			sup = sup.getSuperStatement();
		}
		return sup.getNextStatement(context);
	}
			
}
