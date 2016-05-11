package hillbillies.model.statement;

public interface IComposedStatement<S> {
	
	void setStatement(S statement);
	
	S getStatement();
}
