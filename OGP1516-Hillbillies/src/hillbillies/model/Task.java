package hillbillies.model;

import java.util.ArrayList;
import java.util.HashMap;

import hillbillies.model.expression.*;
import java.util.List;
import java.util.Map;

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
	 * Variable registering the non-completed activities of this task.
	 */
	private Statement activities;


	/**
	 * Return whether the given statement is well formed.
	 * 
	 * @param activities
	 * 			The statement to check.
	 * @return For all the statements in statement:
	 * 			- if the statement is an AssignmentStatement
	 * 				add the variable to a list of defined variables
	 * 				|definedVariables.add(statement.getVariableName())
	 * 			- if the statement is a BreakStatement
	 * 				return false
	 * 				|result == false
	 * 			- if the statement is a SequenceStatement
	 * 				if this statement is well formed, return false
	 * 				|if (!isWellFormed(statement))
	 * 				|	result == false
	 * 			- if the statement is an ExpressionStatement
	 * 				- if the expression in the statement is a VariableExpression
	 * 					if the variable is not in the list of defined variables, return false
	 * 					| if (!definedVariables.contains(expression.getName()))
	 * 					|	result == false
	 * 				- if the statement is a ComposedStatement
	 * 					if the statement is not a while statement 
	 * 					and the statement in this statement is break, return false
	 * 					|if ((! statement instanceof WhileStatement) && statement.getStatement() instanceof BreakStatement))
	 * 					|	result == false
	 * @return Else, return true
	 * 			|result == true
	 */
	public static boolean isWellFormed(Statement activities){
		List<Statement> statements = new ArrayList<Statement>();
		List<String> variableNames = new ArrayList<String>();
	
		if (activities instanceof SequenceStatement){
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
					Expression<?> e = ((ExpressionStatement<?>)stat).getExpression();
					if (e instanceof BracketVariableExpression){
						e = ((BracketVariableExpression)e).getExpression();
					}
					if (e instanceof BasicVariableExpression){
						boolean variableAssigned = false;
						for (String name: variableNames){
							if (name == ((BasicVariableExpression<?>)e).getName())
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
	
	/**
	 * Remove the first statement of this task.
	 * 
	 * @effect If the activities is a sequencestatement, 
	 * 			remove the first statement from this sequencestatement 
	 * 			and add this statement to the completed activities of this task.
	 * 			|if ((this.getActivities() instanceof SequenceStatement))
	 * 			|completedActivities.addStatement(this.getActivities()).removeFirstStatement());
	 * 				If the whole sequencestatement of activities is executed, 
	 * 				set this task as complete, and set the activities of this task to null.
	 * 				|if (this.getActivities().isStatementExecuted())
	 * 				|this.setActivities(null)
	 * 				|this.setComplete(true)
	 * @effect If the activities is not a sequencestatement,
	 * 			set the activities of this task to null and set this task to completed.
	 * 			|if (!(this.getActivities() instanceof SequenceStatement))
	 * 			|completedActivities.addStatement(getActivities())
	 * 			|this.setActivities(null);
	 *			|this.setComplete(true);
	 */
	public void removeFirstStatement(){
		if (this.getActivities() instanceof SequenceStatement){
			Statement first = ((SequenceStatement<?>) this.getActivities()).removeFirstStatement();
			((SequenceStatement<?>)completedActivities).addStatement(first);
			//setCompletedActivities(getCompletedActivities());
			if (this.getActivities().isStatementExecuted()){
				this.setActivities(null);
				this.setComplete(true);
				//this.getExecutingUnit().
			}
		}
		else{
			((SequenceStatement<?>)completedActivities).addStatement(getActivities());
			this.setActivities(null);
			this.setComplete(true);
		}
		//System.out.println(((SequenceStatement<?>) this.getActivities()).getStatements());
		System.out.println(" first statement removed ");
	}
	
	/**
	 * Return the completedActivities
	 */
	@Basic @Raw
	public Statement getCompletedActivities() {
		return completedActivities;
	}

	/**
	 * Set the completed activities of this task to the given statement.
	 * 
	 * @param completedActivities 
	 * 			the completedActivities to set
	 * @post The completed activities of this new task is equal to completedActivities
	 * 			| new.getCompletedActivities() == completedActivities;
	 */
	@Raw
	public void setCompletedActivities(Statement completedActivities) {
		this.completedActivities = completedActivities;
	}
	
	/**
	 * Variable registering the completed activities of this task.
	 */
	private Statement completedActivities = new SequenceStatement<Statement>(new ArrayList<Statement>());


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
		System.out.println(" set exec unit ");
		this.getExecutionContext().setExecutingUnit(executingUnit);
		System.out.println(" set exec unit ");
		
		this.setScheduledUnit(executingUnit);
		System.out.println(" set exec unit ");
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
		System.out.print(" activities "+ getActivities() + "   ");
//		System.out.print(this.getExecutionContext().getSelectedCube());
//		System.out.print(this.getExecutionContext().getExecutingUnit());
		getActivities().executeStatement(getExecutionContext());
	}
	
	/**
	 * Interrupt the execution of this task. Set the executing unit to null, 
	 * re-add the completed statements to activities and reduce the priority of this task.
	 * 
	 * @effect The executing unit of this task is set to null.
	 * @effect the activities of this task is set to a sequence statement containing the completed activities
	 * 			and the non-completed activities.
	 * 			|this.setActivities(this.getCompletedActivities().addStatement(this.getActivities()))
	 * @effect The priority of this task is reduced with one.
	 * 			|this.setPriority(getPriority()-1)
	 */
	@SuppressWarnings("unchecked")
	public void interruptExecution(){
		this.setExecutingUnit(null);
		Statement activities = this.getCompletedActivities();
		((SequenceStatement<?>) activities).addStatement(getActivities());
		this.setActivities(activities);
		this.setPriority(getPriority()-1);
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
	
	public void restoreTask(){
		List<Statement> statements = new ArrayList<Statement>();
		Statement activities = this.getCompletedActivities();
		Statement activities2 = this.getActivities();
		if (activities instanceof SequenceStatement){
			statements = (List<Statement>) ((SequenceStatement<?>)activities).getStatements();
			for(Statement stat: statements){
					statements.add(stat);
			}
		}
		else if(activities instanceof ExpressionStatement){
				statements.add(activities);
			}
		if (activities2 instanceof SequenceStatement){
			statements = (List<Statement>) ((SequenceStatement<?>)activities2).getStatements();
			for(Statement stat: statements){
					statements.add(stat);
			}
		}
		else if(activities2 instanceof ExpressionStatement){
				statements.add(activities2);
			}
		this.getExecutingUnit().stopExecutingTask();
		this.setActivities((Statement) statements);
	}

}

