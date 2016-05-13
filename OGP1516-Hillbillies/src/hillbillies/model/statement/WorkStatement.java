package hillbillies.model.statement;

import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.PositionExpression;

public class WorkStatement<E extends PositionExpression> extends ActionStatement<E>{
	
	public WorkStatement(E position){
		System.out.print(" selected "+ position.toString());
		setExpression(position);
	}


	@Override
	public void executeStatement(ExecutionContext context) {
		getExpression().evaluateExpression(context);
//		System.out.print(" expr "+getExpression());
//		System.out.print(" coords "+getExpression().getValue().getCoords());
//		System.out.print(" unit "+context.getExecutingUnit());
//		System.out.print();
		context.getExecutingUnit().work(getExpression().getValue().getCoords());		
	}
	

}
