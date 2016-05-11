package hillbillies.model.expression;

public interface IComposedBinaryExpression<T> {
	
	T getRightExpression();
	
	void setRightExpression(T e);
	
	T getLeftExpression();
	
	void setLeftExpression(T e);
}
