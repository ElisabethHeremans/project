package hillbillies.model.statement;

import hillbillies.model.expression.UnitExpression;

public class FollowStatement extends ActionStatement {
	
	public FollowStatement(UnitExpression unit){
		setExpression(unit);
	}
	

}
