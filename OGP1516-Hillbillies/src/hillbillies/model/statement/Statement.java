package hillbillies.model.statement;

import java.util.List;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.ExecutionContext;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.expression.Expression;

public abstract class Statement{
		
	public void setTask(@Raw Task task){
		if (task.getActivities() == this)
			this.task = task;
	}
	
	@Basic @Raw
	public final Task getTask() {
		return task;
	}

	private Task task;

	public abstract void executeStatement(ExecutionContext context); 

}
