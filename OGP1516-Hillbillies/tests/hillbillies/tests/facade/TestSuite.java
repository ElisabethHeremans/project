package hillbillies.tests.facade;
import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;
import org.junit.BeforeClass;
import ogp.framework.util.*;
import hillbillies.model.Status;
import hillbillies.model.Unit;


public class TestSuite {
	
	private static Unit Aunit;
	
	private Unit StandardUnit;
	
	private Unit DefaultEnabledUnit;
	
	private Unit HitAndStaminaZeroUnit;
	
	private Unit HitMaxStaminaZeroUnit;
	
	private Unit HitMaxStaminaMaxUnit;
	
	private Unit NeighbourStandardUnit;

	
	
	
	@BeforeClass
	public static void setUpBeforeClass(){
		Aunit = new Unit("Aunit",new double[] {1.5,4,5,3.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
	}
	
	@Before
	public final void setUpBefore(){
		StandardUnit = new Unit("Bunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		DefaultEnabledUnit = new Unit("Cunit",new double[] {3.5,1.5,4.5},75,25,25,75,true,0.0,0.0,Math.PI/2);
		HitAndStaminaZeroUnit = new Unit("Dunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,0.0,0.0,Math.PI/2);
		HitMaxStaminaZeroUnit = new Unit("Eunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,0.0,0.0,Math.PI/2);
		HitMaxStaminaZeroUnit.setHitPoints(HitMaxStaminaZeroUnit.max_nbPoints());
		HitMaxStaminaMaxUnit = new Unit("Funit",new double[] {3.5,1.5,4.5},75,25,25,75,false,0.0,0.0,Math.PI/2);
		HitMaxStaminaMaxUnit.setHitPoints(HitMaxStaminaMaxUnit.max_nbPoints());
		HitMaxStaminaMaxUnit.setStaminaPoints(HitMaxStaminaMaxUnit.max_nbPoints());
		NeighbourStandardUnit = new Unit("Bunit",new double[] {4.5,0.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);

	}
	
	@Test
	public final void getBaseSpeed(){
		Assert.assertEquals(1.5*(50+50)/(200.0*50/100.0), Aunit.getBaseSpeed(), Util.DEFAULT_EPSILON);
	}
	@Test
	public final void startSprinting_Effective(){
		StandardUnit.status = Status.MOVING;
		StandardUnit.startSprinting();
		Assert.assertTrue(StandardUnit.isSprinting());
		
	}
	
	@Test
	public final void startSprinting_NonEffective(){
		StandardUnit.startSprinting();
		Assert.assertFalse(StandardUnit.isSprinting());
	}
	
	@Test
	public final void stopSprinting(){
		StandardUnit.startSprinting();
		StandardUnit.stopSprinting();
		Assert.assertFalse(StandardUnit.isSprinting());
	}
	
	
	@Test
	public final void getCurrentSpeed_NotMoving(){
		StandardUnit.status = Status.DONE;
		assert(Util.fuzzyEquals(StandardUnit.getCurrentSpeed(), 0.0, Util.DEFAULT_EPSILON));
	}
	
	@Test
	public final void getCurrentSpeed_WalkingStraight(){
		StandardUnit.moveToAdjacent(0, 1, 0);
		assert(Util.fuzzyEquals(StandardUnit.getBaseSpeed(), StandardUnit.getCurrentSpeed()));
	}
	
	@Test
	public final void getCurrentSpeed_WalkingUp(){
		StandardUnit.moveToAdjacent(0, 0, 1);
		assert(Util.fuzzyEquals(1.2*StandardUnit.getBaseSpeed(), StandardUnit.getCurrentSpeed()));
	}
	@Test
	public final void getCurrentSpeed_WalkingDown(){
		StandardUnit.moveToAdjacent(0, 0, -1);
		assert(Util.fuzzyEquals(0.5*StandardUnit.getBaseSpeed(),StandardUnit.getCurrentSpeed()));
	}
	
	@Test
	public final void getCurrentSpeed_Sprinting(){
		StandardUnit.moveToAdjacent(1, 1, 0);
		StandardUnit.startSprinting();
		assert(Util.fuzzyEquals(2.0*StandardUnit.getBaseSpeed(),StandardUnit.getCurrentSpeed()));
		
	}
	
	@Test
	public void moveToAdjacent_LegalCase(){
		StandardUnit.moveToAdjacent(1,0,-1);
		double speed = StandardUnit.getCurrentSpeed();
		double distance = Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(StandardUnit,time,0.05);
		Assert.assertArrayEquals(new double[] {4.5,1.5,3.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
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
	
	@Test(expected=IllegalArgumentException.class)
	public final void moveToAdjacent_IllegalArgument() throws IllegalArgumentException{
		
		StandardUnit.moveToAdjacent(0,0,2);
	}
	
	@Test(expected =IllegalArgumentException.class)
	public final void moveToAdjacent_MovingToIllegalPosition() throws IllegalArgumentException{
		StandardUnit.setPosition(new double[]{0.5,4.6,5.0});
		StandardUnit.moveToAdjacent(-1,0,1);
	}
	
	
	@Test
	public final void getCubeCentre(){
		assertDoublePositionEquals(1.5,2.5,3.5,Unit.getCubeCenter(new double[] {1.0,2.0,3.0}));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void advanceTime_IllegalArgument(){
		StandardUnit.advanceTime((float)0.3);
	}
	
	@Test
	public final void advanceTime_MustRest(){
		advanceTimeFor(StandardUnit,180.1,0.05);
		//System.out.println(StandardUnit.restTimer);
		assertEquals(Status.INITIAL_RESTING,StandardUnit.status);
	}
	
	@Test
	public final void advanceTime_InterruptedMovement(){
		StandardUnit.moveTo(new int[] {1,2,3});
		StandardUnit.status = Status.DONE;
		advanceTimeFor(StandardUnit,20.0,0.1);
		assertIntegerPositionEquals(1,2,3,StandardUnit.getCubeCoordinate());
	}
	
	@Test
	public final void advanceTime_ActivateDefaultBehaviour(){
		DefaultEnabledUnit.advanceTime((float)0.05);
		//System.out.println(DefaultEnabledUnit.status);
		assertTrue(DefaultEnabledUnit.status== Status.INITIAL_RESTING||DefaultEnabledUnit.status==Status.MOVING||DefaultEnabledUnit.status==Status.WORKING);

	}
	
	@Test
	public final void advanceTime_MovingToNextCube(){
		StandardUnit.moveTo(new int[]{4,1,4});
		double speed = StandardUnit.getCurrentSpeed();
		double time = 1.0 / speed;
		StandardUnit.advanceTime((float) 0.1);
		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
		advanceTimeFor(StandardUnit,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.DONE, StandardUnit.status);
		
	}
	
	@Test
	public final void advanceTime_Sprinting(){
		StandardUnit.moveTo(new int[]{4,1,4});
		StandardUnit.startSprinting();
		double speed = StandardUnit.getCurrentSpeed();
		double time = 1.0 / speed;
		StandardUnit.advanceTime((float) 0.1);
		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
		advanceTimeFor(StandardUnit,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.DONE, StandardUnit.status);
		Assert.assertEquals(25.0-10.0*time,StandardUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
	}

	@Test
	public final void advanceTime_MovingTwoCubes(){
		StandardUnit.moveTo(new int[]{5,1,4});
		double speed = StandardUnit.getCurrentSpeed();
		double time = 1.0 / speed;
		StandardUnit.advanceTime((float) 0.1);
		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
		advanceTimeFor(StandardUnit,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.MOVING, StandardUnit.status);
	}
	
	@Test
	public final void advanceTime_Working(){
		StandardUnit.work();
		double totalWorkingTime =  500.0 /25.0;
		advanceTimeFor(StandardUnit,totalWorkingTime, 0.1);
		assertEquals(StandardUnit.status,Status.DONE);
		
	}
	
	@Test
	public final void advanceTime_InitialResting(){
		HitAndStaminaZeroUnit.rest();
		HitAndStaminaZeroUnit.advanceTime((float) 0.1);
		HitAndStaminaZeroUnit.work();
		assertEquals((75/200.0)*5*0.1,HitAndStaminaZeroUnit.getHitpoints(),Util.DEFAULT_EPSILON);
		assertEquals(Status.INITIAL_RESTING,HitAndStaminaZeroUnit.status);
		
	}
	
	@Test
	public final void advanceTime_InitialRestingToResting(){
		HitAndStaminaZeroUnit.rest();
		double step = (200.0/(75*5))/7.0;
		advanceTimeFor(HitAndStaminaZeroUnit,200.0/(75*5),step);
		assertEquals(Status.RESTING,HitAndStaminaZeroUnit.status);
		
	}
	
	@Test
	public final void advanceTime_RestingRecoverHitpoints(){
		HitAndStaminaZeroUnit.status = Status.RESTING;
		double step = (200.0/(75*5))/7.0;
		advanceTimeFor(HitAndStaminaZeroUnit,200.0/(75*5),step);
		assertEquals(Status.RESTING,HitAndStaminaZeroUnit.status);
		
	}
	
	@Test
	public final void advanceTime_RestingRecoverStamina(){
		HitMaxStaminaZeroUnit.status = Status.RESTING;
		double step = (100.0/(75*5))/7.0;
		advanceTimeFor(HitMaxStaminaZeroUnit,100.0/(75*5),step);
		assertEquals(1.0,HitMaxStaminaZeroUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
		assertEquals(Status.RESTING,HitMaxStaminaZeroUnit.status);

	}
	
	@Test
	public final void advanceTime_RestingDone(){
		HitMaxStaminaMaxUnit.status = Status.RESTING;
		HitMaxStaminaMaxUnit.advanceTime((float)0.01);
		assertEquals(HitMaxStaminaMaxUnit.max_nbPoints(),HitMaxStaminaMaxUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
		assertEquals(HitMaxStaminaMaxUnit.max_nbPoints(),HitMaxStaminaMaxUnit.getHitpoints(),Util.DEFAULT_EPSILON);
		assertEquals(Status.DONE,HitMaxStaminaMaxUnit.status);
		
	}
	
	@Test
	public final void advanceTime_Attacking(){
		StandardUnit.status = Status.ATTACKING;
		advanceTimeFor(StandardUnit,1.0,0.1);
		assertEquals(Status.DONE,StandardUnit.status);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void moveTo_IllegalArgument() throws IllegalArgumentException{
		StandardUnit.moveTo(new double[] {50.5,12.1,12.0});
	}
	
	@Test
	public final void moveTo_CannotMove(){
		StandardUnit.status = Status.ATTACKING;
		StandardUnit.moveTo(new double[] {10.5,10.5,10.5});
		StandardUnit.advanceTime((float) 0.1);
		assertEquals(Status.ATTACKING,StandardUnit.status);

	}
	
	@Test
	public final void moveTo_EffectiveCase(){
		StandardUnit.moveTo(new double[] {10.5,1.5,4.5});
		//double speed = StandardUnit.getCurrentSpeed();
		//System.out.println(speed);
		advanceTimeFor(StandardUnit,14.0,0.1);
		assertDoublePositionEquals(10.5,1.5,4.5, StandardUnit.getPosition());
	}
	
	@Test
	public final void moveTo_IntegerPosition(){
		StandardUnit.moveTo(new int[] {10,1,4});
		advanceTimeFor(StandardUnit,14.0,0.1);
		assertDoublePositionEquals(10.5,1.5,4.5, StandardUnit.getPosition());
	}
	
	@Test
	public final void canMove_TrueCase(){
		StandardUnit.status = Status.RESTING;
		assertTrue(StandardUnit.canMove());
	}
	
	@Test
	public final void canMove_FalseCase(){
		StandardUnit.status = Status.MOVING;
		assertFalse(StandardUnit.canMove());
	}
	
	@Test
	public final void work_EffectiveCase(){
		StandardUnit.status = Status.DONE;
		StandardUnit.work();
		assertEquals(Status.WORKING,StandardUnit.status);
		advanceTimeFor(StandardUnit,500.0/25,0.1);
		assertEquals(Status.DONE,StandardUnit.status);
	}
	
	@Test
	public final void work_IneffectiveCase(){
		StandardUnit.status = Status.MOVING;
		StandardUnit.work();
		assertEquals(Status.MOVING,StandardUnit.status);
	}
	
	@Test
	public final void getProgressWork(){
		StandardUnit.status = Status.DONE;
		StandardUnit.work();
		assertEquals(Status.WORKING,StandardUnit.status);
		advanceTimeFor(StandardUnit,250.0/25,0.1);
		assertEquals(0.5,StandardUnit.getProgressWork(),Util.DEFAULT_EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void attack_IllegalArgument() throws IllegalArgumentException{
		StandardUnit.attack(Aunit);
	}
	
	@Test
	public final void attack_CannotAttack(){
		StandardUnit.status = Status.MOVING;
		StandardUnit.attack(NeighbourStandardUnit);
		assertEquals(Status.MOVING,StandardUnit.status);
	}
	
	@Test
	public final void attack_EffectiveCase(){
		StandardUnit.attack(NeighbourStandardUnit);
		assertEquals(Status.ATTACKING,StandardUnit.status);
		assertEquals(Math.atan2(StandardUnit.getPosition()[1] - NeighbourStandardUnit.getPosition()[1],
				StandardUnit.getPosition()[0] - NeighbourStandardUnit.getPosition()[0]),
				NeighbourStandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
//		assertEquals((float)Math.atan2(NeighbourStandardUnit.getPosition()[1] - StandardUnit.getPosition()[1],
//				NeighbourStandardUnit.getPosition()[0] - StandardUnit.getPosition()[0]),
//				StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);

	}
	
	
}
