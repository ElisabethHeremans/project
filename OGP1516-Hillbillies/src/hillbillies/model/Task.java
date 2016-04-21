package hillbillies.model;

import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar Each task can have its name as name . | canHaveAsName(this.getName())
 * @invar The priority of each task must be a valid priority for any task. |
 *        isValidPriority(getPriority())
 * @invar The activities of each task must be a valid activities for any task. |
 *        isValidActivities(getActivities())
 */

public class Task {
	/**
	 * Initialize this new task with given name, the given priority and the
	 * given activities.
	 * 
	 * @param name
	 *            The name for this new task.
	 * @param priority
	 *            The priority for this new task.
	 * @param activities
	 *            The activities for this new task.
	 * @post If the given name is a valid name for any task, the name of this
	 *       new task is equal to the given name. Otherwise, the name of this
	 *       new task is equal to "task". | if (isValidName(name)) | then
	 *       new.getName() == name | else new.getName() == "task"
	 * @post If the given priority is a valid priority for any task, the
	 *       priority of this new task is equal to the given priority.
	 *       Otherwise, the priority of this new task is equal to 0. | if
	 *       (isValidPriority(priority)) | then new.getPriority() == priority |
	 *       else new.getPriority() == 0
	 * @effect The activities of this new task is set to the given activities. |
	 *         this.setActivities(activities)
	 */
	public Task(String name, int priority, Statement activities) throws IllegalArgumentException {
		if (!canHaveAsName(name))
			name = "task";
		this.name = name;
		if (!isValidPriority(priority))
			priority = 0;
		setPriority(priority);
		this.setActivities(activities);
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
		return true;
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

	/**
	 * Return the activities of this task.
	 */
	@Basic
	@Raw
	public Statement getActivities() {
		return this.activities;
	}

	/**
	 * Check whether the given activities is a valid activities for any task.
	 * 
	 * @param activities
	 *            The activities to check.
	 * @return | result == (activities != null)
	 */
	public static boolean isValidActivities(Statement activities) {
		return (activities != null);
	}

	/**
	 * Set the activities of this task to the given activities.
	 * 
	 * @param activities
	 *            The new activities for this task.
	 * @post The activities of this new task is equal to the given activities. |
	 *       new.getActivities() == activities
	 * @throws IllegalArgumentException
	 *             The given activities is not a valid activities for any task.
	 *             | ! isValidActivities(getActivities())
	 */
	@Raw
	public void setActivities(Statement activities) throws IllegalArgumentException {
		if (!isValidActivities(activities))
			throw new IllegalArgumentException();
		this.activities = activities;
	}

	/**
	 * Variable registering the activities of this task.
	 */
	private Statement activities;

}
