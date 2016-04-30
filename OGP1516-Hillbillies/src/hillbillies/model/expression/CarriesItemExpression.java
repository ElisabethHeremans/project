package hillbillies.model.expression;
import hillbillies.model.Unit;

public class CarriesItemExpression extends UnitBExpression {

	public CarriesItemExpression(UnitExpression e) {
		super(e);
		setValue(((Unit) e.getValue()).getLog() !=null ||((Unit) e.getValue()).getBoulder() !=null);

	}
	

}
