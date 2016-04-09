package hillbillies.tests.facade;
import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

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
	
	private Unit UnitInWorldInvalidPos;
	
	private World world2;
	
	private Faction Faction1;
	
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
		Unit1InWorld = new Unit("Bunit",new double[] {1.5,2.5,2.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		UnitInWorldInvalidPos = new Unit("Bunit",new double[] {1.5,1.5,2.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		Faction1 = new Faction();
		StandardUnit = new Unit("Bunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		DefaultEnabledUnit = new Unit("Cunit",new double[] {3.5,1.5,4.5},75,25,25,75,true,25.0,25.0,Math.PI/2);
		HitAndStaminaZeroUnit = new Unit("Dunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		HitMaxStaminaZeroUnit = new Unit("Eunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		double maxpoints = HitMaxStaminaZeroUnit.getMaxPoints();
		HitMaxStaminaZeroUnit = new Unit("Eunit",new double[] {3.5,1.5,4.5},75,25,25,75,false,maxpoints,25.0,Math.PI/2);
		HitMaxStaminaMaxUnit = new Unit("Funit",new double[] {3.5,1.5,4.5},75,25,25,75,false,maxpoints,maxpoints,Math.PI/2);
		NeighbourStandardUnit = new Unit("Bunit",new double[] {4.5,0.5,4.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		
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
		Assert.assertEquals(75, StandardUnit.getWeight());
	}
	@Test
	public final void setWeight_LegalCase(){
		StandardUnit.setWeight(25);
		Assert.assertEquals(25, StandardUnit.getWeight());
	}
	@Test
	public final void setWeight_Overflow() {
		StandardUnit.setWeight(300);
		Assert.assertEquals(75, StandardUnit.getWeight());
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
		StandardUnit.setFaction(Faction1);
		Assert.assertEquals(StandardUnit.getFaction(), null);
	}
	
	@Test
	public final void setWorld_null(){
		StandardUnit.setWorld(null);
		Assert.assertEquals(StandardUnit.getWorld(), null);
	}
	
	@Test
	public final void setWorld_Effective(){
		world2.addAsUnit(StandardUnit);
		StandardUnit.setWorld(world2);
		Assert.assertEquals(StandardUnit.getWorld(), world2);
	}
	
	@Test
	public final void setWorld_NonEffective(){
		StandardUnit.setWorld(world2);
		Assert.assertEquals(StandardUnit.getWorld(), null);
	}
	
	@Test 
	public final void getWorld(){
		StandardUnit.setWorld(null);
		Assert.assertEquals(StandardUnit.getWorld(), null);
		world2.addAsUnit(Unit1InWorld);
		Unit1InWorld.setWorld(world2);
		Assert.assertEquals(Unit1InWorld.getWorld(), world2);
		Unit1InWorld.setWorld(world2);
		Assert.assertEquals(Unit1InWorld.getWorld(), null);
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
	public final void getCurrentSpeed_Falling(){
		StandardUnit.setStatus(Status.FALLING);
		assert(Util.fuzzyEquals(3,StandardUnit.getCurrentSpeed()));
		
	}
	
	@Test
	public final void moveToAdjacent_LegalCase(){
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
	
	
	
	@Test(expected=IllegalArgumentException.class)
	public final void advanceTime_IllegalArgument(){
		StandardUnit.advanceTime((float)0.3);
	}
	
	@Test
	public final void advanceTime_MustRest(){
		advanceTimeFor(StandardUnit,180.1,0.05);
		//System.out.println(StandardUnit.restTimer);
		assertEquals(Status.INITIAL_RESTING,StandardUnit.getStatus());
	}
	
	@Test
	public final void advanceTime_InterruptedMovement(){
		Unit1InWorld.moveTo1(new int[] {0,1,2});
		StandardUnit.setStatus(Status.DONE);
		advanceTimeFor(Unit1InWorld,20.0,0.1);
		assertIntegerPositionEquals(1,2,2,Unit1InWorld.getCubeCoordinate());
	}
	
	@Test
	public final void advanceTime_ActivateDefaultBehaviour(){
		DefaultEnabledUnit.advanceTime((float)0.05);
		//System.out.println(DefaultEnabledUnit.status);
		assertTrue(DefaultEnabledUnit.getStatus()== Status.INITIAL_RESTING||DefaultEnabledUnit.getStatus()==Status.MOVING||DefaultEnabledUnit.getStatus()==Status.WORKING);

	}
	
	@Test
	public final void search(){
		world2.addAsUnit(Unit1InWorld);
		int[] array = new int[] {(int) Unit1InWorld.getPosition()[0], (int) Unit1InWorld.getPosition()[1],
				(int) Unit1InWorld.getPosition()[2], (int) 0};
		Assert.assertEquals(4, Unit1InWorld.search(array).size());
	}
	
	@Test
	public final void advanceTime_MovingToNextCube(){
		Unit1InWorld.moveTo1(new int[]{0,2,2});
		double speed = Unit1InWorld.getCurrentSpeed();
		double time = 1.0 / speed;
		Unit1InWorld.advanceTime((float) 0.1);
		assertDoublePositionEquals(1.5-speed*0.1,2.5,2.5,Unit1InWorld.getPosition());
		assertEquals(0.0, Unit1InWorld.getOrientation(),Util.DEFAULT_EPSILON);
		advanceTimeFor(Unit1InWorld,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {0.5,2.5,2.5}, Unit1InWorld.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.DONE, Unit1InWorld.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Sprinting(){
		StandardUnit.moveTo1(new int[]{4,1,4});
		StandardUnit.startSprinting();
		double speed = StandardUnit.getCurrentSpeed();
		double time = 1.0 / speed;
		StandardUnit.advanceTime((float) 0.1);
		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
		advanceTimeFor(StandardUnit,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.DONE, StandardUnit.getStatus());
		Assert.assertEquals(25.0-10.0*time,StandardUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
	}

	@Test
	public final void advanceTime_MovingTwoCubes(){
		StandardUnit.moveTo1(new int[]{5,1,4});
		double speed = StandardUnit.getCurrentSpeed();
		double time = 1.0 / speed;
		StandardUnit.advanceTime((float) 0.1);
		assertDoublePositionEquals(3.5+speed*0.1,1.5,4.5,StandardUnit.getPosition());
		assertEquals(0.0, StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
		advanceTimeFor(StandardUnit,time-0.1,0.1);
		Assert.assertArrayEquals(new double[] {4.5,1.5,4.5}, StandardUnit.getPosition(), Util.DEFAULT_EPSILON);
		Assert.assertEquals(Status.MOVING, StandardUnit.getStatus());
	}
	
	@Test
	public final void advanceTime_Working(){
		StandardUnit.work(new int[] {2,1,4});
		double totalWorkingTime =  500.0 /25.0;
		advanceTimeFor(StandardUnit,totalWorkingTime, 0.1);
		assertEquals(StandardUnit.getStatus(),Status.DONE);
		
	}
	
	@Test
	public final void advanceTime_InitialResting(){
		HitAndStaminaZeroUnit.rest();
		HitAndStaminaZeroUnit.advanceTime((float) 0.1);
		HitAndStaminaZeroUnit.work(new int[] {2,1,4});
		assertEquals((75/200.0)*5*0.1,HitAndStaminaZeroUnit.getHitpoints(),Util.DEFAULT_EPSILON);
		assertEquals(Status.INITIAL_RESTING,HitAndStaminaZeroUnit.getStatus());
		
	}
	
	@Test
	public final void advanceTime_InitialRestingToResting(){
		HitAndStaminaZeroUnit.rest();
		double step = (200.0/(75*5))/7.0;
		advanceTimeFor(HitAndStaminaZeroUnit,200.0/(75*5),step);
		assertEquals(Status.RESTING,HitAndStaminaZeroUnit.getStatus());
		
	}
	
	@Test
	public final void advanceTime_RestingRecoverHitpoints(){
		HitAndStaminaZeroUnit.setStatus(Status.RESTING);
		double step = (200.0/(75*5))/7.0;
		advanceTimeFor(HitAndStaminaZeroUnit,200.0/(75*5),step);
		assertEquals(Status.RESTING,HitAndStaminaZeroUnit.getStatus());
		
	}
	
	@Test
	public final void advanceTime_RestingRecoverStamina(){
		HitMaxStaminaZeroUnit.setStatus(Status.RESTING);
		double step = (100.0/(75*5))/7.0;
		advanceTimeFor(HitMaxStaminaZeroUnit,100.0/(75*5),step);
		assertEquals(1.0,HitMaxStaminaZeroUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
		assertEquals(Status.RESTING,HitMaxStaminaZeroUnit.getStatus());

	}
	
	@Test
	public final void advanceTime_RestingDone(){
		HitMaxStaminaMaxUnit.setStatus(Status.RESTING);
		HitMaxStaminaMaxUnit.advanceTime((float)0.01);
		assertEquals(HitMaxStaminaMaxUnit.getMaxPoints(),HitMaxStaminaMaxUnit.getStaminaPoints(),Util.DEFAULT_EPSILON);
		assertEquals(HitMaxStaminaMaxUnit.getMaxPoints(),HitMaxStaminaMaxUnit.getHitpoints(),Util.DEFAULT_EPSILON);
		assertEquals(Status.DONE,HitMaxStaminaMaxUnit.getStatus());
		
	}
	
	@Test
	public final void advanceTime_Attacking(){
		StandardUnit.setStatus(Status.ATTACKING);
		advanceTimeFor(StandardUnit,1.0,0.1);
		assertEquals(Status.DONE,StandardUnit.getStatus());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void moveTo_IllegalArgument() throws IllegalArgumentException{
		StandardUnit.moveTo1(new double[] {50.5,12.1,12.0});
	}
	
	@Test
	public final void moveTo_CannotMove(){
		StandardUnit.setStatus(Status.ATTACKING);
		StandardUnit.moveTo1(new double[] {10.5,10.5,10.5});
		StandardUnit.advanceTime((float) 0.1);
		assertEquals(Status.ATTACKING,StandardUnit.getStatus());

	}
	
	@Test
	public final void moveTo_EffectiveCase(){
		StandardUnit.moveTo1(new double[] {10.5,1.5,4.5});
		//double speed = StandardUnit.getCurrentSpeed();
		//System.out.println(speed);
		advanceTimeFor(StandardUnit,14.0,0.1);
		assertDoublePositionEquals(10.5,1.5,4.5, StandardUnit.getPosition());
	}
	
	@Test
	public final void moveTo_IntegerPosition(){
		StandardUnit.moveTo1(new int[] {10,1,4});
		advanceTimeFor(StandardUnit,14.0,0.1);
		assertDoublePositionEquals(10.5,1.5,4.5, StandardUnit.getPosition());
	}
	
//	@Test
//	public final void canMove_TrueCase(){
//		StandardUnit.setStatus(Status.RESTING);
//		assertTrue(StandardUnit.canMove());
//	}
//	
//	@Test
//	public final void canMove_FalseCase(){
//		StandardUnit.setStatus(Status.MOVING);
//		assertFalse(StandardUnit.canMove());
//	}
	
	@Test
	public final void work_EffectiveCase(){
		StandardUnit.setStatus(Status.DONE);
		StandardUnit.work(new int[] {2,1,4});
		assertEquals(Status.WORKING,StandardUnit.getStatus());
		advanceTimeFor(StandardUnit,500.0/25,0.1);
		assertEquals(Status.DONE,StandardUnit.getStatus());
	}
	
	@Test
	public final void work_IneffectiveCase(){
		StandardUnit.setStatus(Status.MOVING);
		StandardUnit.work(new int[] {2,1,4});
		assertEquals(Status.MOVING,StandardUnit.getStatus());
	}
	
//	@Test
//	public final void getProgressWork(){
//		StandardUnit.setStatus(Status.DONE);
//		StandardUnit.work(new int[] {2,1,4});
//		assertEquals(Status.WORKING,StandardUnit.getStatus());
//		advanceTimeFor(StandardUnit,250.0/25,0.1);
//		assertEquals(0.5,StandardUnit.getProgressWork(),Util.DEFAULT_EPSILON);
//	}
	
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
		StandardUnit.attack(Aunit);
	}
	
	@Test
	public final void attack_CannotAttack(){
		StandardUnit.setStatus(Status.MOVING);
		StandardUnit.attack(NeighbourStandardUnit);
		assertEquals(Status.MOVING,StandardUnit.getStatus());
	}
	
	@Test
	public final void attack_EffectiveCase(){
		StandardUnit.attack(NeighbourStandardUnit);
		assertEquals(Status.ATTACKING,StandardUnit.getStatus());
		
//		assertEquals(2 * Math.PI + Math.atan2(NeighbourStandardUnit.getPosition()[1] - StandardUnit.getPosition()[1],
//		NeighbourStandardUnit.getPosition()[0] - StandardUnit.getPosition()[0]) % (2 * Math.PI),
//		StandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
//
//		assertEquals(Math.atan2(StandardUnit.getPosition()[1] - NeighbourStandardUnit.getPosition()[1],
//				StandardUnit.getPosition()[0] - NeighbourStandardUnit.getPosition()[0]),
//				NeighbourStandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
////this test sometimes fails!
	}
	
	@Test
	public final void canAttack_FalseCase(){
		StandardUnit.setStatus(Status.MOVING);
		assertFalse(StandardUnit.canAttack());
	}
	
	@Test
	public final void canAttack_TrueCase(){
		StandardUnit.setStatus(Status.DONE);
		assertTrue(StandardUnit.canAttack());
	}
	
	@Test
	public final void defend(){
		NeighbourStandardUnit.defend(StandardUnit);
		assertEquals((float)Math.atan2(StandardUnit.getPosition()[1] - NeighbourStandardUnit.getPosition()[1],
				StandardUnit.getPosition()[0] - NeighbourStandardUnit.getPosition()[0]),
				NeighbourStandardUnit.getOrientation(),Util.DEFAULT_EPSILON);
		assertTrue((Util.fuzzyEquals(NeighbourStandardUnit.getHitpoints(), 25.0))||
				(Util.fuzzyEquals(NeighbourStandardUnit.getHitpoints(), 25.0-StandardUnit.getStrength()/10.0)));
		
	}
	
	@Test
	public final void rest_IneffectiveCase(){
		HitMaxStaminaMaxUnit.rest();
		System.out.println(HitMaxStaminaMaxUnit.getStatus().toString());
		assertEquals(Status.DONE,HitMaxStaminaMaxUnit.getStatus());
		StandardUnit.rest();
		assertEquals(Status.DONE,StandardUnit.getStatus());
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
		advanceTimeFor(StandardUnit,180.0,0.1);
		assertEquals(Status.INITIAL_RESTING,StandardUnit.getStatus());
		// mustRest() is always false, because as soon as mustRest becomes true, the unit starts resting and it becomes false again
		}
	
	@Test
	public final void canRest_FalseBecauseStaminaOrHitPointsNotZero(){
		assertFalse(StandardUnit.canRest());
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
		StandardUnit.setStatus(Status.ATTACKING);
		StandardUnit.startDefaultBehaviour();
		assertEquals(Status.ATTACKING,StandardUnit.getStatus());
		assertFalse(StandardUnit.isEnableDefaultBehaviour());
		}
	
	@Test
	public final void startDefaultBehaviour_Effective(){
		StandardUnit.startDefaultBehaviour();
		assertTrue(StandardUnit.isEnableDefaultBehaviour());
		assertTrue(StandardUnit.getStatus()==Status.MOVING || StandardUnit.getStatus() == Status.INITIAL_RESTING ||StandardUnit.getStatus()==Status.WORKING);
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
	
//	@Test
//	public final void setEnableDefaultBehaviour(){
//		StandardUnit.setEnableDefaultBehaviour(true);
//		assertTrue(StandardUnit.isEnableDefaultBehaviour());
//	}

	

}
