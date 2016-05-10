package hillbillies.model.expression;

public abstract class BooleanExpression extends Expression {
	
	
	public BooleanExpression(){
		
	}
	
	public void setValue(boolean object){
		value = object;
	}
	
	private boolean value;
	
	@Override
	public final Boolean getValue() {
		return value;
	}

}
