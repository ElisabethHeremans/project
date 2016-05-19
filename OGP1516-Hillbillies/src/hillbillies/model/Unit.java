package hillbillies.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.Util;

/**
 * A class of Units, with a name, weight, strength, agility, toughness and a
 * position in the game world.
 * 
 * @invar Each unit is positioned inside the game world.
 *        |isValidPosition(this.getPosition())
 * @invar The name of each unit must be a valid name for any unit.
 *        |isValidName(this.getName())
 * @invar Each unit can have its strength as strength .
 *        |canHaveAsStrength(this.getStrength())
 * @invar Each unit has a valid agility (for any unit).
 *        |isValidAgility(this.getAgility())
 * @invar Each unit has a valid toughness (for any unit).
 *        |isValidToughness(this.getToughness())
 * @invar Each unit can have its weight as its weight.
 *        |canHaveAsWeight(this.getWeight())
 * @invar Each unit can have its number of stamina- and hitpoints as its number
 *        of stamina- and hitpoints. 
 *        |canHaveAsPoints(this.getHitpoints()) && canHaveAsPoints(this.getStaminaPoints())
 * @invar Each unit has an orientation between 0 and 2*PI.
 *        |0<=(this.getOrientation()) <= 2*PI
 * @invar The startPosition is a position inside the game world.
 *        |isValidPosition(startPosition)
 * @invar The targetPosition is a position inside the game world.
 *        |isValidPosition(targetPosition)
 * @invar The nextTargetPosition is a position in a neighbouring cube of this units position, and a position inside the game world.
 * 		  |isValidPosition(nextTargetPosition) && this.isNeighbouringCube(getCubePosition(nextTargetPosition))
 * @invar
 * @version 2.0
 * @author adminheremans
 */
public class Unit {

	/**
	 * Initialize this new unit with a given name, position, weight, strength,
	 * agility, toughness, state of default behaviour, hitpoints, stamina points
	 * and an orientation.
	 * 
	 * @param name
	 *            The name for this new Unit.
	 * @param weight
	 *            The weight for this new Unit.
	 * @param strength
	 *            The strength for this new Unit.
	 * @param agility
	 *            The agility for this new Unit.
	 * @param toughness
	 *            The toughness for this new Unit.
	 * @param enableDefaultBehavior
	 *            The state of default behavior for this new Unit.
	 * @param position
	 *            The position of this new Unit.
	 * @param hitpoints
	 *            The hitpoints for this new Unit.
	 * @param staminaPoints
	 *            The stamina points for this new Unit.
	 * @param orientation
	 *            The orientation of this new Unit.
	 * @effect The name of this new unit is set to the given name. 
	 * 			|this.setName(name)
	 * @post If the given strength is an integer in the range of 25 to 100, the
	 *       strength of this new unit is equal to the given strength.
	 *       Otherwise, the strength of this new unit is equal to 25. 
	 *       | if (25<=strength<=100) 
	 *       | 	then new.getStrength() == strength 
	 *       |if !(25<=strength<=100) 
	 *       |	then new.getStrength() == 25
	 * @post If the given agility is an integer in the range of 25 to 100, the agility
	 *       of this new unit is equal to the given agility. Otherwise, the
	 *       agility of this new unit is equal to 25. 
	 *       | if (25<=agility<=100) 
	 *       | 	then new.getAgility() == agility 
	 *       |if !(25<=agility<=100) 
	 *       |	then new.getAgility() == 25
	 * @post If the given toughness is an integer in the range of 25 to 100, the
	 *       toughness of this new unit is equal to the given toughness.
	 *       Otherwise, the toughness of this new unit is equal to 25. 
	 *       | if (25<=toughness<=100) 
	 *       | 	then new.getToughness() == toughness 
	 *       | if !(25<=toughness<=100) 
	 *       |	then new.getStrength() == 25
	 * @post If the given weight is an integer in the range of 25 to 100 
	 * 		 and at least this Unit's (strength+agility)/2, the weight of
	 *       this new Unit is equal to the given weight. Otherwise, the weight
	 *       of this new Unit is equal to 25. 
	 *       	| if (25<= weight <=100) && (this.getStrength+this.getAgility)/2 <= weight)
	 *       	|	then new.getWeight() == weight 
	 *       	| if !(25<= weight <=100) || !(this.getStrength+this.getAgility)/2 <= weight)
	 *       	|	new.getWeight() == (int) Math.ceil((this.getStrength()+this.getAgility())/2.0);
	 * @effect The position of this new unit is set to the given position. 
	 * 			|this.setPosition(position)
	 * @effect The hitpoints of this new unit is set to the given number of hitpoints. 
	 * 			|this.setHitpoints(hitpoints)
	 * @effect The the number of stamina points of this new unit is set to the
	 *       	given number of stamina points.
	 *       	|this.setStaminaPoints(staminaPoints)
	 * @effect The orientation of this new unit is set to the given orientation.
	 *         | setOrientation(orientation)
	 * @effect The default behavior of this new unit is set to the given value of enableDefaultBehaviour
	 *         | setEnableDefaultBehavior(enableDefaultBehavior)
	 */
	@Raw
	public Unit(String name, double[] position, int weight, int strength, int agility, int toughness,
			boolean enableDefaultBehavior, double hitpoints, double staminaPoints, double orientation)
					throws IllegalArgumentException {
		this.setName(name);
		if (!(25<=strength&&strength<=100))
			strength = 25;
		setStrength(strength);
		if (!(25<=agility && agility<=100))
			agility = 25;
		setAgility(agility);
		if(! (25<=toughness && toughness<=100))
			toughness = 25;
		setToughness(toughness);
		if (!(25<=weight && weight<=100 && (this.getStrength()+this.getAgility())/2 <= weight))
			weight = (int) Math.ceil((this.getStrength()+this.getAgility())/2.0);
		
		setWeight(weight);
		setOrientation((float) orientation);
		this.setHitPoints(hitpoints);
		this.setStaminaPoints(staminaPoints);
		this.setPosition(position);
		this.setEnableDefaultBehaviour(enableDefaultBehavior);
	}
	/**
	 * Initialize this new unit with a given name, position, weight, strength,
	 * agility, toughness, state of default behavior.
	 * @param name
	 * 		The name for this new unit.
	 * @param position
	 * 		The position for this new unit.
	 * @param weight
	 * 		The weight for this new unit.
	 * @param strength
	 * 		The strength for this new unit.
	 * @param agility
	 * 		The agility for this new unit.
	 * @param toughness
	 * 		The toughness for this new unit.
	 * @param enableDefaultBehavior
	 * 		The state of default behavior for this new Unit.
	 * @effect This new unit is initialized with the given name as its name,
	 * 		   the given position as its position, the given weight as its weight,
	 * 		   the given strength as its strength, the given agility as its agility,
	 * 		   the given toughness as its toughness and the given state of default behavior
	 * 		   as its state of default behavior, and with 0.0 as its stamina- and hitpoints, and PI/2 as its orientation.
	 * 		   | this(name, position, weight, strength, agility, toughness, enableDefaultBehavior, 0.0, 0.0,
	 * 		   |	float) Math.PI / 2.0)
	 */
	@Raw
	public Unit(String name, double[] position, int weight, int strength, int agility, int toughness,
			boolean enableDefaultBehavior) throws IllegalArgumentException{
		this(name, position, weight, strength, agility, toughness, enableDefaultBehavior, 
				Math.ceil(200.0 * (weight / 100.0) * (toughness / 100.0)),Math.ceil(200.0 * (weight / 100.0) * (toughness / 100.0)),
				(float) Math.PI / 2.0);
	}
	
	/**
	 * Return the weight of this Unit.
	 */
	@Basic
	@Raw
	public int getWeight() {
		return this.weight;
	}

