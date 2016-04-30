package hillbillies.model.expression;

public interface ComposedBinaryExpression {
	Expression getRightExpression();
	
	void setRightExpression(Expression e);
	
	Expression getLeftExpression();
	
	void setLeftExpression(Expression e);
}
