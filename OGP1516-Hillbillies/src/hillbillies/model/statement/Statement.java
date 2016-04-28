package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.expression.Expression;

public class Statement {
	
	public boolean hasExpression(Expression e){
		if (this.toString().contains(e.toString()))
			return true;
		return false;
	}
	
	public void setTask(@Raw Task task){
		if (task.getActivities() == this)
			this.task = task;
	}
	
	@Basic @Raw
	public final Task getTask() {
		return task;
	}

	private Task task;
	
	
	
	
	

}
