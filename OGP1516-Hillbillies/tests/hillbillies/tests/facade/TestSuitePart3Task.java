package hillbillies.tests.facade;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hillbillies.model.*;
import hillbillies.model.Position;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.model.expression.XYZExpression;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.facade.IFacade;
import hillbillies.part3.programs.TaskParser;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

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
		world.advanceTime(0.0009);
		

		// work task has been executed
		//assertEquals(TYPE_AIR, facade.getCubeType(world, 0, 1, 1));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
	}

	@Test
	public void testTaskExecuted4() throws ModelException {
		System.out.println("*******************TEST 4************************");
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
				"name: \"work task\"\npriority: -10\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		List<Task> tasks1 = TaskParser.parseTasksFromString(
				"name: \"move task\"\npriority: 1\nactivities: x := false; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 0, 1, 1 }));
		List<Task> tasks2 = TaskParser.parseTasksFromString(
 				"name: \"move task effective\"\npriority: -5\nactivities: x := true; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
 				Collections.singletonList(new int[] { 0, 1, 2 }));
  
		// tasks are created
		assertNotNull(tasks);
		assertNotNull(tasks1);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
		Task task1 = tasks1.get(0);
		Task task2 = tasks2.get(0);
		// test name
		assertEquals("work task", facade.getName(task));
		// test priority
		assertEquals(-10, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
		facade.schedule(scheduler, task1);
		facade.schedule(scheduler, task2);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,10 , 0.02);
		
		

		// work task has been executed
		Assert.assertArrayEquals(new int[] { 0, 1, 2 }, unit.getCubeCoordinate());
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertTrue(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task2)));

	}
	
	@Test(expected = ClassCastException.class)
	public void variableExpression_invalidExpression() throws ModelException{
		System.out.println("*******************TEST 5************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;
		XYZExpression pos =new XYZExpression(new Position(new int[] {0,0,1}));
		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: -10\nactivities: x := true; print x; if x then moveTo x; fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		System.out.println("task made");
		Task task = tasks.get(0);
		facade.schedule(scheduler, task);
		
		advanceTimeFor(facade, world,10 , 0.02);

		
	}
	
	@Test
	public void variableExpression_validExpression() throws ModelException{
		System.out.println("*******************TEST 6************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;
		XYZExpression pos = new XYZExpression(new Position(new int[] {0,0,1}));
		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Faction faction = facade.getFaction(unit);
		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"move task\"\npriority: -10\nactivities: x := (0,0,1); print x; if true then moveTo x; fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		System.out.println("task made");
		Task task = tasks.get(0);
		facade.schedule(scheduler, task);
		
		advanceTimeFor(facade, world,10 , 0.02);
		
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));


		
	}
	
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
		Assert.assertFalse(task.executableActivities());


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
	
	@Test
	public void testTaskExecuted7() throws ModelException {
		System.out.println("*******************TEST 7************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Boulder boulder = new Boulder(new int[] {0,0,0});
		world.addAsBoulder(boulder);
		Assert.assertTrue(world.hasAsBoulder(boulder));
		Assert.assertTrue(unit.isNeighbouringOrSameCube(boulder.getWorld().getCubeCoordinate(boulder.getPosition())));

		unit.setBoulder(boulder);
		world.removeAsBoulder(boulder);
		Assert.assertEquals(boulder, unit.getBoulder());
		
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"dig tunnel\"\npriority: -10\nactivities: if (carries_item(this)) then work here; fi "
				+ "if (is_solid(1,1,2)) then moveTo (1,0,2); work (1,1,2); fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
//		List<Task> tasks1 = TaskParser.parseTasksFromString(
//				"name: \"move task\"\npriority: 1\nactivities: x := false; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
//				Collections.singletonList(new int[] { 0, 1, 1 }));
//		List<Task> tasks2 = TaskParser.parseTasksFromString(
// 				"name: \"move task effective\"\npriority: -5\nactivities: x := true; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
// 				Collections.singletonList(new int[] { 0, 1, 2 }));
  
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
//		Task task1 = tasks1.get(0);
//		Task task2 = tasks2.get(0);
		// test name
		assertEquals("dig tunnel", facade.getName(task));
		// test priority
		assertEquals(-10, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
//		facade.schedule(scheduler, task1);
//		facade.schedule(scheduler, task2);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,25 , 0.02);
		
		

		// work task has been executed
		Assert.assertTrue(world.getPassable(new int[] { 1, 1, 2 }));
		Assert.assertTrue(world.hasAsBoulder(boulder));
		Assert.assertArrayEquals(new double[] {0.5 , 0.5, 0.5}, boulder.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertArrayEquals(new int[] {0,0,0},boulder.getWorld().getCubeCoordinate(boulder.getPosition()));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task2)));

	}
	
	@Test
	public void testTaskExecuted8() throws ModelException {
		System.out.println("*******************TEST 8************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Boulder boulder = new Boulder(new int[] {0,0,0});
		world.addAsBoulder(boulder);
		Assert.assertTrue(world.hasAsBoulder(boulder));
		Assert.assertTrue(unit.isNeighbouringOrSameCube(boulder.getWorld().getCubeCoordinate(boulder.getPosition())));

		unit.setBoulder(boulder);
		world.removeAsBoulder(boulder);
		Assert.assertEquals(boulder, unit.getBoulder());
		
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"dig\"\npriority: -10\nactivities: if carries_item (this) then work here; fi if is_solid(selected) then moveTo (next_to selected); work selected; fi "
				, facade.createTaskFactory(),Collections.singletonList(new int[] { 1, 1, 1 }));
  
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
//		Task task1 = tasks1.get(0);
//		Task task2 = tasks2.get(0);
		// test name
		assertEquals("dig", facade.getName(task));
		// test priority
		assertEquals(-10, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
//		facade.schedule(scheduler, task1);
//		facade.schedule(scheduler, task2);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,25 , 0.02);
		
		

		// work task has been executed
		Assert.assertFalse(world.getPassable(new int[] { 1, 1, 2 }));
		Assert.assertTrue(world.hasAsBoulder(boulder));
		Assert.assertArrayEquals(new int[] {0,0,0},boulder.getWorld().getCubeCoordinate(boulder.getPosition()));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task2)));
		Assert.assertTrue(unit.getTask()==null);

	}
	
	@Test
	public void testTaskExecuted9() throws ModelException {
		System.out.println("*******************TEST 9************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[0][1][0] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 2, 0, 0 }, 95, 95, 95, 95, true);
		facade.addUnit(unit, world);
		Boulder boulder = new Boulder(new int[] {0,0,0});
		Log log = new Log(new int[] {1,0,0});
		world.addAsBoulder(boulder);
		world.addAsLog(log);
		Assert.assertTrue(world.hasAsBoulder(boulder));
		//Assert.assertTrue(unit.isNeighbouringOrSameCube(boulder.getWorld().getCubeCoordinate(boulder.getPosition())));
		Assert.assertTrue(world.hasAsLog(log));
		//Assert.assertTrue(unit.isNeighbouringOrSameCube(log.getWorld().getCubeCoordinate(log.getPosition())));
		//unit.setBoulder(boulder);
		//world.removeAsBoulder(boulder);
		//Assert.assertEquals(boulder, unit.getBoulder());
		
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"operate workshop\"\npriority: -10\nactivities: w := workshop; moveTo boulder ; work here ; "
				+ "moveTo w; work here ; moveTo log ; work here ; moveTo w; work here ; work here ; "
				, facade.createTaskFactory(),Collections.singletonList(new int[] { 1, 1, 1 }));
  
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
//		Task task1 = tasks1.get(0);
//		Task task2 = tasks2.get(0);
		// test name
		assertEquals("operate workshop", facade.getName(task));
		// test priority
		assertEquals(-10, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
//		facade.schedule(scheduler, task1);
//		facade.schedule(scheduler, task2);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,35 , 0.02);
		
		

		// work task has been executed
		//Assert.assertFalse(world.getPassable(new int[] { 1, 1, 2 }));
		Assert.assertFalse(world.hasAsBoulder(boulder));
		Assert.assertFalse(world.hasAsLog(log));
		//Assert.assertArrayEquals(new int[] {0,0,0},boulder.getWorld().getCubeCoordinate(boulder.getPosition()));
		// work task is removed from scheduler
		//Assert.assertEquals(96, unit.getWeight());
		//Assert.assertEquals(96, unit.getToughness());
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task2)));
		Assert.assertTrue(unit.getTask()==null);

	}
	
	@Test
	public void testTaskExecuted10() throws ModelException {
		System.out.println("*******************TEST 10************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[0][1][0] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 2, 0, 0 }, 95, 95, 95, 95, true);
		facade.addUnit(unit, world);
		Boulder boulder = new Boulder(new int[] {0,0,0});
		Log log = new Log(new int[] {1,0,0});
		world.addAsBoulder(boulder);
		world.addAsLog(log);
		Assert.assertTrue(world.hasAsBoulder(boulder));
		//Assert.assertTrue(unit.isNeighbouringOrSameCube(boulder.getWorld().getCubeCoordinate(boulder.getPosition())));
		Assert.assertTrue(world.hasAsLog(log));
		//Assert.assertTrue(unit.isNeighbouringOrSameCube(log.getWorld().getCubeCoordinate(log.getPosition())));
		//unit.setBoulder(boulder);
		//world.removeAsBoulder(boulder);
		//Assert.assertEquals(boulder, unit.getBoulder());
		
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while loop\"\npriority: -10\nactivities: while is_solid selected do moveTo next_to selected; work selected; done"
				, facade.createTaskFactory(),Collections.singletonList(new int[] { 1, 1, 1 }));
  
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
//		Task task1 = tasks1.get(0);
//		Task task2 = tasks2.get(0);
		// test name
		assertEquals("while loop", facade.getName(task));
		// test priority
		assertEquals(-10, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
//		facade.schedule(scheduler, task1);
//		facade.schedule(scheduler, task2);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,10 , 0.02);
		
		

		
		Assert.assertTrue(world.getPassable(new int[] { 1, 1, 1 }));
		//Assert.assertArrayEquals(new int[] {0,0,0},boulder.getWorld().getCubeCoordinate(boulder.getPosition()));
		// work task is removed from scheduler
		//Assert.assertEquals(96, unit.getWeight());
		//Assert.assertEquals(96, unit.getToughness());
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task2)));
		Assert.assertTrue(unit.getTask()==null);

	}
	
	@Test
	public void testTaskExecuted11() throws ModelException {
		System.out.println("*******************TEST 11************************");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;

		World world = facade.createWorld(types, new DefaultTerrainChangeListener());
		Unit unit = facade.createUnit("Test", new int[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		facade.addUnit(unit, world);
		Boulder boulder = new Boulder(new int[] {0,0,0});
		world.addAsBoulder(boulder);
		Assert.assertTrue(world.hasAsBoulder(boulder));
		Assert.assertTrue(unit.isNeighbouringOrSameCube(boulder.getWorld().getCubeCoordinate(boulder.getPosition())));

		unit.setBoulder(boulder);
		world.removeAsBoulder(boulder);
		Assert.assertEquals(boulder, unit.getBoulder());
		
		Faction faction = facade.getFaction(unit);

		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"dig tunnel\"\npriority: -10\nactivities: if (carries_item(this)) then work here; fi "
				+ "if (is_solid(1,1,2)) then moveTo (1,0,2); work (1,1,2); fi if (is_solid(1,1,1)) then moveTo (1,0,1); work (1,1,1);fi", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
//		List<Task> tasks1 = TaskParser.parseTasksFromString(
//				"name: \"move task\"\npriority: 1\nactivities: x := false; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
//				Collections.singletonList(new int[] { 0, 1, 1 }));
//		List<Task> tasks2 = TaskParser.parseTasksFromString(
// 				"name: \"move task effective\"\npriority: -5\nactivities: x := true; print x; if x then moveTo selected; fi", facade.createTaskFactory(),
// 				Collections.singletonList(new int[] { 0, 1, 2 }));
  
		// tasks are created
		assertNotNull(tasks);
		// there's exactly one task
		assertEquals(1, tasks.size());
		Task task = tasks.get(0);
//		Task task1 = tasks1.get(0);
//		Task task2 = tasks2.get(0);
		// test name
		assertEquals("dig tunnel", facade.getName(task));
		// test priority
		assertEquals(-10, facade.getPriority(task));
		
		facade.schedule(scheduler, task);
//		facade.schedule(scheduler, task1);
//		facade.schedule(scheduler, task2);
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,35 , 0.02);
		
		

		// work task has been executed
		Assert.assertTrue(world.getPassable(new int[] { 1, 1, 2 }));
		Assert.assertTrue(world.getPassable(new int[] { 1, 1, 1}));
		Assert.assertTrue(world.hasAsBoulder(boulder));
		Assert.assertArrayEquals(new double[] {0.5 , 0.5, 0.5}, boulder.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertArrayEquals(new int[] {0,0,0},boulder.getWorld().getCubeCoordinate(boulder.getPosition()));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
//		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task2)));

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
