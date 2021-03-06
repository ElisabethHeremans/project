package hillbillies.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import hillbillies.model.expression.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
import hillbillies.model.statement.*;

/**
 * @invar Each task can have its name as name . 
 * 			| canHaveAsName(this.getName())
 * @invar The priority of each task must be a valid priority for any task. 
 * 			| isValidPriority(getPriority())
 * @invar The activities of each task must be a valid activities for any task. 
 *        	| isValidActivities(getActivities())
 * @invar Each task can have its executing unit as its executing unit.
 * 			| canHaveAsExecutingUnit(this.getExecutingUnit())
 * @invar Each task can have its scheduled unit as its executing unit.
 * 			| canHaveAsExecutingUnit(this.getScheduledUnit())
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
	 * @param selectedCube
	 * 			  The selected cube for this new task       
	 * @post If the given name is a valid name for any task, the name of this
	 *       new task is equal to the given name. Otherwise, the name of this
	 *       new task is equal to "task". 
	 *       | if (isValidName(name)) 
	 *       | 	then new.getName() == name 
	 *       | else new.getName() == "task"
	 * @post If the given priority is a valid priority for any task, the
	 *       priority of this new task is equal to the given priority.
	 *       Otherwise, the priority of this new task is equal to 0. 
	 *       | if (isValidPriority(priority)) 
	 *       | 		then new.getPriority() == priority 
	 *       |else new.getPriority() == 0
	 * @effect The activities of this new task is set to the given activities. 
	 * 		 |this.setActivities(activities)
	 * @effect The execution context of this initialized 
	 * 		 as an execution context with the given selected cube as its selected cube.
	 * 		 |this.setExecutionContext(new ExecutionContext(null,selectedCube,null))
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
	
	/**
	 * Initialize this new task with given name, priority and activities.
	 * @param name
	 *            The name for this new task.
	 * @param priority
	 *            The priority for this new task.
	 * @param activities
	 *            The activities for this new task.
	 * @effect This new task is initialized with the given name, priority and activities, 
	 * 			and null as its selected cubes.
	 */
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
	 * @return 
	 * 			| result == (name!=null)
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
	 * @return True
	 * 		| result == true
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
	 *       priority of this new task is equal to the given priority. 
	 *       | if (isValidPriority(priority)) 
	 *       | 	then new.getPriority() == priority
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
	 * Return whether this task is complete.
	 * 
	 * @return the value of isComplete
	 * 			| result == isComplete
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

	private boolean isComplete = false;

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
		return isWellFormed(activities,null);
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
	 * Variable registering the non-completed activities of this task.
	 */
	private Statement activities;


	/**
	 * Return whether the given statement is well formed.
	 * 
	 * @param activities
	 * 			The statement to check.
	 * @param variables
	 * 			The already assigned variables in superstatements of this statement.
	 * @return For all the statements in statement:
	 * 			- if the statement is an AssignmentStatement
	 * 				add the variable to the list of assigned variables
	 * 				|variableNames.add(statement.getVariableName())
	 * 			- if the statement is a BreakStatement
	 * 				if the superstatement is null, return false
	 * 				else if the superstatement is not a whilestatement, and the superstatement's superstatement
	 * 					is null or not a while statement, return false
	 * 				|if (stat.getSuperStatement() == null)
	 * 				|  result == false;
	 * 				|else if (!(stat.getSuperStatement() instanceof WhileStatement)){
	 * 				|  if (stat.getSuperStatement().getSuperStatement() == null || 
	 * 				|			(! (stat.getSuperStatement().getSuperStatement() instanceof WhileStatement)))
	 * 				|		result == false;
	 * 			- if the statement is a SequenceStatement
	 * 				if this statement is not well formed with the current variableNames as assigned variables, return false
	 * 				|if (!isWellFormed(statement, variableNames))
	 * 				|	result == false
	 * 			- if the statement is an ExpressionStatement
	 * 				- if the expression in the statement is a VariableExpression
	 * 					if the variable is not in the list of defined variables, return false
	 * 					| if (!definedVariables.contains(expression.getName()))
	 * 					|	result == false
	 * 				- if the statement is a ComposedUnaryStatement
	 * 					if this statement in this statement not well formed 
	 * 						with the current variableNames as assigned variables, return false
	 *					|if (!isWellFormed((Statement)((IComposedUnaryStatement<?>)e).getStatement(),variableNames))
	 *					|	result == false;
	 * 				- if the statement is a ComposedBinaryStatement
	 * 					if one of the two statement in this statement not well formed 
	 * 						with the current variableNames as assigned variables, return false
	 *					|if (!isWellFormed((Statement)((IComposedBinaryStatement<?>)e).getFirstStatement(),variableNames))
	 *					|	result == false;
	 *					|if (!isWellFormed((Statement)((IComposedBinaryStatement<?>)e).getSecondStatement(),variableNames))
	 *					|	result == false;
	 * @return Else, return true
	 * 			|result == true
	 */
	public static boolean isWellFormed(Statement activities, List<String> variables){
		List<Statement> statements = new ArrayList<Statement>();
		List<String> variableNames = new ArrayList<String>();

		if (variables == null|| variables.size()==0)
			variableNames = new ArrayList<String>();
		else
			for (String var: variables)
				variableNames.add(var);
		if (activities instanceof SequenceStatement){
			statements = (List<Statement>) ((SequenceStatement<?>)activities).getStatements();
		}
		else
			statements.add(activities);
	
		for (Statement stat: statements){
			if (stat instanceof AssignmentStatement<?>){
				variableNames.add(((AssignmentStatement<?>) stat).getVariableName());
			}
			else if (stat instanceof BreakStatement){
				if (stat.getSuperStatement() == null)
					return false;
				else if (!(stat.getSuperStatement() instanceof WhileStatement)){
					if (stat.getSuperStatement().getSuperStatement() == null || 
							(! (stat.getSuperStatement().getSuperStatement() instanceof WhileStatement)))
						return false;
				}
			}
			else if(stat instanceof SequenceStatement){
				if (! isWellFormed(stat,variableNames))
					return false;
			}
			else{
					Expression<?> e = ((ExpressionStatement<?>)stat).getExpression();
					if (e instanceof BracketVariableExpression){
						e = ((BracketVariableExpression)e).getExpression();
					}
					if (e instanceof BasicVariableExpression<?>){
						boolean variableAssigned = false;
						for (String name: variableNames){
							if (name.equals(((BasicVariableExpression<?>)e).getName())){
								variableAssigned = true;
							}
							
						}
						if (!variableAssigned){
							return false;
						}
					}
					if (stat instanceof IComposedUnaryStatement<?>){
						if (!isWellFormed((Statement)((IComposedUnaryStatement<?>)stat).getStatement(),variableNames))
							return false;
					}
					if (stat instanceof IComposedBinaryStatement<?,?>){
						if (!isWellFormed((Statement)((IComposedBinaryStatement<?,?>)stat).getFirstStatement(),variableNames))
							return false;
						if (!isWellFormed((Statement)((IComposedBinaryStatement<?,?>)stat).getSecondStatement(),variableNames))
							return false;

					}
					
				}
			}
		return true;
		}
	

	/**
	 * Return the scheduled unit for this task.
	 * @return the scheduledUnit
	 * 			| result == scheduledUnit
	 */
	@Basic @Raw
	public Unit getScheduledUnit() {
		return scheduledUnit;
	}


	/**
	 * Set the scheduled unit of this task to the given unit.
	 * @param scheduledUnit 
	 * 			the scheduledUnit to set
	 * @post The given unit is the scheduled unit for this new task.
	 * 		| new.getScheduledUnit() == scheduledUnit
	 */
	@Raw
	public void setScheduledUnit(Unit scheduledUnit) {
		
		this.scheduledUnit = scheduledUnit;
		if (scheduledUnit != null)
			scheduledUnit.setScheduledTask(this);
	}

	/**
	 * Variable registering the scheduled unit for this task.
	 */
	private Unit scheduledUnit;

	/**
	 * Return the executing unit of this task.
	 * 
	 * @return the executing unit
	 * 			| result == executingUnit
	 */
	@Raw @Basic
	public Unit getExecutingUnit() {
		return this.getExecutionContext().getExecutingUnit();
	}


	/**
	 * Set the executing unit for this task to the given executing unit.
	 * 
	 * @param executingUnit 
	 * 			the executingUnit to set
	 * @effect If the current executing unit of this task is not null, set its task to null
	 * 			|if (this.getExecutingUnit()!= null)
	 *			|	this.getExecutingUnit().setTask(null);
	 * @post The executing unit of this new task is the given unit.
	 * 			|new.getExecutingUnit() == executingUnit
	 * @effect The task of the given executing unit is set to this task.
	 * 			|executingUnit.setTask(this);
	 * 
	 */
	@Raw
	public void setExecutingUnit(Unit executingUnit) throws IllegalArgumentException {
		if (!canHaveAsExecutingUnit(executingUnit)){
			throw new IllegalArgumentException();
		}
		if (this.getExecutingUnit()!= null)
			this.getExecutingUnit().setTask(null);
		this.getExecutionContext().setExecutingUnit(executingUnit);
		
		this.setScheduledUnit(executingUnit);
		if (executingUnit != null)
			executingUnit.setTask(this);
	}

	
	/**
	 * Check whether the executing unit is a valid executing unit for this task.
	 * @param executingUnit
	 * 			The unit to check 
	 * @return If the selected cube of this task is not null, 
	 * 			return true if and only if the selected cube is in the world of the given unit.
	 * 	       Else, return true.
	 * 			|if (this.getSelectedCube() !=null)
	 *			|	result == executingUnit.getWorld().isCubeInWorld(this.getSelectedCube());
	 *			|else
	 *			|   result == true;
	 */
	public boolean canHaveAsExecutingUnit(Unit executingUnit) {
		if (executingUnit != null && this.getSelectedCube() !=null)
			return executingUnit.getWorld().isCubeInWorld(this.getSelectedCube());
		return true;
	}

	/**
	 * Return the selected Cube for this task.
	 * 
	 * @return The selected cube of this task.
	 * 			| result == this.getExecutionContext().getSelectedCube()
	 */
	public int[] getSelectedCube() {
		return this.getExecutionContext().getSelectedCube();
	}


	/**
	 * Set the selected cube for this task to the given selected cube.
	 * 
	 * @param selectedCube 
	 * 		the selectedCube to set
	 * @post The selected cube of this new task is the given cube.
	 * 			|new.getSelectedCube() == selectedCube
	 */
	private void setSelectedCube(int[] selectedCube) {
		 this.getExecutionContext().setSelectedCube(selectedCube);
	}
	
	
	/**
	 * Add the variable with the given name and given assigned expression to this task.
	 * 
	 * @param variableName
	 * 			the name of the new variable for this new task.
	 * @param expr
	 * 			The assigned expression of the new variable of this new task
	 * @effect The variable with the given name and assigned expression is 
	 * 			added to the execution context of this new task.
	 * 			|this.getExecutionContext().addVariable(variableName,expr);
	 */
	public void addVariable(String variableName,Expression<?> expr){
		 this.getExecutionContext().addVariable(variableName,expr);
	}
	
	/**
	 * 
	 * Return the execution context of this task.
	 */
	@Raw @Basic
	public ExecutionContext getExecutionContext(){
		return executionContext;
	}
	
	/**
	 * Set the execution context of this new task to the given execution context
	 * @param executionContext
	 * 		| the execution context for this new task
	 * @post The execution context of this new task is equal to the given executioncontext.
	 * 		| new.getExecutionContext() == executionContext
	 */
	@Raw
	private void setExecutionContext(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}
	
	/**
	 * Variable registering the execution Context of this task.
	 */
	private ExecutionContext executionContext;
	
	/**
	 * Execute this task.
	 * 
	 * @effect Execute the activities statement of this task.
	 * 			|getActivities().executeStatement(getExecutionContext())
	 * @throws NullPointerException
	 * 			If the executing unit of this task is null.
	 * 			|this.getExecutingUnit()==null
	 * 
	 */
	public void executeTask() throws NullPointerException{
		if (this.getExecutingUnit() == null)
			throw new NullPointerException();
		getActivities().executeStatement(getExecutionContext());
	}
	
	/**
	 * Interrupt the execution of this task. Make the executing unit stop executing this task
	 *  and reduce the priority of this task.
	 * 
	 * @effect The executing unit of this task is set to null.
	 * @effect the activities of this task is set to a sequence statement containing the completed activities
	 * 			and the non-completed activities.
	 * 			|this.setActivities(this.getCompletedActivities().addStatement(this.getActivities()))
	 * @effect The priority of this task is reduced with one.
	 * 			|this.setPriority(getPriority()-1)
	 */
	public void interruptExecution(){
		this.getExecutingUnit().stopExecutingTask();
		//this.setExecutingUnit(null);
		//this.setActivities(activities);
		this.setPriority(this.getPriority()-1);

	}
	
	/**
	 * Return a string representing this task: the name of this task.
	 * 
	 * @return the name of this task as a string
	 * 			| result == this.getName()
	 */
	@Override
	public String toString(){
		return getName().toString();
	}
	
	/**
	 * Check whether all activities of this task are still executable.
	 * @return True if and only if all expressions that return a unit, still return an effective unit
	 * 		and all the expressions that return a position, still return a valid position.
	 * 		| for all the expression in the statements of the activities:
	 * 		| 	if expression is a unitExpression
	 * 		|		then result == (expression.evaluateExpression(executionContext)!=null)
	 * 		|	if expression is a positionExpression
	 * 		|		if expression.evaluateExpression(executionContext)!=null
	 * 		|			then result == (getExecutingUnit().canHaveAsPosition(this.getExecutingUnit().getWorld().getCubeCenter(((PositionExpression)expression).evaluateExpression(executionContext).getCoords())))
	 * 		|		else result == false
	 * 		|	else result == true
	 */
	public boolean executableActivities(){
		List<Statement> statements = new ArrayList<Statement>();
		List<Expression<?>> expressions = new ArrayList<Expression<?>>();
	
		if (activities instanceof SequenceStatement){
			statements = (List<Statement>) ((SequenceStatement<?>)activities).getStatements();
			for(Statement stat: statements){
				if(stat instanceof ExpressionStatement){
					expressions.add(((ExpressionStatement<?>) stat).getExpression());
				}
			}
		}
		else if(activities instanceof ExpressionStatement){
				expressions.add(((ExpressionStatement<?>) activities).getExpression());
			}
	
		for (Expression<?> expr: expressions){
			if (expr instanceof UnitExpression){
				return expr.evaluateExpression(executionContext)!=null;
			}
			if (expr instanceof PositionExpression)
				if(expr.evaluateExpression(executionContext)!=null)
					return getExecutingUnit().canHaveAsPosition(this.getExecutingUnit().getWorld().getCubeCenter(((PositionExpression)expr).evaluateExpression(executionContext).getCoords()));
				else
					return false;
		}
		return true;
	}
	
	/**
	 * Add the given scheduler as a scheduler of this task.
	 * @param scheduler
	 * 			The scheduler to add to the set of schedulers
	 * @throws IllegalArgumentException
	 * 			If the given scheduler does not have this task as its task.
	 * 			| (!scheduler.hasAsTask(this))
	 */
	void addAsScheduler(@Raw Scheduler scheduler) throws IllegalArgumentException {
		if (!scheduler.hasAsTask(this))
			throw new IllegalArgumentException();
		this.schedulers.add(scheduler);
		
	}
	
	/**
	 * Return all the schedulers of which this task is a task.
	 */
	@Basic @Raw
	public Set<Scheduler> getSchedulers(){
		return this.schedulers;
	}
	/**
	 * Variable registering the set of schedulers of which this task is a task.
	 */
	private Set<Scheduler> schedulers = new HashSet<Scheduler>();

	/**
	 * Remove the given scheduler from the set of schedulers of this task.
	 * @param scheduler
	 * 			The scheduler to remove
	 * @post The given scheduler is not a scheduler of this new task.
	 * 		| ! this.getSchedulers().contains(scheduler)
	 */
	public void removeAsScheduler(Scheduler scheduler) {
		if (this.getSchedulers().contains(scheduler))
			schedulers.remove(scheduler);
		
	}
	
//	public void restoreTask(){
//		List<Statement> statements = new ArrayList<Statement>();
//		Statement activities = this.getCompletedActivities();
//		Statement activities2 = this.getActivities();
//		if (activities instanceof SequenceStatement){
//			statements = (List<Statement>) ((SequenceStatement<?>)activities).getStatements();
//			for(Statement stat: statements){
//					statements.add(stat);
//			}
//		}
//		else if(activities instanceof ExpressionStatement){
//				statements.add(activities);
//			}
//		if (activities2 instanceof SequenceStatement){
//			statements = (List<Statement>) ((SequenceStatement<?>)activities2).getStatements();
//			for(Statement stat: statements){
//					statements.add(stat);
//			}
//		}
//		else if(activities2 instanceof ExpressionStatement){
//				statements.add(activities2);
//			}
//		this.getExecutingUnit().stopExecutingTask();
//		this.setActivities((Statement) statements);
//	}

}

