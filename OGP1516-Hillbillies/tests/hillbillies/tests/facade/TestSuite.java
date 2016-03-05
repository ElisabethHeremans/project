package hillbillies.tests.facade;
import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Assert;
import ogp.framework.util.Util;


import hillbillies.model.Unit;


public class TestSuite {
	
	private static Unit unit1;
	
	private Unit unit2;
	private Unit unit3;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		unit1 = new Unit("unit1",new double[] {1.5,4,5,3.5},50,50,50,50,false);
	}
	
	@Test
	public final void getBaseSpeed(){
		Assert.assertEquals(1.5*(50+50)/(200.0*50/100.0), unit1.getBaseSpeed(), Util.DEFAULT_EPSILON);
	}
	
	@Test
	public final void get
	
	@Test
	public final void getCubeCentre(){
		assertDoublePositionEquals(1.5,2.5,3.5,Unit.getCubeCentre(new double[] {1.0,2.0,3.0}));
	}
	
	
	
	//private static Unit 

}
