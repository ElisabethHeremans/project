package hillbillies.model.expression;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;

public class CarriesItemExpression<E extends Expression<Unit>> extends UnaryBooleanExpression<E> {

	public CarriesItemExpression(E e) {
		setExpression(e);
	}
	
	@Override
	public Boolean evaluateExpression(ExecutionContext context) {
		try{
		getExpression().evaluateExpression(context);
		Unit unit = getExpression().getValue();
		setValue(unit.getBoulder()!= null|| unit.getLog()!=null);
		return getValue();
		}
		catch(NullPointerException e){
			return null;
		}

	}

	

}
