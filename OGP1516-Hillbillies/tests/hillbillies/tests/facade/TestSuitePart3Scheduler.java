package hillbillies.tests.facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		scheduler.addAsTask(task1);
		//scheduler.addAsTask(task2);
		scheduler.addAsTask(task3);
		System.out.println(scheduler.getTasks());
	}
	
	@Test
	public void testIterator(){
		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.print(scheduler.iterator().next().toString());
		Assert.assertTrue(scheduler.iterator().hasNext());
		System.out.print(scheduler.iterator().next().toString());
		//Assert.assertTrue(scheduler.iterator().hasNext());
		//scheduler.iterator().next();
		Assert.assertFalse(scheduler.iterator().hasNext());
		
//		while (scheduler.getAllTasksIterator().hasNext()){
//			   System.out.println(scheduler.getAllTasksIterator().next().getPriority());
//			   System.out.println(scheduler.getAllTasksIterator().next() + " OK");
//			   }
//		while (scheduler.getTasks().descendingIterator().hasNext()){
//			System.out.println(scheduler.getTasks().descendingIterator().next() + " ");
//		}
//		System.out.print(scheduler.getTasks());
//		//laagste prioriteit
//		System.out.println(scheduler.getTasks().last());
//		//hoogste prioriteit
//		System.out.println(scheduler.getTasks().first());
//		//eerste element hoger in de treeset, dus met lagere prioriteit teruggeven
//		System.out.println(scheduler.getTasks().ceiling(scheduler.getTasks().first()));
		
		
	}
	
	@Test
	public void testIterator2(){
		System.out.println("---------------------------------Test 2");
		System.out.println(scheduler.iterator().hasNext());
		while (scheduler.iterator().hasNext()){
			   System.out.println(scheduler.iterator().next() + " OK");
			   }
	}
	
	@Test
	public void getAllTasksIterator(){
		System.out.println("---------------------------------Test 3");
		scheduler.getAllTasksIterator();
	}

}
