package hillbillies.model.expression;

public abstract class UnitBExpression extends UnaryBExpression {
	public UnitBExpression(UnitExpression e){
		setExpression(e);
	}

}
