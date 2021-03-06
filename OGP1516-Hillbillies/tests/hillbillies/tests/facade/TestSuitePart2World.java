package hillbillies.tests.facade;

import static hillbillies.tests.util.PositionAsserts.assertDoublePositionEquals;
import static hillbillies.tests.util.PositionAsserts.assertIntegerPositionEquals;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.*;

import hillbillies.model.*;
//import hillbillies.part2.facade.IFacade;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
//import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.*;


public class TestSuitePart2World {
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[][][] types = new int[10][20][30];
	
	private static World world1;
	private Unit StandardUnit;
	private Unit InvalidPosUnit;
	private Unit OutsideUnit;
	private Unit StandardUnit2;
	private Faction faction = new Faction();
	private Boulder boulder = new Boulder(new int[] {0,0,0});
	private Boulder invalidPosBoulder;
	private Boulder OutsideBoulder;
	private Log log;
	private Log invalidPosLog;
	private Log OutsideLog;
	private Unit StandardUnit3;

	
	private World world2;
	
	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		world1 = new World(types, new DefaultTerrainChangeListener());
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
		StandardUnit = new Unit("Bunit",new double[] {2.5,1.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		InvalidPosUnit = new Unit("Bunit",new double[] {1.5,1.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		OutsideUnit = new Unit("Bunit",new double[] {3.5,1.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		StandardUnit2 = new Unit("Bunit",new double[] {1.5,2.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		StandardUnit3 = new Unit("Bunit",new double[] {1.5,0.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		world2 = new World(types, new DefaultTerrainChangeListener());
		OutsideBoulder = new Boulder(new int[] {-1,0,0});
		invalidPosBoulder = new Boulder(new int[] {1,1,1});
		log = new Log(new int[] {0,0,0});
		OutsideLog = new Log(new int[] {-1,0,0});
		invalidPosLog = new Log(new int[] {1,1,1});

	}
	
	@Test
	public final void Constructor_LegalCase(){
		int[][][] terrainTypes = new int[10][20][30];
		terrainTypes[1][1][0] = TYPE_ROCK;

		World newWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		Assert.assertEquals(10, newWorld.getxDimension());
		Assert.assertEquals(20, newWorld.getyDimension());
		Assert.assertEquals(30, newWorld.getzDimension());
		Assert.assertEquals(TerrainType.ROCK, newWorld.getTerrain(new int[] {1,1,0}));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void Constructor_IllegalTerrainTypes(){
		int[][][] terrainTypes = new int[20][20][20];
		terrainTypes[1][1][2] = 4;
		World newWorld = new World(terrainTypes, new DefaultTerrainChangeListener());
		
	}
	
	@Test
	public final void getTerrain_LegalCase(){
		Assert.assertEquals(TerrainType.TREE,world2.getTerrain(new int[] {1,1,1}));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void getTerrain_IllegalArgument(){
		world2.getTerrain(new int[] {3,1,1});
	}
	
	@Test
	public final void getTerrain_Double(){
		Assert.assertEquals(TerrainType.TREE,world2.getTerrain(new double[] {1.3,1.8,1.7}));
	}
	
	@Test
	public final void setTerrain_LegalCase(){
		world2.setTerrain(new int[] {1,1,1}, TerrainType.ROCK);
		Assert.assertEquals(TerrainType.ROCK,world2.getTerrain(new int[] {1,1,1}));
	}
	
	@Test
	public final void getTerrainTypes(){
		Assert.assertTrue(types==world1.getTerrainTypes());
	}
	
	@Test
	public final void setTerrainTypes_LegalCase(){
		int[][][] terrainTypes = new int[3][3][3];
		terrainTypes[1][1][2] = 1;
		world2.setTerrainTypes(terrainTypes);
		Assert.assertEquals(TerrainType.ROCK,world2.getTerrain(new int[] {1,1,2}));
		Assert.assertEquals(TerrainType.AIR,world2.getTerrain(new int[] {2,2,2}));

	}
	
	@Test
	public final void isCubeInWorld_TrueCase(){
		Assert.assertTrue(world1.isCubeInWorld(new int[] {9,0,24}));
	}
	
	@Test
	public final void isCubeInWorld_FalseCase(){
		Assert.assertFalse(world1.isCubeInWorld(new int[] {-1,12,24}));
		Assert.assertFalse(world1.isCubeInWorld(new int[] {10,19,24}));

	}
	
	@Test
	public final void spawnUnit(){
		Unit unit = world2.spawnUnit(false);
		Assert.assertTrue(Unit.isValidAgility(unit.getAgility()));
		Assert.assertTrue(Unit.isValidToughness(unit.getToughness()));
		Assert.assertTrue(Unit.isValidStrength(unit.getStrength()));
		Assert.assertTrue(unit.canHaveAsPosition(unit.getPosition()));
		Assert.assertTrue(unit.canHaveAsHitpoints(unit.getHitpoints()));
		Assert.assertTrue(unit.canHaveAsStaminaPoints(unit.getStaminaPoints()));
		Assert.assertTrue(unit.canHaveAsWeight(unit.getWeight()));
		Assert.assertTrue(Unit.isValidName(unit.getName()));
		Assert.assertTrue(unit.getAgility()<=100);
		Assert.assertFalse(unit.mustFall());
		Assert.assertTrue(unit.getWorld()==world2);
		Assert.assertTrue(world2.hasAsUnit(unit));
		Assert.assertTrue(world2.getUnits(unit.getCubeCoordinate()).contains(unit));
		Assert.assertFalse(unit.isEnableDefaultBehaviour());
		
	}
	
	@Test
	public final void getNumberUnits(){
		Unit unit = world2.spawnUnit(false);
		Assert.assertTrue(world2.getNumberUnits()==1);
		Assert.assertTrue(world1.getNumberUnits()==0);
	}
	
	@Test
	public final void hasAsUnit_LegalCase(){
		Unit unit = world2.spawnUnit(false);
		Assert.assertFalse(world1.hasAsUnit(unit));
		Assert.assertTrue(world2.hasAsUnit(unit));
	}
		
	@Test(expected = IllegalArgumentException.class)
	public final void hasAsUnit_IllegalCase(){
		world2.hasAsUnit(null);
	}
	
	@Test
	public final void canHaveAsUnit_TrueCases(){
		Assert.assertTrue(world2.canHaveAsUnit(StandardUnit));
		world2.terminate();
		StandardUnit.terminate();
		Assert.assertTrue(world2.canHaveAsUnit(StandardUnit));

	}
	
	@Test
	public final void canHaveAsUnit_FalseCases(){
		Assert.assertFalse(world1.canHaveAsUnit(null));
		world2.terminate();
		Assert.assertFalse(world2.canHaveAsUnit(StandardUnit));
	}
	
	@Test
	public final void hasProperUnits_TrueCase(){
		world2.addAsUnit(StandardUnit);
		Assert.assertTrue(world2.hasProperUnits());

	}
	
	@Test
	public final void hasProperUnits_FalseCase(){
		world2.addAsUnit(StandardUnit);
		StandardUnit.setWorld(null);
		Assert.assertFalse(world2.hasProperUnits());
	}
	
	@Test
	public final void hasProperUnits_FalseCase2(){
		world2.addAsUnit(StandardUnit);
		world2.terminate();
		Assert.assertFalse(world2.hasProperUnits());
	}

	
	@Test
	public final void addAsUnit_ValidCase(){
		world2.addAsUnit(StandardUnit);
		Assert.assertTrue(world2.hasAsUnit(StandardUnit));
		Assert.assertTrue(world2.getUnits(StandardUnit.getCubeCoordinate()).contains(StandardUnit));
		Assert.assertEquals(world2,StandardUnit.getWorld());
		Assert.assertTrue(StandardUnit.getFaction()!=null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsUnit_InvalidCube(){
		world2.addAsUnit(InvalidPosUnit);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsUnit_OutsideWorld(){
		world2.addAsUnit(OutsideUnit);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsUnit_HasWorld(){
		world2.addAsUnit(StandardUnit);
		world1.addAsUnit(StandardUnit);
	}
	
	@Test
	public final void listAllUnits(){
		Assert.assertTrue(world2.listAllUnits().size()==0);
		world2.addAsUnit(StandardUnit);
		Assert.assertTrue(world2.listAllUnits().size()==1);
		Assert.assertTrue(world2.listAllUnits().contains(StandardUnit));

	}
	
	@Test
	public final void listAllUnitsOfFaction_ValidCase(){
		world2.addAsUnit(StandardUnit);
		Faction faction = StandardUnit.getFaction();
		Assert.assertTrue(world2.listAllUnitsOfFaction(faction).size()==1);
		Assert.assertTrue(world2.listAllUnitsOfFaction(faction).contains(StandardUnit));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void listAllUnitsOfFaction_InvalidCase(){
		Faction faction = new Faction();
		world2.listAllUnitsOfFaction(faction);
	}
	
	@Test
	public final void getNbFactions(){
		Assert.assertEquals(0, world2.getNbFactions());
		world2.addAsUnit(StandardUnit);
		Assert.assertEquals(1, world2.getNbFactions());
		world2.addAsUnit(StandardUnit2);
		StandardUnit2.terminate();
		Assert.assertEquals(2, world2.getNbFactions());

	}
	
	@Test
	public final void getNbActiveFactions(){
		world2.addAsUnit(StandardUnit);
		world2.addAsUnit(StandardUnit2);
		StandardUnit2.terminate();
		Assert.assertEquals(1, world2.getNbActiveFactions());	
		}
	
	@Test
	public final void getActiveFactions(){
		world2.addAsUnit(StandardUnit);
		world2.addAsUnit(StandardUnit2);
		Faction faction = StandardUnit2.getFaction();
		StandardUnit2.terminate();
		Assert.assertTrue(world2.getActiveFactions().contains(StandardUnit.getFaction()));
		Assert.assertFalse(world2.getActiveFactions().contains(faction));
	}
	
	@Test
	public final void hasAsFaction_TrueCase(){
		world2.addAsUnit(StandardUnit);
		Assert.assertTrue(world2.hasAsFaction(StandardUnit.getFaction()));
	}
	
	@Test
	public final void hasAsFaction_FalseCase(){
		world2.addAsUnit(StandardUnit);
		Assert.assertFalse(world1.hasAsFaction(StandardUnit.getFaction()));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void hasAsFaction_InvalidCase(){
		world1.hasAsFaction(null);
	}
	
	@Test
	public final void canHaveAsFaction_TrueCases(){
		Assert.assertTrue(world1.canHaveAsFaction(faction));
		faction.terminate();
		world2.terminate();
		Assert.assertTrue(world2.canHaveAsFaction(faction));

	}
	
	@Test
	public final void canHaveAsFaction_FalseCases(){
		Assert.assertFalse(world2.canHaveAsFaction(null));
		world2.terminate();
		Assert.assertFalse(world2.canHaveAsFaction(faction));
	}

	@Test
	public final void hasProperFactions_TrueCase(){
		world2.addAsUnit(StandardUnit);
		Assert.assertTrue(world2.hasProperFactions());
	}
	
	@Test
	public final void hasProperFactions_FalseCase(){
		world2.addAsUnit(StandardUnit);
		world2.terminate();
		Assert.assertFalse(world2.hasProperFactions());

	}
	
	@Test
	public final void getNbBoulders(){
		world2.addAsUnit(StandardUnit2);
		StandardUnit2.work(new int[] {1,1,0});
		advanceTimeFor(world2, 100.0, 0.02);
		Assert.assertTrue(world2.getNumberBoulders()==1);
	}
	
	@Test
	public final void canHaveAsBoulder_TrueCase(){
		Assert.assertTrue(world2.canHaveAsBoulder(boulder));
		boulder.terminate();
		world2.terminate();
		Assert.assertTrue(world2.canHaveAsBoulder(boulder));
	}
	
	@Test
	public final void canHaveAsBoulder_FalseCase(){
		Assert.assertFalse(world2.canHaveAsBoulder(null));
		world2.terminate();
		Assert.assertFalse(world2.canHaveAsBoulder(boulder));
	}
	
	@Test
	public final void hasProperBoulders_True(){
		world2.addAsUnit(StandardUnit2);
		StandardUnit2.work(new int[] {1,1,0});
		advanceTimeFor(world2, 100.0, 0.02);
		Assert.assertTrue(world2.hasProperBoulders());
	}
	
	@Test
	public final void hasProperBoulders_False(){
		world2.addAsUnit(StandardUnit2);
		StandardUnit2.work(new int[] {1,1,0});
		advanceTimeFor(world2, 100.0, 0.02);
		world2.terminate();
		Assert.assertFalse(world2.hasProperBoulders());
	}
	

	@Test
	public final void listAllBoulders(){
		world2.addAsUnit(StandardUnit2);
		StandardUnit2.work(new int[] {1,1,0});
		advanceTimeFor(world2, 100.0, 0.02);
		Assert.assertTrue(world2.listAllBoulders().size()==1);
	}
	
	@Test
	public final void addAsBoulder_ValidCase(){
		world2.addAsBoulder(boulder);
		Assert.assertTrue(world2.hasAsBoulder(boulder));
		Assert.assertTrue(world2.getBoulders(boulder.getWorld().getCubeCoordinate(boulder.getPosition())).contains(boulder));
		Assert.assertEquals(world2,boulder.getWorld());

	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsBoulder_InvalidCube(){
		world2.addAsBoulder(invalidPosBoulder);
	}
		
	@Test(expected=IllegalArgumentException.class)
	public final void addAsBoulder_OutsideWorld(){
		world2.addAsBoulder(OutsideBoulder);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsBoulder_HasWorld(){
		world2.addAsBoulder(boulder);
		world1.addAsBoulder(boulder);
	}


	@Test
	public final void getNbLogs(){
		world2.addAsLog(log);
		Assert.assertTrue(world2.getNumberLogs()==1);
	}
	
	@Test
	public final void canHaveAsLog_TrueCase(){
		Assert.assertTrue(world2.canHaveAsLog(log));
		log.terminate();
		world2.terminate();
		Assert.assertTrue(world2.canHaveAsLog(log));
	}
	
	@Test
	public final void canHaveAsLog_FalseCase(){
		Assert.assertFalse(world2.canHaveAsLog(null));
		world2.terminate();
		Assert.assertFalse(world2.canHaveAsLog(log));
	}
	
	@Test
	public final void hasProperLogs_True(){
		world2.addAsLog(log);
		Assert.assertTrue(world2.hasProperLogs());
	}
	
	@Test
	public final void hasProperLogs_False(){
		world2.addAsLog(log);
		world2.terminate();
		Assert.assertFalse(world2.hasProperLogs());
	}
	

	@Test
	public final void listAllLogs(){
		world2.addAsLog(log);
		Assert.assertTrue(world2.listAllLogs().contains(log));
		Assert.assertTrue(world2.listAllLogs().size()==1);

	}
	
	@Test
	public final void addAsLog_ValidCase(){
		world2.addAsLog(log);
		Assert.assertTrue(world2.hasAsLog(log));
		Assert.assertTrue(world2.getLogs(log.getWorld().getCubeCoordinate(log.getPosition())).contains(log));
		Assert.assertEquals(world2,log.getWorld());

	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsLog_InvalidCube(){
		world2.addAsLog(invalidPosLog);
	}
		
	@Test(expected=IllegalArgumentException.class)
	public final void addAsLog_OutsideWorld(){
		world2.addAsLog(OutsideLog);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void addAsLog_HasWorld(){
		world2.addAsLog(log);
		world1.addAsLog(log);
	}

	@Test
	public final void getUnits_LegalCase(){
		world2.addAsUnit(StandardUnit);
		Assert.assertTrue(world2.getUnits(new int[] {1,1,1}).size()==0);
		Assert.assertTrue(world2.getUnits(new int[] {2,1,0}).size()==1);
		Assert.assertTrue(world2.getUnits(new int[] {2,1,0}).contains(StandardUnit));

	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void getUnits_IllegalCase(){
		world2.getUnits(new int[] {-1,0,0});
	}
	
	@Test
	public final void getLogs_LegalCase(){
		world2.addAsLog(log);
		Assert.assertTrue(world2.getLogs(new int[] {0,0,1}).size()==0);
		Assert.assertTrue(world2.getLogs(new int[] {0,0,0}).size()==1);
		Assert.assertTrue(world2.getLogs(new int[] {0,0,0}).contains(log));

	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void getLogs_IllegalCase(){
		world2.getLogs(new int[] {-1,0,0});
	}
	
	@Test
	public final void getBoulders_LegalCase(){
		world2.addAsBoulder(boulder);
		Assert.assertTrue(world2.getBoulders(new int[] {1,1,1}).size()==0);
		Assert.assertTrue(world2.getBoulders(new int[] {0,0,0}).size()==1);
		Assert.assertTrue(world2.getBoulders(new int[] {0,0,0}).contains(boulder));

	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void getBoulders_IllegalCase(){
		world2.getBoulders(new int[] {-1,0,0});
	}

	@Test
	public final void getXDimension(){
		Assert.assertEquals(10,world1.getxDimension());
	}
	
	@Test
	public final void getYDimension(){
		Assert.assertEquals(20,world1.getyDimension());
	}
	
	@Test
	public final void getZDimension(){
		Assert.assertEquals(30,world1.getzDimension());
	}


	@Test
	public final void isSolidConnectedToBorder(){
		world2.addAsUnit(StandardUnit);
		assertTrue(world2.isSolidConnectedToBorder(new int[] {1, 1, 0}));
		assertTrue(world2.isSolidConnectedToBorder(new int[] { 1, 1, 1}));
		StandardUnit.work(new int[] { 1, 1, 0});
		advanceTimeFor(world2,200,0.1);
		assertFalse(world2.isSolidConnectedToBorder(new int[] {1, 1, 0}));
		assertFalse(world2.isSolidConnectedToBorder(new int[] { 1, 1, 1}));
	}
	
	@Test
	public final void advanceTime_Legal(){
		world2.addAsBoulder(boulder);
		world2.addAsLog(log);
		world2.addAsUnit(StandardUnit);
		world2.addAsUnit(StandardUnit3);
		assertTrue(world2.isSolidConnectedToBorder(new int[] {1, 1, 0}));
		assertTrue(world2.isSolidConnectedToBorder(new int[] { 1, 1, 1}));
		assertTrue(world2.hasAsBoulder(boulder));
		StandardUnit.work(new int[] { 1, 1, 0});
		StandardUnit3.work(new int[] {0,0, 0});
		advanceTimeFor(world2,200,0.1);
		assertFalse(world2.isSolidConnectedToBorder(new int[] {1, 1, 0}));
		assertFalse(world2.isSolidConnectedToBorder(new int[] { 1, 1, 1}));
		assertTrue(StandardUnit3.getNbBoulders()==1);
		assertFalse(world2.hasAsBoulder(boulder));

	}
	
	@Test
	public final void terminate(){
		world2.terminate();
		assertTrue(world2.isTerminated());

	}
	
	@Test
	public final void getNeighboringCubes_all(){
		int[] position = new int[] {1,1,1};
//		world2.getNeighboringCubes(position);
//		List<int[]> neighboringCubes = new ArrayList<int[]>();
//		neighboringCubes.add(new int[] {0,0,0});
//		neighboringCubes.add(new int[] {0,1,0});
//		neighboringCubes.add(new int[] {0,2,0});
//		neighboringCubes.add(new int[] {1,0,0});
//		neighboringCubes.add(new int[] {1,1,0});
//		neighboringCubes.add(new int[] {1,2,0});
//		neighboringCubes.add(new int[] {2,0,0});
//		neighboringCubes.add(new int[] {2,1,0});
//		neighboringCubes.add(new int[] {2,2,0});
//		neighboringCubes.add(new int[] {2,0,1});
//		neighboringCubes.add(new int[] {2,1,1});
//		neighboringCubes.add(new int[] {2,2,1});
//		neighboringCubes.add(new int[] {1,0,1});
//		neighboringCubes.add(new int[] {1,2,1});
//		neighboringCubes.add(new int[] {0,0,1});
//		neighboringCubes.add(new int[] {0,1,1});
//		neighboringCubes.add(new int[] {0,2,1});
//		neighboringCubes.add(new int[] {0,0,2});
//		neighboringCubes.add(new int[] {0,1,2});
//		neighboringCubes.add(new int[] {0,2,2});
//		neighboringCubes.add(new int[] {1,0,2});
//		neighboringCubes.add(new int[] {1,1,2});
//		neighboringCubes.add(new int[] {1,2,2});
//		neighboringCubes.add(new int[] {2,0,2});
//		neighboringCubes.add(new int[] {2,1,2});
//		neighboringCubes.add(new int[] {2,2,2});
		Assert.assertEquals(world2.getNeighboringCubes(position).size(), 26);
	}
	
	@Test 
	public final void getNeighboringCubes_Bourder(){
		int[] position = new int[] {2,2,0};
		Assert.assertEquals(world2.getNeighboringCubes(position).size(), 7);
	}
	
//	@Test
//	public final void spawnUnit2(){
//		Map<Position,Set<Unit>> unitsAtCubeMap = new HashMap<Position, Set<Unit>>();
//		int[] coord = new int[]{1,1,1};
//		Set<Unit> units = new HashSet<>();
//		Position p = new Position(coord);
//		unitsAtCubeMap.put(p, units);
//		if(unitsAtCubeMap.containsKey(p)) {
//			System.out.println(coord);
//		}
//	}
	
	/**
	 * Helper method to advance time for the given world by some time.
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private static void advanceTimeFor(World world, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			world.advanceTime(step);
		//advanceTimeFor(world, time - n * step,step);
	}
	
	
	
	

}
