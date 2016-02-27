package hillbillies.model;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;

/**
 * A class of Units, with a name, weight, strength, agility, toughness and a position in the game world.
 * 
 * @invar 	Each unit is positioned inside the game world.
 * 			|isValidPosition(this.getPosition())
 * @invar 	The name of each unit must be a valid name for any
 *          unit.
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
 * @invar  The targetPosition of each unit must be a valid targetPosition for any
 *         unit.
 *       | isValidTargetPosition(getTargetPosition())
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
 	 * @effect The name of this new unit is set to
 	 *         the given name.
 	 *       | this.setName(name)
	 * @post   If the given weight is a valid weight for any Unit,
	 *         the weight of this new Unit is equal to the given
	 *         weight. Otherwise, the weight of this new unit is equal
	 *         to 25.
	 *       | if (isValidWeight(Weight))
	 *       |   then new.getWeight() == Weight
	 *       |   else new.getWeight() == 25
	 * @post   If the given strength is a valid strength for any unit,
	 *         the strength of this new unit is equal to the given
	 *         strength. Otherwise, the strength of this new unit is equal
	 *         to 25.
	 *       | if (isValidStrength(strength))
	 *       |   then new.getStrength() == strength
	 *       |   else new.getStrength() == 25
	 * @post   If the given agility is a valid agility for any unit,
	 *         the agility of this new unit is equal to the given
	 *         agility. Otherwise, the agility of this new unit is equal
	 *         to 25.
	 *       | if (isValidAgility(agility))
	 *       |   then new.getAgility() == agility
	 *       |   else new.getAgility() == 25
	 * @post   If the given toughness is a valid toughness for any unit,
	 *         the toughness of this new unit is equal to the given
	 *         toughness. Otherwise, the toughness of this new unit is equal
	 *         to 25. 
	 *       | if (isValidToughness(toughness))
	 *       |   then new.getToughness() == toughness
	 *       |   else new.getToughness() == 25
	 * @effect The position of this new unit is set to
 	 *         the given position.
 	 *       | this.setPosition(position)
	 * @pre    This new unit can have the given hitpoints as its hitpoints.
	 *       | canHaveAsHitpoints(hitpoints)
	 * @post   The hitpoints of this new unit is equal to the given
	 *         hitpoints.
	 *       | new.getHitpoints() == hitpoints
	 * @pre    The given the number of staminapoints must be a valid the number of staminapoints for any unit.
	 *       | isValidStaminaPoints(the number of staminapoints)
	 * @post   The the number of staminapoints of this new unit is equal to the given
	 *         the number of staminapoints.
	 *       | new.getStaminaPoints() == staminaPoints
	 * @post  If the given orientation is in the range 0..2*PI, the orientation of
	 *         this new unit is equal to the given orientation.
	 *         If the given orientation exceeds 2*PI, the orientation for this new
	 *         unit is equal to the given orientation modulo 2*PI.
	 *         If the given orientation is negative, the orientation for this new
	 *         unit is equal to (2*PI - the given orientation modulo 2*PI). 
	 */
	public Unit(String name, int weight, int strength, int agility, int toughness, double[] position, int hitpoints, int staminapoints,double orientation)throws IllegalArgumentException {
		
		if (! isValidWeight(weight))
			weight = 25;
		setWeight(weight);
		if (! isValidStrength(strength))
			strength = 25;
		setStrength(strength);
		if (! isValidAgility(agility))
			agility = 25;
		setAgility(agility);
		if (! isValidToughness(toughness))
			toughness = 25;
		setToughness(toughness);
		setOrientation(orientation);
		assert this.canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
		this.setStaminaPoints(staminaPoints);
		this.setName(name);
	}
	
/**
 * Return the weight of this Unit.
 */
@Basic @Raw 
public int getWeight() {
	return this.weight;
}


/**
 * Check whether the given weight is a valid weight for
 * any unit.
 * @param  Weight
 *         The weight to check.
 * @return 
 *       | result == (weight >= (strength + agility)/2)
*/
@Raw
public boolean isValidWeight(int weight) {
	return weight >= (this.strength + this.agility)/2;
}

