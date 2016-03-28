package hillbillies.model;

import hillbillies.model.World.*;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;

/**
 * TO BE ADDED TO CLASS HEADING
 * 
 * @invar The position of each log must be a valid position for any log. |
 *        isValidPosition(getPosition())
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
		NbLogs = NbLogs + 1;
	}

	private int NbLogs = 0;

	public int getNbLogs() {
		return NbLogs;
	}

	/**
	 * Return the position of this log.
	 */
	@Basic
	@Raw
	@Override
	public double[] getPosition() {
		return this.position;
	}

	/**
	 * Check whether the given position is a valid position for any log.
	 * 
	 * @param position
	 *            The position to check.
	 * @return True if and only if the terrain type of this cube is passable and
	 *         the z-position is 0 or the position is located directly above a
	 *         solid cube.
	 */
	@Override
	public static boolean isValidPosition(double[] position) {
		if (World.getTerrain(position).getPassable())
			return true;
		return false;
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
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Variable registering the position of this log.
	 */
	private double[] position;

	private final int weight;

	private double[] nextTargetPosition;

	private double[] startPosition;

	/**
	 * 
	 * 
	 * @return the weight
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
					if (!world.getTerrain(nextPosition).isPassable() ||nextPosition[2]<1.0){
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
	public Status getStatus(){
		return this.status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public void setWorld(@Raw World world) {
		assert (world.hasAsLog(this));
		// nog condities?
		this.world = world;
	}

	@Raw
	@Override
	public boolean hasProperWorld() {
		return (getWorld().hasAsLog(this));
	}

	private World world;

	/**
	 * Terminate this log.
	 *
	 * @post This log is terminated. | new.isTerminated()
	 * @post ... | ...
	 */
	@Override
	public void terminate() {
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
