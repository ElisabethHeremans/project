package hillbillies.model.expression;

public interface ComposedUnaryExpression<T extends Expression<?>> {
	
		T getExpression();

		void setExpression(T e);
		

}
