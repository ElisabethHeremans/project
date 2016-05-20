package hillbillies.model.expression;

public interface IComposedBinaryExpression<T,V> {
	
	V getRightExpression();
	
	void setRightExpression(V e);
	
	T getLeftExpression();
	
	void setLeftExpression(T e);
}
