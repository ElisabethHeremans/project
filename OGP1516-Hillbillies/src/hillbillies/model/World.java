package hillbillies.model;

public class World {
	public World(int[] dimensions){
		
	}
	
	TerrainType getTerrain(int[] position){
		return TerrainType.AIR;
	}
	
	TerrainType getTerrain(double[] position){
		return TerrainType.AIR;
	}
	
	private int X;
	private int Y;
	private int Z;
	private int[] dimensions = new int[] {X,Y,Z};
	
	
}
