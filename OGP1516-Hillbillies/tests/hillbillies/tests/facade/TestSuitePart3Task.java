package hillbillies.tests.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.TaskParser;
import ogp.framework.util.ModelException;

public class TestSuitePart3Task {
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
	public void testTaskExecuted2() throws ModelException {
		System.out.println("*******************TEST 2************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		List<Task> task3 = TaskParser.parseTasksFromString(
				"name: \"work task highest priority\"\npriority: 2\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 0, 1, 1 }));
		
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		Task task1 = task3.get(0);
		// test name
		assertEquals("work task", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
		facade.schedule(scheduler, task1);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,24 , 0.02);
		
		

		// work task has been executed
		assertEquals(TYPE_AIR, facade.getCubeType(world, 0, 1, 1));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
	}
	
	@Test
	public void testTaskExecuted3() throws ModelException {
		System.out.println("*******************TEST 3************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

//		List<Task> tasks = TaskParser.parseTasksFromString(
//				"name: \"work task low priority\"\npriority: 1\nactivities: work selected;", facade.createTaskFactory(),
//				Collections.singletonList(new int[] { 1, 1, 1 }));
//		List<Task> task3 = TaskParser.parseTasksFromString(
//				"name: \"work task highest priority\"\npriority: 2\nactivities: work selected;", facade.createTaskFactory(),
//				Collections.singletonList(new int[] { 0, 1, 1 }));
		List<Task> task2 = TaskParser.parseTasksFromString(
				"name: \"print variable\"\npriority: 2\nactivities: x := this; print x;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 0, 1, 1 }));

		// tasks are created
		assertNotNull(task2);
		// there's exactly one task
		assertEquals(1, task2.size());
		Task task = task2.get(0);
		// test name
		assertEquals("print variable", facade.getName(task));
		// test priority
		assertEquals(2, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
		//System.out.print(scheduler.getTasks().size());
		//advanceTimeFor(facade, world, 24, 0.02);
		world.advanceTime(0.0009);
		world.advanceTime(0.0009);
		

		// work task has been executed
		//assertEquals(TYPE_AIR, facade.getCubeType(world, 0, 1, 1));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}

//	@Test
//	public void testTaskExecuted4() throws ModelException {
//		System.out.println("*******************TEST 4************************");
//		int[][][] types = new int[3][3][3];
//		types[1][1][0] = TYPE_ROCK;
//		types[1][1][1] = TYPE_ROCK;
//		types[1][1][2] = TYPE_TREE;
//		types[2][2][2] = TYPE_WORKSHOP;
//
//		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
//		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
//		facade.addUnit(unit, world);
//		Faction faction = facade.getFaction(unit);
//
//		Scheduler scheduler = facade.getScheduler(faction);
//
//		List<Task> tasks = TaskParser.parseTasksFromString(
//				"name: \"work task\"\npriority: 2\nactivities: work selected;", facade.createTaskFactory(),
//				Collections.singletonList(new int[] { 1, 1, 1 }));
//		List<Task> tasks1 = TaskParser.parseTasksFromString(
//				"name: \"move task\"\npriority: 1\nactivities: x := false; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
//				Collections.singletonList(new int[] { 0, 1, 1 }));
//		// tasks are created
//		assertNotNull(tasks);
//		assertNotNull(tasks1);
//		// there's exactly one task
//		assertEquals(1, tasks.size());
//		Task task = tasks.get(0);
//		Task task1 = tasks1.get(0);
//		// test name
//		assertEquals("work task", facade.getName(task));
//		// test priority
//		assertEquals(2, facade.getPriority(task));
//		
//		facade.schedule(scheduler, task);
//		facade.schedule(scheduler, task1);
//		System.out.print(scheduler.getTasks().size());
//		advanceTimeFor(facade, world,24 , 0.02);
//		
//		
//
//		// work task has been executed
//		assertEquals(TYPE_AIR, facade.getCubeType(world, 0, 1, 1));
//		Assert.assertArrayEquals(new int[] { 0, 1, 1 }, unit.getCubeCoordinate());
//		// work task is removed from scheduler
//		System.out.print("remaining tasks  "+scheduler.getTasks().size());
//		//System.out.print(scheduler.);
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
//	}
	
	@Test
	public void executableActivities_InvalidBoulder() throws ModelException{
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);
		
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"move to boulder\"\npriority: 1\nactivities: moveTo boulder;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);

		// test name
		assertEquals("move to boulder", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));
		
		facade.schedule(scheduler, task);

		task.setExecutingUnit(unit);
		Assert.assertFalse(task.executableActivities());;

	}
	
	@Test
	public void executableActivities_InvalidUnit() throws ModelException{
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[0][0][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		
		Unit unit = facade.createUnit("Test", new int[] { 1, 0, 0 }, 50, 50, 50, 50, true);
		Unit other = facade.createUnit("TestOther", new int[] { 0, 1, 1 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		facade.addUnit(other, world);
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);
		
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"move to friend\"\npriority: 1\nactivities: follow friend;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("move to friend", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
		task.setExecutingUnit(unit);
		Assert.assertFalse(task.executableActivities());;


	}

	@Test
	public void executableActivities_Valid() throws ModelException{
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[0][0][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		
		Unit unit = facade.createUnit("Test", new int[] { 1, 0, 0 }, 50, 50, 50, 50, true);
		Unit other = facade.createUnit("TestOther", new int[] { 0, 1, 1 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		facade.addUnit(other, world);
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);
		
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"move to enemy\"\npriority: 1\nactivities: follow enemy;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));

		
		
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		// test name
		assertEquals("move to enemy", facade.getName(task));
		// test priority
		assertEquals(1, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
		task.setExecutingUnit(unit);
		Assert.assertTrue(task.executableActivities());;

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
