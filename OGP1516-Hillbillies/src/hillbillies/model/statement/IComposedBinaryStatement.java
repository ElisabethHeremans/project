package hillbillies.model.statement;

public interface IComposedBinaryStatement<S,T> {
	
	void setFirstStatement(S statement);
	
	S getFirstStatement();
	
	void setSecondStatement(T statement);
	
	T getSecondStatement();



}