	/**
	 * Check whether this unit can have the given weight as its weight.
	 * 
	 * @param Weight
	 *            The weight to check.
	 * @return  True if and only if the weight is equal to or greater than half of
	 * 			the unit's strength increased with his agility, and a number from 1 to 200 if
	 * 			this unit is not carrying a log or boulder, otherwise his weight may exceed 200 with
	 * 			the weight of the log or boulder.
	 * 			| result == (weight >= (this.getStrength() + this.getAgility())/2 && weight >=1 && weight <=200)
	 * 			| OR if (this.getLog() != null)
	 * 			|	then result == (weight >= (( this.getStrength() + this.getAgility()) / 2) 
	 *			|		&& (weight >=1 && weight <=200+this.getLog().getWeight())
	 *			| OR if (this.getBoulder() != null)
	 *			|	then result == (weight >= (this.getStrength() + this.getAgility()) / 2) 
	 *			|		&& (weight >=1 && weight <=200+this.getBoulder().getWeight())
	 */
	@Raw
	public boolean canHaveAsWeight(int weight) {
			if( (this.getLog() == null && this.getBoulder() == null)){
				return (((float) weight >= ((float) this.getStrength() + (float) this.getAgility()) / 2) 
						&& weight >=1 && weight <=200);
				}
			else if(this.getLog() != null){
				return (((float) weight >= ((float) this.getStrength() + (float) this.getAgility()) / 2) 
						&& weight >=1 && weight <=200+this.getLog().getWeight());
				}
			else if(this.getBoulder() != null){
				return (((float) weight >= ((float) this.getStrength() + (float) this.getAgility()) / 2) 
						&& weight >=1 && weight <=200+this.getBoulder().getWeight());
			}
			else{
				return false;
			}
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post If the given weight is not a valid weight for any unit, the weight
	 *       of this new unit is equal to the weight of this unit.
	 *       |if(!canHaveAsWeight(weight))
	 *       | then new.getWeight() == this.getWeight()
	 */
	@Raw
	public void setWeight(int weight) {
		if (canHaveAsWeight(weight))
			this.weight = weight;
		else{
			this.weight = (int) Math.ceil((this.getStrength()+this.getAgility())/2);
		}
	}

	/**
	 * A variable registering the weight of this unit.
	 */
	private int weight = 25;
	
	/**
	 * Return the strength of this unit.
	 */
	@Basic
	@Raw
	public int getStrength() {
		return this.strength;
	}

	/**
	 * Check whether the given strength is a valid strength for any unit.
	 * 
	 * @param strength
	 *            The strength to check.
	 * @return  True if and only if the unit's strength is greater than or equal to 1,
	 * 			but less than or equal to 200.
	 * 			| result == ((1<= strength) && (strength<=200))
	 */
	@Raw
	public static boolean isValidStrength(int strength) {
		return (1 <= strength) && (strength <= 200);
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength. 
	 *       | if (isValidStrength(strength)) 
	 *       | 	then new.getStrength() == strength
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
	}
	/**
	 * Variable registering the strength of this unit.
	 */
	private int strength = 25;
	
	/**
	 * Return the agility of this unit.
	 */
	@Basic
	@Raw
	public int getAgility() {
		return this.agility;
	}

	/**
	 * Check whether the given agility is a valid agility for any unit.
	 * 
	 * @param agility
	 *            The agility to check.
	 * @return  True if and only if the unit's agility is greater than or equal to 1,
	 * 			but less than or equal to 200.
	 * 			| result == ((1<=agility) && (agility<=200))
	 */
	@Raw
	public static boolean isValidAgility(int agility) {
		return ((1 <= agility) && (agility <= 200));
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post If the given agility is a valid agility for any unit, the agility
	 *       of this new unit is equal to the given agility. 
	 *       | if (isValidAgility(agility)) 
	 *       | then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
	}

	/**
	 * A variable registering the agility of this unit.
	 */
	private int agility = 25;
	
	/**
	 * Return the toughness of this unit.
	 */
	@Basic
	@Raw
	public int getToughness() {
		return this.toughness;
	}

	/**
	 * Check whether the given toughness is a valid toughness for any unit.
	 * 
	 * @param toughness
	 *            The toughness to check.
	 * @return  True if and only if the unit's toughness is greater than or equal to 1,
	 * 			but less than or equal to 200.
	 * 			| result == ((1<=toughness) && (toughness<=200))
	 */
	public static boolean isValidToughness(int toughness) {
		return ((1 <= toughness) && (toughness <= 200));
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post If the given toughness is a valid toughness for any unit, the
	 *       toughness of this new unit is equal to the given toughness.
	 *       | if (isValidToughness(toughness)) 
	 *       | 	then new.getToughness() == toughness
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
	}

	/**
	 * A variable registering the toughness of this unit.
	 */
	private int toughness = 25;
	
	/**
	 * Return the name of this unit.
	 */
	@Basic
	@Raw
	public String getName() {
		return this.name;
	}

	/**
	 * Check whether the given name is a valid name for any unit.
	 * 
	 * @param name
	 *            The name to check.
	 * @return True if and only if the given name is at least two characters long, 
	 * 		   starts with an uppercase letter and only consists of letters, 
	 * 		   quotes and spaces. 
	 * 		   | result == (!name.length()<2 && Character.isUpperCase(name.charAt(0)) 
	 * 		   | 			&& for (int index = 0; index < name.length(); index++)(Character.isLetter(name.charAt(index)) ||
	 *         | 				name.charAt(index)=='"' || name.charAt(index)=='\'' ||
	 *         | 				name.charAt(index)==' '))
	 */
	public static boolean isValidName(String name) {
		if (name.length() < 2)
			return false;
		if (!Character.isUpperCase(name.charAt(0)))
			return false;
		for (int index = 0; index < name.length(); index++) {
			if (!Character.isLetter(name.charAt(index)) && (!(name.charAt(index) == '"'))
					&& (!(name.charAt(index) == '\'')) && (!(name.charAt(index) == ' ')))
				return false;
		}
		return true;

	}

	/**
	 * Set the name of this unit to the given name.
	 * 
	 * @param name
	 *            The new name for this unit.
	 * @post The name of this new unit is equal to the given name. 
	 * 		 | new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for any unit. 
	 *             | !isValidName(getName())
	 */
	@Raw
	public void setName(String name) throws IllegalArgumentException {
		//System.out.println(name);
		if (!isValidName(name)){
			//System.out.println("invalid " + name);
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * A variable registering the name of this unit.
	 */
	private String name = "Unit";
	
	/**
	 *Return the number of hitpoints of this unit.
	 */
	@Basic
	@Raw
	public double getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * Check whether this unit can have the given hitpoints as its hitpoints.
	 * 
	 * @param hitpoints
	 *            The hitpoints to check.
	 * @return  True if and only if the number of hitpoints is greater than or equal to 0,
	 * 			but less than or equal to the maximum value.
	 * 			| result == (hitpoints <= this.getMaxPoints() && 0<=hitpoints)
	 */
	@Raw
	public boolean canHaveAsHitpoints(double hitpoints) {
		return (Util.fuzzyLessThanOrEqualTo(hitpoints,getMaxPoints(),Util.DEFAULT_EPSILON)
					&& 0<=hitpoints);
	}

	/**
	 * Return the maximum value of hitpoints and stamina points of this unit.
	 * 
	 * @return The maximum value of hitpoints and staminaPoints. 
	 * 		   | result == (Math.ceil(200.0*(this.getWeight()/100.0)*
	 * 		   | (this.getToughness()/100.0)))
	 */
	public double getMaxPoints() {
		return Math.ceil(200.0 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0));
	}

	/**
	 * Set the the number of hitpoints of this unit to the given number of
	 * hitpoints.
	 * 
	 * @param hitpoints
	 *            The new the number of hitpoints for this unit.
	 * @pre This unit can have the given hitpoints as its hitpoints.
	 *      | canHaveAsHitpoints(hitpoints)
	 * @post The the number of hitpoints of this new unit is equal to the given the
	 *       number of hitpoints. 
	 *      | new.getHitpoints() == hitpoints
	 */
	@Raw
	public void setHitPoints(double hitpoints) {
		assert canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
	}

	/**
	 * A variable registering the number of hitpoints of this unit.
	 */
	private double hitpoints = 0;

	/**
	 * Return the number of staminaPoints of this unit.
	 */
	@Basic
	@Raw
	public double getStaminaPoints() {
		return this.staminaPoints;
	}

	/**
	 * Check whether this unit can have the given number of stamina points as its stamina points.
	 * 
	 * @param staminaPoints
	 *           The number of stamina points.
	 * @return  True if and only if the number of stamina points is greater than or equal to 0,
	 * 			but less than or equal to the maximum number of points for this unit.
	 * 			| result == (staminaPoints <= this.getMaxPoints() && 0<= staminaPoints)
	 */
	public boolean canHaveAsStaminaPoints(double staminaPoints) {
		return (Util.fuzzyLessThanOrEqualTo(staminaPoints,getMaxPoints(),Util.DEFAULT_EPSILON)
				&& 0<= staminaPoints);
	}

	/**
	 * Set the the number of stamina points of this unit to the given number
	 * of stamina points.
	 * 
	 * @param staminaPoints
	 *            The new number of stamina points for this unit.
	 * @pre This unit can have the given number of stamina as its stamina points. 
	 *      | canHaveAsStaminaPoints(staminaPoints)
	 * @post The number of stamina points of this new unit is equal to the given
	 *       number of stamina points. 
	 *       | new.getStaminaPoints() == staminaPoints
	 */
	@Raw
	private void setStaminaPoints(double staminaPoints) {
		assert canHaveAsStaminaPoints(staminaPoints);
		this.staminaPoints = staminaPoints;
	}

	/**
	 * A variable registering the number of stamina points of this unit.
	 */
	private double staminaPoints = 0;

	/**
	 * Return the orientation of this unit.
	 */
	@Basic
	@Raw
	public float getOrientation() {
		return this.orientation;
	}

	/**
	 * Set the orientation of this unit to the given orientation.
	 * 
	 * @param orientation
	 *            The new orientation for this unit.
	 * @post If the given orientation is in the range 0..2*PI, the orientation
	 *       of this new unit is equal to the given orientation. 
	 *       | if (0 <= orientation <= 2*PI) 
	 *       | 	then new.orientation = orientation
	 * @post If the given orientation exceeds 2*PI, the orientation for this new
	 *       unit is equal to the given orientation modulo 2*PI. 
	 *       | if (orientation > 2*PI) 
	 *       | 	then new.orientation = orientation % 2*PI
	 * @post If the given orientation is negative, the orientation for this new
	 *       unit is equal to (2*PI + the given orientation modulo 2*PI). 
	 *       | if (orientation < 0) 
	 *       | 	then new.orientation = 2*PI + orientation % (2*PI)
	 */
	@Raw
	public void setOrientation(float orientation) {
		if (orientation >= 0.0)
			this.orientation = (float) (orientation % (2 * Math.PI));
		else
			this.orientation = (float) (2 * Math.PI + orientation % (2 * Math.PI));
	}

	/**
	 * A variable registering the orientation of this unit.
	 */
	private float orientation = (float) ((float) Math.PI / 2.0);

	/**
	 * Return the position of this unit. The position consists of an x, y and
	 * z-coordinate.
	 * @return the unit's position.
	 * 		| result == this.position
	 */
	@Basic
	@Raw
	public double[] getPosition() {
		return this.position;
	}
	
	/**
	 * Check whether the given position is a position inside the game world and is a
	 * valid position.
	 * 
	 * @param position
	 * 			The position to check.
	 * @return True if the unit is not associated with an effect world.
	 * 		   | if (this.getWorld() == null)
	 * 		   |	then return true
	 * @return Else, true if and only if the x,y and z-coordinate of the position are
	 *         inside the limits of the game world and the terrain is passable.
	 *         |result == (0<= position[0])
	 *         | if(this.getWorld() != null)
	 *         | 	then return (getWorld().isCubeInWorld(this.getWorld().getCubeCoordinate(position))
	 *         | 	&& (0<= position[2]) && (position[2] <= Z)getWorld().getPassable(this.getWorld().getCubeCoordinate(position)))
	 */
	public boolean canHaveAsPosition(double[] position) {
		if (this.getWorld()!= null){
			return (getWorld().isCubeInWorld(this.getWorld().getCubeCoordinate(position)) 
				&& getWorld().getPassable(this.getWorld().getCubeCoordinate(position)));
		}
		else
			return true;
		
		
	}
	
	/** 
	 * Return the position of the game world cube in which this unit is
	 * positioned, as an array of doubles.
	 * 
	 * @return The position of the game world cube in which this unit is
	 *         positioned, which is an array of doubles containing x,y and z rounded down to integer numbers.
	 *         | result == new double[] {Math.floor(this.getPosition()[0]),
	 *         | Math.floor(this.getPosition()[1]),Math.floor(this.getPosition()[2])}
	 */
	protected double[] getCubePosition() {
		return new double[] { Math.floor(this.getPosition()[0]), Math.floor(this.getPosition()[1]),
				Math.floor(this.getPosition()[2]) };
	}
	/**
	 * Return the position of the game world cube in which this unit is
	 * positioned, as an array if integers.
	 * 
	 * @effect The position of the game world cube in which this unit is positioned,
	 * 		   as an array of integers containing x,y and z rounded down to integer numbers.
	 * 			| result == new int[] { (int) getCubePosition()[0], (int) getCubePosition()[1], (int) getCubePosition()[2] }
	 */
	public int[] getCubeCoordinate() {
		return new int[] { (int) getCubePosition()[0], (int) getCubePosition()[1], (int) getCubePosition()[2] };
	}

	
	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param position
	 *            The new position for this unit.
	 * @post The position of this new unit is equal to the given position.
	 *       |new.getPosition() == position
	 * @throws IllegalArgumentException
	 *             The given position is not a valid position for any unit.
	 *             |!isValidPosition(position)
	 */
	@Raw
	public void setPosition(double[] position) throws IllegalArgumentException {
		
		if (!canHaveAsPosition(position))
			throw new IllegalArgumentException();
		if (this.getWorld()!=null)
			this.getWorld().removeUnitFromUnitsAtCubeMap(this);
		this.position = position;
		if (this.getWorld()!=null)
			this.getWorld().addUnitToUnitsAtCubeMap(this);

	}

	/**
	 * Check whether the given cube is neighboring this unit.
	 * 
	 * @param position
	 * 		The position of a cube in the game world. 
	 * @return True if and only if the cube where this unit is positioned inside the game world is neighboring this position.
	 * 		   | result == (isNeighbouringCube(this.getWorld().getCubeCoordinate(position)))
	 */
	private boolean isNeighbouringCube(double[] position) {
		return (isNeighbouringCube(this.getWorld().getCubeCoordinate(position)));
	}
	/**
	 * Check whether the given cube is neighboring this unit.
	 * @param cubePosition
	 * 		The cube to check.
	 * @return True if and only if the difference between the respective x, y and z -coordinates of the cubeCenters are equal to 0, 1 or -1,
	 * 			and the difference between the respective x,y and z coordinates are not all 0.
	 * 			| result == (Math.abs(cubePosition[0]- unitpos[0])<=1
	 *	   	    | 			&&Math.abs(cubePosition[1]- unitpos[1])<=1
	 *		    |			&&Math.abs(cubePosition[2]- unitpos[2])<=1
	 *	        |			&& !(cubePosition[0] ==unitpos[0] && cubePosition[1]==unitpos[1]&& cubePosition[2]==unitpos[2]));
	 */
	public boolean isNeighbouringCube(int[] cubePosition){
		int[] unitpos = this.getWorld().getCubeCoordinate(this.getPosition());
		return (Math.abs(cubePosition[0]- unitpos[0])<=1
				&&Math.abs(cubePosition[1]- unitpos[1])<=1
				&&Math.abs(cubePosition[2]- unitpos[2])<=1
				&& !(cubePosition[0] ==unitpos[0] && cubePosition[1]==unitpos[1]&& cubePosition[2]==unitpos[2]));
	}
	/**
	 * Checks whether the given cube is neighboring this unit or 
	 * 	has the same cubeco�rdinates as this unit.
	 * @param cubePosition
	 * 		The cube to check.
	 * @return True if and only if the difference between the respective x, y and z -coordinates of the cubeCenters are equal to 0, 1 or -1.
	 * 			| result == (Math.abs(cubePosition[0]- unitpos[0])<=1
	 *			|	&&Math.abs(cubePosition[1]- unitpos[1])<=1
	 *			|	&&Math.abs(cubePosition[2]- unitpos[2])<=1)
	 */
	public boolean isNeighbouringOrSameCube(int[] cubePosition){
		int[] unitpos = this.getWorld().getCubeCoordinate(this.getPosition());
		return (Math.abs(cubePosition[0]- unitpos[0])<=1
				&&Math.abs(cubePosition[1]- unitpos[1])<=1
				&&Math.abs(cubePosition[2]- unitpos[2])<=1);
	}
	
	/**
	 * A variable registering the position of this unit.
	 */
	private double[] position = {0.5,0.5,0.5};
	
	/**
	 * Return the status of this unit.
	 */
	@Basic
	public Status getStatus() {
		return status;
	}
	/**
	 * Set the status of this unit to the given status.
	 * @param status
	 * 		The new status for this unit.
	 * @post The status of this unit is equal to the given status.
	 * 		| new.getStatus() == status
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/**
	 * Set the faction attached to this unit to the given faction.
	 * @param faction
	 * 		The faction to be attached to this unit.
	 * @pre If the given faction is effective, it must already reference
	 * 		this unit as one of the units to which it is attached.
	 * 		| assert (faction.hasAsUnit(this))
	 * @post This unit references the given faction as the faction attached to it.
	 * 		| new.getFaction() == faction
	 */
	public void setFaction(@Raw Faction faction){
		if (faction !=null)
			assert world.hasAsUnit(this);
		this.faction = faction;
	}
	/**
	 * Check whether this unit has a proper faction attached to it.
	 * @return True if and only if this unit does not reference an effective faction
	 * 		or if the faction referenced by this unit in turn references this unit as 
	 * 		one of the units to which it is attached.
	 * 		| result == (this.getFaction() == null || this.getFaction().hasAsUnit(this))
	 */
	@Raw
	public boolean hasProperFaction(){
		return (this.getFaction() == null || this.getFaction().hasAsUnit(this));
	}
	/**
	 * Return the faction of this unit.
	 */
	@Basic @Raw
	public Faction getFaction(){
		return this.faction;
	}
	/**
	 * Variable referencing the faction of this unit.
	 */
	private Faction faction;
	

	/**
	 * Set the world attached to this unit to the given world.
	 * @param world
	 * 		The world to be attached to this unit.
	 * @pre If the given world is effective, it must already reference
	 * 		this unit as one of the units to which it is attached.
	 * 		| assert (world.hasAsUnit(this))
	 * @post This unit references the given world as the world attached to it.
	 * 		| new.getWorld() == world
	 */
	public void setWorld(@Raw World world){
		if (world !=null)
			assert world.hasAsUnit(this);
		this.world = world;
	}
	
	
	/**
	 * Check whether this unit has a proper world attached to it.
	 * @return True if and only if this unit does not reference an effective world
	 * 		or if the world referenced by this unit in turn references this unit as 
	 * 		one of the units to which it is attached.
	 * 		| result == (this.getWorld()== null) || (getWorld().hasAsUnit(this))
	 */
	@Raw
	public boolean hasProperWorld(){
		return (this.getWorld()== null) || (getWorld().hasAsUnit(this));
	}
	/**
	 * Return the world of this unit.
	 */
	@Basic @Raw
	public World getWorld(){
		return world;
	}
	/**
	 * Variable referencing the world of this unit.
	 */
	private World world;
	/**
	 * Update the position and activity status of a unit.
	 * 
	 * @param duration
	 *         The game time after which advanceTime is called.
	 * @effect If the experience points of this unit are greater than or equal to ten,
	 * 			his strength or his agility or his toughness is increased by 1
	 * 			and his experience points are decreased by 10.
	 * @effect If the hitpoints of this unit are less than or equal to zero,
	 * 			this unit is terminated.
	 * @effect If this unit must fall and is not already falling, this unit shall fall.
	 * @effect If the status of this unit is falling, the unit will continue falling.
	 * @effect If this unit must rest, this unit shall rest.
	 * @effect If this unit is doing nothing, while default behaviour is not enabled, and the unit still has a
	 *         targetPosition, this unit shall resume moving to the targetPosition.
	 * @effect If this unit's default behaviour is enabled, and this unit is
	 *         doing nothing, he shall start a default behaviour.
	 * @effect If this unit is moving, this unit shall continue moving.
	 * @effect If this unit is working, he will continue working.
	 * @effect If the status of this unit is initial resting, this unit will continue initial resting.
	 * @effect If the status of this unit is resting, this unit will continue resting.
	 * @effect If this unit's status is attacking, this new unit's attackTimer will be increased with duration and if the attackTimer becomes 
	 * 			equal or greater then one, this new unit's status will be updated to done.
	 * @effect If this unit is carrying a boulder, the position of the boulder equals
	 *			the position of this unit.
	 * @effect If this unit is carrying a log, the position of the log equals
	 *			the position of this unit. 
	 * @throws IllegalArgumentException
	 *             If the duration is less than zero or exceeds or equals 0.2 s.
	 */
	public void advanceTime(float duration) throws IllegalArgumentException {
		System.out.println("-----------------------------------ADVANCETIME UNIT----------------------------------");
//		System.out.print(" 1: "+ this.getExperiencePoints());
//		System.out.print(" 2 " + this.getHitpoints());
		if (!(Util.fuzzyGreaterThanOrEqualTo(duration, 0.0-Util.DEFAULT_EPSILON )&& Util.fuzzyLessThanOrEqualTo((double)duration, 0.2+Util.DEFAULT_EPSILON))){
			System.out.println(duration);
			throw new IllegalArgumentException();
		}
		restTimer += duration;
		taskTimer += duration;
		if (this.isExecutingStatement)
			taskTimer += duration;
		
		if (experiencePoints >=10){
			setExperiencePoints(this.getExperiencePoints()-10);
			if (isValidStrength(this.getStrength()+1) && this.getWeight()>=((this.getStrength()+1)+this.getAgility())/2 )
				this.setStrength(this.getStrength()+1);
			else if (isValidAgility(this.getAgility()+1) && this.getWeight()>=(this.getStrength()+(this.getAgility()+1))/2)
				this.setAgility(this.getAgility()+1);
			else if (isValidToughness(this.getToughness()+1))
				this.setToughness(this.getToughness()+1);
		}
		if (this.getHitpoints() <= 0)
			this.terminate();
		if (this.getStatus() != Status.FALLING && mustFall()){
			fall();
		}
		if (this.getStatus() == Status.FALLING){
			falling(duration);
		}
		//System.out.println(" test 1 "+ (this.isExecutingTask  && !isExecutingStatement));
		if (this.isExecutingTask){
			if ( !isExecutingStatement){
			System.out.println(" execute task ");
			executeProgram(duration);
			}
		}
		if (this.isExecutingTask &&this.getTask() != null){
			System.out.println("EXECUTING TASK");
			System.out.println("STATUS =" +getStatus());
		}
		if (this.isFollowing() != null) {
			if(this.isNeighbouringOrSameCube(this.isFollowing().getCubeCoordinate())){
				this.stopFollowing();
			}
			else{
				if(this.isFollowing().getStatus()==status.FALLING){
				}	
				else
					moveTo1(this.isFollowing().getPosition());
			}
		}
		//System.out.println(" UIT FOLLOW ");
//		System.out.print(Arrays.toString(targetPosition));
//		System.out.print(this.isEnableDefaultBehaviour());
//		System.out.print(this.getStatus());
		if (mustRest()){
			rest();
			System.out.println(" RESTING ");
		}
		else if (this.getStatus() == Status.DONE && targetPosition != null && !this.isEnableDefaultBehaviour()){
			moveTo1(targetPosition);
			//System.out.println(" MOVING ");

		}
		
		else if (this.isEnableDefaultBehaviour() && this.getStatus() == Status.DONE){
			
			startDefaultBehaviour();
			System.out.println(" DEFAULT ");
		}
		else if (this.getStatus() == Status.MOVING) {
			moving1(duration);
			System.out.println("MOVING ");
		}
		
		else if (this.getStatus() == Status.WORKING) {
			working(duration);
			//System.out.print(" 4.5 ");
		} 
		else if (getStatus() == Status.INITIAL_RESTING) {
			initialResting(duration);
		}
		else if (getStatus() == Status.RESTING) {
			resting(duration);
		} 
		else if (getStatus() == Status.ATTACKING) {
			attackTimer += duration;
			if (attackTimer >= 1.0)
				setStatus(Status.DONE);
		}
		//System.out.print(" 4 ");
		if (this.getLog() != null)
			this.getLog().setPosition(this.getPosition());
		if (this.getBoulder() != null)
			this.getBoulder().setPosition(this.getPosition());

	}
	
	public void stopExecutingStatement(){
		this.isExecutingStatement = false;
		this.getTask().removeFirstStatement();
		System.out.println(" stopped executing statement ");
		System.out.println(getStatus());
	}
	
	private void executeProgram(float duration) {
		taskTimer = 0.001;
		if (!this.getTask().isComplete()){
			System.out.println(" not complete ");
			if (taskTimer > duration){
				this.isExecutingStatement = true;
				this.getTask().executeTask();
			}
			else{
				while (taskTimer < duration && !isExecutingStatement && !this.getTask().isComplete()){
					System.out.println(" entering this loop ");
					this.isExecutingStatement = true;
					System.out.println(this.getTask());
					this.getTask().executeTask();
					taskTimer += 0.001;
				}
			}
		}
		if (this.getTask().isComplete()
				){
			
			this.isExecutingTask = false;
			this.getFaction().getScheduler().removeAsTask(this.getTask());
			//System.out.println(" complete1 ");
			System.out.println(" executed "+ getTask().toString());
			System.out.println(getTask());
			this.getTask().setExecutingUnit(null);
			System.out.println(" complete ");
			this.setStatus(Status.DONE);
			System.out.println(" complete ");
		}
	}
	
	/**
	 * Make the unit fall.
	 * 
	 * @post This unit's new status will be equal to falling.
	 * 		| setStatus(Status.FALLING)
	 * @post The startposition of this unit is the position of this unit and 
	 * 		the next target position is one z-level lower than the position of this unit.
	 * 		| this.nextTargetPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0})
	 * 		| && this.startPosition = this.getPosition()
	 */ 
	private void fall() {
		setStatus(Status.FALLING);
		this.nextTargetPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
		this.startPosition = this.getPosition();
	}
	/**
	 * Check whether this unit needs to fall.
	 * @return False if the unit is terminated or the z-coordinate of this unit is equal to zero or 
	 * 		there is a neighboring cube that is not passable. Otherwise, return true.
	 * 		result == (this.getCubeCoordinate()[2]!=0 
	 * 				&& !this.getWorld().getTerrain(neighboringCubes).isPassable() && ! this.isTerminated())
	 */
	public boolean mustFall() {
		if ( isTerminated()||(this.getCubeCoordinate()[2]==0)){
			return false;
		}
		for (int[] cube: this.getWorld().getNeighboringCubes(this.getCubeCoordinate())){
			if (!this.getWorld().getTerrain(cube).isPassable())
				return false;
		}

		return true;
	}
	
	/**
	 * The unit is falling.
	 * @param duration
	 * 		The game time after which falling is called.
	 * @effect The position of this unit is updated.
	 * 		| setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)))
	 * @effect If the unit arrived or passed the targetposition,  his hitpoints are decreased and 
	 * 		his position is set at the targetposition. 
	 * 		| if (Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0)
	 * 		|	then setPosition(nextTargetPosition) && setHitPoints(this.getHitpoints() - 10)
	 * @effect And if the position a z-level lower than the units position is the lowest z-level or
	 * 		the terrain is not passable, the status is set to done. Otherwise, the unit keeps falling.
	 * 			| if (this.getCubeCoordinate()[2]==1.0 || !this.getWorld().getTerrain(nextPosition).isPassable())
	 * 			| 	then setStatus(Status.DONE)
	 * 			| else then fall()
	 * 
	 */
	private void falling(double duration){
		double[] v = new double[] {0.0,0.0,-3.0};
		setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
		if (Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
			setPosition(nextTargetPosition);
			setHitPoints(this.getHitpoints() - 10);
			double[] nextPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
			if (this.getCubeCoordinate()[2]==0.0 || !this.getWorld().getTerrain(nextPosition).isPassable()){
				setStatus(Status.DONE);
			}
			else
				fall();
		}
	}
	


	/**
	 * Return the base speed of the unit.
	 * @return the base speed of the unit.
	 * 		| result == 1.5 * (this.getStrength() + this.getAgility()) / (200.0 * (this.getWeight() / 100.0))
	 */
	public double getBaseSpeed() {
		return 1.5 * (this.getStrength() + this.getAgility()) / (200.0 * (this.getWeight() / 100.0));
	}
	
	/**
	 * Return the current speed of the given unit.
	 * 
	 * @return The speed at which the unit is moving is 0.0 if the unit isn't moving. 
	 * 			| if (status != Status.MOVING){ 
	 * 			| 	then result == 0.0 
	 * @return The speed at which the unit is moving is 2 times the walking speed if the unit is sprinting.
	 * 			| if (status == Status.MOVING && isSprinting()) 
	 * 			| 	then result == 2.0*getWalkingSpeed() 
	 * @return The speed at which the unit is moving is the walking speed if the unit is walking.
	 * 			| if (status == Status.MOVING && !isSprinting()) 
	 * 			| 	then result == getWalkingSpeed();
	 * @return The speed at which the unit is moving is 3.0 if the unit is falling.
	 * 			| if (this.getStatus() == Status.FALLING)
	 * 			|	then result == 3.0
	 */
	public double getCurrentSpeed() {
		if (this.getStatus() == Status.MOVING) {
			if (isSprinting())
				return 2.0 * getWalkingSpeed();
			return getWalkingSpeed();
		}
		if (this.getStatus() == Status.FALLING)
			return 3.0;
		else{
			return 0.0;
		}
	}
	
	/**
	 * Check whether it's possible for a unit to move.
	 * 
	 * @return True only if the unit is currently resting or doing nothing or 
	 * 			is moving and currently in the center of a cube or is falling.
	 * 		   | result == (status == Status.RESTING || status == Status.DONE 
	 * 		   |	|| status == Status.IN_CENTER || status == Status.FALLING)
	 */
	public boolean canMove() {
		//System.out.print(this.getStatus().toString());
		if (this.getStatus() == Status.RESTING || this.getStatus() == Status.DONE || this.getStatus() == Status.IN_CENTER || this.getStatus() == Status.FALLING)
			return true;
		return false;
	}

	
	/**
	 * Enable sprinting mode for the given unit.
	 * 
	 * @post If the unit is currently moving and the unit's stamina points are greater then zero,
	 * 		 this new unit is sprinting.
	 * 		 | if (status == Status.MOVING && getStaminaPoints() > 0)
	 * 		 | 	then new.isSprinting() == true
	 * @post If the unit is not moving or its stamina is zero, this new unit is not sprinting.
	 * 		 |if !(status == Status.MOVING && getStaminaPoints() > 0)
	 * 		 |	then new.isSprinting() == false
	 */
	public void startSprinting() {
		if (this.getStatus() == Status.MOVING && getStaminaPoints() > 0)
			isSprinting = true;
		else
			isSprinting = false;
	}

	/**
	 * Disable sprinting mode for the given unit.
	 * 
	 * @post This unit stops sprinting.
	 * 		 | new.isSprinting() == false
	 */
	public void stopSprinting() {
		isSprinting = false;
	}
	
	/**
	 * Check if the unit is sprinting.
	 * @return the value of isSprinting
	 * 			| result == isSprinting
	 */
	public boolean isSprinting(){
		return isSprinting;
	}

	/**
	 * A boolean to register if the unit is sprinting.
	 */
	private boolean isSprinting = false;

	/**
	 * Start moving a unit to the center of a neighboring cube.
	 * 
	 * @param dx
	 * 		The x-direction to which the cube has to move. 		
	 * @param dy
	 * 		The y-direction to which the cube has to move.
	 * @param dz
	 * 		The z-direction to which the cube has to move.
	 * @post If the unit can move, this new unit's startPosition is this unit's current position and
	 * 			his new nextTargetPosition is the center of the neighboring cube to which this unit
	 * 			has to move. This new unit's status is moving.
	 * 		 | if (canMove())
	 * 		 |	then new.startPosition == new double[] {this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]}
	 * 		 |		&& new.nextTargetPosition == getCubeCenter(new double[] {this.getCubePosition()[0] + (double) dx ,
	 * 		 |		   this.getCubePosition()[1] + (double) dy , this.getCubePosition()[2] + (double) dz })
	 * 		 |		&& new.status == Status.MOVING
	 * @effect If the unit can move, the walkingSpeed of this unit is set to the given flag. 
	 * 			| if (canMove())
	 * 			| 	then setWalkingspeed(dz)
	 * @throws IllegalArgumentException
	 * 			If the unit needs to move more than one cube in the x-, y- or z-direction.
	 * 			| !((dx >= -1 && dx <= 1) && (dy >= -1 && dy <= 1) && (dz >= -1 && dz <= 1))
	 * @throws IllegalArgumentException
	 * 			If the unit needs to move in a direction outside the game world or to a not passable cube.
	 * 			| !canHaveAsPosition(new double[] { getPosition()[0] + (double) dx, getPosition()[1] + (double) dy,
					getPosition()[2] + (double) dz })
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {
		if (!((dx >= -1 && dx <= 1) && (dy >= -1 && dy <= 1) && (dz >= -1 && dz <= 1))){
			//System.out.print("move to adj problem");
			throw new IllegalArgumentException();
		}
		if (!canHaveAsPosition(new double[] { getPosition()[0] + (double) dx, getPosition()[1] + (double) dy,
				getPosition()[2] + (double) dz }))
			throw new IllegalArgumentException();
		if (canMove()) {
			//System.out.print("can move");
			startPosition = new double[] {this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]};
			nextTargetPosition = this.getWorld().getCubeCenter(new double[] {this.getCubePosition()[0] + (double) dx , this.getCubePosition()[1] 
				+ (double) dy , this.getCubePosition()[2] + (double) dz });
			setStatus(Status.MOVING);
			setWalkingSpeed(dz);
		}
	}

	/**
	 * A list collecting valid neighboring positions.
	 */
	private Queue<int[]> queue =  new LinkedList<int[]>();
	/**
	 * A list collecting valid neighboring positions.
	 */
	private Queue<int[]> queuePos = new LinkedList<int[]>();
	/**
	 * A variable registering the length of the queue.
	 */
	private int length = 0; 
	
	
	// Documentatie bij een for loop? 
	/**
	 * Search the neighboring cubes of the array that are passable and
	 * are neighboring solid terrain.
	 * @param array
	 * 		The array to search around.
	 * @return A collection of all the neighboring cubes, with an extra index
	 * 		 (one greater then that of the array),
	 * 		 of the array that are passable and are neighboring solid terrain. 
	 */
	public Queue<int[]> search(int[] array){
		//System.out.println("search");
		int[] position = {array[0], array[1], array[2]};
		int n = array[3];
		List<int[]> neighboringCubes = this.getWorld().getNeighboringCubes(position);
		for (int index= 0; index< neighboringCubes.size(); index++){
			int[] currentNeighbour = neighboringCubes.get(index);
			if( this.getWorld().getPassable(currentNeighbour) && 
					this.getWorld().isNeighboringSolidTerrain(currentNeighbour)){
				//System.out.print(Arrays.toString(currentNeighbour));
				//System.out.println("Add1");
				boolean toAdd = true;
				for (int[] queueArray: queue){
					if (queueArray[0]==currentNeighbour[0]&& queueArray[1] == currentNeighbour[1] 
							&& queueArray[2] == currentNeighbour[2] && queueArray[3]>=n){
						//System.out.println("NotAdd");
						toAdd = false;
					}
				}
				//System.out.println("UitFor");
				//System.out.print("toadd? " + toAdd);
				if (toAdd){
					//System.out.println("Add2");
					queuePos.add(currentNeighbour);
					queue.add(new int[] {currentNeighbour[0],currentNeighbour[1],currentNeighbour[2],n+1});
					length = length +1;
				}
			}
		}
		//System.out.print("length "+ length);
		return queue;
	}
	
	//double pathTimer =0;
	/**
	 * Makes this unit move to a given target position.
	 * @param targetPosition
	 * 		The target position to move to.
	 * @effect If a unit can move, his status is IN_CENTER.
	 * 		| if canMove()
	 * 		|	then setStatus(Status.IN_CENTER)
	 * @effect A list with possible positions for different path is created and 
	 * 			the unit will move to an adjacent cube that's in the list.
	 * 		| search(nextPosition)
	 * 		| moveToAdjacent(candidateNextArray[0]-this.getCubeCoordinate()[0],
	 *		| candidateNextArray[1]-this.getCubeCoordinate()[1],candidateNextArray[2]-this.getCubeCoordinate()[2])
	 * @throws IllegalArgumentException
	 * 		The given target position is not a valid position for a unit.
	 * 		| !canHaveAsPosition(targetPosition)
	 */
	public void moveTo1(double[] targetPosition)throws IllegalArgumentException{
		queue.clear();
		queuePos.clear();
		int[] position = {(int) targetPosition[0], (int) targetPosition[1], (int) targetPosition[2]};
		if (!canHaveAsPosition(targetPosition) || !getWorld().isNeighboringSolidTerrain(position)){
			throw new IllegalArgumentException();
		}
		if (Util.fuzzyEquals(Vector.getDistance(this.getPosition(), targetPosition), 0))
			setStatus(Status.DONE);
		else if (canMove()) {
			this.targetPosition = targetPosition;
			setStatus(Status.IN_CENTER);
			int index = 0;
			int[] nextPosition;
			while (!Util.fuzzyEquals(Vector.getDistance(this.getPosition(), targetPosition), 0) && canMove()){
				
				
				int[] positionn = {(int) targetPosition[0], (int) targetPosition[1], (int) targetPosition[2], 0};
				//System.out.println(" wants to move to " +Arrays.toString(position));
				//System.out.println("StartPositie" +Arrays.toString(getCubeCoordinate()));
				queue.add(positionn);
				length = length +1;
				queuePos.add(position);
				while(!queueContainsPos((LinkedList<int[]>) queue, this.getCubeCoordinate()) && length>index && index<getWorld().getxDimension()*getWorld().getyDimension()*getWorld().getzDimension()){
					//System.out.println("index:" +index) ;
 					nextPosition = ((LinkedList<int[]>) queue).get(index);
					search(nextPosition);
					index = index+1;
				}
			if( queueContainsPos((LinkedList<int[]>) queue, this.getCubeCoordinate())){
				int[] candidateNextArray = null;
				for (int[] array: queue){
					int[] nextPos = {array[0],array[1],array[2]};
					int n = array[3];
					if (this.isNeighbouringCube(nextPos)){
						if (candidateNextArray == null || candidateNextArray[3]> n){
							candidateNextArray = array;
						}
					}
				}
				//System.out.println(" is moving" );
				moveToAdjacent(candidateNextArray[0]-this.getCubeCoordinate()[0],
						candidateNextArray[1]-this.getCubeCoordinate()[1],
						candidateNextArray[2]-this.getCubeCoordinate()[2]);
			}
			else{
				System.out.println("Unreachable");
				throw new IllegalArgumentException();
			}
			}
		}	
	}
	
//	private void moveToNext1(){
//		if( queueContainsPos((LinkedList<int[]>) queue, this.getCubeCoordinate())){
//			int[] candidateNextArray = null;
//			for (int[] array: queue){
//				double[] nextPos = {(double)array[0], (double)array[1], (double)array[2]};
//				int n = array[3];
//				if (this.isNeighbouringCube(nextPos)){
//					if (candidateNextArray == null || candidateNextArray[3]> n){
//						System.out.print("candidate " + Arrays.toString(candidateNextArray));
//						candidateNextArray = array;
//
//					}
//				}
//			}
//			//System.out.println("move to adjacent");
//			//System.out.print(Arrays.toString(candidateNextArray));
//			moveToAdjacent(candidateNextArray[0]-this.getCubeCoordinate()[0],
//					candidateNextArray[1]-this.getCubeCoordinate()[1],candidateNextArray[2]-this.getCubeCoordinate()[2]);
//		}
//
//	}
	
	
	/**
	 * Check whether a given array contains a given position
	 * @param queue
	 * 	The array to check.
	 * @param position
	 *	The position to check.
	 * @return True if and only if the given array contains the given position.
	 * 		| if(position[0]==queueArray[0]&& position[1]==queueArray[1]&& position[2]==queueArray[2])
	 * 		|	then return true
	 */
	public boolean queueContainsPos(LinkedList<int[]> queue, int[] position){
		for(int[] queueArray: queue){
			if(position[0]==queueArray[0]
					&& position[1]==queueArray[1]
							&& position[2]==queueArray[2])
				return true;
		}
		return false;
	}
	/**
	 * Move to the given cube.
	 * @param cubePos
	 * 		The cube to move to.
	 * @effect	This unit moves to the given cube.
	 * 		| moveTo1(new double[] {(double)cubePos[0]+0.5,(double) cubePos[1]+0.5,(double)cubePos[2]+0.5})
	 */
	public void moveTo1(int[] cubePos){
		moveTo1(new double[] {(double)cubePos[0]+0.5,(double) cubePos[1]+0.5,(double)cubePos[2]+0.5});
	}
	/**
	 * The unit is moving.
	 * @param duration
	 * 		The game time after which moving is called.
	 * @post The position of this  unit is set to the
	 *         updated position (the old position + this unit's speed *
	 *         duration) and the orientation of this new unit is set to the
	 *         direction in which he is going. If this unit is sprinting,
	 *         the stamina points are decreased with one per 0.1 s. If its stamina
	 *         points are equal to or less than zero, this unit stops sprinting
	 *         and its stamina points are set to 0. If this unit has arrived to
	 *         the target position, its new position is the target position, its
	 *         status is DONE and its target position is null. If this unit
	 *         has arrived to the next target position (the center of the next
	 *         cube), its new position is the next target position and it moves
	 *         to the target position.
	 *		| setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)))
	 *		| setOrientation((float) Math.atan2(v[1],v[0]));
	 *		| if(isSprinting() && StaminaPoints != 0 )
	 *		|	then  setStaminaPoints(this.getStaminaPoints() - 10.0 * duration)
	 *		|		&&  startSprinting()
	 *		| if(isSprinting() && StaminaPoints == 0 )	
	 *		|	then  setStaminaPoints(0.0) && stopSprinting()
	 *		| if (targetPosition != null && startPosition != null
	 *		|	&& Vector.getDistance(targetPosition, startPosition)
	 *		|	- Vector.getDistance(startPosition, this.getPosition())<=0.0)
	 *		|		then setPosition(targetPosition) && setStatus(Status.DONE)
	 *		|			&& setExperiencePoints(this.getExperiencePoints()+1)
	 *		| else if (nextTargetPosition != null && Vector.getDistance(nextTargetPosition, startPosition)
	 *		|	-Vector.getDistance(startPosition, this.getPosition())<=0.0 && targetPosition != null)
	 *		|		then setExperiencePoints(this.getExperiencePoints()+1)
	 *		|		&& setPosition(nextTargetPosition) && setStatus(Status.IN_CENTER)
	 *		|		&& moveTo1(targetPosition);
	 *		| else if (nextTargetPosition != null && Vector.getDistance(nextTargetPosition, startPosition)
	 *		|	-Vector.getDistance(startPosition, this.getPosition())<=0.0 && targetPosition == null)
	 *		|		then setExperiencePoints(this.getExperiencePoints()+1)
	 *		|		&& setPosition(nextTargetPosition) && setStatus(Status.DONE)	   
	 */
	private void moving1(double duration){
		double d = Vector.getDistance(nextTargetPosition,startPosition);
		double[] v = new double[] {getCurrentSpeed()*(nextTargetPosition[0]-startPosition[0])/d,
				getCurrentSpeed()*(nextTargetPosition[1]-startPosition[1])/d,
				getCurrentSpeed()*(nextTargetPosition[2]-startPosition[2])/d};
				
		setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
		setOrientation((float) Math.atan2(v[1],v[0]));
		//pathTimer += duration;
		if (isSprinting()){

			if (Util.fuzzyLessThanOrEqualTo(this.getStaminaPoints()-10.0*duration,0.0)){
		
				setStaminaPoints(0.0);
				stopSprinting();
			} else {
				setStaminaPoints(this.getStaminaPoints() - 10.0 * duration);
				startSprinting();
			}
		}
		if (targetPosition != null && startPosition != null
				&& Vector.getDistance(targetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
			setPosition(targetPosition);
			setStatus(Status.DONE);
			queue = new LinkedList<>();
			queuePos = new LinkedList<>();
			targetPosition = null;
			setExperiencePoints(this.getExperiencePoints()+1);
			if (this.isExecutingStatement){
				System.out.println(Arrays.toString(this.getPosition()));
				stopExecutingStatement();
			}
			else if (this.isEnableDefaultBehaviour())
				startDefaultBehaviour();
		}
		else if (nextTargetPosition != null && Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
			setExperiencePoints(this.getExperiencePoints()+1);
			setPosition(nextTargetPosition);
			if (targetPosition != null){
				setStatus(Status.IN_CENTER);
				moveTo1(targetPosition);
			}
			else
				setStatus(Status.DONE);
		}
	}

	/**
	 * This unit starts following the given unit.
	 * @param other
	 * 		The unit to follow.
	 * @effect This unit will start moving to the given unit.
	 * 		| moveTo1(other.getPosition())
	 */
	public void startFollowing(Unit other){
		followedUnit = other;
		moveTo1(other.getPosition());
	}
	
	/**
	 * Give the unit that this unit is following.
	 * @return this.followedUnit
	 * 	| this.followedUnit
	 */
	private Unit isFollowing(){
		return this.followedUnit;
	}
	
	/**
	 * This unit will stop following any other unit.
	 * @effect This unit status will be set to done.
	 * 		| this.setStatus(Status.DONE)
	 */
	private void stopFollowing(){
		followedUnit = null;
		this.setStatus(Status.DONE);
		this.stopExecutingStatement();
	}
	
	/**
	 * A variable referencing the unit that this unit is following.
	 */
	private Unit followedUnit;
	
	
	/**
	 * Return the walking speed of this unit.
	 */
	private double getWalkingSpeed() {
		return this.walkingSpeed;
	}
	
	/**
	 * Set the walking speed of this unit to the given walking speed.
	 * 
	 * @param dz
	 *         The direction in which this unit is moving in the z-direction.
	 * @effect If the unit is moving to a lower cube, the walkingspeed will be
	 *         1.2 times the unit's basespeed.
	 *         | if (dz == -1)
	 *         | 	then walkingSpeed = 1.2 * getBaseSpeed()
	 * @effect If the unit is moving to a higher cube, the walkingspeed will be
	 *         0.5 times the unit's basespeed.
	 *         | if (dz == 1)
	 *         | 	then walkingSpeed = 0.5 * getBaseSpeed()
	 * @effect If the unit stays at the same level, the walkingspeed will be the
	 *         unit's basespeed.
	 *         | walkingSpeed = getBaseSpeed()
	 */

	private void setWalkingSpeed(int dz) {
		if (dz == -1)
			walkingSpeed = 1.2 * getBaseSpeed();
		if (dz == 1)
			walkingSpeed = 0.5 * getBaseSpeed();
		else
			walkingSpeed = getBaseSpeed();
	}

	/**
	 * A variable registering the walkingspeed of this unit.
	 */
	private double walkingSpeed = 0;
	
	/**
	 * A variable registering the start position of this unit.
	 */
	private double[] startPosition;
	
	/**
	 * A variable registering the target position of this unit.
	 */
	private double[] targetPosition;
	
	/**
	 * A variable registering the next cube this unit will move to.
	 */
	private double[] nextTargetPosition;
	
	/**
	 * Check whether it's possible for a unit to work.
	 * 
	 * @return True if and only if this unit is effective and the unit wasn't currently moving or 
	 * 			wasn't initial resting or wasn't attacking an other unit or wasn't falling.
	 * 		   | result == (!this.isTerminated() && status != Status.MOVING && status != Status.INITIAL_RESTING 
	 * 		   |	&& status != Status.ATTACKING && status != Status.FALLING)
	 */
	public boolean canWork() {
		if (!this.isTerminated() && this.getStatus() != Status.MOVING 
				&& this.getStatus() != Status.INITIAL_RESTING && this.getStatus() != Status.ATTACKING 
				&& this.getStatus() != Status.FALLING)
			return true;
		return false;
	}
	
	/**
	 * Get the progress of the work of this unit, as a double between 0 and 1.
	 * 
	 * @return the value of progressWork
	 * 			| result == this.progressWork
	 */
	public double getProgressWork(){
		return progressWork;
	}
	/**
	 * Make the unit work.
	 * 
	 * @post If a unit can work, his new status will be updated to working.
	 * 		 | if (canWork())
	 *		 |	then new.status == Status.WORKING
	 * @post The workingTime and the progressWork of this new unit will be set on zero
	 * 			and the totalWorkingTime will be calculated as 500 divided by the unit's strength.
	 * 		 | new.workingTime == (float) 0.0
	 *		 | new.totalWorkingTime == (float) 500.0 / this.getStrength()
	 *	 	 | new.progressWork == (float) 0.0
	 * @throws IllegalArgumentException
	 * 	The given position isn't neighboring this unit's position or isn't this unit's position.
	 * 		 | !(this.isNeighbouringCube(position)||this.getCubeCoordinate()!=position)
	 */
	public void work(int[] position) throws IllegalArgumentException{
		if(!(this.isNeighbouringCube(position)||this.getCubeCoordinate()!=position))
			throw new IllegalArgumentException();
		else if (canWork()){
			setStatus(Status.WORKING);
			workTargetPosition = position;
			workingTime = (float) 0.0;
			totalWorkingTime = (float) 500.0 / this.getStrength();
			progressWork = (float) 0.0;
			this.setOrientation((float) Math.atan2(workTargetPosition[1]+0.5 - this.getPosition()[1],
					workTargetPosition[0]+0.5 - this.getPosition()[0]));
		}

	}
	/**
	 * This unit is working
	 * @param duration
	 * 		The game time after which working is called.
	 * @post If this unit is working, and if his task is not completed, the new unit's
	 *       working time is increased with duration, and the progress of its work is
	 *       updated. If this unit is working and its task is completed, the
	 *       new status is DONE.
	 *       | if (workingTime < totalWorkingTime)
	 *       |	then workingTime += duration && progressWork = workingTime / totalWorkingTime
	 * @effect Otherwise the unit will end his work and the progress is completed.
	 * 		 | else then progressWork = (float) 1.0 && endWork(workTargetPosition)
	 */
	private void working(double duration) {
		if (workingTime < totalWorkingTime) {
			workingTime += duration;
			progressWork = workingTime / totalWorkingTime;
		} else {
			progressWork = (float) 1.0;
			endWork(workTargetPosition);
		}
	}
	/**
	 * End this units work.
	 * @param targetPosition
	 * 		The position on which the unit is working.
	 * @post The status of this unit is done.
	 * 		| this.setStatus(Status.DONE)
	 * @effect If this unit carries a boulder, the boulder is dropped 
	 * 			at the centre of the cube targeted.
	 * 		| If (this.getBoulder()!= null)
	 * 		|	then this.getBoulder().setPosition(this.getWorld().getCubeCenter(targetPosition))
	 * 		|	&& this.getWorld().addAsBoulder(this.getBoulder())
	 * 		|	&& this.setWeight(this.getWeight()-this.getBoulder().getWeight())
	 * 		|	&& this.setBoulder(null) && setExperiencePoints(this.getExperiencePoints()+10)
	 * @effect Otherwise if this unit carries a log, the log is dropped
	 * 			at the centre of the cube targeted.
	 * 		| If (this.getBoulder() == null && this.getLog() != null)
	 * 		|	then this.getLog().setPosition(this.getWorld().getCubeCenter(targetPosition))
	 * 		|	&& this.getWorld().addAsLog(this.getLog())
	 *		|	&& this.setWeight(this.getWeight()-this.getLog().getWeight())
	 *		|	&& this.setLog(null) && setExperiencePoints(this.getExperiencePoints()+10)
	 * @effect Otherwise if the targeted cube has the terraintype workshop and one boulder and
	 * 			one log is available an the targeted cube, this unit consumes the 
	 * 			boulder and the log and increases his weight and toughness by one.
	 * 		| If(this.getBoulder() == null && this.getLog() == null 
	 * 		|	&& this.getWorld().getTerrain(targetPosition) == WORKSHOP 
	 * 		|	&& this.getWorld().getBoulders(targetPosition).size() > 0
	 * 		|	&& this.getWorld().getLogs(targetPosition).size() > 0)
	 * 		|		then this.getWorld().removeAsBoulder(boulder)
	 *		|		&& this.getWorld().removeAsLog(log)
	 *		|		&& this.setWeight(this.getWeight()+1)
	 *		|		&& this.setToughness(this.getToughness()+1)
	 *		|		&& setExperiencePoints(this.getExperiencePoints()+10)
	 * @effect Otherwise if a boulder is present on the targeted cube, 
	 * 			this unit carries the boulder.
	 * 		| else if (this.getWorld().getBoulders(targetPosition).size()>0)
	 * 		|	then this.setBoulder(boulder) && this.getWorld().removeAsBoulder(boulder)
	 *		|	&& setExperiencePoints(this.getExperiencePoints()+10)
	 *		|	&& this.setWeight(this.getWeight()+boulder.getWeight())
	 * @effect Otherwise if a log is present on the targeted cube,
	 * 			this unit carries the log.
	 * 		| else if (this.getWorld().getBoulders(targetPosition).size() ==0 
	 * 		|	&& this.getWorld().getLogs(targetPosition).size() >0)
	 * 		|		then this.getWorld().removeAsLog(log) && this.setLog(log)
	 *		|			setExperiencePoints(this.getExperiencePoints()+10)
	 *		|			&& this.setWeight(this.getWeight()+log.getWeight())
	 * @effect Otherwise if the terraintype of the target cube is tree, 
	 * 		the terrain is changed to passable leaving with 25 percent chance a log
	 * 		 and the experience points are increased with 10.
	 * 		| case TREE
	 * 		|	then his.getWorld().solidToPassableUpdate(targetPosition) && 
	 * 		|	setExperiencePoints(this.getExperiencePoints()+10)
	 * @effect Otherwise if the terraintype of the target cube is rock,
	 * 		the terrain is changed to passable leaving with 25 percent chance a boulder 
	 * 		and the experience points are increased with 10.
	 * 		| case ROCK
	 * 		|	then this.getWorld().solidToPassableUpdate(targetPosition) &&
	 * 		|	setExperiencePoints(this.getExperiencePoints()+10)
	 */
	private void endWork(int[] targetPosition) {
		switch(this.getWorld().getTerrain(targetPosition)){
		case TREE:
			this.getWorld().solidToPassableUpdate(targetPosition);
			setExperiencePoints(this.getExperiencePoints()+10);
			System.out.println("CASE TREE solidToPassableUpdate");
			break;
		case ROCK:
			this.getWorld().solidToPassableUpdate(targetPosition);
			setExperiencePoints(this.getExperiencePoints()+10);
			System.out.println("CASE ROCK solidToPassableUpdate");
			break;
		default:
			switch(this.getNbBoulders()){
			case 1:
				this.getBoulder().setPosition(this.getWorld().getCubeCenter(targetPosition));
				this.getWorld().addAsBoulder(this.getBoulder());
				this.setWeight(this.getWeight()-this.getBoulder().getWeight());
				this.setBoulder(null);
				setExperiencePoints(this.getExperiencePoints()+10);
				System.out.println("CASE CARRYING BOULDER addBoulder to the world");
				break;
			case 0:
				switch(this.getNbLogs()){
				case 1:
					this.getLog().setPosition(this.getWorld().getCubeCenter(targetPosition));
					this.getWorld().addAsLog(this.getLog());
					this.setWeight(this.getWeight()-this.getLog().getWeight());
					this.setLog(null);
					setExperiencePoints(this.getExperiencePoints()+10);
					System.out.println("CASE NOT CARRYING BOULDER BUT LOG addLog to the world");
					break;
				case 0:
					switch(this.getWorld().getTerrain(targetPosition)){
					case WORKSHOP:
						switch(this.getWorld().getBoulders(targetPosition).size()){
						case 0:
							switch(this.getWorld().getLogs(targetPosition).size()){
							case 0:
								break;
							default:
								System.out.println("CASE NOT CARRYING BOULDER, NOT LOG, addLog to the unit");
								Log log = (Log) this.getWorld().inspectCube(targetPosition).get(2).get(0);
								this.getWorld().removeAsLog(log);
								this.setLog(log);
								setExperiencePoints(this.getExperiencePoints()+10);
								this.setWeight(this.getWeight()+log.getWeight());
								break;
							}
							break;
						default:
							switch(this.getWorld().getLogs(targetPosition).size()){
							case 0:
								System.out.println("CASE NOT CARRYING BOULDER, NOT LOG, addBoulder to the unit");
								Boulder boulder = (Boulder) this.getWorld().inspectCube(targetPosition).get(3).get(0);
								this.setBoulder(boulder);
								this.getWorld().removeAsBoulder(boulder);
								setExperiencePoints(this.getExperiencePoints()+10);
								this.setWeight(this.getWeight()+boulder.getWeight());
								break;
							default:
								Log log = (Log) this.getWorld().inspectCube(targetPosition).get(2).get(0);
								Boulder boulder2 = (Boulder) this.getWorld().inspectCube(targetPosition).get(3).get(0);
								this.getWorld().removeAsBoulder(boulder2);
								this.getWorld().removeAsLog(log);
								this.setWeight(this.getWeight()+1);
								this.setToughness(this.getToughness()+1);
								setExperiencePoints(this.getExperiencePoints()+10);
								System.out.println("CASE WORKSHOP + LOG + BOULDER");
								break;
							}
							break;
						}
						break;
					default:
						switch(this.getWorld().getBoulders(targetPosition).size()){
						case 0:
							switch(this.getWorld().getLogs(targetPosition).size()){
							case 0:
								break;
							default:
								System.out.println("CASE addLog to the unit");
								Log log = (Log) this.getWorld().inspectCube(targetPosition).get(2).get(0);
								this.getWorld().removeAsLog(log);
								this.setLog(log);
								setExperiencePoints(this.getExperiencePoints()+10);
								this.setWeight(this.getWeight()+log.getWeight());
								break;
							}
							break;
						default:
							System.out.println("CASE addBoulder to the unit");
							Boulder boulder = (Boulder) this.getWorld().inspectCube(targetPosition).get(3).get(0);
							this.setBoulder(boulder);
							this.getWorld().removeAsBoulder(boulder);
							setExperiencePoints(this.getExperiencePoints()+10);
							this.setWeight(this.getWeight()+boulder.getWeight());
							break;
						}
						break;
					}
					break;
			}
			break;
			}
			break;
		}
		setStatus(Status.DONE);
		if (this.isExecutingStatement){
			stopExecutingStatement();
			System.out.println(" done ");
		}
		else if (this.isEnableDefaultBehaviour())
			startDefaultBehaviour();

	}
	/**
	 * A variable registering the progress of a unit's work.
	 */
	private float progressWork;

	/**
	 * A variable registering the duration that the unit is working.
	 */
	private float workingTime;

	/**
	 * A variable registering the duration that the unit must work to complete a task.
	 */
	private float totalWorkingTime;
	/**
	 * A variable registering the position of the place to work.
	 */
	private int[] workTargetPosition;

	/**
	 * Check whether it's possible for a unit to attack.
	 * 
	 * @return True if the unit is not currently moving or falling and this unit is effective.
	 * 		   | result == (status != Status.MOVING)&& this.getStatus() != Status.FALLING && !this.isTerminated()
	 */
	public boolean canAttack() {
		if (this.getStatus() != Status.MOVING && this.getStatus() != Status.FALLING && !this.isTerminated())
			return true;
		return false;
	}
	
	/**
	 * Make the unit attack an other unit.
	 * 
	 * @param other
	 * 		The unit that will be attacked.
	 * @post If this unit can attack another unit, this new unit's status will be updated to attacking
	 * 			and this unit's attackTimer will be set to zero.
	 * 		 | if (canAttack())
	 * 		 |	then new.status == Status.ATTACKING && new.attackTimer == 0.0
	 * @effect If this unit can attack, the orientation of this new unit will be changed so that this unit faces the other unit.
	 * 			The new other unit defends itself against this unit.
	 * 		 | if (canAttack())
	 * 		 |	then this.setOrientation((float) Math.atan2(other.getPosition()[1] - this.getPosition()[1],
	 *		 |		other.getPosition()[0] - this.getPosition()[0])) && other.defend(this).
	 *		 |		(new other).defend(this)
	 * @throws IllegalArgumentException
	 * 			If the unit that will be attacked isn't positioned in a neighboring cube or in the same cube.
	 * 			| (!(isNeighbouringCube(other.getCubePosition()) || ((other.getCubeCoordinate()[0] == this.getCubeCoordinate()[0])
	 * 			| &&(other.getCubeCoordinate()[1] == this.getCubeCoordinate()[1])
	 * 			| && (other.getCubeCoordinate()[2] == this.getCubeCoordinate()[2]))))
	 * @throws IllegalArgumentException
	 * 			If  the given unit is not effective or is from the same faction.
	 * 			| other.isTerminated || other.getFaction() == this.getFaction()
	 */
	public void attack(Unit other) throws IllegalArgumentException {
		if (!(isNeighbouringCube(other.getCubePosition()) || ((other.getCubeCoordinate()[0] == this.getCubeCoordinate()[0])
				&&(other.getCubeCoordinate()[1] == this.getCubeCoordinate()[1]) 
				&& (other.getCubeCoordinate()[2] == this.getCubeCoordinate()[2]))))
			throw new IllegalArgumentException();
		if( other.isTerminated || other.getFaction() == this.getFaction())
			throw new IllegalArgumentException();
		if (canAttack()) {
			setStatus(Status.ATTACKING);
			this.setOrientation((float) Math.atan2(other.getPosition()[1] - this.getPosition()[1],
					other.getPosition()[0] - this.getPosition()[0]));
			other.defend(this);
			attackTimer = 0.0;
		}
		if (this.isExecutingStatement){
			stopExecutingStatement();
		}
		else if (this.isEnableDefaultBehaviour())
			startDefaultBehaviour();

	}

	/**
	 * A variable registering the duration of the attack.
	 */
	private double attackTimer;

	/**
	 * Make the unit defend itself against an attack of an other unit.
	 * 
	 * @param unit
	 * 		The attacking unit.
	 * @effect If this unit can defend, change the orientation of this unit so that this new unit faces the attacking unit.
	 * 		   | if (this.canDefend())
	 * 		   | 	then this.setOrientation((float) Math.atan2(unit.getPosition()[1] - this.getPosition()[1],
	 *		   |	unit.getPosition()[0] - this.getPosition()[0]))
	 * @effect If this unit is able to dodge, his position is set to a random valid neighbhouring cube 
	 * 			and his experience points are increased by 20.
	 * 		   | if (new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility()))
	 * 		   |	then this.setPosition(new double[] {this.getPosition()[0]+ (double)(-1 + (new Random().nextInt(3))),
	 *		   |			this.getPosition()[1]+ (double)(-1 + (new Random().nextInt(3))),
	 *		   |			this.getPosition()[2]+ (double)(-1 + (new Random().nextInt(3)))});
	 *		   |		&& this.setExperiencePoints(this.getExperiencePoints()+20)
	 * @effect If this unit is able to block, his experience points are increased by 20.
	 * 		   | if (new Random().nextDouble() <= 0.25* ((this.getStrength() + this.getAgility()) / (unit.getStrength() + unit.getAgility())))
	 * 		   |	then this.setExperiencePoints(this.getExperiencePoints()+20)
	 * @effect If this unit isn't able to dodge or to block the attack, this unit will lose hitpoints equal to 
	 * 			to the attacking unit's strength divided by 10. If the hitpoints would go under zero,
	 * 			they will be set to zero. The experience points of the attacking unit are increased by 20.
	 * 		   | if (!(new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility()))
	 * 		   |	this.setPosition(new double[] {this.getPosition()[0]+ (double)(-1 + (new Random().nextInt(3))) &&
	 * 		   | 	!(new Random().nextDouble() <= 0.25*
	 *	 	   |	((this.getStrength() + this.getAgility()) / (unit.getStrength() + unit.getAgility())))){
	 *		   |		then unit.setExperiencePoints(unit.getExperiencePoints() +20)
	 * 		   | 	if (this.getHitpoints() - unit.getStrength() / 10.0 > 0)
	 * 		   |		then this.setHitPoints(this.getHitpoints() - unit.getStrength() / 10.0)
	 * 		   | 	if (this.getHitpoints() - unit.getStrength() / 10.0 <=0)
	 * 		   |		then this.setHitPoints(0.0)
	 * 		   |	}
	 * @post This new unit's status is DONE.
	 * 		   | new.status == Status.DONE;
	 */
	public void defend(Unit unit) {
		if (this.canDefend()){
			setStatus(Status.DEFENDING);
			setOrientation((float) Math.atan2(unit.getPosition()[1] - this.getPosition()[1],
					unit.getPosition()[0] - this.getPosition()[0]));
			if (new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility())) {
				try {
					double[] newPos = new double[] {this.getPosition()[0]+ (double)(-1 + (new Random().nextInt(3))),
							this.getPosition()[1]+ (double)(-1 + (new Random().nextInt(3))),
							this.getPosition()[2]};
					if (newPos == this.getPosition())
						defend(unit);
					else
						this.setPosition(newPos);
				} 
				catch (IllegalArgumentException exc) {
					defend(unit);
				}
				setStatus(Status.DONE);
				this.setExperiencePoints(this.getExperiencePoints()+20);
			}
			else if (new Random().nextDouble() <= 0.25
					* ((this.getStrength() + this.getAgility()) / (unit.getStrength() + unit.getAgility()))){
				setStatus(Status.DONE);
				this.setExperiencePoints(this.getExperiencePoints()+20);
			}
			else {
				double newHitPoints = this.getHitpoints() - unit.getStrength() / 10.0;
				unit.setExperiencePoints(unit.getExperiencePoints() +20);
				if (newHitPoints > 0)
					this.setHitPoints(newHitPoints);
				else
					this.setHitPoints(0.0);
				setStatus(Status.DONE);
				
			}
		}
	}
	/**
	 * Check whether this unit is able to defend.
	 * @return True if and only if the unit is not currently falling and the unit is effective.
	 * 		| result == this.getStatus() != Status.FALLING && !this.isTerminated()
	 */		
	private boolean canDefend(){
		return (this.getStatus() != Status.FALLING && !this.isTerminated());
	}

	/**
	 * Check whether this unit needs to rest.
	 * @return True if and only if 3 minutes of game time have passed or the stamina points 
	 * 		   or hitpoints or equal to zero.
	 * 		   | result == (restTimer >= 180)
	 */
	public boolean mustRest() {
		if (Math.floor(restTimer) >= 180 ){
			return true;
		}
		return false;
	}
	/**
	 * Check whether it's possible for this unit to rest.
	 * @return True if and only if this unit is effective and this unit's stamina points or hitpoints are less then the maximum
	 * 			and if this unit is currently doing nothing or is resting or 
	 * 			working or is moving and currently in the center of a cube.
	 * 		   | result == (this.getStaminaPoints() <= 0 || this.getHitpoints() <= 0)&&
	 * 		   |		(status == Status.DONE || status == Status.RESTING || 
	 * 		   | 		status == Status.WORKING || status == Status.IN_CENTER)
	 */
	public boolean canRest() {
		if(this.getStaminaPoints() < this.getMaxPoints() || this.getHitpoints() < this.getMaxPoints())
			if (!isTerminated())
				if (this.getStatus() == Status.DONE || this.getStatus() == Status.RESTING || this.getStatus() == Status.WORKING || this.getStatus() == Status.IN_CENTER)
					return true;
		return false;
	}
	
	/**
	 * Makes the unit rest.
	 * 
	 * @post If this unit needs or can rest, this new unit's restTimer and recoveredHitpoints will be set to zero.
	 * 		 | if (mustRest() || canRest())
	 * 		 |		then restTimer = 0.0 && recoveredHitpoints = 0.0
	 * @post If this unit needs or can rest and its hitpoints are less than the maximum value of hitpoints,
	 * 			this new unit's status will be set to initial resting.
	 * 		 | if (mustRest() || canRest())
	 * 		 |		if (this.getHitpoints() < max_nbPoints())
	 * 	 	 |			then status = Status.INITIAL_RESTING
	 * @post If this unit needs or can rest and its hitpoints are equal to the maximum number of hitpoints, 
	 * 			this new unit's status will be set to resting.
	 * 		 | if (mustRest() || canRest())
	 * 		 |		if (this.getHitpoints() >= max_nbPoints())
	 * 	 	 |			then status = Status.RESTING
	 */
	public void rest() {
		if (mustRest() || canRest()) {
			restTimer = 0.0;
			recoveredHitpoints = 0.0;
			if (this.getHitpoints() < getMaxPoints())
				setStatus(Status.INITIAL_RESTING);
			else
				setStatus(Status.RESTING);
		}
	}
	/**
	 * This unit is initial resting.
	 * @param duration
	 * 		The game time after which initial resting is called.
	 * @effect If this unit is initial resting, his new hitpoints are increased by (getToughness() / 200.0) * 5 * duration,
	 * 			as well as the recovered hitpoints. If his hitpoints are equal to or greater then the maximum value, 
	 * 			this unit's status will be updated to resting and his hitpoints will be set to the maximum value.
	 * 			If his recoveredHitpoints are equal to or greater than 1, this unit's status is updated to resting.
	 * 			| this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints()) && recoveredHitpoints += (getToughness() / 200.0) * 5 * duration
	 * 			| if (this.getHitpoints() >= getMaxPoints())
	 * 			|	then this.setHitPoints(getMaxPoints()) && setStatus(Status.RESTING)
	 * 			| else if (recoveredHitpoints >= 1.0) 
	 * 			|	then setStatus(Status.RESTING)
	 */
	private void initialResting(double duration) {
		this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints());
		recoveredHitpoints += (getToughness() / 200.0) * 5 * duration;
		if (this.getHitpoints() >= getMaxPoints()) {
			this.setHitPoints(getMaxPoints());
			setStatus(Status.RESTING);
		}
		else if (recoveredHitpoints >= 1.0){
			setStatus(Status.RESTING);
		}
	}
	/**
	 * This unit is resting.
	 * @param duration
	 * 		The game time after which resting is called.
	 * @effect If the status of this unit is resting and if his hitpoints are less then the maximum value, 
	 * 			this unit will increase its hitpoints by (getToughness() / 200.0) * 5 * duration. 
	 * 			If his hitpoints are at the maximum value but the stamina points aren't, 
	 * 			the unit will increase stamina points by (getToughness() / 100.0) * 5 * duration. 
	 * 			If both hitpoints and stamina points are at the maximum value, the unit's status will be updated to done.
	 * 			| if this.getHitpoints() < getMaxPoints()
	 * 			|	then this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints())
	 * 			| else if this.getStaminaPoints() < getMaxPoints()
	 * 			|	then this.setHitPoints(getMaxPoints()) && this.setStaminaPoints((getToughness() / 100.0) * 5 * duration + getStaminaPoints())
	 * 			| else then this.setStaminaPoints(getMaxPoints()) &&  setStatus(Status.DONE)
	 */
	private void resting(double duration){
		if (this.getHitpoints() < getMaxPoints())
			this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints());
		else if (this.getStaminaPoints() < getMaxPoints()) {
			this.setHitPoints(getMaxPoints());
			this.setStaminaPoints((getToughness() / 100.0) * 5 * duration + getStaminaPoints());
		} else {
			this.setStaminaPoints(getMaxPoints());
			setStatus(Status.DONE);
			if (this.isEnableDefaultBehaviour())
				startDefaultBehaviour();
		}
	}

