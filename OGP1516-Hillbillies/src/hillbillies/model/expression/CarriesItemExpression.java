package hillbillies.model.expression;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class CarriesItemExpression<E extends UnitExpression> extends UnaryBooleanExpression<E> {

	public CarriesItemExpression(E e) {
		setExpression(e);
	}
	
	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		Unit unit = getExpression().getValue();
		setValue(unit.getBoulder()!= null|| unit.getLog()!=null);
		return null;
	}

	

}
