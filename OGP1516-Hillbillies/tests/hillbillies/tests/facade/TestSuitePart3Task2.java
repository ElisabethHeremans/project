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
	public void test_breakStatement() throws ModelException {
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
		Boulder boulder = new Boulder(new int[] {0,1,1});
		facade.addUnit(unit, world);
		world.addAsBoulder(boulder);
		Faction faction = facade.getFaction(unit);
		Assert.assertEquals(1, world.getLogs(new int[] {0,1,1}).size());
		Assert.assertEquals(1, world.getLogs(new int[] {0,1,1}).size());
		Scheduler scheduler = facade.getScheduler(faction);

		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 1, 1, 1 }));
		List<Task> tasks2 = TaskParser.parseTasksFromString(
				"name: \"break task\"\npriority: 2\nactivities: x := log; work log; if carries_item this then moveTo (0,0,1); else moveTo (0,0,2); fi y:= selected;", facade.createTaskFactory(),
				Collections.singletonList(new int[] { 0, 1, 1 }));
		
		
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
		System.out.print(scheduler.getTasks().size());
		advanceTimeFor(facade, world,24 , 0.02);
		
		

		// work task has been executed
		assertEquals(TYPE_AIR, facade.getCubeType(world, 0, 1, 1));
		// work task is removed from scheduler
		System.out.print("remaining tasks  "+scheduler.getTasks().size());
		//System.out.print(scheduler.);
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task)));
		assertFalse(facade.areTasksPartOf(scheduler, Collections.singleton(task1)));
		System.out.print(" position " + Arrays.toString(unit.getPosition()));
	}

	private Map<Position,Boolean> logsAtCubeMap = new HashMap<Position,Boolean>();

	@Test
	public void test2(){
		System.out.println("********");
		logsAtCubeMap = new HashMap<Position,Boolean>();
		logsAtCubeMap.put(new Position(new int[] {0,0,0}), Boolean.TRUE);
		logsAtCubeMap.size();
		logsAtCubeMap.get(new Position(new int[] {0,0,0}));
		System.out.println(logsAtCubeMap.get(new Position(new int[] {0,0,0})));
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
