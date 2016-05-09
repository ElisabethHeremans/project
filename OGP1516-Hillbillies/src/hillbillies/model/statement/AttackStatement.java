package hillbillies.model.statement;

import hillbillies.model.expression.UnitExpression;

public class AttackStatement<E extends UnitExpression<?>,S> extends ActionStatement<E,S> {

	public AttackStatement(E e) {
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
