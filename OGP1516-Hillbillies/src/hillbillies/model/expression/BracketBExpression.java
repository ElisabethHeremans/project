package hillbillies.model.expression;

public class BracketBExpression extends BooleanBExpression {

	public BracketBExpression(BooleanExpression e) {
		super(e);
		setValue(e.getValue());
	}

}