	/**
	 * Symbolic constant registering the recovered hitpoints of a unit.
	 */
	private double recoveredHitpoints = 0.0;
	
	/**
	 * A variable registering the passed game time since the last rest.
	 */
	private double restTimer = 0.0;
	/**
	 * Check whether the default behavior is enabled.
	 * 
	 * @return True if and only if this unit's default behavior is enabled.
	 * 		   | result == this.enableDefaultBehaviour
	 */
	public boolean isEnableDefaultBehaviour() {
		return enableDefaultBehaviour;
	}

	/**
	 * Set the enableDefaultBehaviour of this unit to the given enableDefaultBehaviour.
	 * 
	 * @param enableDefaultBehaviour
	 *          The new enableDefaultBehaviour for this unit.
	 * @post The enableDefaultBehaviour of this new unit is equal to the given enableDefaultBehaviour.
	 * 		 | new.isEnableDefaultBehaviour() == enableDefaultBehaviour
	 */
	private void setEnableDefaultBehaviour(boolean enableDefaultBehaviour) {
		this.enableDefaultBehaviour = enableDefaultBehaviour;
	}

	/**
	 * Makes the unit start with his default behaviour.
	 * 
	 * @effect If this unit's status is done, its enableDefaultBehaviour is set to true. 
	 * 		 | if (status == Status.DONE)
	 * 		 |	then this.setEnableDefaultBehaviour(true)
	 * @effect If this unit's status is not done, its enableDefaultBehaviour is set to false.
	 * 		 | if !(status == Status.DONE)
	 * 		 |	then this.setEnableDefaultBehaviour(false)
	 * @post If this unit can be assigned a task, this unit will start executing his task.
	 * 		 | if (this.getFaction().getScheduler() != null 
	 * 		 | && this.getFaction().getScheduler().getAllTasksIterator().hasNext())
	 * 		 |		then setTask(this.getFaction().getScheduler().getAllTasksIterator().next());
	 * 		 |		&& this.getTask().executeTask(this)
	 * @effect If this unit's status is done, it will conduct at random an activity:
	 * 			 - this new unit's status is moving and this unit sprints to a random position
	 * 			 - this new unit's status is moving and this unit walks to a random position
	 * 			 - this unit starts working
	 * 			 - this unit starts resting
	 * 			 - If this new unit is surrounded by enemies, this unit can also attack.
	 * 		   | (new.status == Status.MOVING && startSprinting() && moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
	 *		   |		 (new Random().nextDouble()) * 50 }))
	 *		   |OR (new.status = Status.MOVING && stopSprinting() &&
	 *		   |		moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
	 *		   |		 (new Random().nextDouble()) * 50 }))
	 *		   |OR work()
	 *		   |OR rest()
	 *		   |OR if ! (potentialEnemies.size()==0)
	 *		   |	then attack()	   
	 */
	public void startDefaultBehaviour() {
		if (this.getStatus() == Status.DONE) {
			setEnableDefaultBehaviour(true);
			System.out.print(" restart default ");
			System.out.print(this.getFaction().getScheduler());
			System.out.print(this.getFaction().getScheduler().getTasks());
			if (this.getFaction().getScheduler() != null && this.getFaction().getScheduler().getHighestPriorityTask()!=null){
				System.out.print(" execute task 1");
				this.isExecutingTask = true;
				Task newTask = this.getFaction().getScheduler().getHighestPriorityTask();
				newTask.setExecutingUnit(this);
				System.out.print(" execute task ");
				this.isExecutingStatement = true;
				this.getTask().executeTask();
				//System.out.println(" 6 ");
				//System.out.println(getFaction().getScheduler().getAllTasksIterator().next());
				
			}
			else{
			//System.out.println(" 7 ");
			List<Unit> potentialEnemies = new ArrayList<>();
			List<int[]> potEnemyPos = this.getWorld().getNeighboringCubes(this.getCubeCoordinate());
			potEnemyPos.add(this.getCubeCoordinate());		
			for (int[] neighbouringCube: potEnemyPos)
				for(Unit other: this.getWorld().getUnits(neighbouringCube)){
					if (other.getFaction() != this.getFaction())
						potentialEnemies.add(other);
				}
			int i = 0;
			if (! (potentialEnemies.size()==0)){
				i = new Random().nextInt(5);

			}
			else{
				i = new Random().nextInt(4);
			} 
			System.out.println(" i " + i);
			if (i == 0) {
				setStatus(Status.IN_CENTER);
				try {
					int[] pos = new int[] { new Random().nextInt(this.getWorld().getxDimension()), 
							new Random().nextInt(this.getWorld().getyDimension()), 
							new Random().nextInt(this.getWorld().getzDimension()) };
					//System.out.println(Arrays.toString(pos));
					moveTo1(pos);
					startSprinting();
				}
				catch (IllegalArgumentException exc){
					System.out.println(" catched ");
					startDefaultBehaviour();
				}
			}
			else if (i == 1) {
				setStatus(Status.IN_CENTER);
				try {
					int[] pos = new int[] { new Random().nextInt(this.getWorld().getxDimension()), 
							new Random().nextInt(this.getWorld().getyDimension()), 
							new Random().nextInt(this.getWorld().getzDimension()) };
					//System.out.println(Arrays.toString(pos));
					moveTo1(pos);
					stopSprinting();
				}
				catch (IllegalArgumentException exc){
					startDefaultBehaviour();
				}
			}
			else if (i == 2){
				List<int[]> neighbouring = this.getWorld().getNeighboringCubes(this.getCubeCoordinate());
				neighbouring.add(this.getCubeCoordinate());
				for (int[] n:neighbouring)
					//System.out.println(Arrays.toString(n));
				i = new Random().nextInt(neighbouring.size());
				//System.out.print(Arrays.toString(neighbouring.get(i)));
				work(neighbouring.get(i));
			}
			else if (i == 3){
				rest();
			}
			else if (i==4){
				if (potentialEnemies.size() != 0){
					int index = new Random().nextInt(potentialEnemies.size());
				//System.out.println(index);
					attack(potentialEnemies.get(index));
				}
				else
					startDefaultBehaviour();
			}
				
		}}
		else
			enableDefaultBehaviour = false;

	}

