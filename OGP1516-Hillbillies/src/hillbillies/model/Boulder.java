package hillbillies.model;
import hillbillies.model.World.*;
import ogp.framework.util.Util;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/** 
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
		this.weight = new Random().nextInt(41)+ 10;
		//NbBoulder = NbBoulder + 1;
	}
	public Boulder (int[] position){
		super(position);
		this.weight = new Random().nextInt(41)+ 10;
	}
//
//	private int NbBoulder = 0;
//	
//	public int getNbBoulder(){
//		return NbBoulder;
//	}
	/**
	 * Return the position of this boulder.
	 */
	@Basic @Raw @Override
	public double[] getPosition() {
		return this.position;
	}
	
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
	 * Variable registering the position of this boulder.
	 */
	private double[] position;
	/**
	 * Variable registering the weight of this boulder.
	 */
	private final int weight;
	/**
	 * Variable registering the next target position of a falling boulder.
	 */
	private double[] nextTargetPosition;
	/**
	 * Variable registering the start position of a falling boulder.
	 */
	private double[] startPosition;


	/**
	 *Return the weight of this boulder.
	 */
	@Override
	public final int getWeight() {
		return this.weight;
	}
	/**
	 * Update the position and activity status of this boulder.
	 * @param duration
	 *        The game time after which advanceTime is called.
	 * @effect If this boulder needs to fall and his status isn't already falling, 
	 * 		this boulder will fall. 
	 * @post if this boulder is falling, his position will be updated. When this boulder 
	 * 		arrived at his target position. His next position will be updated one z-level
	 * 		lower.
	 * @effect If this boulder next position is not passable, his status is set to done.
	 * @effect Otherwise, this boulder will keep falling.
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
		// nog condities?
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
	
//	@Basic @Raw
// 	public World getWorld(){
// 		return world;
// 	}
	
//	private World world;
	
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
	 private Status status= Status.DONE;
	 
}
