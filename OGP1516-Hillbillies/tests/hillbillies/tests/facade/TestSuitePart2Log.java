	package hillbillies.tests.facade;

	import org.junit.Assert;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Test;

import hillbillies.model.Log;
	import hillbillies.model.Status;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.tests.util.PositionAsserts;
	import ogp.framework.util.Util;

public class TestSuitePart2Log {
		
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[][][] types = new int[10][20][30];
	
	private static Log DoubleLog;
	private static Log IntegerLog;
	private Log ALog;
	private Log BLog;
	private Log CLog;
	private Log DLog;
	private Log ELog;
	
	private static World world1;
	private World world2;
	
	
	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		DoubleLog = new Log(new double[] {1.5,4.5,3.5});
		IntegerLog = new Log(new int[] {1,2,3});
		world1 = new World(types, new DefaultTerrainChangeListener());
	}
	/**
	 * Set up an mutable test fixture
	 */
	@Before
	public void setUpBefore(){
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[0][2][1] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		world2 = new World(types, new DefaultTerrainChangeListener());
		ALog = new Log(new double[] {3.5,1.5,4.5});
		BLog = new Log(new double[] {6.5,3.5,0.5});
		BLog = new Log(new double[] {6.5,3.5,0.5});
		CLog = new Log(new double[] {1.5,1.5,2.5});
		DLog = new Log(new double[] {1.5,2.5,1.5});
		ELog = new Log(new double[] {2.5,2.5,2.5});
		
	}
	
	@Test
	public final void getPosition(){
		PositionAsserts.assertDoublePositionEquals(1.5,4.5,3.5,DoubleLog.getPosition());
	}
	
	@Test 
	public final void getPosition_int(){
		Assert.assertArrayEquals(new double[] {1.5,2.5,3.5},IntegerLog.getPosition(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public final void getWeight(){
		Assert.assertTrue(DoubleLog.getWeight()<=51);
		Assert.assertTrue(DoubleLog.getWeight()>=10);
	}
	
	@Test
	public final void getWeight_int(){
		Assert.assertTrue(IntegerLog.getWeight()<=51);
		Assert.assertTrue(IntegerLog.getWeight()>=10);
	}
	
	/**
	 * Helper method to advance time by some time for the given unit. (we based this on the helper method in Part1TestPartial)
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Log log, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			log.advanceTime((float)step);
		log.advanceTime((float) (time - n * step));
	}
	
	@Test
	public final void mustFall_ground(){
		Assert.assertFalse(BLog.mustFall());
	}
	
	@Test
	public final void mustFall_Solid(){
		world2.addAsLog(CLog);
		Assert.assertFalse(CLog.mustFall());
	}
	
	@Test
	public final void mustFall_Passable(){
		world2.addAsLog(DLog);
		Assert.assertTrue(DLog.mustFall());
	}
	
	@Test
	public final void fall(){
		ALog.fall();
		Assert.assertEquals(ALog.getStatus(),Status.FALLING);
	}
	
	@Test
	public final void advanceTime_MustFall(){
		world2.addAsLog(DLog);
		DLog.advanceTime((float) 0.1);
		//PositionAsserts.assertDoublePositionEquals(3.5,1.5,4.5+speed*0.1,ABoulder.getPosition());
		//advanceTimeFor(ABoulder, time-0.1, 0.1);
		Assert.assertEquals(Status.FALLING, DLog.getStatus());
	}
	
	@Test
	public final void advanceTime_Falling(){
		world2.addAsLog(DLog);
		DLog.fall();
		double speed = 3.0;
		double time = 1.0 / speed;
		DLog.advanceTime((float) 0.1);
		PositionAsserts.assertDoublePositionEquals(1.5,2.5,1.5-3.0*0.1,DLog.getPosition());
		advanceTimeFor(DLog, time-0.1, 0.1);
		PositionAsserts.assertDoublePositionEquals(1.5,2.5,0.5,DLog.getPosition());
		Assert.assertEquals(Status.DONE, DLog.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Falling1(){
		world2.addAsLog(ELog);
		ELog.fall();
		double speed = 3.0;
		double time = 1.0 / speed;
		ELog.advanceTime((float) 0.1);
		PositionAsserts.assertDoublePositionEquals(2.5,2.5,2.5-3.0*0.1,ELog.getPosition());
		advanceTimeFor(ELog, time-0.1, 0.1);
		PositionAsserts.assertDoublePositionEquals(2.5,2.5,1.5,ELog.getPosition());
		Assert.assertEquals(Status.FALLING, ELog.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Normal(){
		BLog.advanceTime((float) 0.1);
		Assert.assertEquals(BLog.getStatus(),Status.DONE);
		PositionAsserts.assertDoublePositionEquals(6.5,3.5,0.5,BLog.getPosition());
	}
	
	@Test 
	public final void getStatus_Normal(){
		Assert.assertEquals(ALog.getStatus(),Status.DONE);
	}
	
	@Test 
	public final void getStatus_Falling(){
		ALog.fall();
		Assert.assertEquals(ALog.getStatus(),Status.FALLING);
	}
	
	@Test
	public final void terminate(){
		world2.addAsLog(CLog);
		CLog.terminate();
		Assert.assertEquals(CLog.getWorld(),null);
		Assert.assertFalse(world2.hasAsLog(CLog));
	}
}