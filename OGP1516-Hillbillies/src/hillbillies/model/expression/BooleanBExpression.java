package hillbillies.model.expression;

public abstract class BooleanBExpression extends UnaryBExpression {
	public BooleanBExpression(BooleanExpression e){
		setExpression(e);
	}
}
