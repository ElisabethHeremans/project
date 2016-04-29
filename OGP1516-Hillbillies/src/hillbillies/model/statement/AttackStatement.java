package hillbillies.model.statement;

import hillbillies.model.expression.Expression;
import hillbillies.model.expression.UnitExpression;

public class AttackStatement extends ActionStatement {

	public AttackStatement(UnitExpression e) {
		setExpression(e);
	}
	
//	public AttackStatement(UnitExpression unit){
//		setUnit(unit);
//	}
//	
//	public UnitExpression getUnit() {
//		return unit;
//	}
//
//	public void setUnit(UnitExpression unit) {
//		this.unit = unit;
//	}
//
//	private UnitExpression unit;

}
