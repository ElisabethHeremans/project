package hillbillies.tests.facade;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import hillbillies.model.*;
import hillbillies.model.expression.*;
import hillbillies.model.statement.*;
import hillbillies.part3.programs.SourceLocation;

public class TestSuitePart3TaskFactory {
	
	private TaskFactory factory;
	private Expression falseExpr;
	private Expression enemyExpr;
	private Expression xyz;
	private Expression var;
	private SourceLocation sourceLoc;
	private Statement moveTo;
	private Statement print;
	private Statement breakStat;
	private List<Statement> list;
	private Task task;
	private List<int[]> selectedCubes;
	
	@Before
	public void setUpBefore(){
		factory = new TaskFactory();
		falseExpr = new FalseExpression();
		enemyExpr = new EnemyExpression();
		xyz = new XYZExpression(new Position(new int[] {1,1,1}));
		var = new BasicVariableExpression("var");
		sourceLoc = new SourceLocation(5,3);
		print = new PrintStatement<Expression>(enemyExpr);
		breakStat = new BreakStatement();
		list = new ArrayList<Statement>();
		list.add(moveTo);
		list.add(print);
		selectedCubes = new ArrayList<int[]>();
		selectedCubes.add(new int[] {1,1,1});
		selectedCubes.add(new int[] {1,2,3});

	}
	
