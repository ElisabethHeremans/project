package hillbillies.model;
import hillbillies.model.World.*;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;


/** TO BE ADDED TO CLASS HEADING
 * @invar  The position of each log must be a valid position for any
 *         log.
 *       | isValidPosition(getPosition())
 */
public class Log {
	
	
	/**
	 * Initialize this new log with given position.
	 *
	 * @param  position
	 *         The position for this new log.
	 * @effect The position of this new log is set to
	 *         the given position.
	 *       | this.setPosition(position)
	 * @post The weight of this new log is an integer in the range of 10 to 40.
	 */
	public Log(double[] position)
			throws IllegalArgumentException {
		this.setPosition(position);
		this.weight = new Random().nextInt(41)+ 10;
	}
	
	
	/**
	 * Return the position of this log.
	 */
	@Basic @Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a valid position for
	 * any log.
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
	 * Set the position of this log to the given position.
	 * 
	 * @param  position
	 *         The new position for this log.
	 * @post   The position of this new log is equal to
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
	 * Variable registering the position of this log.
	 */
	private double[] position;

	
	final private int weight;

	/**
	 * 
	 * 
	 * @return the weight
	 */
	public final int getWeight() {
		return weight;
	}
	
	public void advanceTime(){
		if (position[2] != 0 || world.getTerrain(position[2]-1).getPassable())
			moveToAdjacent(0,0,-1);
	}

}
