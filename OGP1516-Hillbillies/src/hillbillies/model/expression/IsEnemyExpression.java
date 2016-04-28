package hillbillies.model.expression;

public class IsEnemyExpression extends UnitBExpression {
	
	public IsEnemyExpression(UnitExpression unit){
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