/**
 * Set the weight of this unit to the given weight.
 * 
 * @param  weight
 *         The new weight for this unit.
 * @post   If the given weight is a valid weight for any unit,
 *         the weight of this new unit is equal to the given
 *         weight.
 *       | if (isValidWeight(weight))
 *       |   then new.getWeight() == weight
 */
@Raw
private void setWeight(int weight) {
	if (isValidWeight(weight))
		this.weight = weight;
}


/**
 * Return the strength of this unit.
 */
@Basic @Raw 
public int getStrength() {
	return this.strength;
}

/**
 * Check whether the given strength is a valid strength for
 * any unit.
 * @param  strength
 *         The strength to check.
 * @return 
 *       | result == ((25<= strength) && (strength<=100)) 
*/
@Raw
public boolean isValidStrength(int strength) {
	return (25<= strength) && (strength<=100);
}

/**
 * Set the strength of this unit to the given strength.
 * 
 * @param  strength
 *         The new strength for this unit.
 * @post   If the given strength is a valid strength for any unit,
 *         the strength of this new unit is equal to the given
 *         strength.
 *       | if (isValidStrength(strength))
 *       |   then new.getStrength() == strength
 */
@Raw
private void setStrength(int strength) {
	if (isValidStrength(strength))
		this.strength = strength;
}

/**
 * Return the agility of this unit.
 */
@Basic @Raw 
public int getAgility() {
	return this.agility;
}

/**
 * Check whether the given agility is a valid agility for
 * any unit. 
 * @param  agility
 *         The agility to check.
 * @return 
 *       | result == ((25<=agility) && (agility<=100))
*/
@Raw
public boolean isValidAgility(int agility) {
	return ((25<=agility) && (agility<=100));
}

/**
 * Set the agility of this unit to the given agility.
 * 
 * @param  agility
 *         The new agility for this unit.
 * @post   If the given agility is a valid agility for any unit,
 *         the agility of this new unit is equal to the given
 *         agility.
 *       | if (isValidAgility(agility))
 *       |   then new.getAgility() == agility
 */
@Raw
private void setAgility(int agility) {
	if (isValidAgility(agility))
		this.agility = agility;
}



/**
 * Return the toughness of this unit.
 */
@Basic @Raw
public int getToughness() {
	return this.toughness;
}

/**
 * Check whether the given toughness is a valid toughness for
 * any unit.
 *  
 * @param  toughness
 *         The toughness to check.
 * @return 
 *       | result == ((25<=toughness) && (toughness<=100))
*/
public static boolean isValidToughness(int toughness) {
	return ((25<=toughness) && (toughness<=100));
}

/**
 * Set the toughness of this unit to the given toughness.
 * 
 * @param  toughness
 *         The new toughness for this unit.
 * @post   If the given toughness is a valid toughness for any unit,
 *         the toughness of this new unit is equal to the given
 *         toughness.
 *       | if (isValidToughness(toughness))
 *       |   then new.getToughness() == toughness
 */
