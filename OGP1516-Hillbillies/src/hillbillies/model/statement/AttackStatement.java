package hillbillies.model.statement;

import hillbillies.model.expression.UnitExpression;

public class AttackStatement<E extends UnitExpression> extends ActionStatement<E> {

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
