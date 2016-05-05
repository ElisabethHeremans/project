package hillbillies.model.expression;
import hillbillies.model.Unit;

public class CarriesItemExpression extends UnitBExpression {

	public CarriesItemExpression(UnitExpression e) {
		super(e);
		setValue(((Unit) e.getValue()).getLog() !=null ||((Unit) e.getValue()).getBoulder() !=null);

	}
	@Override
	public Boolean evaluateExpression(Unit unit){
		return (unit.getBoulder()!= null || unit.getLog()!=null);
	}
	@Override
	public Boolean evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