@Raw
private void setToughness(int toughness) {
	if (isValidToughness(toughness))
		this.toughness = toughness;
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
 *       | result == !name.length()<2 && Character.isUpperCase(name.charAt(0)) 
 *       | 	&& (Character.isLetter(name.charAt(index)) || name.charAt(index)=='"' || name.charAt(index)=='\'' || name.charAt(index)==' ')
*/
public static boolean isValidName(String name) {
	if (name.length()<2)
		return false;
	if (!Character.isUpperCase(name.charAt(0)))
		return false;
	for (int index =0; index < name.length();) {
		if(!Character.isLetter(name.charAt(index)) && (!(name.charAt(index)=='"')) &&(!(name.charAt(index)=='\'')) && (!(name.charAt(index)==' ')))
			return false;
	}
	return true;
	
}

/**
 * Set the name of this unit to the given name.
 * 
 * @param  name
 *         The new name for this unit.
 * @post   The name of this new unit is equal to
 *         the given name.
 *       | new.getName() == name
 * @throws IllegalArgumentException
 *         The given name is not a valid name for any
 *         unit.
 *       | ! isValidName(getName())
 */
@Raw
public void setName(String name) 
		throws IllegalArgumentException {
	if (! isValidName(name))
		throw new IllegalArgumentException();
	this.name = name;
}

@Basic @Raw 
public int getHitpoints() {
	return this.hitpoints;
}

/**
 * Check whether this unit can have the given hitpoints as its hitpoints.
 *  
 * @param  hitpoints
 *         The hitPoints to check.
 * @return 
 *       | result == (hitpoints < max_nbHitpoints())
*/
@Raw
public boolean canHaveAsHitpoints(int hitpoints) {
	return hitpoints < max_nbPoints();
}

private double max_nbPoints() {
	return Math.ceil(200.0*(this.getWeight()/100.0)*(this.getToughness()/100.0));
}

@Raw
public void setHitPoints(int hitpoints) {
	assert canHaveAsHitpoints(hitpoints);
	this.hitpoints = hitpoints;
}

/**
 * Return the the number of staminapoints of this unit.
 */
@Basic @Raw
public int getStaminaPoints() {
	return this.staminaPoints;
}

/**
 * Check whether the given the number of staminapoints is a valid the number of staminapoints for
 * any unit.
 *  
 * @param  the number of staminapoints
 *         The the number of staminapoints to check.
 * @return 
 *       | result == 
*/
public boolean isValidStaminaPoints(int staminaPoints) {
	return staminaPoints < max_nbPoints();
}

/**
 * Set the the number of staminapoints of this unit to the given the number of staminapoints.
 * 
 * @param  staminaPoints
 *         The new the number of staminapoints for this unit.
 * @pre    The given the number of staminapoints must be a valid the number of staminapoints for any
 *         unit.
 *       | isValidStaminaPoints(staminaPoints)
 * @post   The the number of staminapoints of this unit is equal to the given
 *         the number of staminapoints.
 *       | new.getStaminaPoints() == staminaPoints
 */
@Raw
public void setStaminaPoints(int staminaPoints) {
	assert isValidStaminaPoints(staminaPoints);
	this.staminaPoints = staminaPoints;
}

/**
 * @invar  The orientation of each unit must be a valid orientation for any
 *         unit.
 *       | isValidOrientation(getOrientation())
 */

/**
 * Initialize this new unit with given orientation.
 * 
 * @param  orientation
 *         The orientation for this new unit.
 * @post   If the given orientation is a valid orientation for any unit,
 *         the orientation of this new unit is equal to the given
 *         orientation. Otherwise, the orientation of this new unit is equal
 *         to PI/2.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 *       |   else new.getOrientation() == PI/2
 */
public Unit(float orientation) {
	if (! isValidOrientation(orientation))
		orientation = (float) ((float) Math.PI/2.0);
	setOrientation(orientation);
}

/**
 * Return the orientation of this unit.
 */
@Basic @Raw
public float getOrientation() {
	return this.orientation;
}

/**
 * Check whether the given orientation is a valid orientation for
 * any unit.
 *  
 * @param  orientation
 *         The orientation to check.
 * @return 
 *       | result == 
*/
public static boolean isValidOrientation(float orientation) {
	return false;
}

/**
 * Set the orientation of this unit to the given orientation.
 * 
 * @param  orientation
 *         The new orientation for this unit.
 * @post   If the given orientation is a valid orientation for any unit,
 *         the orientation of this new unit is equal to the given
 *         orientation.
 *       | if (isValidOrientation(orientation))
 *       |   then new.getOrientation() == orientation
 */
@Raw
public void setOrientation(float orientation) {
	if (isValidOrientation(orientation))
		this.orientation = orientation;
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
	public double[] getCubePosition(){
		return new double[] {Math.floor(this.getPosition()[0]),Math.floor(this.getPosition()[1]),Math.floor(this.getPosition()[2])};
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
	private void setPosition(double[] position) throws IllegalArgumentException{
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
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
	public void advanceTime(float duration) throws IllegalArgumentException,IllegalArgumentException{
		if(duration<0 || duration>= 0.2)
			throw new IllegalArgumentException(); 
		double d = Math.sqrt(Math.pow((getCubeCentre(getTargetPosition())[0]-this.getPosition()[0]),2.0)
				+Math.pow((getCubeCentre(getTargetPosition())[1]-this.getPosition()[1]),2.0)
				+Math.pow((getCubeCentre(getTargetPosition())[2]-this.getPosition()[2]),2.0));
		double[] v = {getCurrentStatus()*(getCubeCentre(getTargetPosition())[0]-this.getPosition()[0])/d,
				getCurrentStatus()*(getCubeCentre(getTargetPosition())[1]-this.getPosition()[1])/d,
				getCurrentStatus()*(getCubeCentre(getTargetPosition())[2]-this.getPosition()[2])/d};
		
		setPosition(new double[] {this.getPosition()[0]+v[0]*duration,this.getPosition()[1]+v[1]*duration,this.getPosition()[2]+v[2]*duration});
	}
	
	public void moveToAdjacent(double[] targetPosition, float duration){
		double[] startPosition = this.getPosition();
		while (afstand(start,targetPosition)-afstand(start,huidig)>0 && notAttacked()) {
			advanceTime(duration);
			huidig = this.getPosition();
		}
		
	}
	

	public double getBaseSpeed(){
		return 1.5*(this.getStrength()+this.getAgility())/(200.0*(this.getWeight()/100.0));
	}
	

/**
 * Return the targetPosition of this unit.
 */
@Basic @Raw
public double[] getTargetPosition() {
	return this.targetPosition;
}

/**
 * Check whether the given targetPosition is a valid targetPosition for
 * any unit.
 *  
 * @param  targetPosition
 *         The targetPosition to check.
 * @return 
 *       | result == canHaveAsTargetPosition(targetPosition)
*/
public boolean canHaveAsTargetPosition(double[] targetPosition) {
	return isValidPosition(targetPosition) && isNeighbouringCube(targetPosition);
}

/**
 * Set the targetPosition of this unit to the given targetPosition.
 * 
 * @param  targetPosition
 *         The new targetPosition for this unit.
 * @post   The targetPosition of this new unit is equal to
 *         the given targetPosition.
 *       | new.getTargetPosition() == targetPosition
 * @throws ExceptionName_Java
 *         The given targetPosition is not a valid targetPosition for any
 *         unit.
 *       | ! canHaveAsTargetPosition(getTargetPosition())
 */
@Raw
public void setTargetPosition(double[] targetPosition) 
		throws IllegalArgumentException {
	if (! canHaveAsTargetPosition(targetPosition))
		throw new IllegalArgumentException();
	this.targetPosition = targetPosition;
}

public boolean isNeighbouringCube(double[] cubePosition) {
	return (Math.abs(cubePosition[0]-this.getCubePosition()[0])<=1) 
			&& (Math.abs(cubePosition[1]-this.getCubePosition()[1])<=1) 
			&& (Math.abs(cubePosition[1]-this.getCubePosition()[1])<=1);
}
	


public double[] getTargetCubePosition(){
	return new double[] {Math.floor(this.getTargetPosition()[0]),Math.floor(this.getTargetPosition()[1]),Math.floor(this.getTargetPosition()[2])};
}

public double[] getCubeCentre(double[] cubePosition) {
	return new double[] {cubePosition[0]+0.5,cubePosition[1]+0.5,cubePosition[2]+0.5};
}

/**
 * Variable registering the targetPosition of this unit.
 */
private double[] targetPosition;

	public double getWalkingSpeed() {
		
		if (Util.fuzzyEquals(getTargetPosition()[2]-this.getPosition()[2],-1.0))
			return 0.5*getBaseSpeed();
		if (Util.fuzzyEquals(getTargetPosition()[2]-this.getPosition()[2],1.0))
			return 1.2*getBaseSpeed();
		return getBaseSpeed();
	}
	
	private boolean isSprinting = false;
	
	public void startSprinting(){
		isSprinting = true;
	}
	
	public void stopSprinting(){
		isSprinting = false;
	}
	
	public double getCurrentStatus() {
		if (isSprinting)
			return 2.0*getWalkingSpeed();
		return getWalkingSpeed();
	}
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
