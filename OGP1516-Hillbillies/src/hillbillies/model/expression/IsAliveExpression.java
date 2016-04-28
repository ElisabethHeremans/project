package hillbillies.model.expression;

public class IsAliveExpression extends UnitBExpression {
	
	public IsAliveExpression(UnitExpression unit){
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
