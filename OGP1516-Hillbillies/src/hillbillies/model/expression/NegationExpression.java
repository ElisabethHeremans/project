package hillbillies.model.expression;

public class NegationExpression extends BooleanBExpression {

	public NegationExpression(BooleanExpression e) {
		super(e);
		setValue(! (Boolean)e.getValue());
	}
	

}
