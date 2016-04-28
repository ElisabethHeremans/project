package hillbillies.model.expression;

public class IsFriendExpression extends UnitBExpression {
	
	public IsFriendExpression(UnitExpression unit){
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
