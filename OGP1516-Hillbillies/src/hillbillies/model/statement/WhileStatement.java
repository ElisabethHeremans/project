package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Unit;
import hillbillies.model.expression.BooleanExpression;
import hillbillies.model.expression.Expression;

public class WhileStatement<E extends Expression<Boolean>, S extends Statement>
extends ExpressionStatement<E> implements IComposedUnaryStatement<S> {
	
	public WhileStatement(E condition, S body){
		setExpression(condition);
		setStatement(body);
		
	}
	
	public E getCondition() {
		return getExpression();
	}

	public S getBody() {
		return getStatement();
		
	}
	
	@Basic @Raw @Override
	public final S getStatement() {
		return statement;
		
	}

	@Raw @Override
	public final void setStatement(S statement) {
		statement.setSuperStatement(this);
		this.statement = statement;
	}
	

	private S statement;


	@Override
	public void executeStatement(ExecutionContext context) {
		context.getExecutingUnit().setCurrentStatement(this);
		super.executeStatement(context);
		if (getExpression().getValue()){
			System.out.println("execute while ");
			getStatement().executeStatement(context);
//			}
		}
		else
			context.getExecutingUnit().stopExecutingStatement();

			
	}
	
	@Override
	public Statement getNextStatement(ExecutionContext context){
		super.executeStatement(context);
		System.out.println(" in get next while ");
		System.out.println(context.isBroken());
		if (!context.isBroken()&& getExpression().getValue()){
			return this;
		}
		context.setBroken(false);
		System.out.println(" in get next while ");
		if (!isLast() && this.getSuperStatement() != null){
			System.out.println(" a ");
			return (Statement) ((SequenceStatement<?>)getSuperStatement()).getStatements().get(this.getIndex()+1);
		}
		else if (this.getSuperStatement() != null){
			System.out.println(" b ");
			return getSuperStatement().getNextStatement(context);
		}
		else{
			System.out.println(" c ");
			return null;
		}
			
	}
	


}
