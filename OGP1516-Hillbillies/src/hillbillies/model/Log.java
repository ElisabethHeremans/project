package hillbillies.model;

import hillbillies.model.World.*;

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

//	private int NbLogs = 0;
//
//	public int getNbLogs() {
//		return NbLogs;
//	}

	/**
	 * Return the position of this log.
	 */
	@Basic
	@Raw
	@Override
	public double[] getPosition() {
		return this.position;
	}
	
	public int[] getCubeCoordinate() {
		return new int[] { (int) Math.floor(this.getPosition()[0]), (int) Math.floor(this.getPosition()[1]),
				(int) Math.floor(this.getPosition()[2]) };
	}



	/**
	 * Set the position of this log to the given position.
	 * 
	 * @param position
	 *            The new position for this log.
	 * @post The position of this new log is equal to the given position. |
	 *       new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any log. | !
	 *             isValidPosition(getPosition())
	 */
	@Raw
	@Override
	public void setPosition(double[] position) throws IllegalArgumentException {
		if (!canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Variable registering the position of this log.
	 */
	private double[] position;
	/**
	 * Variable registering the weight of this log.
	 */
	private final int weight;

	private double[] nextTargetPosition;

	private double[] startPosition;

	/**
	 * Return the weight of this log.
	 */
	@Override
	public final int getWeight() {
		return this.weight;
	}

	@Override
	public void advanceTime(float duration) {
		 if (mustFall() && this.getStatus()!= Status.FALLING)
			 fall();
		 if (getStatus() == Status.FALLING){
				double[] v = new double[] {0.0,0.0,-3.0};
				setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
				if (Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
					setPosition(nextTargetPosition);
					double[] nextPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
					if (!this.getWorld().getTerrain(nextPosition).isPassable() ||nextPosition[2]<1.0){
						status = Status.DONE;
					}
					else
						fall();
				}
			}	

	}

	public boolean mustFall() {

		double[] ijk = new double[] { 0.0, 0.0, -1.0 };
		double[] neighbouring = Vector.vectorAdd(this.getPosition(), ijk);
		if (!world.getTerrain(neighbouring).isPassable()) {
			return false;
		}

		return true;
	}
	
	public void fall() {
		setStatus(Status.FALLING);
		this.nextTargetPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
		this.startPosition = this.getPosition();
	}
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
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * Set the world attached to this log to the given world.
	 * @param world
	 * 		The world to be attached to this log.
	 * @pre If the given world is effective, it must already reference
	 * 		this log as one of the logs to which it is attached.
	 * @post This log references the given world as the world attached to it.
	 */
	@Override
	public void setWorld(@Raw World world) {
		if (world != null)
			assert (world.hasAsLog(this));
		// nog condities?
		this.world = world;
	}
	/**
	 * Check whether this log has a proper world attached to it.
	 * @return True if and only if this log does not reference an effective world
	 * 		or if the world referenced by this log in turn references this log as 
	 * 		one of the logs to which it is attached.
	 */
	@Raw
	@Override
	public boolean hasProperWorld() {
		return (this.getWorld()== null || this.getWorld().hasAsLog(this));
	}

//	@Basic @Raw
//	public World getWorld(){
//		return world;
//	}
	
//	private World world;

	/**
	 * Terminate this log.
	 * @effect This log is removed from the set of logs attached to its world.
	 * @post This log is terminated. 
	 */
	@Override
	public void terminate() {
		this.getWorld().removeAsLog(this);
		this.setWorld(null);
		this.isTerminated = true;
	}

	/**
	 * Return a boolean indicating whether or not this log is terminated.
	 */
	@Basic
	@Raw
	@Override
	public boolean isTerminated() {
		return this.isTerminated;
	}

	/**
	 * Variable registering whether this log is terminated.
	 */
	private boolean isTerminated = false;
	private Status status= Status.DONE;

}