	/**
	 * Makes the unit stop with his default behaviour.
	 * 
	 * @post enableDefaultBehaviour of this new unit is false 
	 * 			and this new unit's status is set to done 
	 * 			and this unit's targetPosition and nextTargetPosition are null.
	 * 		 | !new.isEnableDefaultBehaviour() && new.status == Status.DONE
	 * 		 | && new.targetPosition == null && new.nextTargetPosition == null
	 */
	public void stopDefaultBehaviour() {
		setEnableDefaultBehaviour(false);
		targetPosition = null;
		nextTargetPosition = null;
		setStatus(Status.DONE);
	}
	
	/**
	 * A boolean to check if the default behaviour is enabled.
	 */
	private boolean enableDefaultBehaviour;
	
	/**
	 * A boolean to check if this unit is executing a task.
	 */
	
	private boolean isExecutingTask = false;
	
	/**
	 * A boolean to check if this unit is executing a statement.
	 */
	public boolean isExecutingStatement = false;
	
	/**
	 * A variable registering the duration of a task. 
	 */
	private double taskTimer = 0.0;

	/**
 	 * Terminate this unit.
 	 *
 	 * @post   This unit  is terminated.
 	 *       | new.isTerminated()
 	 * @post   No boulder is attached any longer to this unit.
 	 * 		| this.setBoulder(null)
 	 * @post   No log is attached any longer to this unit.
 	 * 		| this.setLog(null);
 	 * @effect This unit is removed from the world and from the faction to which it is attached.
 	 * 		| this.getWorld().removeAsUnit(this);
 	 * @effect The status of this unit is set to done.
 	 * 		| this.setStatus(Status.DONE);
 	 */
 	 public void terminate() {
 		 
 		 if (this.getBoulder() != null){
 			 this.getBoulder().setPosition(this.getPosition());
 			 getWorld().addAsBoulder(this.getBoulder());
 			 this.setWeight(this.getWeight()-this.getBoulder().getWeight());

 		 }
 		 this.setBoulder(null);
 		 if (this.getLog()!= null){
 			 this.getLog().setPosition(this.getPosition());
 			 getWorld().addAsLog(this.getLog());
 			 this.setWeight(this.getWeight()-this.getLog().getWeight());

 		 }
 		 this.setLog(null);
 		 if (this.getWorld()!=null){
 			 this.getWorld().removeAsUnit(this);
 		 }
 		 this.setStatus(Status.DONE);
 		 this.isTerminated = true;
 	 }
 	 
