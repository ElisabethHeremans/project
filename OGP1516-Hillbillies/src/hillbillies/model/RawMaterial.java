package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class RawMaterial {

	public RawMaterial(double[] position) {
		this.setPosition(position);

	}
	
	public RawMaterial(int[] position){
		this(new double[] { position[0], position[1],position[2]});
	}
	public abstract double[] getPosition();
	
	public abstract int[] getCubeCoordinate();

	
	public abstract void setPosition(double[] position);
	
	/**
	 * Check whether the given position is a valid position for any raw material.
	 * 
	 * @param position
	 *            The position to check.
	 * @return True if and only if the terrain type of this cube is passable and
	 *         the z-position is 0 or the position is located directly above a
	 *         solid cube.
	 */
	public boolean canHaveAsPosition(double[] position){
		return (getWorld().isCubeInWorld(this.getWorld().getCubeCoordinate(position)) &&
				this.getWorld().getTerrain(position).isPassable());
	}
	
	public abstract int getWeight();
			
	public abstract void advanceTime(float duration);
	
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	
	public abstract void setWorld(@Raw World world);
	
	@Raw
	public abstract boolean hasProperWorld();
	
	protected World world;
	
	public abstract void terminate();
	
	public abstract boolean isTerminated();

	
}
