package hillbillies.model.statement;

import java.util.List;

public interface ISequenceStatement<S> {
	
	List<S> getStatements();

	void setStatements(List<S> statements);

	

}