 	 /**
 	  * Check whether this unit is terminated.
	  */
 	 @Basic @Raw
 	 public boolean isTerminated() {
 		 return this.isTerminated;
 	 }
 	 
 	 /**
 	  * Variable registering whether this unit is terminated.
 	  */
 	 private boolean isTerminated = false;
 	 
  	/**
  	 * Return the boulder attached to this unit.
  	 */
 	@Basic @Raw
 	public Boulder getBoulder() {
 		return this.boulder;
 	}
 	/**
 	 * Check whether the given boulder is a valid boulder for any unit.
 	 * @param boulder
 	 * 		The boulder to check.
 	 * @return True if and only if the given boulder is not effective
 	 * 		or the position of the given boulder equals the position of this unit and they are in the same world.
 	 * 		| result == (boulder.getWorld()==this.getWorld()) && ((boulder == null) 
 	 *		|	 || (boulder.getPosition() == this.getPosition())||(this.isNeighbouringCube(boulder.getPosition())))
 	 */

 	public boolean isValidBoulder(Boulder boulder){
 		
 		if (this.isTerminated()){
			return (boulder == null);
 		}
 		else if (boulder == null)
 			return true;
 		else{
 			return (((boulder.getWorld()==this.getWorld()) && (!boulder.isTerminated()) &&
 					((boulder.getWorld().getCubePosition(boulder.getPosition())[0]==this.getWorld().getCubeCoordinate(this.getPosition())[0] 
 					&& boulder.getWorld().getCubePosition(boulder.getPosition())[1]==this.getWorld().getCubeCoordinate(this.getPosition())[1] 
 							&& boulder.getWorld().getCubePosition(boulder.getPosition())[2]==this.getWorld().getCubeCoordinate(this.getPosition())[2]) 
 				|| this.isNeighbouringCube(boulder.getPosition()))));
 		}
 	}
 	
