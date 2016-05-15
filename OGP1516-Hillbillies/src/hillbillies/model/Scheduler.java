package hillbillies.model;

import java.util.*;
import java.util.function.Predicate;

import be.kuleuven.cs.som.annotate.*;

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
			//if (!this.getTasks().contains(tasks))
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
//	public void addAsTask(Task task, int priority) throws IllegalArgumentException{
//		if (priority > this.getTasks().size()+1)
//			throw new IllegalArgumentException();
//		if(! canHaveAsTask(task)){
//			throw new IllegalArgumentException();
//		}
//		
//		if(! task.isWellFormed()){
//			throw new IllegalArgumentException();
//		}
//		this.tasks.add(priority-1, task);
//	}
	
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
			System.out.println(TasksExecuted);
			this.tasks.add(task);
		}
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
	 * Replace the given task by the other given task.
	 * @param toReplace
	 * 	The task to replace.
	 * @param replacement
	 * 	The new task.
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
				toReplace.getExecutingUnit().setStatus(null);
			}
			//addAsTask(replacement,getPriority(toReplace));
			addAsTask(replacement);
			removeAsTask(toReplace);
		}
	}


//	/**
//	 * Return the set collecting all tasks attached to this scheduler.
//	 * 	@return The set of tasks of this scheduler.
//	 */
//	public TreeSet<Task> getTasks(){
//		return tasks;
//	}
	
	/**
	 * Return the set collecting all tasks attached to this scheduler.
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
	public int getPriority(Task task){
		if (! this.hasAsTask(task))
			throw new IllegalArgumentException();
		else
			return task.getPriority();
	}
	
	/**
	 * Return the task with the highest priority that is currently not being executed.
	 * @return 
	 */
	public Task getHighestPriorityTask(){
//		for (int i;i<getTasks().size();i++){
//			if (getTasks()[i] not being executed)
//				return getTasks()[i];
//		}
//		return null;
//		return getTasks().last();
		int prior = getTasks().get(0).getPriority();
		Task HighestPriorityTask = getTasks().get(0);
		for(Task task: getTasks()){
			if (task.getPriority()> prior){
				HighestPriorityTask = task;
				prior = task.getPriority();
			}
				
		}
		return HighestPriorityTask;
			
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
	public void markTaskForUnit(Task task,Unit unit){
		if (!this.getFaction().hasAsUnit(unit))
			throw new IllegalArgumentException();
		if (! this.hasAsTask(task))
			throw new IllegalArgumentException();
		else
			task.setScheduledUnit(unit);

	}

	/**
	 * Gives an iterator 
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
	Faction getFaction(){
		return this.faction;
	}
	
	/**
	 * Set the faction attached to this scheduler to the given faction.
	 * @param faction
	 * 		The faction to be attached to this scheduler.
	 * @post This scheduler references the given faction as the faction
	 * 		attached to it.
	 * 		| new.getFaction() == faction 
	 */
	void setFaction(Faction faction) {
		this.faction = faction;
	}
	/**
	 * A variable referencing the faction of this scheduler.
	 */
	private Faction faction;
	
//	/**
//	 * A variable referencing a set collecting all the tasks of this scheduler.
//	 * @invar The set of tasks is effective.
//	 * @invar Each element in the set of tasks references a task that
//	 * 		is an acceptable task for this scheduler.
//	 */
//	TreeSet<Task> tasks = new TreeSet<Task>(new Comp());
//
//	public class Comp implements Comparator<Task>{
//		
//		@Override
//		public int compare(Task task1, Task task2){
//			return ((Integer)task1.getPriority()).compareTo(task2.getPriority());
//		}
//
//	}
//
//	@Override
//	public Iterator<Task> iterator() {
//
//		return new Iterator<Task>(){
//			
//			
//			
//			@Override
//			public boolean hasNext() {
//				
//				return getTasks().first() != previous;
//			}
//
//			@Override
//			public Task next() {
//				System.out.println(hasNext());
//				if (!hasNext()){
//					throw new NoSuchElementException();
//				}
//				else if (previous == null){
//					previous = getTasks().last();
//					return getTasks().last();
//				}
//				else{
//					System.out.print(" previous: "+previous.toString());
//					Task task = getTasks().lower(previous);
//					previous = task;
//					return task;
//				}
//					
//				
//			}
//			//private Iterator<int> priorityIterator = 
//			private Task previous;
//			
//		};
//	}
//	
////	SchedulerIterator schedulerIterator = new SchedulerIterator();
//	
//	public class SchedulerIterator implements Iterator<Task>{
//		
//		@Override
//		public boolean hasNext() {
//			
//			return getTasks().first() != previous;
//		}
//
//		@Override
//		public Task next() {
//			if (!hasNext()){
//				throw new NoSuchElementException();
//			}
//			else if (previous == null){
//				previous = getTasks().last();
//				return getTasks().last();
//			}
//			else{
//				System.out.print(" previous: "+previous);
//				Task task = getTasks().lower(previous);
//				previous = task;
//				return task;
//			}
//				
//			
//		}
//		private Task previous;
		
//		private int index;
//		
//		public SchedulerIterator(){
//			index =0;
//		}
//		
//		@Override
//		public boolean hasNext() {
//			return index < tasks.size();
//		}
//
//		@Override
//		public T next() {
//			return (T) ( tasks).get(index++);
//		}
//		
//		public void remove(){
//			if (index > 0)
//				tasks.remove(index-1);
//		}
//	}
	
	List<Task> tasks = new ArrayList<>();
	private List<Task> TasksExecuted = new ArrayList<Task>();
	
	@Override
	public Iterator<Task> iterator() {
		
		return new Iterator<Task>(){
		
		private Integer LastPriority;
		private Task next;
		
		public boolean hasNext() {
			return getTasks().size()!= 0;
			}

		
		public Task next() {
			System.out.print("executed  "+TasksExecuted.size());
			System.out.print("tasks size  "+getTasks().size());

			if (!hasNext()){
				throw new NoSuchElementException();
			}
			
			else if(LastPriority == null) {
				int priority = 0; 
				if(TasksExecuted.size() != 0){
					priority = TasksExecuted.get(TasksExecuted.size()-1).getPriority();
				}
				next = getTasks().get(0);
				for(Task t: getTasks()){
					if (t.getPriority()>priority && !TasksExecuted.contains(t)){
						next = t;
						priority = t.getPriority();
						
					}		
				}
				
			}
			TasksExecuted.add(next);
			LastPriority = null;
			return next;	
			
		}
	
		
	};

	}
	}
	


