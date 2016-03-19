package hillbillies.model;
import hillbillies.model.World.*;

import java.util.Random;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;


/** 
 * @invar  The position of each boulder must be a valid position for any
 *         boulder.
 *       | isValidPosition(getPosition())
 */
public class Boulder {
	
	
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
		this.setPosition(position);
		this.weight = new Random().nextInt(41)+ 10;
		NbBoulder = NbBoulder + 1;
		setWeight();
	}
	
	private int NbBoulder = 0;
	
	public int getNbBoulder(){
		return NbBoulder;
	}
	/**
	 * Return the position of this boulder.
	 */
	@Basic @Raw
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
	public static boolean isValidPosition(double[] position) {
		if (world.getTerrain(position).getPassable())
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
	@Raw
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

	
	private int weight;

	/**
	 * 
	 * 
	 * @return the weight
	 */
	public final int getWeight() {
		return this.weight;
	}
	
	private void setWeight() {
		this.weight = new Random().nextInt(41)+10;
	}
	
	public void advanceTime(){
		if (position[2] != 0 || world.getTerrain(position[2]-1).getPassable())
			moveToAdjacent(0,0,-1);
	}
	public void setWorld(@Raw World world){
		assert (world.hasAsBoulder(this));
		// nog condities?
		this.world = world;
	}
	
	@Raw
	public boolean hasProperWorld(){
		return (getWorld().hasAsBoulder(this));
	}
	
	@Basic @Raw
	public World getWorld(){
		return world;
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
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this boulder
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this boulder is terminated.
	  */
	 private boolean isTerminated = false;
	 
}
