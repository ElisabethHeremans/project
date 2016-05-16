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

import hillbillies.model.Faction;
import hillbillies.model.Scheduler;
import hillbillies.model.Task;
import hillbillies.model.TaskFactory;
import hillbillies.model.World;
import hillbillies.model.expression.Expression;
import hillbillies.part3.programs.ITaskFactory;
import hillbillies.part3.programs.TaskParser;

public class TestSuitePart3Scheduler {
	
	private World world;
	private Faction faction;
	private Scheduler scheduler;
	private Task task1;
	private Task task2;
	private Task task3;
	private List<Task> tasks;
	
	/**
	 * Set up a mutable test fixture.
	 */
	@Before
	public void setUpBefore(){
		System.out.println("StartSetUpBefore");
		scheduler = new Scheduler();
		System.out.println("NewScheduler");
		faction = new Faction();
		List<int[]> selectedCubes = new ArrayList<int[]>();
		selectedCubes.add(new int[] {1,1,1});
		selectedCubes.add(new int[] {1,2,3});
		System.out.println("StartParsing");
		tasks = TaskParser.parseTasksFromString(
				"name: \"work task\"\npriority: 1\nactivities: work selected;", (ITaskFactory<Expression, ?, Task>) new TaskFactory(),
				selectedCubes);
		task1 = tasks.get(0);
		System.out.println("task 1 "+ task1);
		task2 = tasks.get(1);
		System.out.println("task 2 "+task2);
		task3 = TaskParser.parseTasksFromString(
				"name: \"operate workshop\"\npriority: 100\nactivities: if (is_solid boulder || carries_item this) then moveTo position_of this; moveTo boulder; fi",
				(ITaskFactory<Expression, ?, Task>) new TaskFactory(), Collections.emptyList()).get(0);
		System.out.println("task 3 "+task3);
		faction.setScheduler(scheduler);
	}
	
	@Test
	public void testIterator(){
	scheduler.addAsTask(task1);
	scheduler.addAsTask(task2);
	scheduler.addAsTask(task3);
		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.print(scheduler.iterator().next().toString());
		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.print(scheduler.iterator().next().toString());
		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.print(scheduler.iterator().next().toString());
		Assert.assertFalse(scheduler.iterator().hasNext());
				
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
		scheduler.replaceAsTask(task1, task3);
		Assert.assertFalse(scheduler.hasAsTask(task1));
		Assert.assertTrue(scheduler.hasAsTask(task3));
		Assert.assertTrue(scheduler.getTasks().size() == 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void replaceAsTask_InvalidTaskToReplace() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		scheduler.replaceAsTask(task3, task1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void replaceAsTask_InvalidReplacement() throws IllegalArgumentException{
		scheduler.addAsTask(task1);
		scheduler.replaceAsTask(task1, null);
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
	public void getTasksSatisfying(){
		scheduler.addAsTask(task1);
		scheduler.addAsTask(task3);
		Set<Task> task = scheduler.getTasksSatisfying(x-> x.getPriority()==100);
		Assert.assertTrue(task.contains(task3));
		Assert.assertFalse(task.contains(task1));
	}
	
	@Test 
	public void getFaction(){
		//Assert.assertEquals(null, scheduler.getFaction());
		//faction.setScheduler(scheduler);
		Assert.assertEquals(faction, scheduler.getFaction());
	}
	
}
