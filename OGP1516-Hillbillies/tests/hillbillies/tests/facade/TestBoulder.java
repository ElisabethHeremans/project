package hillbillies.tests.facade;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.Util;

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
	private World world3;
	
	private  Unit Aunit;
	private  Unit Bunit;
	
	/**
	 * Set up an immutable test fixture
	 */
	@BeforeClass
	public static void setUpBeforeClass(){
		DoubleBoulder = new Boulder(new double[] {1.5,4.5,3.5});
		IntegerBoulder = new Boulder(new int[] {1,2,3});
		int[][][] types1 = new int[4][4][4];
		types1[1][1][0] = TYPE_ROCK;
		types1[1][1][1] = TYPE_TREE;
		types1[1][1][2] = TYPE_WORKSHOP;
		types1[1][0][1] = TYPE_ROCK;
		world1 = new World(types1, new DefaultTerrainChangeListener());
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
		int[][][] types2 = new int[2][2][2];
		types2[1][1][0] = TYPE_ROCK;
		types2[0][1][0] = TYPE_ROCK;
		types2[1][0][0] = TYPE_ROCK;
		types2[0][0][0] = TYPE_ROCK;
		types2[1][0][1] = TYPE_ROCK;
		world3 = new World(types2, new DefaultTerrainChangeListener());
		ABoulder = new Boulder(new double[] {0.5,0.5,1.5});
		BBoulder = new Boulder(new double[] {0.5,1.5,1.5});
		CBoulder = new Boulder(new double[] {1.5,1.5,2.5});
		DBoulder = new Boulder(new double[] {1.5,2.5,1.5});
		EBoulder = new Boulder(new double[] {2.5,2.5,2.5});
		Aunit = new Unit("Aunit",new double[] {1.5,2.5,2.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
		Bunit = new Unit("Bunit",new double[] {0.5,1.5,1.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
	}
	
	@Test
	public final void isValidBoulder_null(){
		Assert.assertTrue(Aunit.isValidBoulder(null));
	}
	
	@Test
	public final void isValidBoulder_terminateU(){
		world3.addAsUnit(Bunit);
		Bunit.terminate();
		Assert.assertTrue(Bunit.isValidBoulder(null));
	}
	
//	return (boulder == null) || (!boulder.isTerminated() && 
//				(boulder.getWorld().getCubePosition(boulder.getPosition()) == this.getWorld().getCubePosition(this.getPosition()))
//				||(this.isNeighbouringCube(boulder.getPosition())));

	
	@Test
	public final void isValidBoulder_terminateB(){
		world3.addAsBoulder(ABoulder);
		world3.addAsUnit(Bunit);
		ABoulder.terminate();
		Assert.assertFalse(ABoulder == null);
		Assert.assertTrue(ABoulder.isTerminated());
//		int[] BPosition = world3.getCubePosition(ABoulder.getPosition());
//		int[] UPosition = Bunit.getWorld().getCubePosition(Bunit.getPosition());
//		Assert.assertTrue(BPosition[0] == UPosition[0]);
//		Assert.assertTrue(BPosition[1] == UPosition[1]);
//		Assert.assertTrue(BPosition[2] == UPosition[2]);
		Assert.assertTrue(Bunit.isNeighbouringCube(ABoulder.getPosition()));
		Assert.assertFalse(Bunit.isValidBoulder(ABoulder));
	}

	
	@Test
	public final void isValidBoulder_LegalCase(){
		world3.addAsBoulder(ABoulder);
		world3.addAsUnit(Bunit);
		Assert.assertFalse(ABoulder == null);
		Assert.assertFalse(ABoulder.isTerminated());
//		Assert.assertArrayEquals(world3.getCubePosition(ABoulder.getPosition()), Bunit.getWorld().getCubePosition(Bunit.getPosition()));
//		int[] BPosition = world3.getCubePosition(ABoulder.getPosition());
//		int[] UPosition = Bunit.getWorld().getCubePosition(Bunit.getPosition());
//		Assert.assertTrue(BPosition[0] == UPosition[0]);
//		Assert.assertTrue(BPosition[1] == UPosition[1]);
//		Assert.assertTrue(BPosition[2] == UPosition[2]);
		Assert.assertTrue(Bunit.isNeighbouringCube(ABoulder.getPosition()));
		Assert.assertFalse(Bunit.isTerminated());
		Assert.assertTrue(Bunit.isValidBoulder(ABoulder));
	}
	
	@Test
	public final void isValidBoulder_LegalCase2(){
		world3.addAsUnit(Bunit);
		System.out.println(world3.isCubeInWorld(world3.getCubeCoordinate(BBoulder.getPosition())));
		System.out.println(Arrays.toString(world3.getCubeCoordinate(BBoulder.getPosition())));
		world3.addAsBoulder(BBoulder);
		Assert.assertFalse(BBoulder == null);
		Assert.assertFalse(BBoulder.isTerminated());
		Assert.assertArrayEquals(world3.getCubePosition(BBoulder.getPosition()), Bunit.getWorld().getCubePosition(Bunit.getPosition()));
		int[] BPosition = world3.getCubePosition(BBoulder.getPosition());
		int[] UPosition = Bunit.getWorld().getCubePosition(Bunit.getPosition());
		Assert.assertTrue(BPosition[0] == UPosition[0]);
		Assert.assertTrue(BPosition[1] == UPosition[1]);
		Assert.assertTrue(BPosition[2] == UPosition[2]);
		Assert.assertFalse(Bunit.isNeighbouringCube(BBoulder.getPosition()));
		Assert.assertFalse(Bunit.isTerminated());
		Assert.assertTrue(Bunit.isValidBoulder(BBoulder));
	}

}
