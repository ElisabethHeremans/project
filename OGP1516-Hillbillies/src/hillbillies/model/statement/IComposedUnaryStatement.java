package hillbillies.model.statement;

public interface IComposedUnaryStatement<S> {
	
	void setStatement(S statement);
	
	S getStatement();
}
