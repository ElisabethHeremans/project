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
	/**
	 * Return the position of this raw material.
	 */
	public abstract double[] getPosition();
	/**
	 * Return the coordinates of the cube where this raw material is located.
	 */
	public abstract int[] getCubeCoordinate();

	/**
	 * Set the position of this raw material to the given position.
	 * @param position
	 * 		The new position for this raw material
	 * @post The position of this raw material is equal to the given position.
	 */
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
	/**
	 * Return the weight of this raw material.
	 */
	public abstract int getWeight();
	/**
	 * Update the position and activity status of this raw material	
	 * @param duration
	 * 		The game time after which advanceTime is called.
	 */
	public abstract void advanceTime(float duration);
	/**
	 * Return the world of this raw material.
	 */
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	/**
	 * Set the world attached to this raw material to the given world.
	 * @param world
	 * 		The world to be attached to this raw material..
	 * @post This raw material references the given world as the world attached to it.
	 */
	public abstract void setWorld(@Raw World world);
	/**
	 * Check whether this raw material has a proper world attached to it.
	 * @return ...
	 */
	@Raw
	public abstract boolean hasProperWorld();
	/**
	 * Variable referencing the world of this raw material.
	 */
	protected World world;
	/**
	 * Terminate this raw material.
	 * @post   This raw material  is terminated.
	 */
	public abstract void terminate();
	/**
	  * Variable registering whether this raw material is terminated.
	  */
	public abstract boolean isTerminated();

	
}
