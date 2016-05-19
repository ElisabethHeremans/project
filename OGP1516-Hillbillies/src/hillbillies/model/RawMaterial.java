package hillbillies.model;
import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Basic;
/**
 * A class of raw materials
 * 
 * @invar  The position of each raw material must be a valid position for this
 *         raw material.
 * @invar The world of each raw material must be a proper world for this raw material.
 */
public abstract class RawMaterial {
	/**
	 * Initialize this new raw material with a given double position
	 * @param position
	 * 		The double position for this new raw material
	 * @effect The position of this new raw material is set to the given position. 
	 */
	public RawMaterial(double[] position) {
		this.setPosition(position);

	}
	/**
	 * Initialize this new raw material with a given integer position
	 * @param position
	 * 		The integer position for this new raw material
	 * @effect The position of this new raw material is set to the given position. 
	 */
	public RawMaterial(int[] position){
		this(new double[] {(double) position[0]+0.5, (double)position[1]+0.5,(double) position[2]+0.5});
	}
	/**
	 * Return the position of this raw material.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	/**
	 * Return the coordinates of the cube where this raw material is located.
	 */
	protected int[] getCubeCoordinate() {
		return new int[] { (int) Math.floor(this.getPosition()[0]), (int) Math.floor(this.getPosition()[1]),
				(int) Math.floor(this.getPosition()[2]) };
	}

	/**
	 * Set the position of this raw material to the given position.
	 * @param position
	 * 		The new position for this raw material
	 * @post The position of this raw material is equal to the given position.
	 * @throws IllegalArgumentException
	 * 			This raw material can not have the given position as its position
	 */
	protected void setPosition(double[] position) throws IllegalArgumentException{
		if (! canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Check whether the given position is a valid position for this raw material.
	 * 
	 * @param position
	 *            The position to check.
	 * @return If this log has a world, true if and only if the cube in which this raw mat is located is in its world. 
	 */
	protected boolean canHaveAsPosition(double[] position){
		if (this.getWorld()!=null){
			return getWorld().isCubeInWorld(this.getWorld().getCubeCoordinate(position));
			
		}
		return true;
	}
	
	/**
	 * Variable registering the position of this log.
	 */
	protected double[] position;

	
	
	/**
	 * Return the weight of this raw material.
	 */
	public abstract int getWeight();

	/**
	 * Update the position and activity status of this raw material	
	 * @param duration
	 * 		The game time after which advanceTime is called.
	 */
	public abstract void advanceTime(float duration) throws IllegalArgumentException;
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
	 * 		The world to be attached to this raw material.
// wegdoen?	 *@post This raw material references the given world as the world attached to it.
	 */
	protected abstract void setWorld(@Raw World world);
	
	/**
	 * Check whether this raw material can have the given world
	 * as its world.
	 *
	 * @param   world
	 *          The world to check.
	 * @return  If this raw mat is terminated, true if and only if
	 *          the given world is not effective.
	 *        | if (this.isTerminated())
	 *        |   then result == (world == null)
	 *          Otherwise, true if and only if the given world is
	 *          either not effective or not terminated.
	 *        | else result ==
	 *        |   (world == null) || (! world.isTerminated())
	 */
	@Raw
	public boolean canHaveAsWorld(World world) {
		if (this.isTerminated())
			return (world == null);
		return (world == null) || (!world.isTerminated());
	}

	
	/**
	 * Check whether this raw material has a proper world attached to it.
	 * @return True if and only if this raw mat can have its world as its world.
	 */
	@Raw
	protected boolean hasProperWorld(){
		return this.canHaveAsWorld(this.getWorld());
	}
	/**
	 * Variable referencing the world of this raw material.
	 */
	protected World world;
	/**
	 * Terminate this raw material.
	 * @post   This raw material  is terminated.
	 */
	protected void terminate(){
		this.isTerminated = true;
	}
	
	/**
	  * Return a boolean indicating whether or not this raw material
	  * is terminated.
	  */
	public boolean isTerminated(){
		 return this.isTerminated;
	}
	
	 /**
	  * Variable registering whether this boulder is terminated.
	  */
	 protected boolean isTerminated = false;


	
}
