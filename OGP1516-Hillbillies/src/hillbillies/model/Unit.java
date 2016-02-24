package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Units, with a name, weight, strength, agility, toughness and a position in the game world.
 * 
 * @invar 	Each unit is positioned inside the game world.
 * 			|isValidPosition(this.getPosition())
 * @invar 	Each unit has a name that is a valid name for any unit.
 * 			|isValidName(this.getName())
 * @invar  Each unit can have its strength as strength .
 *       	| canHaveAsStrength(this.getStrength())
 * @invar	Each unit has a valid agility (for any unit).
 * 			|isValidAgility(this.getAgility())
 * @invar	Each unit has a valid toughness (for any unit).
 * 			|isValidToughness(this.getToughness())
 * @invar	Each unit can have its weight as its weight.
 * 			|canHaveAsWeight(this.getWeight())
 * @invar	Each unit can have its number of stamina- and hitpoints as its number of stamina- and hitpoints.
 * 			|canHaveAsPoints(this.getHitpoints()) && canHaveAsPoints(this.getStaminaPoints())
 * @invar	Each unit has an orientation that is valid for any unit.
 * 			|isValidOrientation(this.getOrientation()) //we gaan zeggen dat tss 0 en PI*2 moet liggen
 * @invar	The duration is a valid duration for any unit.
 * 			|isValidDuration(this.getDuration())
 * @version 1.0
 * @author adminheremans
 */
public class Unit { 
	
