package hillbillies.tests.facade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Status;
import hillbillies.model.Unit;
import hillbillies.tests.util.PositionAsserts;
import ogp.framework.util.Util;

public class TestSuitePart2Boulder {
	
	private static Boulder DoubleBoulder;
	
	private static Boulder IntegerBoulder;
	
	private Boulder ABoulder;
	
	private Boulder BBoulder;
	
	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		DoubleBoulder = new Boulder(new double[] {1.5,4.5,3.5});
		IntegerBoulder = new Boulder(new int[] {1,2,3});
	}
	/**
	 * Set up an mutable test fixture
	 */
	@Before
	public void setUpBefore(){
		ABoulder = new Boulder(new double[] {3.5,1.5,4.5});
		BBoulder = new Boulder(new double[] {6.5,3.5,0.5});
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
	private static void advanceTimeFor(Unit unit, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			unit.advanceTime((float)step);
		unit.advanceTime((float) (time - n * step));
	}
	
	@Test
	public final void mustFall(){
		Assert.assertFalse(BBoulder.mustFall());
	}
	
	@Test
	public final void fall(){
		ABoulder.fall();
		Assert.assertEquals(ABoulder.getStatus(),Status.FALLING);
	}
	
//	@Test
//	public final void advanceTime_Falling(){
//		ABoulder.fall();
//		float duration = (float) 0.1;
//		ABoulder.advanceTime(duration);
//		PositionAsserts.assertDoublePositionEquals(3.5,1.5,4.5-3.0*duration,ABoulder.getPosition());
//	}
	
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
}
