package hillbillies.model.expression;

public class CarriesItemExpression extends UnitBExpression {
	
	public CarriesItemExpression(UnitExpression unit){
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