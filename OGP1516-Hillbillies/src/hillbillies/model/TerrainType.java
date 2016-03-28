package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

public enum TerrainType {
	AIR(0,true),
	WORKSHOP(3,true),
	ROCK(1,false),
	TREE(2,false);
	
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
		return type;
	}
	
	public static final TerrainType getTerrain(int type){
		for (TerrainType terrain: TerrainType.values()){
			if (terrain.getType() == type)
				return terrain;
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

	private final int type;
	
	private final boolean passable;
	
	
	

}