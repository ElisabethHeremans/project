package hillbillies.model;

import java.util.*;
import java.util.function.Predicate;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar The tasks for a scheduler must be valid tasks for any scheduler.
 * 		| canHaveAsTask(getTask)
 * @invar Each scheduler can have its faction as its faction.
 * 		| canHavaAsFaction(getFaction)
 */

public class Scheduler implements Iterable<Task> {
	/**
	 * Initialize a new scheduler.
	 */
	@Raw
	public Scheduler(){
	
	}
	
	/**
	 * Check whether this scheduler has the given task as one of the tasks attached to it.
	 * @param task
	 * 		The task to check.
	 * @throws IllegalArgumentException
	 * 		If the given task is null.
	 * 		| (task==null)	
	 */
	@Basic
	@Raw
	public boolean hasAsTask(Task task) throws IllegalArgumentException{
		if (task==null)
			throw new IllegalArgumentException();
		return this.getTasks().contains(task);
	}
	
	/**
	 * Check whether this scheduler contains the given tasks.
	 * @param tasks
	 * @return True if and only if this scheduler contains every
	 * 	task of the given set of tasks.
	 * 		| result == this.getTasks().contains(tasks)
	 * @throws IllegalArgumentException
	 * 		If these tasks are not effective.
	 * 		| (tasks==null)
	 */
	public boolean hasAsTasks(Set<Task> tasks)throws IllegalArgumentException{
		if (tasks==null)
			throw new IllegalArgumentException();
		for (Task task: tasks){
			if (!this.getTasks().contains(task))
				return false;
		}
		return true;
	}

	/**
	 * Check whether this scheduler can have the given task as one of its tasks.
	 * @param task
	 * 		The task to check.
	 * @return True if and only if the given task is effective.
	 * 		| result == (task != null)
	 */
	@Raw
	public boolean canHaveAsTask(Task task){
		// task is well formed? of zo checken
		return (task != null); 
	}

