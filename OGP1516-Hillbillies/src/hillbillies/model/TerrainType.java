package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

@Value
public enum TerrainType {
	AIR(0,true),
	ROCK(1,false),
	TREE(2,false),
	WORKSHOP(3,true);
	
	@Raw
	private TerrainType(int type, boolean passable){
		this.type = type;
		this.passable = passable;
	}
	
	/**
	 * @return the type
	 */
	@Basic @Raw @Immutable
	public final int getType() {
		return this.type;
	}
	
	public static final TerrainType getTerrain(int type){
		for (TerrainType terrain: TerrainType.values()){
			//System.out.print("gegeven TYPE" +terrain.getType());
			//System.out.println("TYPE" +type);
			if (terrain.getType() == type){
				//System.out.println("TERRAIN-----" +terrain);
				return terrain;
			}
		}
		return AIR;
	}

	/**
	 * @return the passable
	 */
	@Basic @Raw @Immutable
	public final boolean isPassable() {
		return passable;
	}

	private  int type;
	
	private  boolean passable;
	
	
	

}
