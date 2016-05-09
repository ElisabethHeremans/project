package hillbillies.model.statement;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.Task;
import hillbillies.model.expression.Expression;

public abstract class Statement<E, S> {
		
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
