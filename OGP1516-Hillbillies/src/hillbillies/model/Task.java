package hillbillies.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar Each task can have its name as name . | canHaveAsName(this.getName())
 * @invar The priority of each task must be a valid priority for any task. |
 *        isValidPriority(getPriority())
 */

public class Task {
	/**
	 * Initialize this new task with given name and the given priority.
	 * 
	 * @param name
	 *            The name for this new task.
	 * @param priority
	 *            The priority for this new task.
	 * @post If the given name is a valid name for any task, the name of this
	 *       new task is equal to the given name. Otherwise, the name of this
	 *       new task is equal to "task". | if (isValidName(name)) | then
	 *       new.getName() == name | else new.getName() == "task"
	 * @post If the given priority is a valid priority for any task, the
	 *       priority of this new task is equal to the given priority.
	 *       Otherwise, the priority of this new task is equal to 0. | if
	 *       (isValidPriority(priority)) | then new.getPriority() == priority |
	 *       else new.getPriority() == 0
	 */

	public Task(String name, int priority, List<List> activities) {
		if (!canHaveAsName(name))
			name = "task";
		this.name = name;
		if (!isValidPriority(priority))
			priority = 0;
		setPriority(priority);
	}

	/**
	 * Return the name of this task.
	 */
	@Basic
	@Raw
	@Immutable
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether this task can have the given name as its name.
	 * 
	 * @param name
	 *            The name to check.
	 * @return | result == (name!=null)
	 */
	@Raw
	public boolean canHaveAsName(String name) {
		return (name != null);
	}

	/**
	 * Variable registering the name of this task.
	 */
	private final String name;

	/**
	 * Initialize this new task with given priority.
	 * 
	 * @param priority
	 *            The priority for this new task.
	 * @post If the given priority is a valid priority for any task, the
	 *       priority of this new task is equal to the given priority.
	 *       Otherwise, the priority of this new task is equal to 0. | if
	 *       (isValidPriority(priority)) | then new.getPriority() == priority |
	 *       else new.getPriority() == 0
	 */

	/**
	 * Return the priority of this task.
	 */
	@Basic
	@Raw
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Check whether the given priority is a valid priority for any task.
	 * 
	 * @param priority
	 *            The priority to check.
	 * @return | result ==
	 */
	public static boolean isValidPriority(int priority) {
		return false;
	}

	/**
	 * Set the priority of this task to the given priority.
	 * 
	 * @param priority
	 *            The new priority for this task.
	 * @post If the given priority is a valid priority for any task, the
	 *       priority of this new task is equal to the given priority. | if
	 *       (isValidPriority(priority)) | then new.getPriority() == priority
	 */
	@Raw
	public void setPriority(int priority) {
		if (isValidPriority(priority))
			this.priority = priority;
	}

	/**
	 * Variable registering the priority of this task.
	 */
	private int priority;

}
