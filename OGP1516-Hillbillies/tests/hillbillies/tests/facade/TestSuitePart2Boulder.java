package hillbillies.tests.facade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Status;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import hillbillies.tests.util.PositionAsserts;
import ogp.framework.util.Util;

public class TestSuitePart2Boulder {
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[][][] types = new int[10][20][30];
	
	private static Boulder DoubleBoulder;
	private static Boulder IntegerBoulder;
	private Boulder ABoulder;
	private Boulder BBoulder;
	private Boulder CBoulder;
	private Boulder DBoulder;
	private Boulder EBoulder;
	
	private static World world1;
	private World world2;
	
	
	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		DoubleBoulder = new Boulder(new double[] {1.5,4.5,3.5});
		IntegerBoulder = new Boulder(new int[] {1,2,3});
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
		ABoulder = new Boulder(new double[] {3.5,1.5,4.5});
		BBoulder = new Boulder(new double[] {6.5,3.5,0.5});
		BBoulder = new Boulder(new double[] {6.5,3.5,0.5});
		CBoulder = new Boulder(new double[] {1.5,1.5,2.5});
		DBoulder = new Boulder(new double[] {1.5,2.5,1.5});
		EBoulder = new Boulder(new double[] {2.5,2.5,2.5});
		
	}
	
	@Test
	public final void getPosition(){
		PositionAsserts.assertDoublePositionEquals(1.5,4.5,3.5,DoubleBoulder.getPosition());
	}
	
	@Test 
	public final void getPosition_int(){
		Assert.assertArrayEquals(new double[] {1.5,2.5,3.5},IntegerBoulder.getPosition(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public final void getWeight(){
		Assert.assertTrue(DoubleBoulder.getWeight()<=51);
		Assert.assertTrue(DoubleBoulder.getWeight()>=10);
	}
	
	@Test
	public final void getWeight_int(){
		Assert.assertTrue(IntegerBoulder.getWeight()<=51);
		Assert.assertTrue(IntegerBoulder.getWeight()>=10);
	}
	
	/**
	 * Helper method to advance time by some time for the given unit. (we based this on the helper method in Part1TestPartial)
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(Boulder boulder, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			boulder.advanceTime((float)step);
		boulder.advanceTime((float) (time - n * step));
	}
	
	@Test
	public final void mustFall_ground(){
		Assert.assertFalse(BBoulder.mustFall());
	}
	
	@Test
	public final void mustFall_Solid(){
		world2.addAsBoulder(CBoulder);
		Assert.assertFalse(CBoulder.mustFall());
	}
	
	@Test
	public final void mustFall_Passable(){
		world2.addAsBoulder(DBoulder);
		Assert.assertTrue(DBoulder.mustFall());
	}
	
	@Test
	public final void fall(){
		ABoulder.fall();
		Assert.assertEquals(ABoulder.getStatus(),Status.FALLING);
	}
	
	@Test
	public final void advanceTime_MustFall(){
		world2.addAsBoulder(DBoulder);
		DBoulder.advanceTime((float) 0.1);
		//PositionAsserts.assertDoublePositionEquals(3.5,1.5,4.5+speed*0.1,ABoulder.getPosition());
		//advanceTimeFor(ABoulder, time-0.1, 0.1);
		Assert.assertEquals(Status.FALLING, DBoulder.getStatus());
	}
	
	@Test
	public final void advanceTime_Falling(){
		world2.addAsBoulder(DBoulder);
		DBoulder.fall();
		double speed = 3.0;
		double time = 1.0 / speed;
		DBoulder.advanceTime((float) 0.1);
		PositionAsserts.assertDoublePositionEquals(1.5,2.5,1.5-3.0*0.1,DBoulder.getPosition());
		advanceTimeFor(DBoulder, time-0.1, 0.1);
		PositionAsserts.assertDoublePositionEquals(1.5,2.5,0.5,DBoulder.getPosition());
		Assert.assertEquals(Status.DONE, DBoulder.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Falling1(){
		world2.addAsBoulder(EBoulder);
		EBoulder.fall();
		double speed = 3.0;
		double time = 1.0 / speed;
		EBoulder.advanceTime((float) 0.1);
		PositionAsserts.assertDoublePositionEquals(2.5,2.5,2.5-3.0*0.1,EBoulder.getPosition());
		advanceTimeFor(EBoulder, time-0.1, 0.1);
		PositionAsserts.assertDoublePositionEquals(2.5,2.5,1.5,EBoulder.getPosition());
		Assert.assertEquals(Status.FALLING, EBoulder.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Normal(){
		BBoulder.advanceTime((float) 0.1);
		Assert.assertEquals(BBoulder.getStatus(),Status.DONE);
		PositionAsserts.assertDoublePositionEquals(6.5,3.5,0.5,BBoulder.getPosition());
	}
	
	@Test 
	public final void getStatus_Normal(){
		Assert.assertEquals(ABoulder.getStatus(),Status.DONE);
	}
	
	@Test 
	public final void getStatus_Falling(){
		ABoulder.fall();
		Assert.assertEquals(ABoulder.getStatus(),Status.FALLING);
	}
	
	@Test
	public final void terminate(){
		world2.addAsBoulder(CBoulder);
		CBoulder.terminate();
		Assert.assertEquals(CBoulder.getWorld(),null);
		Assert.assertFalse(world2.hasAsBoulder(CBoulder));
	}
}