	/**
	 * Initialize this new unit with a given name, weight, strength, 
	 * agility, toughness, position, hitpoints, staminapoints and an orientation.
	 * @param name
	 * 		The name for this new Unit.
	 * @param weight
	 * 		The weight for this new Unit.
	 * @param strength
	 * 		The strength for this new Unit.
	 * @param agility
	 * 		The agility for this new Unit.
	 * @param toughness
	 * 		The toughness for this new Unit.
	 * @param position
	 * 		The position of this new Unit.
	 * @param hitpoints
	 * 		The hitpoints for this new Unit.
	 * @param staminapoints
	 * 		The staminapoints for this new Unit.
	 * @param orientation
	 * 		The orientation of this new Unit.
	 * @post The name of this new Unit is equal to the given name.
	 * 		 |new.getName() == name
	 * @throws IllegalNameException
	 * 		   The given name is not a valid name for a unit.
	 *       | ! isValidName(this.getName)
	 * @post   If the given weight is a valid weight for any Unit,
	 *         the weight of this new Unit is equal to the given
	 *         weight. 
	 *       | if (isValidWeight(Weight))
	 *       |   then new.getWeight() == Weight
	 * @post   If the given strength is a valid strength for any unit,
	 *         the strength of this new unit is equal to the given
	 *         strength.
	 *       | if (isValidStrength(strength))
	 *       |   then new.getStrength() == strength
	 * @post   If the given agility is a valid agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility.
	 *       | if (isValidAgility(agility))
	 *       |   then new.getAgility() == agility
	 * @post   If the given toughness is a valid toughness for any unit,
	 *         the toughness of this new unit is equal to the given
	 *         toughness. 
	 *       | if (isValidToughness(toughness))
	 *       |   then new.getToughness() == toughness
	 * @post The position of this new Unit is equal to the given position.
	 * 		|new.getPosition() == position
	 * @throws IllegalPositionException
	 * 			The given position is not a valid position for a unit.
	 * 		| ! isValidPosition(this.getPosition)
	 * @pre    This new unit can have the given hitpoints as its hitpoints.
	 *       | canHaveAsHitpoints(hitpoints)
	 * @post   The hitpoints of this new unit is equal to the given
	 *         hitpoints.
	 *       | new.getHitpoints() == hitpoints* @post The hit-/staminapoints of this new Unit is equal to the given hit-/staminapoints.
	 * @post  If the given orientation is in the range 0..2*PI, the orientation of
	 *         this new unit is equal to the given orientation.
	 *         If the given orientation exceeds 2*PI, the orientation for this new
	 *         unit is equal to the given orientation modulo 2*PI.
	 *         If the given orientation is negative, the orientation for this new
	 *         unit is equal to (2*PI - the given orientation modulo 2*PI). 
	 */
	public Unit(String name, int weight, int strength, int agility, int toughness, double[] position, int hitpoints, int staminapoints,double orientation){
		
		if (canHaveAsWeight(weight))
			this.weight = weight;
		if ( canHaveAsStrength(strength))
			this.strength = strength;
		if ( canHaveAsAgility(agility))
			this.agility = agility;
		if (canHaveAsToughness(toughness))
			this.toughness = toughness;
		setOrientation(orientation);
		assert this.canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
	}
	
/**
 * Return the weight of this Unit.
 */
@Basic @Raw @Immutable
public int getWeight() {
	return this.weight;
}

/**
 * Check whether this Unit can have the given weight as its weight.
 *  
 * @param  Weight
 *         The weight to check.
 * @return 
 *       | result == (weight >= (strength + agility)/2)
*/
@Raw
public boolean canHaveAsWeight(int weight) {
	return weight >= (strength + agility)/2;
}


/**
 * Return the strength of this unit.
 */
@Basic @Raw @Immutable
public int getStrength() {
	return this.strength;
}

/**
 * Check whether this unit can have the given strength as its strength.
 *  
 * @param  strength
 *         The strength to check.
 * @return 
 *       | result == ((25<= strength) && (strength<=100)) 
*/
@Raw
public boolean canHaveAsStrength(int strength) {
	return (25<= strength) && (strength<=100);
}

/**
 * Return the agility of this unit.
 */
@Basic @Raw @Immutable
public int getAgility() {
	return this.agility;
}

/**
 * Check whether this unit can have the given agility as its agility.
 *  
 * @param  agility
 *         The agility to check.
 * @return 
 *       | result == ((25<=agility) && (agility<=100))
*/
@Raw
public boolean canHaveAsAgility(int agility) {
	return ((25<=agility) && (agility<=100));
}


/**
 * Return the toughness of this unit.
 */
@Basic @Raw @Immutable
public int getToughness() {
	return this.toughness;
}

/**
 * Check whether this unit can have the given toughness as its toughness.
 *  
 * @param  toughness
 *         The toughness to check.
 * @return 
 *       | result == ((25<=toughness) && (toughness<=100))
*/
@Raw
public boolean canHaveAsToughness(int toughness) {
	return ((25<=toughness) && (toughness<=100));
}

/** TO BE ADDED TO CLASS HEADING
 * @invar  The name of each unit must be a valid name for any
 *         unit.
 *       | isValidName(getName())
 */


/**
 * Initialize this new unit with given name.
 *
 * @param  name
 *         The name for this new unit.
 * @effect The name of this new unit is set to
 *         the given name.
 *       | this.setName(name)
 */
public Unit(String name)
		throws ExceptionName_Java {
	this.setName(name);
}


/**
 * Return the name of this unit.
 */
@Basic @Raw
public String getName() {
	return this.name;
}

/**
 * Check whether the given name is a valid name for
 * any unit.
 *  
 * @param  name
 *         The name to check.
 * @return 
 *       | result == 
*/
public static boolean isValidName(String name) {
	return false;
}

/**
 * Set the name of this unit to the given name.
 * 
 * @param  name
 *         The new name for this unit.
 * @post   The name of this new unit is equal to
 *         the given name.
 *       | new.getName() == name
 * @throws ExceptionName_Java
 *         The given name is not a valid name for any
 *         unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) 
		throws ExceptionName_Java {
	if (! isValidName(name))
		throw new ExceptionName_Java();
	this.name = name;
}

@Basic @Raw @Immutable
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Check whether this unit can have the given hitpoints as its hitpoints.
 *  
 * @param  hitpoints
 *         The hitpoints to check.
 * @return 
 *       | result == (hitpoints < max_nbHitpoints())
*/
@Raw
public boolean canHaveAsHitpoints(int hitpoints) {
	return hitpoints < max_nbHitpoints();
}

private int max_nbHitpoints() {
	return 200*(this.getWeight()/100)*(this.getToughness()/100);
}


	
	/**
	 * Return the position of this unit. 
	 * 	The position consists of an x, y and z-coordinate.
	 * @return the position
	 */
	@Basic @Raw
	public double[] getPosition() {
		return position;
	}
	
