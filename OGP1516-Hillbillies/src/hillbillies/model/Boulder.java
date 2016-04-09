package hillbillies.model;
import hillbillies.model.World.*;
import ogp.framework.util.Util;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/** 
 * A class of boulders, with a position.
 * 
 * @invar  The position of each boulder must be a valid position for any
 *         boulder.
 *       | isValidPosition(getPosition())
 */
public class Boulder extends RawMaterial {
	
	
	/**
	 * Initialize this new boulder with given position.
	 *
	 * @param  position
	 *         The position for this new boulder.
	 * @effect The position of this new boulder is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 * @post The weight of this new boulder is an integer in the range of 10 to 40.
	 */
	public Boulder(double[] position)
			throws IllegalArgumentException {
		super(position);
		this.weight = new Random().nextInt(31)+ 10;
	}
	
	/**
	 * Initialize this new boulder with a given cube position.
	 * @param position
	 * 			The integer position to give to the boulder
	 * @effect Initialize this new boulder with the given position.
	 * @post The weight of this new boulder is an integer in the range of 10 to 40.
	 */
	public Boulder (int[] position){
		super(position);
		this.weight = new Random().nextInt(41)+ 10;
	}

	
	/**
	 * Return the position of this boulder.
	 */
	@Basic @Raw @Override
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Return the cube coordinate of this boulder.
	 * 
	 * @return The cube coordinate of this boulder, which means that each coordinate is rounded down to an integer.
	 */
	protected int[] getCubeCoordinate() {
		return new int[] { (int) Math.floor(this.getPosition()[0]), (int) Math.floor(this.getPosition()[1]),
				(int) Math.floor(this.getPosition()[2]) };
	}
	
	
	/**
	 * Set the position of this boulder to the given position.
	 * 
	 * @param  position
	 *         The new position for this boulder.
	 * @post   The position of this new boulder is equal to
	 *         the given position.
	 *       | new.getPosition() == position
	 * @throws IllegalArgumentException
	 *         The given position is not a valid position for any
	 *         log.
	 *       | ! isValidPosition(getPosition())
	 */
	@Raw @Override
	protected void setPosition(double[] position) 
			throws IllegalArgumentException {
		if (! canHaveAsPosition(position))
			throw new IllegalArgumentException();
		if (this.getWorld()!=null)
			this.getWorld().removeBoulderFromBouldersAtCubeMap(this);
		this.position = position;
		if (this.getWorld()!=null)
			this.getWorld().addBoulderToBouldersAtCubeMap(this);
	}
	
	/**
	 * Check whether the given position is a valid position for this boulder.
	 * 
	 * @param position
	 *            The position to check.
	 * @return if this log has a world, true if and only if the cube in which this boulder is located is in the world, 
	 * 			and the terrain type of this cube is passable.
	 */
	@Override
	public boolean canHaveAsPosition(double[] position){
		if (getWorld()!= null){
		return (getWorld().isCubeInWorld(this.getWorld().getCubeCoordinate(position)) &&
				this.getWorld().getTerrain(position).isPassable());
		}
		else
			return true;
	}

	
	/**
	 * Variable registering the position of this boulder.
	 */
	private double[] position;
	
	/**
	 *Return the weight of this boulder.
	 */
	@Override
	public final int getWeight() {
		return this.weight;
	}
	/**
	 * Variable registering the weight of this boulder.
	 */
	private final int weight;


	/**
	 * Update the position and activity status of this boulder.
	 * @param duration
	 *        The game time after which advanceTime is called.
	 * @effect If this boulder needs to fall and his status isn't already falling, 
	 * 		this boulder will fall. 
	 * @post if this boulder is falling, his position will be updated. When this boulder 
	 * 		arrived at his next target position, the next position will be updated one z-level
	 * 		lower.
	 * 			then, 	If this boulder must not fall, his status is set to done.
	 * 					If this boulder must fall, this boulder will fall.
	 */	
	@Override
	public void advanceTime(float duration) throws IllegalArgumentException{
	 	 if (!(Util.fuzzyGreaterThanOrEqualTo(duration, 0.0-Util.DEFAULT_EPSILON )&& Util.fuzzyLessThanOrEqualTo((double)duration, 0.2+Util.DEFAULT_EPSILON))){
			System.out.println(duration);
			throw new IllegalArgumentException();
		 }
		 if (mustFall() && this.getStatus()!= Status.FALLING){
			 fall();
			 }
		 if (this.getStatus() == Status.FALLING){
				double[] v = new double[] {0.0,0.0,-3.0};
				setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
				if (Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
					setPosition(nextTargetPosition);
					double[] nextPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
					if (!mustFall()){
						status = Status.DONE;
					}
					else
						fall();
				}
			}	
	}
	
	/**
	 * Check whether this boulder needs to fall.
	 * @return False if the cube one z-level lower than the position of this boulder
	 * 		is not passable terrain.
	 */
	public boolean mustFall() {
		if ( (this.getCubeCoordinate()[2]==0)){
			return false;
		}
		double[] ijk = new double[] { 0.0, 0.0, -1.0 };
		double[] neighbouring = Vector.vectorAdd(this.getPosition(), ijk);
		if (!world.getTerrain(neighbouring).isPassable()) {
			return false;
		}

		return true;
	}
	/**
	 * Makes the boulder fall.
	 * @post This boulders new status will be equal to falling.
	 * @post The startposition of this boulder is the position of this boulder and 
	 * 		the next target position is one z-level lower than the position of this boulder.
	 */
	public void fall() {
		setStatus(Status.FALLING);
		this.nextTargetPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
		this.startPosition = this.getPosition();
	}
	/**
	 * Variable registering the next target position of this boulder when falling.
	 */
	private double[] nextTargetPosition;
	/**
	 * Variable registering the start position of this boulder when falling.
	 */
	private double[] startPosition;


	/**
	 * Return the status of this boulder.
	 */
	public Status getStatus(){
		return this.status;
	}
	/**
	 * Set the status of this boulder to the given status.
	 * @param status
	 * 		The new status for this boulder.
	 * @post The new status of this boulder is equal to the given status.
	 */
	protected void setStatus(Status status) {
		this.status = status;
	}
	/**
	  * Variable registering the status of this boulder.
	  */
	 private Status status= Status.DONE;

	/**
	 * Set the world attached to this boulder to the given world.
	 * @param world
	 * 		The world to be attached to this boulder.
	 * @pre If the given world is effective, it must already reference
	 * 		this boulder as one of the boulders to which it is attached.
	 * @post This boulder references the given world as the world attached to it.
	 */
	@Override
	protected void setWorld(@Raw World world){
		if (world != null)
			assert (world.hasAsBoulder(this));
		this.world = world;
	}
	/**
	 * Check whether this boulder has a proper world attached to it.
	 * @return True if and only if this boulder does not reference an effective world
	 * 		or if the world referenced by this boulder in turn references this boulder as 
	 * 		one of the boulders to which it is attached.
	 */
	@Raw
	@Override
	protected boolean hasProperWorld(){
		return (this.getWorld() == null || this.getWorld().hasAsBoulder(this));
	}
	
	
	/**
	 * Terminate this boulder.
	 * @effect This boulder is removed from the set of boulders attached to its world.
	 * @post   This boulder  is terminated.
	 */
	@Override
	 public void terminate() {
		if (this.getWorld()!=null)
			this.getWorld().removeAsBoulder(this);
		//this.setWorld(null);
		this.setWorld(null);
		this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this boulder
	  * is terminated.
	  */
	 @Basic @Raw @Override
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this boulder is terminated.
	  */
	 private boolean isTerminated = false;
	 
}
