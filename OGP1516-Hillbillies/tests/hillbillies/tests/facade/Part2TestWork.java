package hillbillies.tests.facade;

import org.junit.Before;
import org.junit.Test;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.DefaultTerrainChangeListener;
import junit.framework.Assert;


	
	public class Part2TestWork {
		
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
	public final void work_CarryLog(){
		world2.addAsLog(Log);
		world2.addAsUnit(BUnit);
		BUnit.work(world2.getCubeCoordinate(Log.getPosition()));
		Assert.assertTrue(BUnit.isValidLog(Log));
		advanceTimeFor(BUnit, 30, 0.1);
		Assert.assertFalse(world2.hasAsLog(Log));
		Assert.assertEquals(Log, BUnit.getLog());
		
	}
	
	/**
	 * Helper method to advance time by some time for the given unit. (we based this on the helper method in Part1TestPartial)
	 * 
	 * @param time
	 *            The time, in seconds, to advance.
	 * @param step
	 *            The step size, in seconds, by which to advance.
	 */
	private void advanceTimeForWorld(World world, double time, double step) {
		int n = (int) (time / step);
		for (int i = 0; i < n; i++)
			world.advanceTime((float)step);
		world.advanceTime((float) (time - n * step));
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
			System.out.println(unit.getStatus());
		}
		unit.advanceTime((float) (time - n * step));
	}
	
	}

