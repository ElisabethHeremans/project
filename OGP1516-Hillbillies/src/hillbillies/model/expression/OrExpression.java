package hillbillies.model.expression;

public class OrExpression extends BinaryBExpression {
	public OrExpression(BooleanExpression right, BooleanExpression left) {
		super(right, left);
		setValue((boolean)right.getValue()||(boolean)left.getValue());
	}

//	public boolean isValidExpression(){
//		
//	}
}
