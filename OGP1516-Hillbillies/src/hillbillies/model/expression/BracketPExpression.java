package hillbillies.model.expression;

public class BracketPExpression extends ComposedPExpression {
	public BracketPExpression(PositionExpression e){
		setExpression(e);
		setValue(e.getValue());
	}
}
