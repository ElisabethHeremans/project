package hillbillies.tests.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import hillbillies.model.*;
import hillbillies.model.expression.*;
import hillbillies.model.statement.*;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.part3.facade.Facade;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.TaskParser;

public class TestSuitePart3Scheduler {
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	
	private Facade facade;
	private Unit unit;
	private Unit unit2;
	private World world;
	private Faction faction;
	private Scheduler scheduler;
	private Task task1;
	private Task task2;
	private Task task3;
	private List<Task> tasks;
	
	
	@Before
	public void setup() {
		this.facade = new Facade();
	}
	
	/**
	 * Set up a mutable test fixture.
	 */
	@Before
	public void setUpBefore(){
		System.out.println("StartSetUpBefore");
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_ROCK;
		types[1][1][2] = TYPE_TREE;
		types[2][2][2] = TYPE_WORKSHOP;
		world = new World(types,new DefaultTerrainChangeListener() );
		unit = new Unit("Test", new double[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		unit2 = new Unit("Test", new double[] { 0, 0, 0 }, 50, 50, 50, 50, true);
		scheduler = new Scheduler();
		System.out.println("NewScheduler");
		List<int[]> selectedCubes = new ArrayList<int[]>();
		selectedCubes.add(new int[] {1,1,1});
		selectedCubes.add(new int[] {1,2,3});
		System.out.println("StartParsing");
		tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", (ITaskFactory<Expression<?>, ?, Task>) new TaskFactory(),
				selectedCubes);
		task1 = tasks.get(0);
		System.out.println("task 1 "+ task1);
		task2 = tasks.get(1);
		System.out.println("task 2 "+task2);
		List<Task> tasks = TaskParser.parseTasksFromString(
				"name: \"while loop\"\npriority: -10\nactivities: while is_solid selected do moveTo next_to selected; work selected; done"
				, facade.createTaskFactory(),Collections.singletonList(new int[] { 1, 1, 1 }));
		task3 = tasks.get(0);
//		List<Task> tasks3 = TaskParser.parseTasksFromString(
//				"name: \"operate workshop\"\npriority: 100\nactivities: if ((is_solid(selected))||(carries_item(this))) then moveTo boulder; work position_of this; fi",
//				(ITaskFactory<Expression<?>, ?, Task>) new TaskFactory(), Collections.singletonList(new int[] { 1, 1, 1 }));
//		Task task3 = tasks.get(0);
		System.out.println("task 3 "+task3);
		//faction.setScheduler(scheduler);
	}
	
	@Test
	public void testIterator(){
	scheduler.addAsTask(task1);
	scheduler.addAsTask(task2);
	scheduler.addAsTask(task3);
	System.out.println("---------------------------------Test 1");

		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.println("NEXT" +scheduler.iterator().next().toString());
		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.println("NEXT" +scheduler.iterator().next().toString());
		Assert.assertTrue(scheduler.iterator().hasNext());

		System.out.print(scheduler.iterator().next().toString());

		Assert.assertFalse(scheduler.iterator().hasNext());
		System.out.println("---------------------------------END 1");		
	}
	
	@Test
	public void testIterator2(){
	scheduler.addAsTask(task1);
	scheduler.addAsTask(task2);
	scheduler.addAsTask(task3);
		System.out.println("---------------------------------Test 2");
		System.out.println(scheduler.iterator().hasNext());
		while (scheduler.iterator().hasNext()){
			   System.out.println(scheduler.iterator().next() + " OK");
			   }
	}
	
	@Test
	public void getAllTasksIterator(){
	scheduler.addAsTask(task1);
	scheduler.addAsTask(task2);
	scheduler.addAsTask(task3);
		System.out.println("---------------------------------Test 3");
		Assert.assertEquals(scheduler.getAllTasksIterator().next(),scheduler.getTasks().iterator().next());
		Assert.assertEquals(scheduler.getAllTasksIterator().next(),scheduler.getTasks().iterator().next());
		Assert.assertEquals(scheduler.getAllTasksIterator().next(),scheduler.getTasks().iterator().next());
	}
	
	@Test
	public void hasAsTask_ValidCase(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task2);
		scheduler.addAsTask(task3);
		Assert.assertTrue(scheduler.hasAsTask(task3));
		Assert.assertTrue(scheduler.hasAsTask(task1));
		Assert.assertTrue(scheduler.hasAsTask(task2));
	}
	
