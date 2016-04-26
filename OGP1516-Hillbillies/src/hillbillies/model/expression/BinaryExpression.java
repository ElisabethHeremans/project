package hillbillies.model.expression;

public class BinaryExpression extends ComposedExpression {
	
	public BinaryExpression(Expression right, Expression left){
		this.rightExp = right;
		this.leftExp = left;
				
	}
	
	
	public Expression getRightExpression(){
		return rightExp;
	}
	public Expression getLeftExpression(){
		return leftExp;
	}
	
	private Expression rightExp;
	private Expression leftExp;
	
	public isValidExpression(Expression expression){
		if (this.leftExp instanceof PositionExpression)
			return this.rightExp instanceof PositionExpression;
		if (this.leftExp instanceof UnitExpression)
			return this.rightExp instanceof UnitExpression;
		if (this.leftExp instanceof BooleanExpression)
			return this.rightExp instanceof BooleanExpression;
		if (this.leftExp instanceof VariableExpression)
			return this.rightExp instanceof VariableExpression;
		//?
					
	}
}