	/**
	 * Check whether this scheduler has proper tasks attached to it.
	 * @return True if and only if this scheduler can have each of its tasks as
	 * 		a task attached to it.
	 * 		| result == canHaveAsTask(task)
	 */
	@Raw
	private boolean hasProperTasks(){
	
		
		for (Task task: this.getTasks()){
			if (! canHaveAsTask(task))
				return false;
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
	 * @post The given new task has this scheduler as its scheduler.
	 * @throws IllegalArgumentException
	 * 		If this scheduler cannot have the given task as one of its tasks.
	 * @throws IllegalArgumentException
	 * 		If the given task is not well formed. (???)
	 */
	public void addAsTask(Task task) throws IllegalArgumentException{
		//System.out.print("adding task");
		if(! canHaveAsTask(task)){
			System.out.print("problem 1");
			throw new IllegalArgumentException();
		}
		
		if(!Task.isWellFormed(task.getActivities())){
			System.out.print("problem 2");
			throw new IllegalArgumentException();
		}
		if( ! getTasks().contains(task)){
			//System.out.println(TasksExecuted);
			this.tasks.add(task);
		}
	}
	/**
	 * Add all the tasks in the given list of tasks to this scheduler
	 * @param tasks
	 * 			The list of tasks to be added to this scheduler
	 * @effect For all the tasks in the given list, add the task to this scheduler.
	 * 			|for (Task task: tasks){
	 *			|	this.addAsTask(task);
	 *			|}
	 */
	public void addTasks(List<Task> tasks) throws IllegalArgumentException{
		for (Task task: tasks)
			this.addAsTask(task);
	}


	/**
	 * Remove the given task from the list of tasks attached to this scheduler.
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
	public void removeAsTask(Task task) throws IllegalArgumentException{
		if( task == null)
			throw new IllegalArgumentException();
		this.tasks.remove(task);
	}
	
	/**
	 * Remove the given tasks from the list of tasks attached to this scheduler
	 * 
	 * @param tasks
	 * 			 A list with all the tasks to be removed.
	 * @effect For all the tasks in the list of tasks, remove the task from this scheduler
	 * 			|for (Task task: tasks){
	 *			|	this.removeAsTask(task);
	 *			|}
	 */
	public void removeTasks(List<Task> tasks) throws IllegalArgumentException{
		for (Task task: tasks){
			removeAsTask(task);
		}
	}
	
	/**
	 * Replace the given task by the other given task.
	 * @param toReplace
	 * 	The task to replace.
	 * @param replacement
	 * 		The new task.
	 * @effect If the task to replace is currently being executed, interrupt the execution of the task to replace.
	 * 			|if (toReplace.getExecutingUnit()!=null)
	 *			|	toReplace.interruptExecution();
	 * @effect This scheduler does not have the old task as one of its tasks.
	 * 		| removeAsTask(toReplace)
	 * @effect This scheduler has the new task as one of its tasks.
	 * 		| addAsTask(replacement)
	 * @throws IllegalArgumentException
	 * 	If this scheduler does not have the old task as one of its tasks,
	 * 	or this scheduler can't have the new task as one of its tasks.
	 * 		| !hasAsTask(toReplace) || !canHaveAsTask(replacement)
	 */
	public void replaceAsTask(Task toReplace, Task replacement){
		if (!hasAsTask(toReplace) || !canHaveAsTask(replacement))
			throw new IllegalArgumentException();
		else{
			if (toReplace.getExecutingUnit()!=null){
				toReplace.interruptExecution();
			}
			//addAsTask(replacement,getPriority(toReplace));
			addAsTask(replacement);
			removeAsTask(toReplace);
		}
	}

	
	/**
	 * Return the list collecting all tasks attached to this scheduler.
	 */
	@Basic @Raw
	public List<Task> getTasks(){
		return tasks;
	}

	
	/**
	 * Return the priority of the given task in this scheduler.
	 * @param task
	 * 		  The task to check the priority of.
	 * @return The priority of the given task.
	 * 		  |task.getPriority()
	 * @throws IllegalArgumentException()
	 * 			If this scheduler does not have the given task as its task.
	 * 		  |!this.hasAsTask(task)
	 */
	private int getPriority(Task task){
		if (! this.hasAsTask(task))
			throw new IllegalArgumentException();
		else
			return task.getPriority();
	}
	
	/**
	 * Return the task with the highest priority that is currently not being executed.
	 */
	public Task getHighestPriorityTask(){
		List<Task> tasksNotExecuted = new ArrayList<>();
		this.getTasks().stream().filter(n->n.getExecutingUnit()==null).forEach(tasksNotExecuted::add);
		if (tasksNotExecuted.size()==0)
			return null;
		else{
		int prior = tasksNotExecuted.get(0).getPriority();
		Task HighestPriorityTask = tasksNotExecuted.get(0);
		for(Task task: tasksNotExecuted){
			if (task.getPriority()> prior){
				HighestPriorityTask = task;
				prior = task.getPriority();
			}
		}	
		
			return HighestPriorityTask;
		}
	}
	
	/**
	 * Search all tasks that satisfy the given condition.
	 * @param condition
	 * 	The condition to satisfy 
	 * @return A set of all tasks satisfying the given condition.
	 * 		| result == this.getTasks().stream().filter(condition)
	 */
	public Set<Task> getTasksSatisfying(Predicate<Task> condition){
		Set<Task> tasksSat = new HashSet<>();
		this.getTasks().stream().filter(condition).forEach(tasksSat::add);
		return tasksSat;
	}

	/**
	 * Schedule the given task for the given unit.
	 * @param task
	 * 		The scheduled task.
	 * @param unit
	 * 		The scheduled unit.
	 * @effect The given task is scheduled for the given unit.
	 * 		| task.setScheduledUnit(unit)
	 * @throws IllegalArgumentException
	 * 	If the faction of this scheduler does not has the given unit as one of its units.
	 * 		| !this.getFaction().hasAsUnit(unit)
	 * @throws IllegalArgumentException
	 * 	If this scheduler does not have the given task as one of its tasks.
	 * 		| ! this.hasAsTask(task)
	 */
	private void markTaskForUnit(Task task,Unit unit){
		if (!this.getFaction().hasAsUnit(unit))
			throw new IllegalArgumentException();
		if (! this.hasAsTask(task))
			throw new IllegalArgumentException();
		else
			task.setScheduledUnit(unit);

	}

	/**
	 *  Return an iterator returning all the tasks of this scheduler, one by one.
	 */
	public Iterator<Task> getAllTasksIterator(){
		//return new SchedulerIterator<Task>();
		//return tasks.descendingIterator();
		return tasks.iterator();

	}

	// methodes voor faction verder uitbreiden
	/**
	 * Return the faction of this scheduler.
	 */
	public Faction getFaction(){
		return this.faction;
	}
	
	/**
	 * Check whether this scheduler has a proper faction attached to it.
	 * @return True if and only if this scheduler does not reference an effective faction
	 * 		or if the faction referenced by this scheduler in turn references this scheduler as 
	 * 		the scheduler attached to it.
	 * 		| result == (this.getFaction() == null || this.getFaction().getScheduler()==this)
	 */
	@Raw
	private boolean hasProperFaction(){
		return (this.getFaction() == null || this.getFaction().getScheduler()==this);
	}
	
	/**
	 * Check whether this scheduler can have the given faction 
	 * as a faction attached to it.
	 * @return True if and only if the given faction is not  an effective faction
	 * 		or if the given faction references this scheduler as 
	 * 		the scheduler attached to it.
	 * 		| result == (this.getFaction() == null || this.getFaction().getScheduler()==this)
	 */
	@Raw
	private boolean canHaveAsFaction(Faction faction){
		return (faction == null || faction.getScheduler()==this);
	}
	
	/**
	 * Set the faction attached to this scheduler to the given faction.
	 * @param faction
	 * 		The faction to be attached to this scheduler.
	 * @post This scheduler references the given faction as the faction
	 * 		attached to it.
	 * 		| new.getFaction() == faction 
	 */
	protected void setFaction(Faction faction) {
		this.faction = faction;
	}
	/**
	 * A variable referencing the faction of this scheduler.
	 */
	private Faction faction;	
	
	/**
	 * List collecting references to tasks attached to this scheduler.
	 */
	List<Task> tasks = new ArrayList<>();
	
	/**
	 * List collecting all executed tasks.
	 */
	private List<Task> TasksExecuted = new ArrayList<Task>();
	
	/**
	 * List collecting all tasks that have been iterated over.
	 */
	private List<Task> iteratedTasks = new ArrayList<Task>();
	
	/**
	 * Return an iterator returning all the tasks of this scheduler by descending priority.
	 */
	@Override
	public Iterator<Task> iterator() {
		
		return new Iterator<Task>(){
		
		//private Integer LastPriority;
		private Task next;
		
		public boolean hasNext() {
			return getTasks().size()!= iteratedTasks.size();
			}

		
		public Task next() {
			System.out.print("iterated  "+iteratedTasks.size());
			System.out.print("tasks size  "+getTasks().size());

			if (!hasNext()){
				throw new NoSuchElementException();
			}
			
			else /*if(LastPriority == null)*/ {
				int newPriority = 0;
				next = getTasks().get(0);
				newPriority = next.getPriority();
				for(Task t: getTasks()){
					if ((t.getPriority()>newPriority) && !iteratedTasks.contains(t)){
						next = t;
						newPriority = t.getPriority();
						
					}		
				}
				
			}
			iteratedTasks.add(next);
			//LastPriority = null;
			return next;	
			
		}
	
		
	};

	}
	}
	