	/**
	 * Check whether this unit has a proper boulder.
	 * 
	 * @return True if and only if this unit can have its boulder as its
	 *         boulder.
	 *         | isValidBoulder(getBoulder())
	 */
 	@Raw
	public boolean hasProperBoulder() {
		return isValidBoulder(getBoulder());
	}

 	
 	/**
	 * Set the boulder for this unit to the given boulder.
	 * @param boulder
	 * 		The new boulder for this unit.
	 * @post The new boulder for this unit is the same as the given boulder.
	 * 		| new.getBoulder() == boulder
	 * @throws IllegalArgumentException()
	 * 		The given boulder is not a valid boulder for any unit.
	 * 		| ! isValidBoulder(boulder)
	 */
 	@Raw
 	public void setBoulder(Boulder boulder) throws IllegalArgumentException{
 		if(! isValidBoulder(boulder))
 			throw new IllegalArgumentException();
 		
 		this.boulder = boulder;
 	}
 	/**
 	 * Return the number of boulders this unit is carrying.
 	 * @return 1 if a boulder is attached to this unit or 0 if there's no boulder attached.
 	 * 		| if (this.getBoulder()!=null)
 	 * 		|	 then return 1
 	 * 		|	 else return 0
 	 */
 	public int getNbBoulders(){
 		if (this.getBoulder()!=null)
 			return 1;
 		return 0;
 	}
 	
