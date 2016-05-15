package hillbillies.model;

import java.util.ArrayList;
import java.util.HashMap;

import hillbillies.model.expression.*;
import java.util.List;
import java.util.Map;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.statement.*;

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
	 *       Otherwise, the priority of this new task is equal to 0. 
	 *       | if (isValidPriority(priority)) 
	 *       | then new.getPriority() == priority 
	 *       |else new.getPriority() == 0
	 * @effect The activities of this new task is set to the given activities. |
	 *         this.setActivities(activities)
	 * @effect The selected cube of this new task is set to the given selected cube
	 */
	public Task(String name, int priority, Statement activities, int[] selectedCube) throws IllegalArgumentException {
		if (!canHaveAsName(name))
			name = "task";
		this.name = name;
		if (!isValidPriority(priority))
			priority = 0;
		setPriority(priority);
		this.setActivities(activities);
		this.setExecutionContext(new ExecutionContext(null,selectedCube,null));
	}
	
	public Task(String name, int priority, Statement activities){
		this(name,  priority, activities, null);
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
	 * @return the isComplete
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	private boolean isComplete;

	/**
	 * Return the activities of this task.
	 */
	@Basic
	@Raw
	public Statement getActivities() {
		return this.activities;
	}

	/**
	 * Check whether the given activities is a valid activities statement for any task.
	 * 
	 * @param activities
	 *            The activities to check.
	 * @return The activities statement of this task is well formed.
	 * 		   | result == isWellFormed(activities)
	 */
	public static boolean isValidActivities(Statement activities) {
		return isWellFormed(activities);
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
	 * 
	 * @param activities
	 * @return
	 */
	public static boolean isWellFormed(Statement activities){
		List<Statement> statements = new ArrayList<Statement>();
		List<String> variableNames = new ArrayList<String>();
	
		if (activities instanceof SequenceStatement<?>){
			statements = (List<Statement>) ((SequenceStatement<?>)activities).getStatements();
		}
		else
			statements.add(activities);
	
		for (Statement stat: statements){
			if (stat instanceof AssignmentStatement<?>){
				variableNames.add(((AssignmentStatement<?>) stat).getVariableName());
			}
			else if (stat instanceof BreakStatement)
				return false;
			else if(stat instanceof SequenceStatement)
				if (! isWellFormed(stat))
					return false;
			else{
					Expression e = ((ExpressionStatement<?>)stat).getExpression();
					if (e instanceof BracketVariableExpression){
						e = ((BracketVariableExpression)e).getExpression();
					}
					if (e instanceof BasicVariableExpression){
						boolean variableAssigned = false;
						for (String name: variableNames){
							if (name == ((BasicVariableExpression)e).getName())
								variableAssigned = true;
							
						}
						if (!variableAssigned){
							return false;
						}
					}
					if (stat instanceof IComposedUnaryStatement<?>)
						if (!( stat instanceof WhileStatement<?,?>))
							if(((IComposedUnaryStatement<?>)stat).getStatement() 
									instanceof BreakStatement)
								return false;
					if (stat instanceof IComposedBinaryStatement<?,?>)
						if ((((IComposedBinaryStatement<?,?>)stat).getFirstStatement() 
								instanceof BreakStatement) ||
								(((IComposedBinaryStatement<?,?>)stat).getSecondStatement() 
										instanceof BreakStatement))
							return false;
									
				}
			}
		return true;
		}

	public void removeFirstStatement(){
		if (this.getActivities() instanceof SequenceStatement){
			((SequenceStatement<?>) this.getActivities()).removeFirstStatement();
			if (this.getActivities().isStatementExecuted()){
				this.setActivities(null);
				this.setComplete(true);
			}
		}
		else{
			this.setActivities(null);
			this.setComplete(true);
		}
	}

	/**
	 * Variable registering the activities of this task.
	 */
	private Statement activities;
	
	/**
	 * @return the scheduledUnit
	 */
	@Basic @Raw
	public Unit getScheduledUnit() {
		return scheduledUnit;
	}


	/**
	 * @param scheduledUnit the scheduledUnit to set
	 */
	@Raw
	public void setScheduledUnit(Unit scheduledUnit) {
		this.scheduledUnit = scheduledUnit;
	}


	private Unit scheduledUnit;

	/**
	 * @return the executingUnit
	 */
	public Unit getExecutingUnit() {
		return this.getExecutionContext().getExecutingUnit();
	}


	/**
	 * @param executingUnit the executingUnit to set
	 */
	public void setExecutingUnit(Unit executingUnit) {
		this.getExecutionContext().setExecutingUnit(executingUnit);
		
	}

	
	
	/**
	 * Return the selected Cube for this task.
	 */
	public int[] getSelectedCube() {
		return this.getExecutionContext().getSelectedCube();
	}


	/**
	 * @param selectedCube the selectedCube to set
	 */
	private void setSelectedCube(int[] selectedCube) {
		 this.getExecutionContext().setSelectedCube(selectedCube);
	}
	
	
	
	public void addVariable(String variableName,Expression expr){
		 this.getExecutionContext().addVariable(variableName,expr);
	}
	
//	public Expression lookForVariable(String variableName){
//		return ((HashMap<String,Expression>)getExecutionContext()).get(variableName);
//		
//	}

	public void executeTask(Unit unit) {
		this.getExecutionContext().setExecutingUnit(unit);
//		System.out.print(getActivities() + "   ");
//		System.out.print(this.getExecutionContext().getSelectedCube());
//		System.out.print(this.getExecutionContext().getExecutingUnit());
		getActivities().executeStatement(getExecutionContext());
	}
	
	@Raw @Basic
	public ExecutionContext getExecutionContext(){
		return executionContext;
	}
	
	
	@Raw
	public void setExecutionContext(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	private ExecutionContext executionContext;
	
	public String toString(){
		return getName().toString();
	}

}
