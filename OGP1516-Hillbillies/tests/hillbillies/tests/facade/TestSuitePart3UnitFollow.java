package hillbillies.tests.facade;

import java.util.Arrays;

import org.junit.*;

import hillbillies.model.Status;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import ogp.framework.util.Util;

public class TestSuitePart3UnitFollow {
	
	private static final int TYPE_AIR = 0;
	private static final int TYPE_ROCK = 1;
	private static final int TYPE_TREE = 2;
	private static final int TYPE_WORKSHOP = 3;
	private static final int[][][] types = new int[10][20][30];
	
	private World world;
	
	private  Unit Aunit;
	private  Unit Bunit;
	private  Unit Cunit;
	
	/**
	 * Set up an mutable test fixture
	 */
	@Before
	public void setUpBefore(){
		int[][][] types1 = new int[4][4][4];
		types1[1][1][0] = TYPE_ROCK;
		types1[1][1][1] = TYPE_TREE;
		types1[1][1][2] = TYPE_WORKSHOP;
		types1[1][0][1] = TYPE_ROCK;
		world = new World(types1, new DefaultTerrainChangeListener());
		Aunit = new Unit("Aunit",new double[] {1.5,3.5,3.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
		Bunit = new Unit("Bunit",new double[] {2.5,0.5,1.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
		Cunit = new Unit("Cunit",new double[] {0.5,3.5,3.5},50,50,50,50,false,25.0,25.0,Math.PI/2);
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
		for (int i = 0; i < n; i++){
			unit.advanceTime((float)step);
		}
		unit.advanceTime((float) (time - n * step));
	}
	
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
		world.advanceTime((float) (time - n * step));
	}
	
	@Test
	public final void isNeighboringOrSameCube_X(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Cunit);
		Assert.assertTrue(Aunit.isNeighbouringOrSameCube(Cunit.getCubeCoordinate()));
	}
	
	@Test
	public final void isNeighboringOrSameCube_Z(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Cunit);
		Cunit.setPosition(new double[] {0.5,3.5,2.5});
		Assert.assertTrue(Aunit.isNeighbouringOrSameCube(Cunit.getCubeCoordinate()));
	}
	
	@Test
	public final void isNeighboringOrSameCube_Y(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Cunit);
		Cunit.setPosition(new double[] {0.5,2.5,3.5});
		Assert.assertTrue(Aunit.isNeighbouringOrSameCube(Cunit.getCubeCoordinate()));
	}
	
	@Test
	public final void isNeighboringOrSameCube_YZ(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Cunit);
		Cunit.setPosition(new double[] {0.5,2.5,2.5});
		Assert.assertTrue(Aunit.isNeighbouringOrSameCube(Cunit.getCubeCoordinate()));
	}
	
	@Test
	public final void isNeighboringOrSameCube_Not(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Bunit);
		Assert.assertFalse(Aunit.isNeighbouringOrSameCube(Bunit.getCubeCoordinate()));
	}
	
	@Test
	public final void isNeighboringOrSameCube_Same(){
		world.addAsUnit(Aunit);
		Assert.assertTrue(Aunit.isNeighbouringOrSameCube(Aunit.getCubeCoordinate()));
	}
	
	
	@Test
	public final void Follow_NotMoving(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Bunit);
		Bunit.startFollowing(Aunit);
		advanceTimeFor(Bunit,4.0,0.1);
		System.out.println(Arrays.toString(Bunit.getCubeCoordinate()));
		System.out.println(Arrays.toString(Aunit.getCubeCoordinate()));
		System.out.println(Aunit.getStatus());
		Assert.assertTrue(Bunit.isNeighbouringOrSameCube(Aunit.getCubeCoordinate()));
	}
	
	@Test
	public final void Follow_Moving(){
		world.addAsUnit(Aunit);
		world.addAsUnit(Bunit);
		Bunit.startFollowing(Aunit);
		Aunit.moveTo1(new double[] {0.5,1.5,3.5});
		advanceTimeFor(world,4.0,0.1);
		System.out.println(Arrays.toString(Bunit.getCubeCoordinate()));
		System.out.println(Arrays.toString(Aunit.getCubeCoordinate()));
		
		Assert.assertTrue(Bunit.isNeighbouringOrSameCube(Aunit.getCubeCoordinate()));
	}

}