 	/**
 	 * Variable referencing the boulder of this unit.
 	 */
 	private Boulder boulder;
 	/**
  	 * Return the log attached to this unit.
  	 */
 	@Basic @Raw
 	public Log getLog() {
 		return this.log;
 	}
 	/**
 	 * Check whether the given log is a valid log for any unit.
 	 * @param log
 	 * 		The log to check.
 	 * @return True if and only if the given log is not effective
 	 * 		or the position of the given log equals the position of this unit and they are in the same world.
 	 * 		| result == (log.getWorld()==this.getWorld()) && (log == null) 
 	 * 		|	|| (log.getPosition() == this.getPosition())|| this.isNeighbouringCube(log.getPosition())
 	 */ 
 	public boolean isValidLog(Log log){
 		if (this.isTerminated()){
			return (log == null);
 		}
 		else if (log == null)
 			return true;
 		else{
 			return (((log.getWorld()==this.getWorld()) && (!log.isTerminated()) &&
 					((log.getWorld().getCubePosition(log.getPosition())[0]==this.getWorld().getCubeCoordinate(this.getPosition())[0] 
 					&& log.getWorld().getCubePosition(log.getPosition())[1]==this.getWorld().getCubeCoordinate(this.getPosition())[1] 
 							&& log.getWorld().getCubePosition(log.getPosition())[2]==this.getWorld().getCubeCoordinate(this.getPosition())[2]) 
 				|| this.isNeighbouringCube(log.getPosition()))));
 		}
 	}
 	
