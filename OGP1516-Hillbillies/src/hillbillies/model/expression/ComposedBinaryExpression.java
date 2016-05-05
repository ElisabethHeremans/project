package hillbillies.model.expression;

public interface ComposedBinaryExpression<T extends Expression<?>> {
	T getRightExpression();
	
	void setRightExpression(T e);
	
	T getLeftExpression();
	
	void setLeftExpression(T e);
}
