package hillbillies.model;

import hillbillies.model.World.*;
import ogp.framework.util.Util;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * @invar The position of each log must be a valid position for any log. 
 */
public class Log extends RawMaterial {

	/**
	 * Initialize this new log with given position.
	 *
	 * @param position
	 *            The position for this new log.
	 * @effect The position of this new log is set to the given position. |
	 *         this.setPosition(position)
	 * @post The weight of this new log is an integer in the range of 10 to 40.
	 */
	public Log(double[] position) throws IllegalArgumentException {
		super(position);
		this.weight = new Random().nextInt(41) + 10;
		//NbLogs = NbLogs + 1;
	}
	
	public Log(int[] position){
		super(position);
		this.weight = new Random().nextInt(41) + 10;
	}

	/**
	 * Return the weight of this log.
	 */
	@Override @Basic @Raw
	public final int getWeight() {
		return this.weight;
	}

	/**
	 * Variable registering the weight of this log.
	 */
	private final int weight;

	/**
	 * Set the position of this log to the given position.
	 * 
	 * @param position
	 *            The new position for this log.
	 * @post The position of this new log is equal to the given position. |
	 *       new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any log. | !
	 *             canHaveAsPosition(getPosition())
	 */
	@Raw
	@Override
	protected void setPosition(double[] position) throws IllegalArgumentException {
		if (!canHaveAsPosition(position))
			throw new IllegalArgumentException();
		if (this.getWorld()!=null)
			this.getWorld().removeLogFromLogsAtCubeMap(this);
		this.position = position;
		if (this.getWorld()!=null)
			this.getWorld().addLogToLogsAtCubeMap(this);
	}
	
	/**
	 * Check whether the given position is a valid position for this log.
	 * 
	 * @param position
	 *            The position to check.
	 * @return If this log has a world, true if and only if the cube in which this log is located is in the world, 
	 * 			and the terrain type of this cube is passable.
	 * @return else true
	 */
	@Override
	public boolean canHaveAsPosition(double[] position){
		if (super.canHaveAsPosition(position)){
		if (getWorld()!= null){
			return (this.getWorld().getTerrain(position).isPassable());
		}
		else
			return true;
		}
		return false;
	}

	
	/**
	 * Update the position and activity status of a log.
	 * @param duration
	 *        The game time after which advanceTime is called.
	 * @effect If this log needs to fall and his status isn't already falling, 
	 * 		this log will fall. 
	 * @post if this log is falling, his position will be updated. When this log 
	 * 		arrived at his target position. His next position will be updated one z-level
	 * 		lower.
	 * 		then,  If this log must still fall, his status is set to done.
	 *				if this log must fall, this log falls.
	 */
	@Override
	public void advanceTime(float duration) throws IllegalArgumentException {
		 if (!(Util.fuzzyGreaterThanOrEqualTo(duration, 0.0-Util.DEFAULT_EPSILON )&& Util.fuzzyLessThanOrEqualTo((double)duration, 0.2+Util.DEFAULT_EPSILON))){
			System.out.println(duration);
			throw new IllegalArgumentException();
		 }
		 if (mustFall() && this.getStatus()!= Status.FALLING)
			 fall();
		 if (getStatus() == Status.FALLING){
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
	 * Check whether this log needs to fall.
	 * @return False if the cube one z-level lower than the position of this log
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
	 * Makes the log fall.
	 * @post This logs new status will be equal to falling.
	 * @post The startposition of this log is the position of this log and 
	 * 		the next target position is one z-level lower than the position of this log.
	 */
	public void fall() {
		setStatus(Status.FALLING);
		this.nextTargetPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
		this.startPosition = this.getPosition();
	}
	/**
	 * Variable registering the next target position of a falling log.
	 */
	private double[] nextTargetPosition;
	/**
	 * Variable registering the start position of a falling log.
	 */
	private double[] startPosition;

	/**
	 * Return the status of this log.
	 */
	public Status getStatus(){
		return this.status;
	}
	/**
	 * Set the status of this log to the given status.
	 * @param status
	 * 		The new status for this log.
	 * @post The status of this log is equal to the given status.
	 */
	protected void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * Variable registering the status of this log.
	 */
	private Status status= Status.DONE;
	/**
	 * Set the world attached to this log to the given world.
	 * @param world
	 * 		The world to be attached to this log.
	 * @pre If the given world is effective, it must already reference
	 * 		this log as one of the logs to which it is attached.
	 * @post This log references the given world as the world attached to it.
	 */
	@Override
	protected void setWorld(@Raw World world) {
		if (world != null)
			assert (world.hasAsLog(this));
		this.world = world;
	}
	/**
	 * Check whether this log has a proper world attached to it.
	 * @return True if and only if this log can have its world as its world and (does not reference an effective world
	 * 		or if the world referenced by this log in turn references this log as 
	 * 		one of the logs to which it is attached).
	 */
	@Raw
	@Override
	public boolean hasProperWorld() {
		return (super.hasProperWorld()&&
				(this.getWorld()== null || this.getWorld().hasAsLog(this)));
	}


	/**
	 * Terminate this log.
	 * @effect This log is removed from the set of logs attached to its world.
	 * @post This log is terminated. 
	 */
	@Override
	public void terminate() {
		if (this.getWorld()!=null)
			this.getWorld().removeAsLog(this);
		this.isTerminated = true;
	}


}
