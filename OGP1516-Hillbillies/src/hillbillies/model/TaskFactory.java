package hillbillies.model;
import java.util.*;

import hillbillies.model.expression.*;
import hillbillies.model.statement.*;
import hillbillies.model.Position;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.SourceLocation;

public class TaskFactory implements ITaskFactory<Expression<?>, Statement, Task> {


	public TaskFactory(){
		
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
		return new AssignmentStatement<Expression>(variableName, value); // correct? 
	}

	@Override
	public Statement createWhile(Expression condition, Statement body, SourceLocation sourceLocation) {
		return new WhileStatement<BooleanExpression,Statement>((BooleanExpression) condition,(ComposedStatement) body);
	}

	@Override
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody, SourceLocation sourceLocation) {
		try{
			return new IfElseStatement<BooleanExpression,ComposedStatement,ComposedStatement>((BooleanExpression) condition,(ComposedStatement) ifBody, (ComposedStatement)elseBody);
		}
		catch(ClassCastException c){
			if (condition instanceof BasicVariableExpression){
					return new IfElseStatement<BasicVariableExpression<Boolean>,ComposedStatement,ComposedStatement>((BasicVariableExpression<Boolean>) condition,(ComposedStatement) ifBody, (ComposedStatement)elseBody);
			}
			else
				throw new ClassCastException();
		}
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new BreakStatement();
	}

	@Override
	public Statement createPrint(Expression value, SourceLocation sourceLocation) {
		return new PrintStatement<Expression>(value);
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new SequenceStatement<Statement>(statements);
	}

	@Override
	public Statement createMoveTo(Expression<?> position, SourceLocation sourceLocation) {
		try{
			return new MoveToStatement<PositionExpression>((PositionExpression) position);
		}
		catch(ClassCastException c){
			if (position instanceof BasicVariableExpression){
				return new MoveToStatement<BasicVariableExpression<Position>>((BasicVariableExpression<Position>) position);
			}
			else
				throw new ClassCastException();
		}
	}

	@Override
	public Statement createWork(Expression<?> position, SourceLocation sourceLocation) {
		try{
			return new WorkStatement<PositionExpression>((PositionExpression) position);
		}
		catch(ClassCastException c){
			if (position instanceof BasicVariableExpression){
				return new WorkStatement<BasicVariableExpression<Position>>((BasicVariableExpression<Position>) position);
			}
			else
				throw new ClassCastException();
		}

		
	}

	@Override
	public Statement createFollow(Expression<?> unit, SourceLocation sourceLocation) {
		try{
			return new FollowStatement<UnitExpression>((UnitExpression) unit);
		}
		catch(ClassCastException c){
			if (unit instanceof BasicVariableExpression){
				return new FollowStatement<BasicVariableExpression<Unit>>((BasicVariableExpression<Unit>) unit);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Statement createAttack(Expression<?> unit, SourceLocation sourceLocation) {
		try{
			return new AttackStatement<UnitExpression>((UnitExpression) unit);
		}
		catch(ClassCastException c){
			if (unit instanceof BasicVariableExpression){
				return new AttackStatement<BasicVariableExpression<Unit>>((BasicVariableExpression<Unit>) unit);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createReadVariable(String variableName, SourceLocation sourceLocation) {
		return new BasicVariableExpression<Object>(variableName);
	}

	@Override
	public Expression<?> createIsSolid(Expression<?> position, SourceLocation sourceLocation) {
		try{
			return new IsSolidExpression<PositionExpression>((PositionExpression) position);
		}
		catch(ClassCastException c){
			if (position instanceof BasicVariableExpression){
				return new IsSolidExpression<BasicVariableExpression<Position>>((BasicVariableExpression<Position>) position);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createIsPassable(Expression<?> position, SourceLocation sourceLocation) {
		try{
			return new IsPassableExpression<PositionExpression>((PositionExpression) position);
		}
		catch(ClassCastException c){
			if (position instanceof BasicVariableExpression){
				return new IsPassableExpression<BasicVariableExpression<Position>>((BasicVariableExpression<Position>) position);
			}
			else
				throw new ClassCastException();
		}
	}

	@Override
	public Expression<?> createIsFriend(Expression<?> unit, SourceLocation sourceLocation) {
		try{
			return new IsFriendExpression<UnitExpression>((UnitExpression) unit);
		}
		catch(ClassCastException c){
			if (unit instanceof BasicVariableExpression){
				return new IsFriendExpression<BasicVariableExpression<Unit>>((BasicVariableExpression<Unit>) unit);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createIsEnemy(Expression<?> unit, SourceLocation sourceLocation) {
		try{
			return new IsEnemyExpression<UnitExpression>((UnitExpression) unit);
		}
		catch(ClassCastException c){
			if (unit instanceof BasicVariableExpression){
				return new IsEnemyExpression<BasicVariableExpression<Unit>>((BasicVariableExpression<Unit>) unit);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createIsAlive(Expression<?> unit, SourceLocation sourceLocation) {
		try{
			return new IsAliveExpression<UnitExpression>((UnitExpression) unit);
		}
		catch(ClassCastException c){
			if (unit instanceof BasicVariableExpression){
				return new IsAliveExpression<BasicVariableExpression<Unit>>((BasicVariableExpression<Unit>) unit);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createCarriesItem(Expression<?> unit, SourceLocation sourceLocation) {
		try{
			return new CarriesItemExpression<UnitExpression>((UnitExpression) unit);
		}
		catch(ClassCastException c){
			if (unit instanceof BasicVariableExpression){
				return new CarriesItemExpression<BasicVariableExpression<Unit>>((BasicVariableExpression<Unit>) unit);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createNot(Expression<?> expression, SourceLocation sourceLocation) {
		try{
			return new NegationExpression<BooleanExpression>((BooleanExpression) expression);
		}
		catch(ClassCastException c){
			if (expression instanceof BasicVariableExpression){
				return new NegationExpression<BasicVariableExpression<Boolean>>((BasicVariableExpression<Boolean>) expression);
			}
			else
				throw new ClassCastException();
		}

	}
	
	@Override
	public Expression createAnd(Expression left, Expression right, SourceLocation sourceLocation) {
		try{
			return new AndExpression<BooleanExpression>((BooleanExpression)left, (BooleanExpression)right);
		}
		catch(ClassCastException c){
			if (expression instanceof BasicVariableExpression){
				return new AndExpression<BasicVariableExpression<Boolean>>((BasicVariableExpression<Boolean>) expression);
			}
			else
				throw new ClassCastException();
		}

	}

	@Override
	public Expression<?> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new OrExpression<Expression<Boolean>>((Expression<Boolean>)left, (Expression<Boolean>)right);
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
		return new NextToExpression<>((PositionExpression) position);
	}

	@Override
	public Expression createLiteralPosition(int x, int y, int z, SourceLocation sourceLocation) {
		return new XYZExpression(new Position(new int[] {x,y,z}));
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
		return new TrueExpression();
	}

	@Override
	public Expression createFalse(SourceLocation sourceLocation) {
		return new FalseExpression();
	}

	@Override
	public Expression createPositionOf(Expression unit, SourceLocation sourceLocation) {
		return new PositionOfExpression<>((UnitExpression) unit);
	}








}
