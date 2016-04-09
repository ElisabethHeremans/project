package hillbillies.tests.facade;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Faction;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class TestSuitePart2Faction {
	
	private static final int[][][] types = new int[10][20][30];
	
	private static Faction AFaction;
	
	private Faction BFaction;
	
	private World world1;
	
	private Unit AUnit;
	private Unit BUnit;
	private Unit CUnit;
	private Unit DUnit;
	
	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		AFaction = new Faction();
	}
	
	/**
	 * Set up an mutable test fixture
	 */
	@Before
	public void setUpBefore(){
		BFaction = new Faction();
		world1 = new World(types, new DefaultTerrainChangeListener());
		AUnit = new Unit("Bunit",new double[] {2.5,1.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		BUnit = new Unit("Bunit",new double[] {1.5,1.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		CUnit = new Unit("Bunit",new double[] {3.5,1.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		DUnit = new Unit("Bunit",new double[] {1.5,2.5,0.5},75,25,25,75,false,25.0,25.0,Math.PI/2);
		
	}
	
	@Test
	public final void getUnits(){
		Set<Unit> test = new HashSet<Unit>();
		test.add(AUnit);
		AFaction.addAsUnit(AUnit);
		Assert.assertEquals(AFaction.getUnits(),test);
	}
	
	@Test
	public final void terminate(){
		Set<Unit> test = new HashSet<Unit>();
		test.add(AUnit);
		AFaction.addAsUnit(AUnit);
		Set<Unit> empty = new HashSet<Unit>();
		AFaction.terminate();
		Assert.assertTrue(AFaction.isTerminated());
		Assert.assertEquals(AUnit.getFaction(), null);
		Assert.assertEquals(AFaction.getUnits(), empty);
	}
}
