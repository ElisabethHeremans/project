package hillbillies.model.expression;

public class BracketUExpression extends UnitExpression {
	public BracketUExpression(UnitExpression e){
		setExpression(e);
		setValue(e.getValue());
	}
	
	public UnitExpression getExpression() {
		return expression;
	}

	public void setExpression(UnitExpression e) {
		this.expression = e;
	}
	
	private UnitExpression expression;
}
