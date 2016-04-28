package hillbillies.model.statement;

import hillbillies.model.expression.UnitExpression;

public class FollowStatement extends ActionStatement {
	
	public FollowStatement(UnitExpression unit){
		setUnit(unit);
	}
	
	public UnitExpression getUnit() {
		return unit;
	}

	public void setUnit(UnitExpression unit) {
		this.unit = unit;
	}

	private UnitExpression unit;

}