	@Test
	public final void createTasks_valid(){
		Statement s = new SequenceStatement<Statement>(list);
		List<Task> tasks = factory.createTasks("x", 20, s, selectedCubes);
		Task task1 = tasks.get(0);
		Assert.assertEquals("x",task1.getName());
		Assert.assertArrayEquals(new int[] {1,1,1},task1.getExecutionContext().getSelectedCube());
		Assert.assertEquals(20,task1.getPriority());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void createTasks_NotWellFormed(){
		list.add(breakStat);
		Statement s = new SequenceStatement<Statement>(list);
		factory.createTasks("x", 20, s, selectedCubes);
	}
	
	@Test
	public final void createAssignment_valid(){
		Statement stat = factory.createAssignment("x", falseExpr, sourceLoc);
		Assert.assertTrue(stat instanceof AssignmentStatement<?>);
		Assert.assertEquals(falseExpr,((AssignmentStatement<?>) stat).getExpression());
		Assert.assertEquals("x", ((AssignmentStatement<?>) stat).getVariableName());
		
	}
	
	@Test
	public final void createWhile(){
		Statement stat= factory.createWhile(falseExpr, moveTo,  sourceLoc);
		Assert.assertTrue(stat instanceof WhileStatement<?,?>);
		Assert.assertEquals(falseExpr,((WhileStatement<?,?>) stat).getExpression());
		Assert.assertEquals(moveTo, ((WhileStatement<?,?>) stat).getStatement());

	}
	
	@Test(expected = ClassCastException.class)
	public final void createWhile_invalidExpression(){
		factory.createWhile(enemyExpr, moveTo,  sourceLoc);
	}
	
	
	@Test
	public final void createIf_valid(){
		Statement stat = factory.createIf(falseExpr,moveTo, print,  sourceLoc);
		Assert.assertTrue(stat instanceof IfElseStatement<?,?,?>);
		Assert.assertEquals(falseExpr,((IfElseStatement<?,?,?>) stat).getExpression());
		Assert.assertEquals(moveTo, ((IfElseStatement<?,?,?>) stat).getFirstStatement());
		Assert.assertEquals(print, ((IfElseStatement<?,?,?>) stat).getSecondStatement());

	}
		
	
	
	@Test(expected = ClassCastException.class)
	public final void createIf_invalidExpr(){
		factory.createIf(enemyExpr,moveTo, breakStat,  sourceLoc);
	}
	
	@Test
	public final void createBreak(){
		Statement stat = factory.createBreak(sourceLoc);
		Assert.assertTrue(stat instanceof BreakStatement);

	}
	
	@Test
	public final void createPrint(){
		Statement stat = factory.createPrint(xyz, sourceLoc);
		Assert.assertTrue(stat instanceof PrintStatement<?>);
		Assert.assertEquals(xyz,((PrintStatement<?>) stat).getExpression());
	}
	
	
	@Test
	public final void createSequence(){
		Statement stat = factory.createSequence(list, sourceLoc);
		Assert.assertTrue(stat instanceof SequenceStatement<?>);
		Assert.assertEquals(list,((SequenceStatement<?>) stat).getStatements());
	}
	
	@Test
	public final void createMoveTo_validExpr(){
		Statement stat = factory.createMoveTo(xyz, sourceLoc);
		Assert.assertTrue(stat instanceof MoveToStatement<?>);
		Assert.assertEquals(xyz,((MoveToStatement<?>) stat).getExpression());
		
	}

	
	@Test(expected = ClassCastException.class)
	public final void createMoveTo_invalidExpr(){
		factory.createMoveTo(enemyExpr, sourceLoc);
	}
	
	@Test
	public final void createWork_validExpr(){
		Statement stat = factory.createWork(xyz, sourceLoc);
		Assert.assertTrue(stat instanceof WorkStatement<?>);
		Assert.assertEquals(xyz,((WorkStatement<?>) stat).getExpression());
		
	}

	
	@Test(expected = ClassCastException.class)
	public final void createWork_invalidExpr(){
		factory.createWork(enemyExpr, sourceLoc);
	}

	@Test
	public final void createFollow_validExpr(){
		Statement stat = factory.createFollow(enemyExpr, sourceLoc);
		Assert.assertTrue(stat instanceof FollowStatement<?>);
		Assert.assertEquals(enemyExpr,((FollowStatement<?>) stat).getExpression());
		
	}

	
	@Test(expected = ClassCastException.class)
	public final void createFollow_invalidExpr(){
		factory.createFollow(falseExpr, sourceLoc);
	}
	
	@Test
	public final void createAttack_validExpr(){
		Statement stat = factory.createAttack(enemyExpr, sourceLoc);
		Assert.assertTrue(stat instanceof AttackStatement<?>);
		Assert.assertEquals(enemyExpr,((AttackStatement<?>) stat).getExpression());
		
	}

	
	@Test(expected = ClassCastException.class)
	public final void createAttack_invalidExpr(){
		factory.createAttack(falseExpr, sourceLoc);
	}
	
	@Test
	public final void createReadVariable(){
		Expression e = factory.createReadVariable("x", sourceLoc);
		Assert.assertTrue(e instanceof BasicVariableExpression);
		Assert.assertEquals("x",((BasicVariableExpression) e).getName());

	}
	
	@Test
	public final void createIsSolid_valid(){
		Expression e = factory.createIsSolid(xyz, sourceLoc);
		Assert.assertTrue(e instanceof IsSolidExpression<?>);
		Assert.assertEquals(xyz,((IsSolidExpression<?>) e).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createIsSolid_invalidExpr(){
		factory.createIsSolid(falseExpr, sourceLoc);
	}

	@Test
	public final void createIsPassable_valid(){
		Expression e = factory.createIsPassable(xyz, sourceLoc);
		Assert.assertTrue(e instanceof IsPassableExpression<?>);
		Assert.assertEquals(xyz,((IsPassableExpression<?>) e).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createIsPassable_invalidExpr(){
		factory.createIsPassable(falseExpr, sourceLoc);
	}
	
	@Test
	public final void createIsFriend_valid(){
		Expression e = factory.createIsFriend(enemyExpr, sourceLoc);
		Assert.assertTrue(e instanceof IsFriendExpression<?>);
		Assert.assertEquals(enemyExpr,((IsFriendExpression<?>) e).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createIsFriend_invalidExpr(){
		factory.createIsFriend(falseExpr, sourceLoc);
	}

	@Test
	public final void createIsEnemy_valid(){
		Expression e = factory.createIsEnemy(enemyExpr, sourceLoc);
		Assert.assertTrue(e instanceof IsEnemyExpression<?>);
		Assert.assertEquals(enemyExpr,((IsEnemyExpression<?>) e).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createIsEnemy_invalidExpr(){
		factory.createIsEnemy(falseExpr, sourceLoc);
	}

	@Test
	public final void createIsAlive_valid(){
		Expression e = factory.createIsAlive(enemyExpr, sourceLoc);
		Assert.assertTrue(e instanceof IsAliveExpression<?>);
		Assert.assertEquals(enemyExpr,((IsAliveExpression<?>) e).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createIsAlive_invalidExpr(){
		factory.createIsAlive(falseExpr, sourceLoc);
	}

	@Test
	public final void createCarriesItem_valid(){
		Expression e = factory.createCarriesItem(enemyExpr, sourceLoc);
		Assert.assertTrue(e instanceof CarriesItemExpression<?>);
		Assert.assertEquals(enemyExpr,((CarriesItemExpression<?>) e).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createCarriesItem_invalidExpr(){
		factory.createCarriesItem(falseExpr, sourceLoc);
	}
	
	@Test
	public final void createNot_valid(){
		Expression e1 = factory.createCarriesItem(enemyExpr, sourceLoc);
		Expression e2 = factory.createNot(e1, sourceLoc);
		Assert.assertTrue(e2 instanceof NegationExpression<?>);
		Assert.assertEquals(e1,((NegationExpression<?>) e2).getExpression());

	}
	@Test(expected = ClassCastException.class)
	public final void createNot_invalidExpr(){
		factory.createNot(enemyExpr, sourceLoc);
	}

	@Test
	public final void createAnd_valid(){
		Expression e1 = factory.createCarriesItem(enemyExpr, sourceLoc);
		Expression e2 = factory.createAnd(e1, falseExpr, sourceLoc);
		Assert.assertTrue(e2 instanceof AndExpression<?>);
		Assert.assertEquals(e1,((AndExpression<?>) e2).getLeftExpression());
		Assert.assertEquals(falseExpr,((AndExpression<?>) e2).getRightExpression());
	}
	@Test(expected = ClassCastException.class)
	public final void createAnd_invalidExpr(){
		factory.createAnd(enemyExpr, falseExpr, sourceLoc);
	}
	
	@Test
	public final void createOr_valid(){
		Expression e1 = factory.createCarriesItem(enemyExpr, sourceLoc);
		Expression e2 = factory.createOr(e1, falseExpr, sourceLoc);
		Assert.assertTrue(e2 instanceof OrExpression<?>);
		Assert.assertEquals(e1,((OrExpression<?>) e2).getLeftExpression());
		Assert.assertEquals(falseExpr,((OrExpression<?>) e2).getRightExpression());
	}
	@Test(expected = ClassCastException.class)
	public final void createOr_invalidExpr(){
		factory.createAnd(enemyExpr, falseExpr, sourceLoc);
	}

	
	@Test
	public final void createHerePosition_valid(){
		Expression e = factory.createHerePosition(sourceLoc);
		Assert.assertTrue(e instanceof HereExpression);

	}

	@Test
	public final void createLogPosition_valid(){
		Expression e = factory.createLogPosition(sourceLoc);
		Assert.assertTrue(e instanceof LogExpression);

	}
	
	@Test
	public final void createBoulderPosition_valid(){
		Expression e = factory.createBoulderPosition(sourceLoc);
		Assert.assertTrue(e instanceof BoulderExpression);

	}

	@Test
	public final void createWorkshopPosition_valid(){
		Expression e = factory.createWorkshopPosition(sourceLoc);
		Assert.assertTrue(e instanceof WorkshopExpression);

	}
	@Test
	public final void createSelectedPosition_valid(){
		Expression e = factory.createSelectedPosition(sourceLoc);
		Assert.assertTrue(e instanceof SelectedExpression);

	}

	@Test
	public final void createNextToPosition_validExpr(){
		Expression w = new WorkshopExpression();
		Expression e = factory.createNextToPosition(w, sourceLoc);
		Assert.assertTrue(e instanceof NextToExpression<?>);
		Assert.assertEquals(w,((NextToExpression<?>) e).getExpression());
		
	}

	
	@Test(expected = ClassCastException.class)
	public final void createNextToPosition_invalidExpr(){
		factory.createNextToPosition(falseExpr, sourceLoc);
	}
	
	@Test
	public final void createLiteralPosition(){
		Expression e = factory.createLiteralPosition(4,3,2, sourceLoc);
		Assert.assertTrue(e instanceof XYZExpression);
		Assert.assertEquals(new Position(new int[] {4,3,2}),((XYZExpression) e).getValue());
		
	}

	@Test
	public final void createThis(){
		Expression e = factory.createThis(sourceLoc);
		Assert.assertTrue(e instanceof ThisExpression);
		
	}
	
	@Test
	public final void createFriend(){
		Expression e = factory.createFriend(sourceLoc);
		Assert.assertTrue(e instanceof FriendExpression);
		
	}

	@Test
	public final void createEnemy(){
		Expression e = factory.createEnemy(sourceLoc);
		Assert.assertTrue(e instanceof EnemyExpression);
		
	}

	@Test
	public final void createAny(){
		Expression e = factory.createAny(sourceLoc);
		Assert.assertTrue(e instanceof AnyExpression);
		
	}
	@Test
	public final void createTrue(){
		Expression e = factory.createTrue(sourceLoc);
		Assert.assertTrue(e instanceof TrueExpression);
		Assert.assertEquals(true,((TrueExpression) e).getValue());
		
	}
	@Test
	public final void createFalse(){
		Expression e = factory.createFalse(sourceLoc);
		Assert.assertTrue(e instanceof FalseExpression);
		Assert.assertEquals(false,((FalseExpression) e).getValue());
		
	}


}
