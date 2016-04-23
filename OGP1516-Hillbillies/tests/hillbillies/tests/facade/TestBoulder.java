package hillbillies.tests.facade;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;

public class TestBoulder {
	
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
	
	private  Unit Aunit;
	private  Unit Bunit;
	
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
		ABoulder = new Boulder(new double[] {1.5,1.5,1.5});
		BBoulder = new Boulder(new double[] {6.5,3.5,0.5});
		BBoulder = new Boulder(new double[] {6.5,3.5,0.5});
		CBoulder = new Boulder(new double[] {1.5,1.5,2.5});
		DBoulder = new Boulder(new double[] {1.5,2.5,1.5});
		EBoulder = new Boulder(new double[] {2.5,2.5,2.5});
		Aunit = new Unit("Aunit",new double[] {1.5,2.5,2.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
		Bunit = new Unit("Bunit",new double[] {1.5,2.5,2.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
	}
	
	@Test
	public final void isValidBoulder_terminate(){
		Aunit.setBoulder(null);
		Assert.assertTrue(Aunit.isValidBoulder(ABoulder));
	}

}
