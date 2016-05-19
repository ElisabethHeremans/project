package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Position;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

public class WorkStatement<E extends Expression<Position>> extends ActionStatement<E>{
	
	public WorkStatement(E position){
		System.out.print(" selected "+ position.toString());
		setExpression(position);
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);
		super.executeStatement(context);
		getExpression().evaluateExpression(context);
//		System.out.print(" expr "+getExpression());
//		System.out.print(" coords "+getExpression().getValue().getCoords());
//		System.out.print(" unit "+context.getExecutingUnit());
//		System.out.print();
		context.getExecutingUnit().work(getExpression().getValue().getCoords());		
	}
	

}
