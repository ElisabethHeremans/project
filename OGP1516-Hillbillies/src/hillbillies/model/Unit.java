package hillbillies.model;

/**
 * A class of Units, with a name, weight, strength, agility, toughness and a position in the game world.
 * 
 * @invar 	Each unit is positioned inside the game world.
 * 			|isValidPosition(this.getPosition())
 * @invar 	Each unit has a name that is a valid name for any unit.
 * 			|isValidName(this.getName())
 * @invar 	Each unit has a valid strength (for any unit).
 * 			|isValidStrength(this.getStrength())
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
	 * 		|new.getName() == name
	 * @throws IllegalNameException
	 * 		   The given name is not a valid name for a unit.
	 *      | ! isValidName(this.getName)
	 * @post The weight of this new Unit is equal to the given weight.
	 * 		|new.getWeight() == weight
	 * @post The strength of this new Unit is equal to the given strength.
	 * 		|new.getStrength() == strength
	 * @post The agility of this new Unit is equal to the given agility.
	 * 		|new.getAgility() == agility
	 * @post The toughness of this new Unit is equal to the given toughness.
	 * 		|new.getToughness() == toughness
	 * @post The position of this new Unit is equal to the given position.
	 * 		|new.getPosition() == position
	 * @throws IllegalPositionException
	 * 			The given position is not a valid position for a unit.
	 * 		| ! isValidPosition(this.getPosition)
	 * @pre The given number of hit-/staminapoints must be a valid number of hit-/staminapoints for the Unit.
	 * 		|canHaveAsPoints(this.getHitpoints()) && canHaveAsPoints(this.getStaminaPoints())
	 * @post The hit-/staminapoints of this new Unit is equal to the given hit-/staminapoints.
	 * @post  If the given orientation is in the range 0..2*PI, the orientation of
	 *         this new unit is equal to the given orientation.
	 *         If the given orientation exceeds 2*PI, the orientation for this new
	 *         unit is equal to the given orientation modulo 2*PI.
	 *         If the given orientation is negative, the orientation for this new
	 *         unit is equal to (2*PI - the given orientation modulo 2*PI). 
	 */
	public Unit(String name, int weight, int strength, int agility, int toughness, double[] position, int hitpoints, int staminapoints,double orientation){
		
		setWeight(weight);
		setStrenght(strenght);
		setAgility(agility);
		setToughness(toughness);
		setOrientation(orientation);
		assert canHaveAsPoints(hitpoints);
		assert canHaveAsPoints(staminapoints);
		this.hitpoints = hitpoints;
		this.staminapoints = staminapoints;
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
	 * A variable registering the strength of this unit.
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
	 * A variabel registering the number of hitpoints of this unit.
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
