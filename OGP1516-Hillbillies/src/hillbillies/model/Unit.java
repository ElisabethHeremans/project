package hillbillies.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

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
	 *       	|	new.getWeight() == 25
	 * @effect The position of this new unit is set to the given position. 
	 * 			|this.setPosition(position)
	 * @effect The hitpoints of this new unit is set to the given number of hitpoints. 
	 * 			|this.setHitpoints(hitpoints
	 * @effect The the number of staminapoints of this new unit is set to the
	 *       	given the number of staminapoints.
	 *       	|this.setStaminaPoints(staminaPoints)
	 * @effect The orientation of this new unit is set to the given orientation
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
		if (!(25<=weight && weight<=100 && (this.getStrength()+this.getAgility())/2.0 <= weight))
			weight = 25;
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
		this(name, position, weight, strength, agility, toughness, enableDefaultBehavior, 0.0, 0.0,
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
	 * 			the unit's strength increased with his agility, and a number from 1 to 200.
	 * 			| result == (weight >= (this.getStrength() + this.getAgility())/2 && weight >=1 && weight <=200)
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
		if (!isValidName(name))
			throw new IllegalArgumentException();
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
	 * Set the the number of hitpoints of this unit to the given the number of
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
	private void setHitPoints(double hitpoints) {
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
	 * Set the the number of stamina points of this unit to the given the number
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
	 * 
	 * @return the unit's position.
	 * 		| result == this.position
	 */
	@Basic
	@Raw
	public double[] getPosition() {
		return this.position;
	}
	
	

	
	
	
	/**
	 * Check whether the given position is a position inside the game world, a
	 * valid position.
	 * 
	 * @param position
	 * 			The position to check.
	 * @return True if and only if the x,y and z-coordinate of the position are
	 *         inside the limits of the game world. 
	 *         |result == (0<= position[0])
	 *         | && (position[0] <= X) | && (0<= position[1]) && (position[1] <=Y)
	 *         | && (0<= position[2]) && (position[2] <= Z)
	 */
	public boolean isValidPosition(double[] position) {
		return (getWorld().isCubeInWorld(this.getWorld().getCubeCoordinate(position)) 
				&& getWorld().getPassable(this.getWorld().getCubeCoordinate(position)));
		
		
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
	public double[] getCubePosition() {
		return new double[] { Math.floor(this.getPosition()[0]), Math.floor(this.getPosition()[1]),
				Math.floor(this.getPosition()[2]) };
	}
	/**
	 * Return the position of the game world cube in which this unit is
	 * positioned, as an array if integers.
	 * 
	 * @return The position of the game world cube in which this unit is positioned,
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
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * Check whether the given cube is a neighboring cube of the unit's cubePosition.
	 * 
	 * @param cubePosition
	 * 		The position of a cube. 
	 * @return True if and only if the difference between the respective x, y and z -coordinates of the cubeCenters are equal to 0, 1 or -1,
	 * 			and the difference between the respective x,y and z coordinates are not all 0.
	 * 		   | result == ((Util.fuzzyLessThanOrEqualTo(Math.abs((cubePosition)[0] - this.getCubePosition()[0]),1.0)
	 *	   	   | 			&& (Util.fuzzyLessThanOrEqualTo(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),1.0))
	 *		   |			&& (Util.fuzzyLessThanOrEqualTo(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),1.0)))
	 *	       |		&& !(Util.fuzzyEquals(Math.abs((cubePosition)[0] - this.getCubePosition()[0]),0.0)
	 *		   |			&& (Util.fuzzyEquals(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),0.0))
	 *		   |			&& (Util.fuzzyEquals(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),0.0))));
	 */
	private boolean isNeighbouringCube(double[] cubePosition) {
		return ((Util.fuzzyLessThanOrEqualTo(Math.abs((cubePosition)[0] - this.getCubePosition()[0]),1.0)
				&& (Util.fuzzyLessThanOrEqualTo(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),1.0))
				&& (Util.fuzzyLessThanOrEqualTo(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),1.0)))
				&& !(Util.fuzzyEquals(Math.abs((cubePosition)[0] - this.getCubePosition()[0]),0.0)
						&& (Util.fuzzyEquals(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),0.0))
						&& (Util.fuzzyEquals(Math.abs((cubePosition)[1] - this.getCubePosition()[1]),0.0))));
	}
	
	/**
	 * A variable registering the position of this unit.
	 */
	private double[] position = {0.5,0.5,0.5};
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public void setFaction(@Raw Faction faction){
		if (faction != null)
			assert (faction.hasAsUnit(this));
		// nog condities?
		this.faction = faction;
	}
	
	@Raw
	public boolean hasProperFaction(){
		return (getFaction().hasAsUnit(this));
	}
	
	@Basic @Raw
	public Faction getFaction(){
		return faction;
	}
	
	private Faction faction;
	
	public void setWorld(@Raw World world){
		if (world != null)
			assert (world.hasAsUnit(this));
		// nog condities?
		this.world = world;
	}
	
	@Raw
	public boolean hasProperWorld(){
		return (getWorld().hasAsUnit(this));
	}
	
	@Basic @Raw
	public World getWorld(){
		return world;
	}
	
	private World world;

	/**
	 * Update the position and activity status of a unit.
	 * 
	 * @param duration
	 *         The game time after which advanceTime is called.
	 * @effect If this unit must rest, this unit shall rest.
	 * @effect If this unit is doing nothing, while default behaviour is not enabled, and the unit still has a
	 *         targetPosition, this unit shall resume moving to the targetPosition.
	 * @effect If this unit's default behaviour is enabled, and this unit is
	 *         doing nothing, he shall start a default behaviour.
	 * @effect If this unit is moving, the position of this  unit is set to the
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
	 * @post If this unit is working, and if his task is not completed, the new unit's
	 *       working time is increased with duration, and the progress of its work is
	 *       updated. If this unit is working and its task is completed, the
	 *       new status is DONE.
	 * @effect If the status of this unit is initial resting, his new hitpoints are increased by (getToughness() / 200.0) * 5 * duration,
	 * 			as well as the recovered hitpoints. If his hitpoints are equal to or greater then the maximum value, 
	 * 			this unit's status will be updated to resting and his hitpoints will be set to the maximum value.
	 * 			If his recoveredHitpoints are equal to or greater than 1, this unit's status is updated to resting.
	 * @effect If the status of this unit is resting and if his hitpoints are less then the maximum value, 
	 * 			this unit will increase its hitpoints by (getToughness() / 200.0) * 5 * duration. 
	 * 			If his hitpoints are at the maximum value but the stamina points aren't, 
	 * 			the unit will increase stamina points by (getToughness() / 100.0) * 5 * duration. 
	 * 			If both hitpoints and stamina points are at the maximum value, the unit's status will be updated to done.
	 * @post If this unit's status is attacking, this new unit's attackTimer will be increased with duration and if the attackTimer becomes 
	 * 			equal or greater then one, this new unit's status will be updated to done.
	 * @throws IllegalArgumentException
	 *             If the duration is less than zero or exceeds or equals 0.2 s.
	 */
	public void advanceTime(float duration) throws IllegalArgumentException {
		if (duration < 0 || duration >= 0.2)
			throw new IllegalArgumentException();
		restTimer += duration;
		if (experiencePoints >=10){
			if (isValidStrength(this.getStrength()+1))
				this.setStrength(this.getStrength()+1);
			else if (isValidAgility(this.getAgility()+1))
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
		// moet nog documentatie van experience points en falling
		if (mustRest())
			rest();
		else if (this.getStatus() == Status.DONE && targetPosition != null && !this.isEnableDefaultBehaviour())
			moveTo(targetPosition);
		else if (this.isEnableDefaultBehaviour() && this.getStatus() == Status.DONE)
			startDefaultBehaviour();
		else if (this.getStatus() == Status.MOVING) {
			moving(duration);
		}
		else if (this.getStatus() == Status.WORKING) {
			working(duration);
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
	}
	
	private void fall() {
		setStatus(Status.FALLING);
		this.nextTargetPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
		this.startPosition = this.getPosition();
	}

	private boolean mustFall() {
		for (int i = -1; i <2 ; i ++){
			for (int j = -1; j<2 ; j++){
				for (int k = -1; k <2; k++){
					double[] ijk = new double[] {(double) i, (double) j, (double) k};
					double[] neighbouring = Vector.vectorAdd(this.getPosition(),ijk);
					if (!this.getWorld().getTerrain(neighbouring).isPassable()){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private void falling(double duration){
		double[] v = new double[] {0.0,0.0,-3.0};
		setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
		if (Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
			setPosition(nextTargetPosition);
			setHitPoints(this.getHitpoints() - 10);
			double[] nextPosition = Vector.vectorAdd(this.getPosition(), new double[] {0.0,0.0,-1.0});
			if (!this.getWorld().getTerrain(nextPosition).isPassable() ||nextPosition[2]<1.0){
				setStatus(Status.DONE);
			}
			else
				fall();
		}
	}
	
//	private boolean isFalling(){
//		return this.isFalling;
//	}
	
//	private void setFalling(boolean falling){
//		this.isFalling = falling;
//	}
	

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
	 * 			is moving and currently in the center of a cube.
	 * 		   | result == (status == Status.RESTING || status == Status.DONE || status == Status.IN_CENTER)
	 */
	public boolean canMove() {
		if (this.getStatus() == Status.RESTING || this.getStatus() == Status.DONE || this.getStatus() == Status.IN_CENTER || this.getStatus() == Status.FALLING)
			return true;
		return false;
	}

	
	/**
	 * Enable sprinting mode for the given unit.
	 * 
	 * @post If the unit is currently moving and the unit's stamina points are greater then zero,
	 * 		 this new unit is not sprinting.
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
	 * @post isSprinting() for this new unit is false.
	 * 		 | new.isSprinting() == false
	 */
	public void stopSprinting() {
		isSprinting = false;
	}



	/**
	 * Check if the unit is sprinting.
	 * 
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
	 * 			If the unit needs to move in a direction outside the game world.
	 * 			| !isValidPosition(new double[] { getPosition()[0] + (double) dx, getPosition()[1] + (double) dy,
					getPosition()[2] + (double) dz })
	 */
	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {
		if (!((dx >= -1 && dx <= 1) && (dy >= -1 && dy <= 1) && (dz >= -1 && dz <= 1)))
			throw new IllegalArgumentException();
		if (!isValidPosition(new double[] { getPosition()[0] + (double) dx, getPosition()[1] + (double) dy,
				getPosition()[2] + (double) dz }))
			throw new IllegalArgumentException();
		if (canMove()) {
			startPosition = new double[] {this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]};
			nextTargetPosition = World.getCubeCenter(new double[] {this.getCubePosition()[0] + (double) dx , this.getCubePosition()[1] 
				+ (double) dy , this.getCubePosition()[2] + (double) dz });
			setStatus(Status.MOVING);
			setWalkingSpeed(dz);
		}
	}

	/**
	 * Start moving the given unit to the given cube.
	 * 
	 * @param targetPosition
	 *         the position to move to
	 * @post If this unit can move, this new unit's target position is targetPosition and this units new status is in-center
	 * 		   | if (canMove())
	 * 		   |	then new.targetPosition == targetPosition && new.status == Status.IN_CENTER
	 * @effect If this unit can move, the variables x, y and z  register
	 * 			if this unit needs to go further (=1) in the x-, y-, z-direction or
	 * 			if this unit needs to go back (=-1) in the x-, y-, z-direction or 
	 * 			if this unit needs to stay (=0) at the same x-, y-, z-coordinate. 
	 * 			The unit will move to the appropriate neighboring cube.
	 * 		   | if (canMove()) 
	 * 		   |		 if (Util.fuzzyEquals(targetPosition[0] - this.getPosition()[0], 0))
	 * 		   |			then x = 0
	 * 		   |		 if (Math.signum(targetPosition[0] - this.getPosition()[0]) == -1)
	 * 		   |		 	then x = -1
	 * 		   |		 if (Math.signum(targetPosition[0] - this.getPosition()[0]) == 1)
	 * 		   |			then x = 1
	 * 		   |		 if (Util.fuzzyEquals(targetPosition[1] - this.getPosition()[1], 0))
	 * 		   |			then y = 0
	 * 		   |		 if (Math.signum(targetPosition[1] - this.getPosition()[1]) == -1)		
	 * 		   |			then y = -1
	 * 		   |		 if (Math.signum(targetPosition[1] - this.getPosition()[1]) == 1)		
	 * 		   |			then y = 1
	 * 		   |		 if (Util.fuzzyEquals(targetPosition[2] - this.getPosition()[2], 0))
	 * 		   |			then z = 0
	 * 		   |		 if (Math.signum(targetPosition[2] - this.getPosition()[2]) == -1)
	 * 		   |			then z = -1
 	 * 		   |		 if (Math.signum(targetPosition[2] - this.getPosition()[2]) == 1)
	 * 		   |		 	then z = 1
	 * 		   |		moveToAdjacent(x,y,z)
	 * @throws IllegalArgumentException
	 *             The given targetPosition is not a valid targetPosition for a
	 *             unit. 
	 *             | ! isValidPosition(targetPosition)
	 */
	public void moveTo(double[] targetPosition) throws IllegalArgumentException {
		if (!isValidPosition(targetPosition))
			throw new IllegalArgumentException();
		
		if (canMove()) {
			this.targetPosition = targetPosition;
			setStatus(Status.IN_CENTER);
			int x = 0;
			int y = 0;
			int z = 0;

			if (Util.fuzzyEquals(targetPosition[0] - this.getPosition()[0], 0))
				x = 0;
			else if (Math.signum(targetPosition[0] - this.getPosition()[0]) == -1)
				x = -1;
			else
				x = 1;
			if (Util.fuzzyEquals(targetPosition[1] - this.getPosition()[1], 0))
				y = 0;
			else if (Math.signum(targetPosition[1] - this.getPosition()[1]) == -1)
				y = -1;
			else
				y = 1;
			if (Util.fuzzyEquals(targetPosition[2] - this.getPosition()[2], 0))
				z = 0;
			else if (Math.signum(targetPosition[2] - this.getPosition()[2]) == -1)
				z = -1;
			else
				z = 1;
			moveToAdjacent(x, y, z);

		}
	}

	
	private Queue<int[]> queue =  new LinkedList<int[]>();
	private Queue<int[]> queuePos = new LinkedList<int[]>();
	
	public void search(int[] array){
		int[] position = {array[0], array[1], array[2]};
		int n = array[3];
		List<int[]> neighboringCubes = this.getWorld().getNeighboringCubes(position);
		for (int index= 0; index< neighboringCubes.size(); index++){
			int[] currentNeighbour = neighboringCubes.get(index);
			if( this.getWorld().getPassable(currentNeighbour) && 
					this.getWorld().isNeighboringSolidTerrain(currentNeighbour)){
				boolean toAdd = true;
				for (int[] queueArray: queue){
					if (queueArray[0]==currentNeighbour[0]&& queueArray[1] == currentNeighbour[1] 
							&& queueArray[2] == currentNeighbour[2] && queueArray[3]<n)
						toAdd = false;
				}
				if (toAdd){
					queuePos.add(currentNeighbour);
					queue.add(new int[] {currentNeighbour[0],currentNeighbour[1],currentNeighbour[2],n+1});
				}
				
			}
		}
	}
	
	public void moveTo1(double[] targetPosition){
		if (!isValidPosition(targetPosition))
			throw new IllegalArgumentException();
		if (canMove()) {
			int index = 0;
			int[] nextPosition;
			while (this.getPosition() != targetPosition){
				int[] position = {(int) targetPosition[0], (int) targetPosition[1], (int) targetPosition[2]};
				int[] positionn = {(int) targetPosition[0], (int) targetPosition[1], (int) targetPosition[2], 0};
				queue.add(positionn);
				queuePos.add(position);
				while(!queuePos.contains(this.getPosition()) && queue.size()>index){ 
 					nextPosition = ((LinkedList<int[]>) queue).get(index);
					search(nextPosition);
					index = index+1;
				}
			if( queuePos.contains(this.getPosition())){
				int[] candidateNextArray = null;
				for (int[] array: queue){
					double[] nextPos = {(double)array[0], (double)array[1], (double)array[2]};
					int n = array[3];
					if (this.isNeighbouringCube(nextPos))
						if (candidateNextArray == null || candidateNextArray[3]> n)
							candidateNextArray = array;
				}
				moveToAdjacent(candidateNextArray[0]-this.getCubeCoordinate()[0],
						candidateNextArray[1]-this.getCubeCoordinate()[1],candidateNextArray[2]-this.getCubeCoordinate()[2]);
			}
			}
		}	
	}
	
	public void moving1(double duration){
		double d = Vector.getDistance(nextTargetPosition,startPosition);
		double[] v = new double[] {getCurrentSpeed()*(nextTargetPosition[0]-startPosition[0])/d,
				getCurrentSpeed()*(nextTargetPosition[1]-startPosition[1])/d,
				getCurrentSpeed()*(nextTargetPosition[2]-startPosition[2])/d};
		//double[] v1 = new double[] Vector.scalarMultiplication(Vector.vectorReduction(nextTargetPosition, startPosition), getCurrentSpeed()/d);
		
		setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
		setOrientation((float) Math.atan2(v[1],v[0]));
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
		}
		else if (nextTargetPosition != null && Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
			setExperiencePoints(this.getExperiencePoints()+1);
			setPosition(nextTargetPosition);
			if (targetPosition != null){
				//System.out.println("moveto" + targetPosition +"huidige loc" + this.getPosition());
				setStatus(Status.IN_CENTER);
				moveTo(targetPosition);
			}
			else
				setStatus(Status.DONE);
		}
	}

	/**
	 * Start moving the given unit to the given cube, given as an array of integers.
	 * @param cubePosition
	 * 			The coordinate of the cube to move to.
	 * @effect The new position of the unit is the center of the given cube.
	 * 		   | moveTo(new double[] { (double) cubePosition[0] + L/2, (double) cubePosition[1] + L/2,
	 * 		   |	(double) cubePosition[2] + L/2 })  			
	 */
	public void moveTo(int[] cubePosition) throws IllegalArgumentException{
		moveTo(World.getCubeCenter(cubePosition));
	}
	
	public void moving(double duration){
		double d = Vector.getDistance(nextTargetPosition,startPosition);
		double[] v = new double[] {getCurrentSpeed()*(nextTargetPosition[0]-startPosition[0])/d,
				getCurrentSpeed()*(nextTargetPosition[1]-startPosition[1])/d,
				getCurrentSpeed()*(nextTargetPosition[2]-startPosition[2])/d};
		//double[] v1 = new double[] Vector.scalarMultiplication(Vector.vectorReduction(nextTargetPosition, startPosition), getCurrentSpeed()/d);
		
		setPosition(Vector.vectorAdd(this.getPosition(), Vector.scalarMultiplication(v, duration)));
		setOrientation((float) Math.atan2(v[1],v[0]));
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
			targetPosition = null;
			setExperiencePoints(this.getExperiencePoints()+1);
		}
		else if (nextTargetPosition != null && Vector.getDistance(nextTargetPosition, startPosition)-Vector.getDistance(startPosition, this.getPosition())<=0.0){
			setExperiencePoints(this.getExperiencePoints()+1);
			setPosition(nextTargetPosition);
			if (targetPosition != null){
				//System.out.println("moveto" + targetPosition +"huidige loc" + this.getPosition());
				setStatus(Status.IN_CENTER);
				moveTo(targetPosition);
			}
			else
				setStatus(Status.DONE);
		}
	}
	
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
	 * @return True if and only if the unit wasn't currently moving or 
	 * 			wasn't initial resting or wasn't attacking an other unit.
	 * 		   | result == (status != Status.MOVING && status != Status.INITIAL_RESTING && status != Status.ATTACKING)
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
	 */
	public void work(int[] position) {
		if(!(this.isNeighbouringCube(World.getCubeCenter(position))||this.getCubeCoordinate()==position))
			throw new IllegalArgumentException();
		else if (canWork()){
			setStatus(Status.WORKING);
			workTargetPosition = position;
			workingTime = (float) 0.0;
			totalWorkingTime = (float) 500.0 / this.getStrength();
			progressWork = (float) 0.0;
		}

	}
	
	public void working(double duration) {
		if (workingTime < totalWorkingTime) {
			workingTime += duration;
			progressWork = workingTime / totalWorkingTime;
		} else {
			progressWork = (float) 1.0;
			endWork(workTargetPosition);
		}
	}
	
	public void endWork(int[] targetPosition) {
		if (this.getBoulder() !=null) {
			this.getWorld().addAsBoulder(this.getBoulder());
			this.getBoulder().setPosition(World.getCubeCenter(targetPosition));
			this.setWeight(this.getWeight()-this.getBoulder().getWeight());
			this.setBoulder(null);
			setExperiencePoints(this.getExperiencePoints()+10);

		}
		else if (this.getLog() !=null) {
			this.getWorld().addAsLog(this.getLog());
			this.getLog().setPosition(World.getCubeCenter(targetPosition));
			this.setWeight(this.getWeight()-this.getLog().getWeight());
			this.setLog(null);
			setExperiencePoints(this.getExperiencePoints()+10);

		}
		else if (this.getWorld().getTerrain(targetPosition) == TerrainType.WORKSHOP 
				&& !this.getWorld().getBoulders(targetPosition).isEmpty() 
				&& !this.getWorld().getLogs(targetPosition).isEmpty()) {
			Log log = (Log) this.getWorld().inspectCube(targetPosition).get(2).get(0);
			Boulder boulder = (Boulder) this.getWorld().inspectCube(targetPosition).get(3).get(0);
			this.getWorld().removeAsBoulder(boulder);
			boulder.terminate();
			this.getWorld().removeAsLog(log);
			log.terminate();
			this.setWeight(this.getWeight()+1);
			this.setToughness(this.getToughness()+1);
			setExperiencePoints(this.getExperiencePoints()+10);

		}
		
		else if (!this.getWorld().getBoulders(targetPosition).isEmpty()){
			Boulder boulder = (Boulder) this.getWorld().inspectCube(targetPosition).get(3).get(0);
			this.setBoulder(boulder);
			this.getWorld().removeAsBoulder(boulder);
			boulder.setWorld(null);
			this.setWeight(this.getWeight()+boulder.getWeight());
			setExperiencePoints(this.getExperiencePoints()+10);


		}

		else if (!this.getWorld().getLogs(targetPosition).isEmpty()){
			Log log = (Log) this.getWorld().inspectCube(targetPosition).get(2).get(0);
			this.getWorld().removeAsLog(log);
			log.setWorld(null);
			this.setLog(log);
			this.setWeight(this.getWeight()+log.getWeight());
			setExperiencePoints(this.getExperiencePoints()+10);

			
		}
		else if (this.getWorld().getTerrain(targetPosition)== TerrainType.TREE){
			this.getWorld().setTerrain(targetPosition, TerrainType.AIR);
			if( new Random().nextDouble()<=0.25){
				// nieuwe log creeeren en verbinden met de wereld. Weet niet of dit helemaal correct is.
				// targetPosition in het centrum van een cube? 
				Log log = new Log(targetPosition);
				this.getWorld().addAsLog(log);
				setExperiencePoints(this.getExperiencePoints()+10);

			}
		}
		else if (this.getWorld().getTerrain(targetPosition)== TerrainType.ROCK){
			this.getWorld().setTerrain(targetPosition, TerrainType.AIR);
			if( new Random().nextDouble()<=0.25){
				// nieuwe boulder creeeren en verbinden met de wereld. Weet niet of dit helemaal correct is.
				// targetPosition in het centrum van een cube? 
				Boulder boulder = new Boulder(targetPosition);
				this.getWorld().addAsBoulder(boulder);
				setExperiencePoints(this.getExperiencePoints()+10);

			}
		}
		setStatus(Status.DONE);
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
	
	private int[] workTargetPosition;

	/**
	 * Check whether it's possible for a unit to attack.
	 * 
	 * @return True if the unit is not currently moving.
	 * 		   | result == (status != Status.MOVING)
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
	 * 			If the unit that will be attacked isn't positioned in a neighboring cube.
	 * 			| (!isNeighbouringCube(other.getCubePosition()))
	 */
	public void attack(Unit other) throws IllegalArgumentException {
		if (!(isNeighbouringCube(other.getCubePosition())|| other.getCubeCoordinate() == this.getCubeCoordinate()) 
				|| other.isTerminated()|| other.getFaction() == this.getFaction())
			throw new IllegalArgumentException();
		if (canAttack()) {
			setStatus(Status.ATTACKING);
			this.setOrientation((float) Math.atan2(other.getPosition()[1] - this.getPosition()[1],
					other.getPosition()[0] - this.getPosition()[0]));
			other.defend(this);
			attackTimer = 0.0;
		}
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
	 * @effect Change the orientation of this unit so that this new unit faces the attacking unit.
	 * 		   | this.setOrientation((float) Math.atan2(unit.getPosition()[1] - this.getPosition()[1],
	 *		   |	unit.getPosition()[0] - this.getPosition()[0]))
	 * @effect If this unit is able to dodge, his position is set to a random neighbhouring cube.
	 * 		   | if (new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility()))
	 * 		   |	this.setPosition(new double[] {this.getPosition()[0]+ (double)(-1 + (new Random().nextInt(3))),
	 *		   |			this.getPosition()[1]+ (double)(-1 + (new Random().nextInt(3))),
	 *		   |			this.getPosition()[2]+ (double)(-1 + (new Random().nextInt(3)))});
	 * @effect If this unit isn't able to dodge or to block the attack, this unit will lose hitpoints equal to 
	 * 			to the attacking unit's strength divided by 10. If the hitpoints would go under zero,
	 * 			they will be set to zero.
	 * 		   | if (!(new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility()))
	 * 		   |	this.setPosition(new double[] {this.getPosition()[0]+ (double)(-1 + (new Random().nextInt(3))) &&
	 * 		   | 	!(new Random().nextDouble() <= 0.25*
	 *	 	   |	((this.getStrength() + this.getAgility()) / (unit.getStrength() + unit.getAgility())))){
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
	
	public boolean canDefend(){
		return (this.getStatus() != Status.FALLING && !this.isTerminated());
	}

	/**
	 * Checks whether the unit needs to rest.
	 * 
	 * @return True if and only if 3 minutes of game time have passed or the stamina points or hitpoints or equal to zero.
	 * 		   | result == (restTimer >= 180)
	 */
	public boolean mustRest() {
		if (restTimer >= 180 )
			return true;
		return false;
	}
	/**
	 * Check whether it's possible for a unit to rest.
	 * @return True if and only if this unit's stamina points or hitpoints are equal to zero, 
	 * 			and if this unit is currently doing nothing or is resting or 
	 * 			working or is moving and currently in the center of a cube.
	 * 		   | result == (this.getStaminaPoints() <= 0 || this.getHitpoints() <= 0)&&
	 * 		   |		(status == Status.DONE || status == Status.RESTING || 
	 * 		   | 		status == Status.WORKING || status == Status.IN_CENTER)
	 */
	public boolean canRest() {
		if(this.getStaminaPoints() <= this.getMaxPoints() || this.getHitpoints() <= this.getMaxPoints())
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
	
	public void initialResting(double duration) {
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
	
	public void resting(double duration){
		if (this.getHitpoints() < getMaxPoints())
			this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints());
		else if (this.getStaminaPoints() < getMaxPoints()) {
			this.setHitPoints(getMaxPoints());
			this.setStaminaPoints((getToughness() / 100.0) * 5 * duration + getStaminaPoints());
		} else {
			this.setStaminaPoints(getMaxPoints());
			setStatus(Status.DONE);
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
	public void setEnableDefaultBehaviour(boolean enableDefaultBehaviour) {
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
	 * @effect If this unit's status is done, it will do randomly walks to a random position
	 * 			 - this new unit's status is moving and this sprints to a random position
	 * 			 - this unit starts working
	 * 			 - this unit starts resting
	 * 		   | (new.status == Status.MOVING && startSprinting() && moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
	 *		   |		 (new Random().nextDouble()) * 50 }))
	 *		   |OR (new.status = Status.MOVING && stopSprinting() &&
	 *		   |		moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
	 *		   |		 (new Random().nextDouble()) * 50 }))
	 *		   |OR work()
	 *		   |OR rest()	   
	 */
	public void startDefaultBehaviour() {
		if (this.getStatus() == Status.DONE) {
			setEnableDefaultBehaviour(true);
			Set<Unit> potentialEnemies = new HashSet<>();
			List<int[]> potEnemyPos = World.getNeighboringCubes(this.getCubeCoordinate());
			potEnemyPos.add(this.getCubeCoordinate());		
			for (int[] neighbouringCube: potEnemyPos)
				for(Unit other: this.getWorld().getUnits(neighbouringCube)){
					if (other.getFaction() != this.getFaction())
						potentialEnemies.add(other);
				}
			int i = 0;
			if (potentialEnemies.size()==0){
				i = new Random().nextInt(5);
			}
			else{
				i = new Random().nextInt(4);
			} 
			if (i == 0) {
				setStatus(Status.IN_CENTER);
				moveTo(new double[] { (new Random().nextDouble()) * this.getWorld().getxDimension(), 
						(new Random().nextDouble()) * this.getWorld().getyDimension(),
						(new Random().nextDouble()) * this.getWorld().getzDimension() });
				startSprinting();
			}
			if (i == 1) {
				setStatus(Status.IN_CENTER);
				moveTo(new double[] { (new Random().nextDouble()) * this.getWorld().getxDimension(), 
						(new Random().nextDouble()) * this.getWorld().getyDimension(),
						(new Random().nextDouble()) * this.getWorld().getzDimension() });
				stopSprinting();
			}
			if (i == 2){
				List<int[]> neighbouring = World.getNeighboringCubes(this.getCubeCoordinate());
				neighbouring.add(this.getCubeCoordinate());
				i = new Random().nextInt(neighbouring.size());
				work(neighbouring.get(i));
			}
			if (i == 3){
				rest();
			}
			if (i==4){
				attack(potentialEnemies.iterator().next());
			}
				
		} else
			enableDefaultBehaviour = false;

	}

	/**
	 * Makes the unit stop with his default behaviour.
	 * 
	 * @post enableDefaultBehaviour of this new unit is false and this new unit's status is set to done 
	 * 			and this unit's targetPosition and nextTargetPosition are null.
	 * 		 | !new.isEnableDefaultBehaviour() && new.status == Status.DONE && new.targetPosition == null && new.nextTargetPosition == null
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
 	 * Terminate this unit.
 	 *
 	 * @post   This unit  is terminated.
 	 *       | new.isTerminated()
 	 * @post   ...
 	 *       | ...
 	 */
 	 public void terminate() {
 		 
 		 if (this.getBoulder() != null)
 			 this.getBoulder().setPosition(this.getPosition());
 			 getWorld().addAsBoulder(this.getBoulder());
 		 this.setBoulder(null);
 		 if (this.getLog()!= null)
 			 this.getLog().setPosition(this.getPosition());
 			 getWorld().addAsLog(this.getLog());
 		 this.setLog(null);
 		 this.getWorld().removeAsUnit(this);
		 this.setWorld(null);
 		 this.getFaction().removeAsUnit(this);
 		 this.setFaction(null);
 		 this.setStatus(Status.DONE);
 		 this.isTerminated = true;
 	 }
 	 
 	 /**
 	  * Return a boolean indicating whether or not this unit
 	  * is terminated.
	  */
 	 @Basic @Raw
 	 public boolean isTerminated() {
 		 return this.isTerminated;
 	 }
 	 
 	 /**
 	  * Variable registering whether this unit is terminated.
 	  */
 	 private boolean isTerminated = false;
  	
 	@Basic @Raw
 	public Boulder getBoulder() {
 		return this.boulder;
 	}
 	// Hier de voorwaarde dat de boulder in dezelfde cube als de unit moet zijn. Maakt de methode wel niet 
 	// meer static. 
 	public boolean isValidBoulder(Boulder boulder){
 		return (boulder == null) || (boulder.getPosition() == this.getPosition());
 	}
 	
 	@Raw
 	public void setBoulder(Boulder boulder) throws IllegalArgumentException{
 		if(! isValidBoulder(boulder))
 			throw new IllegalArgumentException();
 		this.boulder = boulder;
 	}
 	
 	private Boulder boulder;
 	
 	@Basic @Raw
 	public Log getLog() {
 		return this.log;
 	}
 	// Hier de voorwaarde dat de log in dezelfde cube als de unit moet zijn. Maakt de methode wel niet 
 	// meer static. 
 	public boolean isValidLog(Log log){
 		return (log == null) || (log.getPosition() == this.getPosition());
 	}
 
	@Raw
	public void setLog(Log log) throws IllegalArgumentException{
 		if(! isValidLog(log))
 			throw new IllegalArgumentException();
 		this.log = log;
 	}
 	
 	private Log log;
	
	public int getExperiencePoints(){
		return this.experiencePoints;
	}
	
	private void setExperiencePoints(int points){
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
	
	
	
	
}