	/**
	 * Check whether the given position is a position inside the game world, a valid position.
	 * @param position
	 * @return True if and only if the x,y and z-coordinate of the position are inside the limits of the game world.
	 * 			|result == (0<= position[0]) && (position[0] <= X) 
	 * 			|	&& (0<= position[1]) && (position[1] <= Y) && (0<= position[2]) && (position[2] <= Z)
	 */
	public static boolean isValidPosition(double[] position){
		return 0<= position[0] && position[0] <= X && 0<= position[1] && position[1] <= Y && 0<= position[2] && position[2] <= Z;
	}
	
	/**
	 * Return the position of the game world cube in which this unit is positioned
	 * @return The position
	 */
	public int[] getCubePosition(){
		return {Math.floor(this.getPosition()[0]),Math.floor(this.getPosition()[1]),Math.floor(this.getPosition()[2])};
	}

	/**
	 * Set the position of this unit to the given position.
	 * @param position
	 * 		The new position for this unit.
	 * @post The new position for this unit is equal to the given position.
	 * 		|new.getPosition() == position
	 * @throws IllegalPositionException(position,this)
	 * 		The given position is not a valid position for any unit.
	 * 		|!isValidPosition(position)
	 */
	@Raw
	private void setPosition(double[] position) throws IllegalPositionException{
		if (!isValidPosition(position))
			throw new IllegalPositionException(position,this);
		this.position = position;
	}

	/**
	 * A variable registering the position of this unit.
	 */
	private double[] position = {0.0,0.0,0.0};
	
	/**
	 * A variable registering the name of this unit.
	 */
	private String name = "Unit";
	
	/**
	 * A variable registering the weight of this unit.
	 */
	private int weight = 25;
	
	/**
	 * Variable registering the strength of this unit.
	 */

	private int strength = 25;
	
	/**
	 * A variable registering the agility of this unit.
	 */
	private int agility = 25;
	
	/**
	 * A variable registering the toughness of this unit.
	 */
	private int toughness = 25;
	
	/**
	 * A variable registering the number of hitpoints of this unit.
	 */
	private int hitpoints = 0;
	
	/**
	 * A variable registering the number of stamina points of this unit.
	 */
	private int staminaPoints = 0;
	
	/**
	 * A variable registering the orientation of this unit.
	 */
	private float orientation = (float) ((float) Math.PI/2.0);
	
	/**
	 * A variable registering the duration before the units position and activity status are updated.
	 */
	private float duration = 0;
	
	/**
	 * Update the position and activity status.
	 * @param duration
	 * @post The new position and activity status of a unit.
	 * @throws IllegalArgumentException
	 * 		If the duration is less than zero or exceeds or equals 0.2 s.
	 */
	public void advanceTime(float duration) throws IllegalArgumentException{
		if(duration<0 || duration>= 0.2)
			throw new IllegalArgumentException(); 
	}
	
//	public int getMaxStaminaPoints(){
//		return Math.ceil(200.0*(this.getWeight/100.0)*(this.getToughness()/100.0));
//	}
//	
//	public int getMaxHitPoints(){
//		return Math.ceil(200.0*(this.getWeight/100.0)*(this.getToughness()/100.0));
//	}
//
//	public double getOrientation() {
//		return orientation;
//	}
//
//	public void setOrientation(double orientation) {
//		this.orientation = orientation;
//	}
//	
//	public float getBaseSpeed(){
//		return 1.5*(this.getStrength+this.getAgility)/(200.0*(this.getWeight/100));
//	}
	
	/**
	 * Symbolic constant registering the fixed number of cubes in direction x.
	 */
	private static int X = 50;
	
	/**
	 * Symbolic constant registering the fixed number of cubes in direction y.
	 */
	private static int Y = 50;
	
	/**
	 * Symbolic constant registering the fixed number of cubes in direction z.
	 */
	private static int Z = 50; //p72

}
