package hillbillies.tests.facade;
import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.*;

import hillbillies.model.*;
import ogp.framework.util.*;

import hillbillies.part2.listener.DefaultTerrainChangeListener;


public class TestSuitePart2Unit {
	
	
	private static Unit Aunit;
	
	private Unit StandardUnit;
	
	private Unit DefaultEnabledUnit;
	
	private Unit HitAndStaminaZeroUnit;
	
	private Unit HitMaxStaminaZeroUnit;
	
	private Unit HitMaxStaminaMaxUnit;
	
	private Unit NeighbourStandardUnit;
	
	private Unit Unit1InWorld;
	
	private Unit NeighbourUnit1InWorld;
	
	private Unit UnitInWorldInvalidPos;
	
	private Unit BUnit;
	
	private Unit Cunit;
	
	private Unit Dunit;
	
	private World world1;
	
	private World world2;
	
	private World world3;
	
	private Faction Faction1;
	
	private Faction Faction2;
	
	private Boulder Boulder;
	
	private Log Log;
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;


	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		Aunit = new Unit("Aunit",new double[] {1.5,4,5,3.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
	}
	
	/**
	 * Set up a mutable test fixture.
	 */
	@Before
	public void setUpBefore(){
		int[][][] types = new int[3][3][3];
		types[1][1][0] = TYPE_ROCK;
		types[1][1][1] = TYPE_TREE;
		types[1][1][2] = TYPE_WORKSHOP;
		types[1][0][1] = TYPE_ROCK;
		world2 = new World(types, new DefaultTerrainChangeListener());
		int[][][] types1 = new int[4][4][4];
		types1[1][1][0] = TYPE_ROCK;
		types1[1][1][1] = TYPE_TREE;
		types1[1][1][2] = TYPE_WORKSHOP;
		types1[1][0][1] = TYPE_ROCK;
		world1 = new World(types1, new DefaultTerrainChangeListener());
		int[][][] types2 = new int[2][2][2];
		types2[1][1][0] = TYPE_ROCK;
		types2[0][1][0] = TYPE_ROCK;
		types2[1][0][0] = TYPE_ROCK;
		types2[0][0][0] = TYPE_ROCK;
		types2[1][0][1] = TYPE_ROCK;
		world3 = new World(types2, new DefaultTerrainChangeListener());
		Unit1InWorld = new Unit("Bunit",new double[] {1.5,2.5,2.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		NeighbourUnit1InWorld = new Unit("Bunit",new double[] {1.5,2.5,2.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		UnitInWorldInvalidPos = new Unit("Bunit",new double[] {0.5,1.5,2.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		Faction1 = new Faction();
		Faction2 = new Faction();
		StandardUnit = new Unit("Bunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		DefaultEnabledUnit = new Unit("Cunit",new double[] {3.5,1.5,4.5},75,25,25,75,true,25.0,25.0,Math.PI/2);
		HitAndStaminaZeroUnit = new Unit("Dunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		HitMaxStaminaZeroUnit = new Unit("Eunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		double maxpoints = HitMaxStaminaZeroUnit.getMaxPoints();
		HitMaxStaminaZeroUnit = new Unit("Eunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,maxpoints,25.0,Math.PI/2);
		HitMaxStaminaMaxUnit = new Unit("Funit",new double[] {1.5,2.5,1.5},75,25,25,75,false,maxpoints,maxpoints,Math.PI/2);
		NeighbourStandardUnit = new Unit("Bunit",new double[] {4.5,0.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		BUnit = new Unit("Bunit",new double[] {0.5,1.5,1.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		Boulder = new Boulder(new double[] {0.5,1.5,1.5});
		Log = new Log(new double[] {0.5,1.5,1.5});
		Cunit = new Unit("Cunit",new double[] {2.5,1.5,1.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		Dunit = new Unit("Dunit",new double[] {0.5,0.5,1.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		
	}
	
	@Test
	public final void extendConstructor_LegalCase(){
		Unit NewUnit = new Unit("Bunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		Assert.assertEquals("Bunit", NewUnit.getName());
		Assert.assertArrayEquals(new double[] {3.5,1.5,4.5}, NewUnit.getPosition(),Util.DEFAULT_EPSILON);
		Assert.assertEquals(75,NewUnit.getWeight());
		Assert.assertEquals(25,NewUnit.getAgility());
		Assert.assertEquals(75,NewUnit.getToughness());
		Assert.assertEquals(25,NewUnit.getStrength());
		Assert.assertEquals(false, NewUnit.isEnableDefaultBehaviour());
		Assert.assertEquals(25.0, NewUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
		Assert.assertEquals(25.0, NewUnit.getHitpoints(),Util.DEFAULT_EPSILON);
		Assert.assertEquals(Math.PI/2, NewUnit.getOrientation(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public final void extendedConstructor_InvalidAgility(){
		Unit NewUnit = new Unit("Bunit",new double[] {3.5,1.5,4.5},75,25,10,75,false,25.0,25.0,Math.PI/2);
		Assert.assertEquals(25, NewUnit.getAgility());
	}
	
	@Test
	public final void extendedConstructor_InvalidWeight(){
		Unit NewUnit = new Unit("Bunit",new double[] {3.5,1.5,4.5},45,50,50,75,false,25.0,25.0,Math.PI/2);
		Assert.assertEquals(50, NewUnit.getWeight());

	}
	
	@Test
	public final void getWeight() {
		Assert.assertEquals(75, StandardUnit.getWeight());
	}
	@Test
	public final void setWeight_NegativeNumber() {
		StandardUnit.setWeight(-25);
		Assert.assertEquals(25, StandardUnit.getWeight());
	}
	@Test
	public final void setWeight_LegalCase(){
		StandardUnit.setWeight(25);
		Assert.assertEquals(25, StandardUnit.getWeight());
	}
	@Test
	public final void setWeight_Overflow() {
		StandardUnit.setWeight(300);
		Assert.assertEquals(25, StandardUnit.getWeight());
	}
	@Test
	public final void getStrength() {
		Assert.assertEquals(25, StandardUnit.getStrength());
	}
	@Test
	public final void setStrength_NegativeNumber() {
		StandardUnit.setStrength(-25);
		Assert.assertEquals(25, StandardUnit.getStrength());
	}
	@Test
	public final void setStrength_LegalCase(){
		StandardUnit.setStrength(75);
		Assert.assertEquals(75, StandardUnit.getStrength());
	}
	@Test
	public final void setStrength_Overflow() {
		StandardUnit.setStrength(300);
		Assert.assertEquals(25, StandardUnit.getStrength());
	}
	@Test
	public final void getAgility() {
		Assert.assertEquals(25, StandardUnit.getAgility());
	}
	@Test
	public final void setAgility_NegativeNumber() {
		StandardUnit.setAgility(-25);
		Assert.assertEquals(25, StandardUnit.getAgility());
	}
	@Test
	public final void setAgility_LegalCase(){
		StandardUnit.setAgility(75);
		Assert.assertEquals(75, StandardUnit.getAgility());
	}
	@Test
	public final void setAgility_Overflow() {
		StandardUnit.setAgility(300);
		Assert.assertEquals(25, StandardUnit.getAgility());
	}
	@Test
	public final void getToughness() {
		Assert.assertEquals(75, StandardUnit.getToughness());
	}
	@Test
	public final void setToughness_NegativeNumber() {
		StandardUnit.setToughness(-25);
		Assert.assertEquals(75, StandardUnit.getToughness());
	}
	@Test
	public final void setToughness_LegalCase(){
		StandardUnit.setToughness(75);
		Assert.assertEquals(75, StandardUnit.getToughness());
	}
	@Test
	public final void setToughness_Overflow() {
		StandardUnit.setToughness(300);
		Assert.assertEquals(75, StandardUnit.getToughness());
	}

	@Test
	public final void getHitpoints() {
		Assert.assertEquals(25.0, StandardUnit.getHitpoints(),Util.DEFAULT_EPSILON);
	}
	public final void getMaxPoints() {
		Assert.assertEquals(Math.ceil(200.0 * (75.0 / 100.0) * (75.0/ 100.0)), StandardUnit.getMaxPoints(),Util.DEFAULT_EPSILON);
	}
	@Test
	public final void getStaminaPoints() {
		Assert.assertEquals(25.0, StandardUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
	}
	@Test
	public final void getName() {
		Assert.assertEquals("Bunit", StandardUnit.getName());
	}
	@Test
	public final void setName_LegalCase() throws IllegalArgumentException{
		StandardUnit.setName("B\'unit b\"uNit");
		Assert.assertEquals("B\'unit b\"uNit", StandardUnit.getName());
	}
	@Test (expected = IllegalArgumentException.class)
	public final void setName_InvalidToken() throws IllegalArgumentException{
		StandardUnit.setName("Bunit1");
	}
	@Test (expected = IllegalArgumentException.class) 
	public final void setName_InvalidLength() throws IllegalArgumentException{
		StandardUnit.setName("A");
	}
	@Test (expected = IllegalArgumentException.class)
	public final void setName_NoUppercase() throws IllegalArgumentException{
		StandardUnit.setName("bunit");
	}
	@Test
	public final void getOrientation() {
		Assert.assertEquals(Math.PI/2,StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
	}
	@Test
	public final void setOrientation_NormalCase() {
		StandardUnit.setOrientation((float) (Math.PI/4));
		Assert.assertEquals(Math.PI/4, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
	}
	@Test
	public final void setOrientation_ExceedingCase() {
		StandardUnit.setOrientation((float) (3*Math.PI));
		Assert.assertEquals(Math.PI, StandardUnit.getOrientation(), Util.DEFAULT_EPSILON);
	}
	@Test
	public final void setOrientation_NegativeCase() {
		StandardUnit.setOrientation((float) (-5*Math.PI/2)); 
		Assert.assertEquals(3*Math.PI/2, StandardUnit.getOrientation(), Util.DEFAULT_EPSILON);
	}
	@Test
	public final void getPosition() {
		assertDoublePositionEquals(3.5,1.5,4.5,StandardUnit.getPosition());
	}
//	@Test 
//	public final void getCubePosition() {
//		Assert.assertArrayEquals(new double[] {Math.floor(3.5),Math.floor(1.5),Math.floor(4.5)}, StandardUnit.getCubePosition(), Util.DEFAULT_EPSILON);
//	}
	@Test
	public final void getCubeCoordinate() {
		Assert.assertArrayEquals(new int[] {(int) Math.floor(3.5),(int) Math.floor(1.5),(int) Math.floor(4.5)}, 
				new int[] {(int) StandardUnit.getCubeCoordinate()[0],(int) StandardUnit.getCubeCoordinate()[1],(int) StandardUnit.getCubeCoordinate()[2]});
	}
	@Test (expected = IllegalArgumentException.class)
	public final void setPosition_InvalidOutsideWorld() throws IllegalArgumentException {
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setPosition(new double[] {-0.5,1.5,2.3});
	}
	@Test 
	public final void setPosition_ValidInWorld() throws IllegalArgumentException{
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setPosition(new double[] {0.5,1.5,2.3});
		Assert.assertArrayEquals(Unit1InWorld.getPosition(),new double[] {0.5,1.5,2.3},Util.DEFAULT_EPSILON);
	}
	@Test(expected = IllegalArgumentException.class)
	public final void setPosition_InvalidInWorld() throws IllegalArgumentException{
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setPosition(new double[] {1.5,1.5,1.3});
	}
	@Test
	public final void getBaseSpeed(){
		Assert.assertEquals(1.5*(50+50)/(200.0*50/100.0), Aunit.getBaseSpeed(), Util.DEFAULT_EPSILON);
	}
	
	@Test 
	public final void getStatus(){
		Assert.assertEquals(StandardUnit.getStatus(), Status.DONE);
		StandardUnit.setStatus(Status.IN_CENTER);
		Assert.assertEquals(StandardUnit.getStatus(), Status.IN_CENTER);
	}
	
	@Test
	public final void setStatus(){
		StandardUnit.setStatus(Status.IN_CENTER);
		Assert.assertEquals(StandardUnit.getStatus(), Status.IN_CENTER);
		StandardUnit.setStatus(Status.ATTACKING);
		Assert.assertEquals(StandardUnit.getStatus(), Status.ATTACKING);
	}
	
	@Test
	public final void getFaction(){
		Faction1.addAsUnit(StandardUnit);
		StandardUnit.setFaction(Faction1);
		Assert.assertEquals(StandardUnit.getFaction(), Faction1);
	}
	
	@Test
	public final void setFaction_Null(){
		StandardUnit.setFaction(null);
		Assert.assertEquals(StandardUnit.getFaction(), null);
	}
	
	@Test
	public final void setFaction_Effective(){
		Faction1.addAsUnit(StandardUnit);
		StandardUnit.setFaction(Faction1);
		Assert.assertEquals(StandardUnit.getFaction(), Faction1);
	}
	
	@Test
	public final void setFaction_NonEffective(){
		Unit1InWorld.setFaction(Faction1);
		Assert.assertEquals(Unit1InWorld.getFaction(), Faction1);
	}
	
	@Test
	public final void setWorld_null(){
		StandardUnit.setWorld(null);
		Assert.assertEquals(StandardUnit.getWorld(), null);
	}
	
	@Test
	public final void setWorld_Effective(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setWorld(world2);
		Assert.assertEquals(Unit1InWorld.getWorld(), world2);
	}
	
	@Test
	public final void setWorld_NonEffective(){
		BUnit.setWorld(world2);
		Assert.assertFalse(world2.hasAsUnit(BUnit));
		Assert.assertEquals(BUnit.getWorld(), null);
	}
	
	@Test 
	public final void getWorld(){
		StandardUnit.setWorld(null);
		Assert.assertEquals(StandardUnit.getWorld(), null);
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setWorld(world2);
		Assert.assertEquals(Unit1InWorld.getWorld(), world2);
		
	}
	
	@Test
	public final void startSprinting_Effective(){
		StandardUnit.setStatus(Status.MOVING);
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
		StandardUnit.setStatus(Status.DONE);
		assert(Util.fuzzyEquals(StandardUnit.getCurrentSpeed(), 0.0, Util.DEFAULT_EPSILON));
	}
	
	@Test
	public final void getCurrentSpeed_WalkingStraight(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveToAdjacent(1, 0, 0);
		assert(Util.fuzzyEquals(Unit1InWorld.getBaseSpeed(), Unit1InWorld.getCurrentSpeed()));
	}
	
	@Test
	public final void getCurrentSpeed_WalkingUp(){
		Unit1InWorld.setPosition(new double[] {1.5,2.5,1.5});
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveToAdjacent(0, 0, 1);
		assert(Util.fuzzyEquals(1.2*Unit1InWorld.getBaseSpeed(), Unit1InWorld.getCurrentSpeed()));
	}
	@Test
	public final void getCurrentSpeed_WalkingDown(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveToAdjacent(0, 0, -1);
		assert(Util.fuzzyEquals(0.5*Unit1InWorld.getBaseSpeed(),Unit1InWorld.getCurrentSpeed()));
	}
	
	@Test
	public final void getCurrentSpeed_Sprinting(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveToAdjacent(1, -1, 0);
		Unit1InWorld.startSprinting();
		assert(Util.fuzzyEquals(2.0*Unit1InWorld.getBaseSpeed(),Unit1InWorld.getCurrentSpeed()));
		
	}
	
	@Test
	public final void getCurrentSpeed_Falling(){
		StandardUnit.setStatus(Status.FALLING);
		assert(Util.fuzzyEquals(3,StandardUnit.getCurrentSpeed()));
		
	}
	
	@Test
	public final void moveToAdjacent_LegalCase(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveToAdjacent(1,0,-1);
		double speed = Unit1InWorld.getCurrentSpeed();
		double distance = Math.sqrt(2);
		double time = distance / speed;
		advanceTimeFor(Unit1InWorld,time,0.05);
		Assert.assertArrayEquals(new double[] {2.5,2.5,1.5}, Unit1InWorld.getPosition(), Util.DEFAULT_EPSILON);
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
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveToAdjacent(0,1,1);
	}
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public final void advanceTime_IllegalArgument(){
		StandardUnit.advanceTime((float)0.3);
	}
	
	@Test
	public final void advanceTime_MustRest(){
		world2.addAsUnit(Unit1InWorld);
		advanceTimeFor(Unit1InWorld,180.1,0.05);
		//System.out.println(StandardUnit.restTimer);
		assertEquals(Status.INITIAL_RESTING,Unit1InWorld.getStatus());
	}
	@Test
	public final void advanceTime_PointsExceed1(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setExperiencePoints(11);
		Unit1InWorld.advanceTime((float) 0.1);
		Assert.assertEquals(26,Unit1InWorld.getStrength());
		Assert.assertEquals(25,Unit1InWorld.getAgility());
		Assert.assertEquals(75,Unit1InWorld.getToughness());
		Assert.assertEquals(1,Unit1InWorld.getExperiencePoints());
	}
	
	@Test
	public final void advanceTime_PointsExceed2(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setExperiencePoints(11);
		Unit1InWorld.setStrength(200);
		Unit1InWorld.setWeight(115);
		Unit1InWorld.advanceTime((float) 0.1);
		Assert.assertEquals(26,Unit1InWorld.getAgility());
		Assert.assertEquals(200,Unit1InWorld.getStrength());
		Assert.assertEquals(75,Unit1InWorld.getToughness());
		Assert.assertEquals(1,Unit1InWorld.getExperiencePoints());
	}
	
	@Test
	public final void advanceTime_PointsExceed3(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setExperiencePoints(11);
		Unit1InWorld.setStrength(200);
		Unit1InWorld.setAgility(200);
		Unit1InWorld.setWeight(200);
		Unit1InWorld.advanceTime((float) 0.1);
		Assert.assertEquals(200,Unit1InWorld.getAgility());
		Assert.assertEquals(200,Unit1InWorld.getStrength());
		Assert.assertEquals(76,Unit1InWorld.getToughness());
		Assert.assertEquals(1,Unit1InWorld.getExperiencePoints());
	}
	
	@Test
	public final void advanceTime_PointsNotExceed(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setExperiencePoints(2);
		Unit1InWorld.advanceTime((float) 0.1);
		Assert.assertEquals(25,Unit1InWorld.getStrength());
		Assert.assertEquals(2, Unit1InWorld.getExperiencePoints());
	}
	
	@Test
	public final void advanceTime_terminate() {
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setHitPoints(0);
		Unit1InWorld.advanceTime((float) 0.1);
		Assert.assertTrue(Unit1InWorld.isTerminated());
	}
	
	@Test
	public final void advanceTime_NotTerminate() {
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.advanceTime((float) 0.1);
		Assert.assertFalse(Unit1InWorld.isTerminated());
	}
	
	@Test
	public final void advanceTime_mustFall(){
		world1.addAsUnit(Unit1InWorld);
		Unit1InWorld.setPosition(new double[] {1.5,3.5,2.5});
		Unit1InWorld.advanceTime((float)0.1);
		Assert.assertEquals(Status.FALLING, Unit1InWorld.getStatus());
	}
	
	@Test
	public final void advanceTime_Falling(){
		world1.addAsUnit(Unit1InWorld);
		Unit1InWorld.setPosition(new double[] {1.5,3.5,2.5});
		double speed = 3.0;
		double time = 1.0 / speed;
		Unit1InWorld.advanceTime((float) 0.1);
		assertDoublePositionEquals(1.5,3.5,2.5-speed*0.1,Unit1InWorld.getPosition());
		advanceTimeFor(Unit1InWorld,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {1.5,3.5,1.5}, Unit1InWorld.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.FALLING, Unit1InWorld.getStatus());
		advanceTimeFor(Unit1InWorld,time,0.1);
		Assert.assertArrayEquals(new double[] {1.5,3.5,0.5}, Unit1InWorld.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.DONE, Unit1InWorld.getStatus());
	}
	
//	@Test
//	public final void advanceTime_InterruptedMovement(){
//		world2.addAsUnit(Unit1InWorld);
//		Unit1InWorld.moveTo1(new int[] {0,0,2});
//		Unit1InWorld.setStatus(Status.DONE);
//		advanceTimeFor(Unit1InWorld,20.0,0.1);
//		assertIntegerPositionEquals(0,0,2,Unit1InWorld.getCubeCoordinate());
//	}
	
//	@Test
//	public final void advanceTime_ActivateDefaultBehaviourNoEnemies(){
//		world1.addAsUnit(Unit1InWorld);
//		Unit1InWorld.setStatus(Status.DONE);
//		Unit1InWorld.startDefaultBehaviour();
//		Unit1InWorld.advanceTime((float)0.05);
//		System.out.println(Unit1InWorld.getStatus());
//		assertTrue(Unit1InWorld.getStatus()== Status.INITIAL_RESTING||Unit1InWorld.getStatus()==Status.MOVING||Unit1InWorld.getStatus()==Status.WORKING);
//
//	}
//	
//	@Test
//	public final void search(){
//		world2.addAsUnit(Unit1InWorld);
//		int[] array = new int[] {(int) Unit1InWorld.getPosition()[0], (int) Unit1InWorld.getPosition()[1],
//				(int) Unit1InWorld.getPosition()[2], (int) 0};
//		Assert.assertEquals(4, Unit1InWorld.search(array).size());
//	}
//	
//	@Test
//	public final void advanceTime_MovingToNextCube(){
//		Unit1InWorld.moveTo1(new int[]{0,2,2});
//		double speed = Unit1InWorld.getCurrentSpeed();
//		double time = 1.0 / speed;
//		Unit1InWorld.advanceTime((float) 0.1);
//		assertDoublePositionEquals(1.5-speed*0.1,2.5,2.5,Unit1InWorld.getPosition());
//		assertEquals(0.0, Unit1InWorld.getOrientation(),Util.DEFAULT_EPSILON);
//		advanceTimeFor(Unit1InWorld,time-0.1,0.1);
//		Assert.assertArrayEquals(new double[] {0.5,2.5,2.5}, Unit1InWorld.getPosition(), Util.DEFAULT_EPSILON);
//		Assert.assertEquals(Status.DONE, Unit1InWorld.getStatus());
//		
//	}
//	
//	@Test
//	public final void advanceTime_Sprinting(){
//		StandardUnit.moveTo1(new int[]{4,1,4});
//		StandardUnit.startSprinting();
//		double speed = StandardUnit.getCurrentSpeed();
//		double time = 1.0 / speed;
//		StandardUnit.advanceTime((float) 0.1);
//		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
//		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
//		advanceTimeFor(StandardUnit,time-0.1,0.1);
//		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
//		Assert.assertEquals(Status.DONE, StandardUnit.getStatus());
//		Assert.assertEquals(25.0-10.0*time,StandardUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
//	}
//
//	@Test
//	public final void advanceTime_MovingTwoCubes(){
//		StandardUnit.moveTo1(new int[]{5,1,4});
//		double speed = StandardUnit.getCurrentSpeed();
//		double time = 1.0 / speed;
//		StandardUnit.advanceTime((float) 0.1);
//		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
//		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
//		advanceTimeFor(StandardUnit,time-0.1,0.1);
//		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
//		Assert.assertEquals(Status.MOVING, StandardUnit.getStatus());
//	}
//	
	@Test
	public final void advanceTime_Working(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.work(new int[] {0,2,2});
		double totalWorkingTime =  500.0 /Unit1InWorld.getStrength();
		advanceTimeFor(Unit1InWorld,totalWorkingTime, 0.1);
		assertEquals(StandardUnit.getStatus(),Status.DONE);
	}
	
//	@Test
//	public final void advanceTime_InitialResting(){
//		world2.addAsUnit(Unit1InWorld);
//		Unit1InWorld.rest();
//		Unit1InWorld.advanceTime((float) 0.1);
//		Unit1InWorld.work(new int[] {0,2,2});
//		assertEquals((25+Unit1InWorld.getToughness()/200.0)*5*0.1,Unit1InWorld.getHitpoints(),Util.DEFAULT_EPSILON);
//		assertEquals(Status.INITIAL_RESTING,Unit1InWorld.getStatus());
//		
//	}
	
	@Test
	public final void advanceTime_InitialRestingToResting(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.rest();
		Unit1InWorld.rest();
		double step = (200.0/(75*5))/7.0;
		advanceTimeFor(Unit1InWorld,200.0/(75*5),step);
		assertEquals(Status.RESTING,Unit1InWorld.getStatus());
		
	}
	
	@Test
	public final void advanceTime_RestingRecoverHitpoints(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setStatus(Status.RESTING);
		double step = (200.0/(75*5))/7.0;
		advanceTimeFor(Unit1InWorld,200.0/(75*5),step);
		assertEquals(Status.RESTING,Unit1InWorld.getStatus());
		
	}
	
//	@Test
//	public final void advanceTime_RestingRecoverStamina(){
//		world2.addAsUnit(Unit1InWorld);
//		Unit1InWorld.setStatus(Status.RESTING);
//		double step = (100.0/(75*5))/7.0;
//		advanceTimeFor(Unit1InWorld,100.0/(75*5),step);
//		assertEquals(1.0,Unit1InWorld.getStaminaPoints(),Util.DEFAULT_EPSILON);
//		assertEquals(Status.RESTING,Unit1InWorld.getStatus());

//	}
	
	@Test
	public final void advanceTime_RestingDone(){
		world2.addAsUnit(HitMaxStaminaMaxUnit);
		HitMaxStaminaMaxUnit.setStatus(Status.RESTING);
		HitMaxStaminaMaxUnit.advanceTime((float)0.01);
		assertEquals(Status.DONE,Unit1InWorld.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Attacking(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setStatus(Status.ATTACKING);
		advanceTimeFor(Unit1InWorld,1.0,0.1);
		assertEquals(Status.DONE,Unit1InWorld.getStatus());
	}
	
	@Test
	public void mustFall_trueCase(){
		world1.addAsUnit(Unit1InWorld);
		Unit1InWorld.setPosition(new double[] {1.5,3.5,2.5});
		Assert.assertTrue(Unit1InWorld.mustFall());
	}
	
	@Test
	public void mustFall_FalseCase(){
		world1.addAsUnit(Unit1InWorld);
		Assert.assertFalse(Unit1InWorld.mustFall());
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public final void moveTo_IllegalArgumentOutOfBounds() throws IllegalArgumentException{
		world1.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveTo1(new double[] {50.5,12.1,12.0});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void moveTo_IllegalArgumentSolid() throws IllegalArgumentException{
		world1.addAsUnit(Unit1InWorld);
		Unit1InWorld.moveTo1(new double[] {1.5,0.5,1.5});
	}
	
	@Test
	public final void moveTo_CannotMove(){
		world1.addAsUnit(Unit1InWorld);
		Unit1InWorld.setStatus(Status.ATTACKING);
		Unit1InWorld.moveTo1(new double[] {0.5,2.5,2.5});
		Unit1InWorld.advanceTime((float) 0.1);
		assertEquals(Status.ATTACKING,Unit1InWorld.getStatus());

	}
	
	@Test
	public final void queueContainsPos(){
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(StandardUnit.getCubeCoordinate());
		Assert.assertTrue(StandardUnit.queueContainsPos(queue, StandardUnit.getCubeCoordinate()));
	}
	
	@Test
	public final void queueContainsPos_Long(){
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(StandardUnit.getCubeCoordinate());
		queue.add(new int[] {1,5,6});
		Assert.assertTrue(StandardUnit.queueContainsPos(queue, StandardUnit.getCubeCoordinate()));
	}
	
	@Test
	public final void moveTo_OneCube(){
		world3.addAsUnit(BUnit);
		BUnit.moveTo1(new int[] {0,0,1});
//		double speed = Bunit.getCurrentSpeed();
//		System.out.println(speed);
		advanceTimeFor(BUnit,30.0,0.1);
		System.out.print(BUnit.getStatus());
		System.out.println(Arrays.toString(BUnit.getPosition()));
		assertDoublePositionEquals(0.5,0.5,1.5, BUnit.getPosition());
	}
	
	@Test
	public final void moveTo_OnePath(){
		world3.addAsUnit(BUnit);
		BUnit.moveTo1(new int[] {0,1,1});
//		double speed = Bunit.getCurrentSpeed();
//		System.out.println(speed);
		advanceTimeFor(BUnit,30.0,0.1);
		System.out.print(BUnit.getStatus());
		System.out.println(Arrays.toString(BUnit.getPosition()));
		assertDoublePositionEquals(0.5,1.5,1.5, BUnit.getPosition());
	}
	
//	@Test
//	public final void moveTo_IntegerPosition(){
//		StandardUnit.moveTo1(new int[] {10,1,4});
//		advanceTimeFor(StandardUnit,14.0,0.1);
//		assertDoublePositionEquals(10.5,1.5,4.5, StandardUnit.getPosition());
//	}
	
	@Test
	public final void canMove_TrueCase(){
		StandardUnit.setStatus(Status.RESTING);
		assertTrue(StandardUnit.canMove());
	}
	
	@Test
	public final void canMove_FalseCase(){
		StandardUnit.setStatus(Status.MOVING);
		assertFalse(StandardUnit.canMove());
	}
	
	@Test
	public final void work_EffectiveCase(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setStatus(Status.DONE);
		Unit1InWorld.setPosition(new double[] {1.5,2.5,0.5});
		Unit1InWorld.work(new int[] {1,1,0});
		assertEquals(Status.WORKING,Unit1InWorld.getStatus());
		advanceTimeFor(Unit1InWorld,500.0/25,0.1);
		assertEquals(Status.DONE,StandardUnit.getStatus());
		assertEquals(world2.getTerrain(new int[] {1,1,0}), TerrainType.AIR);
	}
	
	@Test
	public final void work_IneffectiveCase(){
		world2.addAsUnit(BUnit);
		BUnit.setStatus(Status.MOVING);
		BUnit.work(new int[] {2,1,2});
		assertEquals(Status.MOVING,BUnit.getStatus());
	}
	
	@Test
	public final void getProgressWork(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setStatus(Status.DONE);
		Unit1InWorld.work(Unit1InWorld.getCubeCoordinate());
		assertEquals(Status.WORKING,Unit1InWorld.getStatus());
		advanceTimeFor(Unit1InWorld,250.0/25,0.1);
		assertEquals(0.5,Unit1InWorld.getProgressWork(),Util.DEFAULT_EPSILON);
	}
	
	@Test
	public final void canWork_False(){
		HitAndStaminaZeroUnit.setStatus(Status.MOVING);
		assertFalse(HitAndStaminaZeroUnit.canWork());
	}
	
	@Test
	public final void canWork_True(){
		assertTrue(HitAndStaminaZeroUnit.canWork());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void attack_IllegalArgument() throws IllegalArgumentException{
		world2.addAsUnit(Dunit);
		world2.addAsUnit(Unit1InWorld);
		Assert.assertTrue(Dunit.getFaction()!=Unit1InWorld.getFaction());
		Unit1InWorld.attack(Dunit);
	}
	
//	@Test
//	public final void attack_CannotAttackStatus(){
//		Faction1.addAsUnit(StandardUnit);
//		Faction2.addAsUnit(NeighbourStandardUnit);
//		StandardUnit.setStatus(Status.MOVING);
//		StandardUnit.attack(NeighbourStandardUnit);
//		assertEquals(Status.MOVING,StandardUnit.getStatus());
//	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void attack_CannotAttackFaction() throws IllegalArgumentException{
		world2.addAsUnit(BUnit);
		BUnit.attack(BUnit);
	}
	
	@Test
	public final void attack_EffectiveCase(){
		world2.addAsUnit(Unit1InWorld);
		world2.addAsUnit(NeighbourUnit1InWorld);
		Unit1InWorld.attack(NeighbourUnit1InWorld);
		assertEquals(Status.ATTACKING,Unit1InWorld.getStatus());
		assertEquals(2 * Math.PI + Math.atan2(NeighbourUnit1InWorld.getPosition()[1] - Unit1InWorld.getPosition()[1],
		NeighbourUnit1InWorld.getPosition()[0] - Unit1InWorld.getPosition()[0]) % (2 * Math.PI),
		Unit1InWorld.getOrientation(),Util.DEFAULT_EPSILON);
		

		assertEquals(Math.atan2(Unit1InWorld.getPosition()[1] - NeighbourUnit1InWorld.getPosition()[1],
				Unit1InWorld.getPosition()[0] - NeighbourUnit1InWorld.getPosition()[0]),
				NeighbourUnit1InWorld.getOrientation(),Util.DEFAULT_EPSILON);
//this test sometimes fails!
	}
	
	@Test
	public final void canAttack_FalseCase(){
		StandardUnit.setStatus(Status.MOVING);
		assertFalse(StandardUnit.canAttack());
		StandardUnit.setStatus(Status.FALLING);
		assertFalse(StandardUnit.canAttack());
		StandardUnit.terminate();
		assertFalse(StandardUnit.canAttack());
	}
	
	@Test
	public final void canAttack_TrueCase(){
		StandardUnit.setStatus(Status.DONE);
		assertTrue(StandardUnit.canAttack());
	}
	
	@Test
	public final void defend(){
		Unit1InWorld.defend(NeighbourUnit1InWorld);
		assertEquals((float)Math.atan2(NeighbourUnit1InWorld.getPosition()[1] - Unit1InWorld.getPosition()[1],
				NeighbourUnit1InWorld.getPosition()[0] - Unit1InWorld.getPosition()[0]),
				Unit1InWorld.getOrientation(),Util.DEFAULT_EPSILON);
		assertTrue((Util.fuzzyEquals(Unit1InWorld.getHitpoints(), 25.0))||
				(Util.fuzzyEquals(Unit1InWorld.getHitpoints(), 25.0-NeighbourUnit1InWorld.getStrength()/10.0)));
		
	}
	
	@Test
	public final void rest_IneffectiveCase(){
		HitMaxStaminaMaxUnit.rest();
		assertEquals(Status.DONE,HitMaxStaminaMaxUnit.getStatus());
	}
	
	@Test
	public final void rest_SkipInitialResting(){
		HitMaxStaminaZeroUnit.rest();
		assertEquals(Status.RESTING,HitMaxStaminaZeroUnit.getStatus());
	}
	
	@Test
	public final void rest_StartInitialResting(){
		HitAndStaminaZeroUnit.rest();
		assertEquals(Status.INITIAL_RESTING,HitAndStaminaZeroUnit.getStatus());
	}
	
	@Test
	public final void mustRest_FalseCase(){
		assertFalse(StandardUnit.mustRest());
	}
	
	@Test
	public final void mustRest_TrueCase(){
		world2.addAsUnit(Unit1InWorld);
		advanceTimeFor(Unit1InWorld,180.0,0.1);
		assertEquals(Status.INITIAL_RESTING,Unit1InWorld.getStatus());
		// mustRest() is always false, because as soon as mustRest becomes true, the unit starts resting and it becomes false again
		}
	
	@Test
	public final void canRest_FalseBecauseStaminaOrHitPointsMax(){
		assertFalse(HitMaxStaminaMaxUnit.canRest());
	}
	
	@Test
	public final void canRest_FalseBecauseWrongStatus(){
		HitAndStaminaZeroUnit.setStatus(Status.MOVING);
		assertFalse(HitAndStaminaZeroUnit.canRest());
	}
	
	@Test
	public final void canRest_True(){
		assertTrue(HitAndStaminaZeroUnit.canRest());
	}
	
	@Test
	public final void startDefaultBehaviour_Ineffective(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setStatus(Status.ATTACKING);
		Unit1InWorld.startDefaultBehaviour();
		assertEquals(Status.ATTACKING,Unit1InWorld.getStatus());
		assertFalse(Unit1InWorld.isEnableDefaultBehaviour());
		}
	
	@Test
	public final void startDefaultBehaviour_EffectiveNoEnemies(){
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.startDefaultBehaviour();
		assertTrue(Unit1InWorld.isEnableDefaultBehaviour());
		assertTrue(Unit1InWorld.getStatus()==Status.MOVING || Unit1InWorld.getStatus() == Status.INITIAL_RESTING ||Unit1InWorld.getStatus()==Status.WORKING);
	}
	
	@Test
	public final void startDefaultBehaviour_EffectiveEnemies(){
		world2.addAsUnit(Unit1InWorld);
		world2.addAsUnit(NeighbourUnit1InWorld);
		Assert.assertFalse(Unit1InWorld.getFaction() == NeighbourUnit1InWorld.getFaction());
		Unit1InWorld.startDefaultBehaviour();
		assertTrue(Unit1InWorld.isEnableDefaultBehaviour());
		assertTrue(Unit1InWorld.getStatus()==Status.MOVING || Unit1InWorld.getStatus() == Status.INITIAL_RESTING 
				||Unit1InWorld.getStatus()==Status.WORKING || Unit1InWorld.getStatus() == Status.ATTACKING);
		
	}
	
	@Test
	public final void stopDefaultBehaviour(){
		DefaultEnabledUnit.stopDefaultBehaviour();
		assertFalse(DefaultEnabledUnit.isEnableDefaultBehaviour());
	}
	
	@Test
	public final void isEnableDefaultBehaviour_True(){
		assertTrue(DefaultEnabledUnit.isEnableDefaultBehaviour());
	}
	
	@Test
	public final void isEnableDefaultBehaviour_False(){
		assertFalse(StandardUnit.isEnableDefaultBehaviour());
	}
	
	@Test
	public final void terminate(){
		StandardUnit.terminate();
		Assert.assertTrue(StandardUnit.isTerminated());
	}
	
	@Test
	public final void isTerminated_True(){
		StandardUnit.terminate();
		Assert.assertTrue(StandardUnit.isTerminated());
	}
	
	@Test
	public final void isTerminated_False(){
		Assert.assertFalse(StandardUnit.isTerminated());
	}
	
	@Test
	public final void getBoulder(){
		Assert.assertEquals(null, StandardUnit.getBoulder());
	}
	
	@Test
	public final void getBoulder_NotNull(){
		world2.addAsBoulder(Boulder);
		world2.addAsUnit(BUnit);
		BUnit.setBoulder(Boulder);
		Assert.assertEquals(Boulder, BUnit.getBoulder());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void setBoulder_IllegalArgument() throws IllegalArgumentException{
		world2.addAsUnit(Unit1InWorld);
		world3.addAsBoulder(Boulder);
		Unit1InWorld.setBoulder(Boulder);
	}
	
	@Test
	public final void setBoulder_LegalCase(){
		StandardUnit.setBoulder(null);
		Assert.assertEquals(null, StandardUnit.getBoulder());
	}
	
	@Test
	public final void getNbBoulders_1(){
		world2.addAsBoulder(Boulder);
		world2.addAsUnit(BUnit);
		BUnit.setBoulder(Boulder);
		Assert.assertEquals(1, BUnit.getNbBoulders());
	}
	
	@Test
	public final void getNbBoulders_0(){
		StandardUnit.setBoulder(null);
		Assert.assertEquals(0, StandardUnit.getNbBoulders());
	}
	
	@Test
	public final void isValidBoulder_False(){
		world1.addAsUnit(Unit1InWorld);
		world1.addAsBoulder(Boulder);
		Boulder.terminate();
		Assert.assertTrue(Boulder.isTerminated());
		Assert.assertFalse(Unit1InWorld.isValidBoulder(Boulder));
	}
	
	@Test
	public final void getLog(){
		Assert.assertEquals(null, StandardUnit.getLog());
	}
	
	@Test
	public final void getLog_NotNull(){
		world2.addAsLog(Log);
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setLog(Log);
		Assert.assertEquals(Log, Unit1InWorld.getLog());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public final void setLog_IllegalArgument() throws IllegalArgumentException{
		world2.addAsUnit(Unit1InWorld);
		world1.addAsLog(Log);
		Unit1InWorld.setLog(Log);
	}
	
	@Test
	public final void setLog_LegalCase(){
		StandardUnit.setLog(null);
		Assert.assertEquals(null, StandardUnit.getLog());
	}
	
	@Test
	public final void getExperiencePoints(){
		StandardUnit.setExperiencePoints(5);
		Assert.assertEquals(5, StandardUnit.getExperiencePoints());
	}
	
	@Test
	public final void setExperiencePoints(){
		StandardUnit.setExperiencePoints(5);
		Assert.assertEquals(5, StandardUnit.getExperiencePoints());
	}

}
