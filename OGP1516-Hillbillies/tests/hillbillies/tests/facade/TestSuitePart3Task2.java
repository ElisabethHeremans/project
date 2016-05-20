package hillbillies.tests.facade;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hillbillies.model.*;
import hillbillies.model.expression.XYZExpression;
import hillbillies.model.statement.Statement;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.TaskParser;
import ogp.framework.util.ModelException;

public class TestSuitePart3Task2 {
	private Facade facade;

	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;

	@Before
	public void setup() {
		this.facade = new Facade();
	}
	
	@Test
	public void test_carriesItemAndLogFalling() throws ModelException {
		System.out.println("*******************TEST LOG FALLING AND CARRYING ITEM************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		Log log = new Log(new int[] {0,1,1});
		world.addAsLog(log);
		Boulder boulder = new Boulder(new int[] {0,0,1});
		facade.addUnit(unit, world);
		world.addAsBoulder(boulder);
		Faction faction = facade.getFaction(unit);
		Assert.assertEquals(1, world.getLogs(new int[] {0,1,1}).size());
		Assert.assertEquals(1, world.getBoulders(new int[] {0,0,1}).size());
		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		List<Task> tasks2 = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 2\nactivities: x := log; work selected; if carries_item this then moveTo (0,0,1); else moveTo (0,0,2); fi y:= selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 0, 1, 0 }));
		
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		Task task1 = tasks2.get(0);
		// test name
		assertEquals("work task", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
		facade.schedule(scheduler, task1);
		Assert.assertTrue(scheduler.getTasks().size() ==2);
		advanceTimeFor(facade, world,14 , 0.02);
		// first task is removed from scheduler
		Assert.assertEquals(1,scheduler.getTasks().size());
		assertTrue(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
		Assert.assertEquals(0, world.getLogs(new int[] {0,1,0}).size());
		Assert.assertEquals(1, world.getBoulders(new int[] {0,0,0}).size());
		Assert.assertArrayEquals(new int[] {0,0,1}, unit.getCubeCoordinate());

	}
	
	@Test
	public void test_Break() throws ModelException{
		System.out.println("*******************TEST BREAK************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		Log log = new Log(new int[] {0,1,1});
		world.addAsLog(log);
		Boulder boulder = new Boulder(new int[] {0,0,1});
		facade.addUnit(unit, world);
		world.addAsBoulder(boulder);
		Faction faction = facade.getFaction(unit);
		Assert.assertEquals(1, world.getLogs(new int[] {0,1,1}).size());
		Assert.assertEquals(1, world.getBoulders(new int[] {0,0,1}).size());
		Scheduler scheduler = facade.getScheduler(faction);
		assertEquals(TerrainType.ROCK, world.getTerrain(new int[] { 1, 1, 1 }));

		List<Task> tasks2 = TaskParser.parseTasksFromString(
				"name: \"while loop break\"\npriority: -10\nactivities: while is_solid selected do moveTo (0,0,1); break; work selected; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: -100\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 0, 0, 0 }));

		
		// tasks are created
		assertNotNull(tasks2);
		// there's exactly one task
		Task task1 = tasks2.get(0);
		Task task2 = tasks.get(0);
		// test name
		assertEquals("while loop break", facade.getName(task1));
		// test priority
		assertEquals(-10, facade.getPriority(task1));
		
		facade.schedule(scheduler, task1);
		facade.schedule(scheduler, task2);
		advanceTimeFor(facade, world,5 , 0.02);
		// work task is removed from scheduler
		Assert.assertEquals(1,scheduler.getTasks().size());
		
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
		
		assertEquals(TerrainType.ROCK, world.getTerrain(new int[] { 1, 1, 1 }));
		Assert.assertArrayEquals(new int[] {0,0,1}, unit.getCubeCoordinate());


	}

	@Test
	public void test_WellFormedTrue(){
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := log; if carries_item this then moveTo x; "
				+ "else moveTo (0,0,2); fi moveTo boulder; while true do print x; break; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		// no problem making these tasks -> wellFormed = true
	}
	
	@Test
	public void test_WellFormedFalseNonInstantiatedVariable(){
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := log; if carries_item this then moveTo x; "
				+ "else moveTo (0,0,2); fi moveTo boulder; while true do print y; break; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		Assert.assertTrue(tasks == null);
		//Task task= tasks.get(0);
	}
	
	@Test
	public void test_WellFormedFalseBreakWrong(){
		System.out.println("TEST WELLFORMED FALSE");
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := log; if carries_item this then moveTo x; "
				+ "else break; fi moveTo boulder; while true do print x; break; done;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		Assert.assertTrue(tasks == null);

	}
	
	@Test
	public void test_ExecuteTaskEffective() throws ModelException{
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		world.addAsUnit(unit);
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := log; if carries_item this then moveTo x; "
				+ "else moveTo (0,0,2); fi moveTo boulder; while true do print x; break; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		Faction faction = unit.getFaction();
		Scheduler scheduler = facade.getScheduler(faction);

		facade.schedule(scheduler, task);
		
		task.setExecutingUnit(unit);
		task.executeTask();
		Assert.assertEquals(task, unit.getTask());
		Assert.assertTrue(unit.getCurrentStatement()!=null);
	}
	
	@Test
	public void test_InterruptExecution() throws ModelException{
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		world.addAsUnit(unit);
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := log; if carries_item this then moveTo x; "
				+ "else moveTo (0,0,2); fi moveTo boulder; while true do print x; break; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		Faction faction = unit.getFaction();
		Scheduler scheduler = facade.getScheduler(faction);

		facade.schedule(scheduler, task);
		
		task.setExecutingUnit(unit);
		task.executeTask();
		Assert.assertEquals(task, unit.getTask());
		Assert.assertTrue(unit.getCurrentStatement()!=null);
		task.interruptExecution();
		Assert.assertEquals(null, task.getExecutingUnit());
		Assert.assertEquals(null, unit.getTask());
		Assert.assertEquals(0, task.getPriority());
	}
	
	@Test
	public void test_StopExecutingActivities_DontStop() throws ModelException{
		System.out.println("STOP EXECUTING ACTIVITIES");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		world.addAsUnit(unit);
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := selected; while true do work x; moveTo selected; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		Faction faction = unit.getFaction();
		Scheduler scheduler = facade.getScheduler(faction);

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world,0.2 , 0.02);
		//try interrupting activity
		unit.moveTo1(new int[] {0,0,2});
		advanceTimeFor(facade,world,15,0.02);
		System.out.println(Arrays.toString(unit.getPosition()));
		Assert.assertEquals(TerrainType.AIR, world.getTerrain(new int[] { 1, 1, 1 }));
		
		assertTrue(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
		Assert.assertArrayEquals(new int[] {1,1,1}, unit.getCubeCoordinate());
		

	}
	
	@Test
	public void test_StopExecutingActivities_Effective() throws ModelException{
		System.out.println("STOP EXECUTING ACTIVITIES 2");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		Unit unit2 = facade.createUnit("Test", new int[] { 1, 0, 0 }, 50, 50, 50, 50, true);
		world.addAsUnit(unit2);
		world.addAsUnit(unit);
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"task\"\npriority: 1\nactivities: x := selected; while true do work x; moveTo selected; done", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		Task task = tasks.get(0);
		Faction faction = unit.getFaction();
		Scheduler scheduler = facade.getScheduler(faction);

		facade.schedule(scheduler, task);
		advanceTimeFor(facade, world,0.2 , 0.02);
		//try interrupting activity
		unit.defend(unit2);
		
		assertEquals(0,task.getPriority());
		
		assertFalse(task.isComplete());
	}

	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(IFacade facade, World world, double time, double step) throws ModelException {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			facade.advanceTime(world, step);
			
		facade.advanceTime(world, time - n * step);
	}

}
