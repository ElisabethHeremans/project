package hillbillies.model;
import hillbillies.model.World.*;

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
		NbBoulder = NbBoulder + 1;
	}

	private int NbBoulder = 0;
	
	public int getNbBoulder(){
		return NbBoulder;
	}
	/**
	 * Return the position of this boulder.
	 */
	@Basic @Raw @Override
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any boulder.
	 *  
	 * @param  position
	 *         The position to check.
	 * @return True if and only if the terrain type of this cube is passable and 
	 * 			the z-position is 0 or the position is located directly above a solid cube.
	*/
	public boolean isValidPosition(double[] position) {
		if (this.getWorld().getTerrain(position).isPassable())
				return true;
		return false;
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
	public void setPosition(double[] position) 
			throws IllegalArgumentException {
		if (! isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable registering the position of this boulder.
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
	public Status getStatus(){
		return this.status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@Override
	public void setWorld(@Raw World world){
		assert (world.hasAsBoulder(this));
		// nog condities?
		this.world = world;
	}
	
	@Raw
	@Override
	public boolean hasProperWorld(){
		return (getWorld().hasAsBoulder(this));
	}
	
	private World world;
	
	/**
	 * Terminate this boulder.
	 *
	 * @post   This boulder  is terminated.
	 *       | new.isTerminated()
	 * @post   ...
	 *       | ...
	 */
	@Override
	 public void terminate() {
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