	@Test
	public void hasAsTask_InvalidCase(){
		scheduler.addAsTask(task1);
		Assert.assertFalse(scheduler.hasAsTask(task3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void hasAsTask_InvalidToken()
 throws IllegalArgumentException {
		scheduler.hasAsTask(null);
	}
	
	@Test
	public void hasAsTasks_ValidCase(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task2);
		scheduler.addAsTask(task3);
		Set<Task> test = new HashSet<>();
		test.add(tasks.get(0));
		test.add(tasks.get(1));
		Assert.assertTrue(scheduler.hasAsTasks(test));
	}
	
	@Test
	public void hasAsTasks_InvalidCase(){
		scheduler.addAsTask(task3);
		Set<Task> test = new HashSet<>();
		test.add(tasks.get(0));
		test.add(tasks.get(1));
		Assert.assertFalse(scheduler.hasAsTasks(test));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void hasAsTasks_InvalidToken()
 throws IllegalArgumentException {
		scheduler.hasAsTasks(null);
	}
	
	@Test 
	public void canHaveAsTask_ValidCase(){
		Assert.assertTrue(scheduler.canHaveAsTask(task1));
	}
	
	@Test
	public void canHaveAsTask_InvalidCase(){
		Assert.assertFalse(scheduler.canHaveAsTask(null));
	}
	
	@Test
	public void addAsTask_ValidCase(){
		scheduler.addAsTask(task1);
		Assert.assertTrue(scheduler.hasAsTask(task1));
	}
	
	@Test
	public void addAsTask_DoubleCase(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task1);
		Assert.assertTrue(scheduler.getTasks().size() == 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsTask_InvalidTask() throws IllegalArgumentException{
		scheduler.addAsTask(null);
	}
	
//	@Test (expected = IllegalArgumentException.class)
//	public void addAsTask_BadFormedTask() throws IllegalArgumentException{
//		
//	}
	
	@Test
	public void removeAsTask_ValidCase(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		Assert.assertTrue(scheduler.hasAsTask(task1));
		Assert.assertTrue(scheduler.hasAsTask(task3));
		scheduler.removeAsTask(task1);
		Assert.assertFalse(scheduler.hasAsTask(task1));
		Assert.assertTrue(scheduler.hasAsTask(task3));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void removeAsTask_InvalidCase() throws IllegalArgumentException{
		scheduler.removeAsTask(null);
	}
	
	@Test
	public void replaceAsTask_ValidCase(){
		scheduler.addAsTask(task1);
		scheduler.replaceTask(task1, task3);
		Assert.assertFalse(scheduler.hasAsTask(task1));
		Assert.assertTrue(scheduler.hasAsTask(task3));
		Assert.assertTrue(scheduler.getTasks().size() == 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void replaceAsTask_InvalidTaskToReplace() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		scheduler.replaceTask(task3, task1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void replaceAsTask_InvalidReplacement() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		scheduler.replaceTask(task1, null);
	}
	
	@Test
	public void getTasks(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		Assert.assertTrue(scheduler.getTasks().size()==2);
		Assert.assertTrue(scheduler.hasAsTask(task1));
		Assert.assertTrue(scheduler.hasAsTask(task3));
		
	}
	
	@Test 
	public void getTasksSatisfying_PrioityGreaterThan(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		Set<Task> task = scheduler.getTasksSatisfying(x-> x.getPriority()>100);
		Assert.assertTrue(task.size()==0);
		Assert.assertFalse(task.contains(task3));
		Assert.assertFalse(task.contains(task1));
	}
	
	@Test 
	public void getTasksSatisfying_PositivePriority(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task2);
		scheduler.addAsTask(task3);
		Set<Task> task = scheduler.getTasksSatisfying(x-> x.getPriority()>=0);
		Assert.assertTrue(task.size()==2);
		Assert.assertFalse(task.contains(task3));
		Assert.assertTrue(task.contains(task1));
		Assert.assertTrue(task.contains(task2));
	}
	
	@Test 
	public void getTasksSatisfying_BeingExecuted(){
		System.out.println("----------------------Executing Tasks--------------------------");
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		//world.addAsUnit(unit);
		world.advanceTime(0.15);
		world.advanceTime(0.15);
		Set<Task> task = scheduler.getTasksSatisfying(x-> x.getExecutingUnit()!= null);
		System.out.println(task.toString());
		System.out.println("END");
		Assert.assertTrue(task.size()==1);
		Assert.assertFalse(task.contains(task3));
		Assert.assertTrue(task.contains(task1));
		System.out.println("END");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void markTaskForUnit_IllegalUnit() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		world.addAsUnit(unit);
		faction = new Faction();
		faction.setScheduler(scheduler);
		scheduler.markTaskForUnit(task1, unit);
		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void markTaskForUnit_IllegalTask() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		scheduler.markTaskForUnit(task2, unit);	
	}
	
	@Test
	public void markTaskForUnit_ValidCase(){
		System.out.println("------------------------------Tast Valid Scheduled Unit -------------------------------");
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		world.addAsUnit(unit);
		world.addAsUnit(unit2);
		faction = unit.getFaction();
		unit2.setFaction(null);
		faction.addAsUnit(unit2);
		faction.setScheduler(scheduler);
		scheduler.markTaskForUnit(task3, unit);
		advanceTimeFor(world, 5,0.1);
		Assert.assertFalse(task3 == unit2.getTask());
		Assert.assertTrue(task3 == unit.getTask()||task1 == unit.getTask());	
		}
	
	@Test (expected = IllegalArgumentException.class)
	public void unmarkTask_IllegalTask() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		scheduler.unmarkTaskForUnit(task2);
		
	}
	
	@Test
	public void unmarkTask_ValidCaseNotScheduled(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		scheduler.unmarkTaskForUnit(task1);
		Assert.assertEquals(null, task1.getScheduledUnit());
		Assert.assertEquals(null, unit.getScheduledTask());
		
	}
	
	@Test
	public void unmarkTask_ValidCaseReset(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		scheduler.markTaskForUnit(task1, unit);
		Assert.assertEquals(unit, task1.getScheduledUnit());
		Assert.assertEquals(task1, unit.getScheduledTask());
		scheduler.unmarkTaskForUnit(task1);
		Assert.assertEquals(null, task1.getScheduledUnit());
		Assert.assertEquals(null, unit.getScheduledTask());
		
	}
	
	@Test 
	public void getFaction(){
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		Assert.assertEquals(faction, scheduler.getFaction());
	}
	
	@Test
	public void canHaveAsFaction_Invalid(){
		faction = new Faction();
		Assert.assertFalse(scheduler.canHaveAsFaction(faction));
	}
	
	@Test
	public void canHaveAsFaction_Null(){
		Assert.assertTrue(scheduler.canHaveAsFaction(null));
	}
	
	@Test
	public void canHaveAsFaction_Valid(){
		faction = new Faction();
		faction.setScheduler(scheduler);
		Assert.assertTrue(scheduler.canHaveAsFaction(faction));
	}
	
	@Test
	public void getHighestPriorityTask_Empty(){
		Set<Task> tasks = new HashSet<Task>();
		Assert.assertEquals(null, scheduler.getHighestPriorityTask(tasks));
	}
	
	@Test
	public void getHighestPriorityTask_Normal(){
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(task1);
		tasks.add(task3);
		Assert.assertEquals(task1, scheduler.getHighestPriorityTask(tasks));
	}
	
	@Test
	public void getHighestPriorityTask_ExecutingTask(){
		Set<Task> tasks = new HashSet<Task>();
		tasks.add(task1);
		tasks.add(task3);
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		world.addAsUnit(unit);
		faction = unit.getFaction();
		faction.setScheduler(scheduler);
		world.advanceTime(0.19);
		world.advanceTime(0.19);
		Assert.assertEquals(task3, scheduler.getHighestPriorityTask(tasks));
	}
	
	/**
	 * Helper method to advance time by some time for the given unit. (we based this on the helper method in Part1TestPartial)
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(World world, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			world.advanceTime((float)step);
		world.advanceTime((float) (time - n * step));
	}
	
	/**
	 * Helper method to advance time by some time for the given unit. (we based this on the helper method in Part1TestPartial)
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Unit unit, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			unit.advanceTime((float)step);
		unit.advanceTime((float) (time - n * step));
	}
	
}
