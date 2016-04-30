package hillbillies.model;

import java.util.ArrayList;
import java.util.*;

import hillbillies.model.expression.*;
import hillbillies.model.statement.*;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression, Statement, Task> {

	public TaskFactory(Expression,Statement,Task){
		
	}
	
	@Override
	public List<Task> createTasks(String name, int priority, Statement activity, List<int[]> selectedCubes) {
		List<Task> tasks = new ArrayList<Task>();
		if (selectedCubes.isEmpty()){
			tasks.add(new Task(name,priority,activity));

		}
		else{
			for (int[] selectedCube: selectedCubes){
				Task task = new Task(name,priority,activity,selectedCube);
				tasks.add(task);
			}
		}
		
		return tasks;
	}

	@Override
	public Statement createAssignment(String variableName, Expression value, SourceLocation sourceLocation) {
		return new AssignmentStatement(variableName, value);
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement(condition, body);
	}

	@Override
	public Statement createIf(Expression condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		return new IfElseStatement(condition, ifBody, elseBody);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement();
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceStatement(statements);
	}

	@Override
	public Statement createMoveTo(Expression position, SourceLocation sourceLocation) {
		return new MoveToStatement(position);
	}

	@Override
	public Statement createWork(Expression position, SourceLocation sourceLocation) {
		return new WorkStatement(position);
	}

	@Override
	public Statement createFollow(Expression unit, SourceLocation sourceLocation) {
		return new FollowStatement(unit);
	}

	@Override
	public Statement createAttack(Expression unit, SourceLocation sourceLocation) {
		return new AttackStatement(unit);
	}

	@Override
	public Expression createReadVariable(String variableName, SourceLocation sourceLocation) {
		return null;
	}

	@Override
	public Expression createIsSolid(Expression position, SourceLocation sourceLocation) {
		return new IsSolidExpression(position);
	}

	@Override
	public Expression createIsPassable(Expression position, SourceLocation sourceLocation) {
		return new IsPassableExpression(position);
	}

	@Override
	public Expression createIsFriend(Expression unit, SourceLocation sourceLocation) {
		return new IsFriendExpression(unit);
	}

	@Override
	public Expression createIsEnemy(Expression unit, SourceLocation sourceLocation) {
		return new IsEnemyExpression(unit);
	}

	@Override
	public Expression createIsAlive(Expression unit, SourceLocation sourceLocation) {
		return new IsAliveExpression(unit);
	}

	@Override
	public Expression createCarriesItem(Expression unit, SourceLocation sourceLocation) {
		return new CarriesItemExpression(unit);
	}

	@Override
	public Expression createNot(Expression expression, SourceLocation sourceLocation) {
		return new NegationExpression(expression);
	}

	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		return new AndExpression(left, right);
	}

	@Override
	public Expression createOr(Expression left, Expression right, SourceLocation sourceLocation) {
		return new OrExpression(left, right);
	}

	@Override
	public Expression createHerePosition(SourceLocation sourceLocation) {
		return new HereExpression();
	}

	@Override
	public Expression createLogPosition(SourceLocation sourceLocation) {
		return new LogExpression();
	}

	@Override
	public Expression createBoulderPosition(SourceLocation sourceLocation) {
		return new BoulderExpression();
	}

	@Override
	public Expression createWorkshopPosition(SourceLocation sourceLocation) {
		return new WorkshopExpression();
	}

	@Override
	public Expression createSelectedPosition(SourceLocation sourceLocation) {
		return new SelectedExpression();
	}

	@Override
	public Expression createNextToPosition(Expression position, SourceLocation sourceLocation) {
		return new NextToExpression(position);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new XYZExpression(x,y,z);
	}

	@Override
	public Expression createThis(SourceLocation sourceLocation) {
		return new ThisExpression();
	}

	@Override
	public Expression createFriend(SourceLocation sourceLocation) {
		return new FriendExpression();
	}

	@Override
	public Expression createEnemy(SourceLocation sourceLocation) {
		return new EnemyExpression();
	}

	@Override
	public Expression createAny(SourceLocation sourceLocation) {
		return new AnyExpression();
	}

	@Override
	public Expression createTrue(SourceLocation sourceLocation) {
		BasicBExpression expression = new BasicBExpression();
		expression.setValue(true);
		expression.setSourceLocation(sourceLocation);
		return expression;
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}


}