	/**
	 * Check whether this unit has a proper log.
	 * 
	 * @return True if and only if this unit can have its log as its
	 *         log.
	 *         | isValidLog(getLog())
	 */
 	@Raw
	public boolean hasProperLog() {
		return isValidLog(getLog());
	}

 	/**
	 * Set the log for this unit to the given log.
	 * @param log
	 * 		The new log for this unit.
	 * @post The new log for this unit is the same as the given log.
	 * 		| new.getLog() == log;
	 * @throws IllegalArgumentException()
	 * 		The given log is not a valid log for any unit.
	 * 		| ! isValidLog(log)
	 */
	@Raw
	public void setLog(Log log) throws IllegalArgumentException{
 		if(! isValidLog(log))
 			throw new IllegalArgumentException();
 		this.log = log;

 	}
	/**
 	 * Return the number of logs this unit is carrying.
 	 * @return 1 if a log is attached to this unit or 0 if there's no log attached.
 	 * 		| if (this.getLog()==null)
 	 * 		|	then return 0
 	 * 		|	else return 1
 	 */
	protected int getNbLogs(){
		if (this.getLog()==null)
			return 0;
		else
			return 1;
	}
	
	/**
 	 * Variable referencing the log of this unit.
 	 */
 	private Log log;
 	
	/**
	 * Return the experience points of this unit. 
	 */
 	@Basic 
	public int getExperiencePoints(){
		return this.experiencePoints;
	}
	/**
	 * Set the experience points of this unit to the given experience points.
	 * @param points
	 * 		The new experience points for this unit.
	 * @post The new experience points for this unit are equal to the given experience points.
	 * 		| new.getExperiencePoints() == points;
	 */
	public void setExperiencePoints(int points){
		experiencePoints = points;
	}
	
	/**
	 * An integer registering the experience points of a unit.
	 */
	private int experiencePoints= 0;
	
	/**
	 * A variable registering the status of this unit.
	 */
	private Status status = Status.DONE;
	
	//test
	
	/**
	 * Set the task assigned to this unit to the given task.
	 * @param task
	 * 		The task to be assigned to this unit.
	 * @pre If the given task is effective, the unit's faction must reference
	 * 		to a scheduler that contains this task.
	 * 		| assert (faction.getScheduler().hasAsTask(task))
	 * @post This unit references the given task as the task assigned to it.
	 * 		| new.getTask() == task
	 */
	public void setTask(@Raw Task task){
		if (task !=null){
			assert this.getFaction().getScheduler().hasAsTask(task);
			assert task.getExecutingUnit() == this;
		}
		this.task = task;
		setScheduledTask(task);
	}
	/**
	 * Check whether this unit has a proper task assigned to it.
	 * @return True if and only if this unit does not reference an effective task
	 * 		or if the faction referenced by this unit references a scheduler that
	 * 		contains this task.
	 * 		| result == (this.getTask() == null || this.getFaction().getScheduler().hasAsTask(task))
	 */
	@Raw
	public boolean hasProperTask(){
		return (this.getTask() == null || this.getFaction().getScheduler().hasAsTask(task));
	}
	/**
	 * Return the task that is assigned to this unit.
	 */
	@Basic @Raw
	public Task getTask(){
		return this.task;
	}
	/**
	 * Variable referencing the task that is assigned to this unit.
	 */
	private Task task;
	
	/**
	 * Set the task assigned to this unit to the given task.
	 * @param task
	 * 		The task to be assigned to this unit.
	 * @pre If the given task is effective, the unit's faction must reference
	 * 		to a scheduler that contains this task.
	 * 		| assert (faction.getScheduler().hasAsTask(task))
	 * @post This unit references the given task as the task assigned to it.
	 * 		| new.getTask() == task
	 */
	public void setScheduledTask(@Raw Task task){
		if (task !=null){
			assert this.getFaction().getScheduler().hasAsTask(task);
			assert task.getScheduledUnit() == this;
		}
		this.scheduledTask = task;
	}
	/**
	 * Check whether this unit has a proper task assigned to it.
	 * @return True if and only if this unit does not reference an effective task
	 * 		or if the faction referenced by this unit references a scheduler that
	 * 		contains this task.
	 * 		| result == (this.getTask() == null || this.getFaction().getScheduler().hasAsTask(task))
	 */
	@Raw
	public boolean hasProperScheduledTask(){
		return (this.getTask() == null || this.getFaction().getScheduler().hasAsTask(task));
	}
	/**
	 * Return the task that is assigned to this unit.
	 */
	@Basic @Raw
	public Task getScheduledTask(){
		return this.scheduledTask;
	}
	/**
	 * Variable referencing the task that is assigned to this unit.
	 */
	private Task scheduledTask;
	
	/**
	 * Return a string that references this unit.
	 * @return Return this unit's name.
	 * 		| return == getName()
	 */
	public String toString(){
		return getName().toString();
	}
	
}