package hillbillies.tests.facade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.tests.util.PositionAsserts;
import ogp.framework.util.Util;

public class TestSuitePart2Boulder {
	
	private static Boulder DoubleBoulder;
	
	private static Boulder IntegerBoulder;
	
	private Boulder ABoulder;
	
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
	}
	
	@Test
	public final void getPosition(){
		PositionAsserts.assertDoublePositionEquals(1.5,4.5,3.5,DoubleBoulder.getPosition());
	}
	
	@Test 
	public final void getPosition_int(){
		Assert.assertArrayEquals(new int[] {1,2,3},IntegerBoulder.getPosition(),Util.DEFAULT_EPSILON)
	}
}
