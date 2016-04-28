package hillbillies.model;

import java.util.*;

import be.kuleuven.cs.som.annotate.*;

public class Scheduler {
	@Raw
	public Scheduler(){
	
		
		
	}
	
	/**
	 * Check whether this scheduler has the given task as one of the tasks attached to it.
	 * @param task
	 * 		The task to check.
	 * @throws IllegalArgumentException
	 * 			If the given task is null.
	 */
	@Basic
	@Raw
	public boolean hasAsTask(Task task) throws IllegalArgumentException{
		if (task==null)
			throw new IllegalArgumentException();
		return this.getTasks().contains(task);
	}
	
	/**
	 * 
	 * @param tasks
	 * @return
	 * @throws IllegalArgumentException
	 */
	public boolean hasAsTasks(Set<Task> tasks)throws IllegalArgumentException{
		if (tasks==null)
			throw new IllegalArgumentException();
		for (Task task: tasks){
			if (!this.getTasks().contains(tasks))
				return false;
		}
		return true;
	}




	/**
	 * Check whether this scheduler can have the given task as one of its tasks.
	 * @param task
	 * 		The task to check.
	 * @return 
	 */
	@Raw
	public boolean canHaveAsTask(Task task){
		// task is well formed? of zo checken
		return (task != null); 
	}




	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * @return False if the total number of tasks is greater than the maximum number of tasks.
	 * 		Otherwise, true if and only if this scheduler can have each of its tasks as
	 * 		a task attached to it, and if each of these tasks references this scheduler
	 * 		as their scheduler.
	 */
	@Raw
	public boolean hasProperTasks(){
	
		
		for (Task task: this.getTasks()){
			if (! canHaveAsTask(task))
				return false;
//			if (task.getScheduler() != this)
//				return false;

			}
		return true;
	}




	/**
	 * Add the given task with the given priority to the list of tasks attached to this scheduler.
	 * @param task
	 * 		  The task to be added.
	 * @param priority
	 * 		  The priority of the task to be added.
	 * @post This new scheduler has the given task as one of its tasks, 
	 * 		 and the position in the list of tasks is given by its priority-1. 
	 * 		 The other tasks with a priority higher or equal to the priority of the new task, 
	 * 		 are shifted to one priority lower.
	 * 		 |new.getTasks()[priority-1]== task
	 * 		 |for (all i from priority-1 to getTasks().size)
	 * 		 |	this.getTasks()[i] == new.getTasks()[i+1]
//	 * @post The given new task has this scheduler as its scheduler.
	 * @throws IllegalArgumentException
	 * 		If this scheduler cannot have the given task as one of its tasks.
	 * @throws IllegalArgumentException
	 * 		If the given task is not well formed. (???)
	 */
	public void addAsTask(Task task, int priority) throws IllegalArgumentException{
		if (priority > this.getTasks().size()+1)
			throw new IllegalArgumentException();
		if(! canHaveAsTask(task)){
			throw new IllegalArgumentException();
		}
		
		if(! task.isWellFormed()){
			throw new IllegalArgumentException();
		}
		this.tasks.add(priority-1, task);
	}




	/**
	 * Remove the given task from the set of tasks attached to this scheduler.
	 * @param task
	 * 		The task to be removed.
	 * @post This new scheduler does not have the given task as one of its tasks.
	 * @post If this scheduler has the given task as one of its tasks,
	 * 		the given task is no longer attached to any scheduler.
	 * @effect If this scheduler has the given task as one of its tasks, 
	 * 			the task is removed from this new schedulers taskmap at the tasks position.
	 * @effect If this scheduler has the given task as one of its tasks, the task is removed from its faction.
	 * @effect If this scheduler has the given task as one of its tasks,
	 * 		the given task is removed from the set of tasks attached to its faction.
	 * @throws IllegalArgumentException
	 * 			If the given task is not effective
	 */
	void removeAsTask(Task task) throws IllegalArgumentException{
		if( task == null)
			throw new IllegalArgumentException();
		this.tasks.remove(task);
	}
	
	/**
	 * 
	 * @param toReplace
	 * @param replacement
	 */
	void replaceAsTask(Task toReplace, Task replacement){
		if (!hasAsTask(toReplace) || !canHaveAsTask(replacement))
			throw new IllegalArgumentException();
		else{
			if (toReplace.getExecutingUnit()!=null){
				toReplace.getExecutingUnit().setStatus(null);
			}
			addAsTask(replacement,getPriority(toReplace));
			removeAsTask(toReplace);
		}
	}


	/**
	 * Return the set collecting all tasks attached to this scheduler.
	 * 	@return The set of tasks of this scheduler.
	 */
	public List<Task> getTasks(){
		return tasks;
	}
	
	/**
	 * Return the priority of the given task in this scheduler.
	 * @param task
	 * 		  The task to check the priority of.
	 * @return The index of this task in the list of tasks +1.
	 * 		  |this.getTasks().indexOf(task)+1;
	 * @throws IllegalArgumentException()
	 * 			If this scheduler does not have the given task as its task.
	 * 			|!this.hasAsTask(task)
	 */
	public int getPriority(Task task){
		if (! this.hasAsTask(task))
			throw new IllegalArgumentException();
		else
			return getTasks().indexOf(task)+1;
	}
	
	/**
	 * Return the task with the highest priority that is currently not being executed.
	 * @return 
	 */
	public Task getHighestPriorityTask(){
		for (int i;i<getTasks().size();i++){
			if (getTasks()[i] not being executed)
				return getTasks()[i];
		}
		return null;
			
	}
	
	public Set<Task> getTasksSatisfying(lambda uitdrukking){
		
	}
	
	public Task getNextTask(){
		//iterator die van meer naar minder prioritair task teruggeeft
	}
	
	public void markTaskForUnit(Task task,Unit unit){
		if (!this.getFaction().hasAsUnit(unit))
			throw new IllegalArgumentException();
		if (! this.hasAsTask(task))
			throw new IllegalArgumentException();
		else
			task.setScheduledUnit(unit);

	}

	
	/**
	 * A variable referencing a set collecting all the tasks of this scheduler.
	 * @invar The set of tasks is effective.
	 * @invar Each element in the set of tasks references a task that
	 * 		is an acceptable task for this scheduler.
	 */
	List<Task> tasks = new LinkedList<>();

	
	// methodes voor faction verder uitbreiden
	
	Faction getFaction(){
		return this.faction;
	}
	
	void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	private Faction faction;

}
