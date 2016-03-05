package hillbillies.model;

import java.util.Random;

import be.kuleuven.cs.som.annotate.*;
import ogp.framework.util.ModelException;
import ogp.framework.util.Util;

/**
 * A class of Units, with a name, weight, strength, agility, toughness and a
 * position in the game world.
 * 
 * @invar Each unit is positioned inside the game world.
 *        |isValidPosition(this.getPosition())
 * @invar The name of each unit must be a valid name for any unit.
 *        |isValidName(this.getName())
 * @invar Each unit can have its strength as strength . |
 *        canHaveAsStrength(this.getStrength())
 * @invar Each unit has a valid agility (for any unit).
 *        |isValidAgility(this.getAgility())
 * @invar Each unit has a valid toughness (for any unit).
 *        |isValidToughness(this.getToughness())
 * @invar Each unit can have its weight as its weight.
 *        |canHaveAsWeight(this.getWeight())
 * @invar Each unit can have its number of stamina- and hitpoints as its number
 *        of stamina- and hitpoints. |canHaveAsPoints(this.getHitpoints()) &&
 *        canHaveAsPoints(this.getStaminaPoints())
 * @invar Each unit has an orientation that is valid for any unit.
 *        |isValidOrientation(this.getOrientation()) //we gaan zeggen dat tss 0
 *        en PI*2 moet liggen
 * @invar The duration is a valid duration for any unit.
 *        |isValidDuration(this.getDuration())
 * @invar The targetPosition of each unit must be a valid targetPosition for any
 *        unit. | isValidTargetPosition(getTargetPosition())
 * @version 1.0
 * @author adminheremans
 */
public class Unit {

	/**
	 * Initialize this new unit with a given name, position, weight, strength,
	 * agility, toughness, state of default behaviour, hitpoints, staminapoints
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
	 *            The state of default behaviour for this new Unit.
	 * @param position
	 *            The position of this new Unit.
	 * @param hitpoints
	 *            The hitpoints for this new Unit.
	 * @param staminapoints
	 *            The staminapoints for this new Unit.
	 * @param orientation
	 *            The orientation of this new Unit.
	 * @effect The name of this new unit is set to the given name. |
	 *         this.setName(name)
	 * @post If the given weight is a valid weight for any Unit, the weight of
	 *       this new Unit is equal to the given weight. Otherwise, the weight
	 *       of this new unit is equal to 25. | if (isValidWeight(Weight)) |
	 *       then new.getWeight() == Weight | else new.getWeight() == 25
	 * @post If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength.
	 *       Otherwise, the strength of this new unit is equal to 25. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength |
	 *       else new.getStrength() == 25
	 * @post If the given agility is a valid agility for any unit, the agility
	 *       of this new unit is equal to the given agility. Otherwise, the
	 *       agility of this new unit is equal to 25. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility | else
	 *       new.getAgility() == 25
	 * @post If the given toughness is a valid toughness for any unit, the
	 *       toughness of this new unit is equal to the given toughness.
	 *       Otherwise, the toughness of this new unit is equal to 25. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness | else new.getToughness() == 25
	 * @effect The position of this new unit is set to the given position. |
	 *         this.setPosition(position)
	 * @pre This new unit can have the given hitpoints as its hitpoints. |
	 *      canHaveAsHitpoints(hitpoints)
	 * @post The hitpoints of this new unit is equal to the given hitpoints. |
	 *       new.getHitpoints() == hitpoints
	 * @pre The given the number of staminapoints must be a valid the number of
	 *      staminapoints for any unit. | canHaveAsStaminaPoints(the number of
	 *      staminapoints)
	 * @post The the number of staminapoints of this new unit is equal to the
	 *       given the number of staminapoints. | new.getStaminaPoints() ==
	 *       staminaPoints
	 * @effect The orientation of this new unit is set to the given orientation
	 *         | this.setOrientation(orientation)
	 */

	@Raw
	public Unit(String name, double[] position, int weight, int strength, int agility, int toughness,
			boolean enableDefaultBehavior, double hitpoints, double staminaPoints, double orientation)
					throws IllegalArgumentException {

		if (!isValidStrength(strength))
			strength = 25;
		setStrength(strength);
		if (!isValidAgility(agility))
			agility = 25;
		setAgility(agility);
		if (!isValidToughness(toughness))
			toughness = 25;
		setToughness(toughness);
		if (!canHaveAsWeight(weight))
			weight = 25;
		setWeight(weight);
		setOrientation((float) orientation);
		assert this.canHaveAsHitpoints(hitpoints);
		this.setHitPoints(hitpoints);
		this.setStaminaPoints(staminaPoints);
		this.setName(name);
		this.setPosition(position);

	}

	@Raw
	public Unit(String name, double[] position, int weight, int strength, int agility, int toughness,
			boolean enableDefaultBehavior) {
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
	 * @return | result == (weight >= (strength + agility)/2)
	 */
	@Raw
	public boolean canHaveAsWeight(int weight) {
		return (float) weight >= ((float) this.strength + (float) this.agility) / 2;
	}

	/**
	 * Set the weight of this unit to the given weight.
	 * 
	 * @param weight
	 *            The new weight for this unit.
	 * @post If the given weight is not a valid weight for any unit, the weight
	 *       of this new unit is equal to the weight of this unit.
	 *       |if(!canHaveAsWeight(weight)) | then new.getWeight() ==
	 *       this.getWeight()
	 */
	@Raw
	public void setWeight(int weight) {
		if (canHaveAsWeight(weight))
			this.weight = weight;
	}

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
	 * @return | result == ((25<= strength) && (strength<=100))
	 */
	@Raw
	public static boolean isValidStrength(int strength) {
		return (25 <= strength) && (strength <= 100);
	}

	/**
	 * Set the strength of this unit to the given strength.
	 * 
	 * @param strength
	 *            The new strength for this unit.
	 * @post If the given strength is a valid strength for any unit, the
	 *       strength of this new unit is equal to the given strength. | if
	 *       (isValidStrength(strength)) | then new.getStrength() == strength
	 */
	@Raw
	public void setStrength(int strength) {
		if (isValidStrength(strength))
			this.strength = strength;
	}

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
	 * @return | result == ((25<=agility) && (agility<=100))
	 */
	@Raw
	public static boolean isValidAgility(int agility) {
		return ((25 <= agility) && (agility <= 100));
	}

	/**
	 * Set the agility of this unit to the given agility.
	 * 
	 * @param agility
	 *            The new agility for this unit.
	 * @post If the given agility is a valid agility for any unit, the agility
	 *       of this new unit is equal to the given agility. | if
	 *       (isValidAgility(agility)) | then new.getAgility() == agility
	 */
	@Raw
	public void setAgility(int agility) {
		if (isValidAgility(agility))
			this.agility = agility;
	}

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
	 * @return | result == ((25<=toughness) && (toughness<=100))
	 */
	public static boolean isValidToughness(int toughness) {
		return ((25 <= toughness) && (toughness <= 100));
	}

	/**
	 * Set the toughness of this unit to the given toughness.
	 * 
	 * @param toughness
	 *            The new toughness for this unit.
	 * @post If the given toughness is a valid toughness for any unit, the
	 *       toughness of this new unit is equal to the given toughness. | if
	 *       (isValidToughness(toughness)) | then new.getToughness() ==
	 *       toughness
	 */
	@Raw
	public void setToughness(int toughness) {
		if (isValidToughness(toughness))
			this.toughness = toughness;
	}

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
	 * @return The given name is at least two characters long and starts with an
	 *         uppercase letter. The name consists of letters, quotes and
	 *         spaces. | result == !name.length()<2 &&
	 *         Character.isUpperCase(name.charAt(0)) | &&
	 *         (Character.isLetter(name.charAt(index)) ||
	 *         name.charAt(index)=='"' || name.charAt(index)=='\'' ||
	 *         name.charAt(index)==' ')
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
	 * @post The name of this new unit is equal to the given name. |
	 *       new.getName() == name
	 * @throws IllegalArgumentException
	 *             The given name is not a valid name for any unit. | !
	 *             isValidName(getName())
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

	@Basic
	@Raw
	public double getHitpoints() {
		return this.hitpoints;
	}

	/**
	 * Check whether this unit can have the given hitpoints as its hitpoints.
	 * 
	 * @param hitpoints
	 *            The hitPoints to check.
	 * @return | result == (hitpoints < max_nbHitpoints())
	 */
	@Raw
	public boolean canHaveAsHitpoints(double hitpoints) {
		return hitpoints < max_nbPoints();
	}

	/**
	 * Return the maximum value of hitpoints and staminaPoints.
	 * 
	 * @return The maximum value of hitpoints and staminaPoints, given by 200
	 *         times the unit's weight divided by 100 and multiplied with the
	 *         unit's toughness divided by 100. | result ==
	 *         Math.ceil(200.0*(this.getWeight()/100.0)*(this.getToughness()/100
	 *         .0))
	 */
	public double max_nbPoints() {
		return Math.ceil(200.0 * (this.getWeight() / 100.0) * (this.getToughness() / 100.0));
	}

	/**
	 * Set the the number of hitpoints of this unit to the given the number of
	 * hitpoints.
	 * 
	 * @param hitpoints
	 *            The new the number of hitpoints for this unit.
	 * @pre The given the number of hitpoints must be a valid the number of
	 *      hitpoints for any unit. | canHaveAsHitpoints(hitpoints)
	 * @post The the number of hitpoints of this unit is equal to the given the
	 *       number of hitpoints. | new.getHitpoints() == hitpoints
	 */
	@Raw
	private void setHitPoints(double hitpoints) {
		assert canHaveAsHitpoints(hitpoints);
		this.hitpoints = hitpoints;
	}

	/**
	 * Return the number of staminaPoints of this unit.
	 */
	@Basic
	@Raw
	public double getStaminaPoints() {
		return this.staminaPoints;
	}

	/**
	 * Check whether the given the number of staminaPoints is a valid the number
	 * of staminapoints for any unit.
	 * 
	 * @param the
	 *            number of staminapoints The the number of staminapoints to
	 *            check.
	 * @return | result == (staminaPoints < max_nbPoints())
	 */
	public boolean canHaveAsStaminaPoints(double staminaPoints) {
		return staminaPoints < max_nbPoints();
	}

	/**
	 * Set the the number of staminapoints of this unit to the given the number
	 * of staminapoints.
	 * 
	 * @param staminaPoints
	 *            The new the number of staminapoints for this unit.
	 * @pre The given the number of staminapoints must be a valid the number of
	 *      staminapoints for any unit. | canHaveAsStaminaPoints(staminaPoints)
	 * @post The the number of staminapoints of this unit is equal to the given
	 *       the number of staminapoints. | new.getStaminaPoints() ==
	 *       staminaPoints
	 */
	@Raw
	private void setStaminaPoints(double staminaPoints) {
		assert canHaveAsStaminaPoints(staminaPoints);
		this.staminaPoints = staminaPoints;
	}

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
	 *       of this new unit is equal to the given orientation. | if (0 <=
	 *       orientation <= 2*PI) | new.orientation = orientation
	 * @post If the given orientation exceeds 2*PI, the orientation for this new
	 *       unit is equal to the given orientation modulo 2*PI. | if
	 *       (orientation > 2*PI) | new.orientation = orientation % 2*PI
	 * @post If the given orientation is negative, the orientation for this new
	 *       unit is equal to (2*PI + the given orientation modulo 2*PI). | if
	 *       (orientation < 0) | new.orientation = 2*PI + orientation % (2*PI)
	 */
	@Raw
	private void setOrientation(float orientation) {
		if (orientation >= 0.0)
			this.orientation = (float) (orientation % (2 * Math.PI));
		else
			this.orientation = (float) (2 * Math.PI + orientation % (2 * Math.PI));
	}

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

	public static double[] getPosition(int[] cubePosition) {
		return new double[] { (double) cubePosition[0] + 0.5, (double) cubePosition[1] + 0.5,
				(double) cubePosition[2] + 0.5 };
	}

	/**
	 * Return the position of the game world cube in which this unit is
	 * positioned.
	 * 
	 * @return The position of the game world cube in which this unit is
	 *         positioned. | result ==
	 *         {Math.floor(this.getPosition()[0]),Math.floor(this.getPosition()[
	 *         1]),Math.floor(this.getPosition()[2])}
	 */
	public double[] getCubePosition() {

		return new double[] { Math.floor(this.getPosition()[0]), Math.floor(this.getPosition()[1]),
				Math.floor(this.getPosition()[2]) };
	}

	/**
	 * Return the position of the game world cube in which this unit is
	 * positioned, as an array if integers.
	 * 
	 * @effect Gives the position of the game world cube in which this unit is
	 *         positioned.
	 * 		| result == { (int) getCubePosition()[0], (int) getCubePosition()[1], (int) getCubePosition()[2] }
	 */
	public int[] getCubeCoordinate() {
		return new int[] { (int) getCubePosition()[0], (int) getCubePosition()[1], (int) getCubePosition()[2] };
	}

	/**
	 * Check whether the given position is a position inside the game world, a
	 * valid position.
	 * 
	 * @param position
	 * @return True if and only if the x,y and z-coordinate of the position are
	 *         inside the limits of the game world. 
	 *         |result == (0<= position[0])
	 *         | && (position[0] <= X) | && (0<= position[1]) && (position[1] <=Y)
	 *         | && (0<= position[2]) && (position[2] <= Z)
	 */
	public static boolean isValidPosition(double[] position) {
		return 0 <= position[0] && position[0] <= X && 0 <= position[1] && position[1] <= Y && 0 <= position[2]
				&& position[2] <= Z;
	}

	/**
	 * Set the position of this unit to the given position.
	 * 
	 * @param position
	 *            The new position for this unit.
	 * @post The new position for this unit is equal to the given position.
	 *       |new.getPosition() == position
	 * @throws IllegalPositionException(position,this)
	 *             The given position is not a valid position for any unit.
	 *             |!isValidPosition(position)
	 */
	@Raw
	private void setPosition(double[] position) throws IllegalArgumentException {
		if (!isValidPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}

	/**
	 * A variable registering the position of this unit.
	 */
	private double[] position = { 10.0, 10.0, 0.5 };

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
	private double hitpoints = 0;

	/**
	 * A variable registering the number of stamina points of this unit.
	 */
	private double staminaPoints = 0;

	/**
	 * A variable registering the orientation of this unit.
	 */
	private float orientation = (float) ((float) Math.PI / 2.0);

	/**
	 * A variable registering the duration before the units position and
	 * activity status are updated.
	 */
	private float duration = 0;

	/**
	 * Update the position and activity status of a unit.
	 * 
	 * @param duration
	 *            The game time after which advanceTime is called.
	 * @effect If this unit must rest, the new unit shall rest.
	 * @effect If this unit is doing nothing, but he hasn't reached his
	 *         targetPosition, he shall resume moving to the targetPosition.
	 * @effect If this units default behaviour is enabled, and this unit is
	 *         doing nothing, he shall start default behaviour.
	 * @effect If this unit is moving, set the position of this new unit to the
	 *         updated position (the old position + this unit's speed *
	 *         duration) and set the orientation of this new unit to the
	 *         direction in which he is going. If this unit is sprinting,
	 *         decrease the stamina points with one per 0.1 s. If the stamina
	 *         points are equal to or less than zero, this unit stops sprinting
	 *         and its stamina points are set to 0. If this unit has arrived to
	 *         the target position, its new position is the target position, its
	 *         status is DONE and its new target position is null. If this unit
	 *         has arrived to the next target position (the center of the next
	 *         cube), its new position is the next target position and it moves
	 *         to the target position.
	 * 
	 * @post If this unit is working, and if his task is not completed, the new
	 *       working time is increased with duration, and the progress is
	 *       updated. Else if the unit is working and the task is completed, the
	 *       new status is DONE.
	 * @post If the status of this unit is initial resting and if his recoveredHitpoints are equal to or greater then 1
	 * 			or his hitpoints are equal to or greater then the maximum value, the unit's status will be updated to resting
	 * 			and his hitpoints will be set to the maximum value. Else his hitpoints will be increased 
	 * @effect If this unit is working
	 * @throws IllegalArgumentException
	 *             If the duration is less than zero or exceeds or equals 0.2 s.
	 */
	public void advanceTime(float duration) throws IllegalArgumentException {
		if (duration < 0 || duration >= 0.2)
			throw new IllegalArgumentException();
		restTimer += duration;
		if (mustRest())
			rest();
		if (status == Status.DONE && targetPosition != null && !this.isEnableDefaultBehaviour())
			moveTo(targetPosition);
		if (this.isEnableDefaultBehaviour() && status == Status.DONE)
			startDefaultBehaviour();
		else if (status == Status.MOVING) {
			double d = getDistance(getCubeCenter(nextTargetPosition),startPosition);
			double[] v = new double[] {getCurrentSpeed()*(nextTargetPosition[0]-startPosition[0])/d,
					getCurrentSpeed()*(nextTargetPosition[1]-startPosition[1])/d,
					getCurrentSpeed()*(nextTargetPosition[2]-startPosition[2])/d};
			
			setPosition(new double[] {this.getPosition()[0]+v[0]*duration,this.getPosition()[1]+v[1]*duration,this.getPosition()[2]+v[2]*duration});
			setOrientation((float) Math.atan2(v[1],v[0]));
			if (isSprinting){
	
				if (Util.fuzzyLessThanOrEqualTo(this.getStaminaPoints()-10.0*duration,0.0)){
			
					setStaminaPoints(0.0);
					stopSprinting();
				} else {
					setStaminaPoints(this.getStaminaPoints() - 10.0 * duration);
					startSprinting();
				}
			}
			System.out.println("position " + this.getPosition()[0] +","+ this.getPosition()[1]+","+ this.getPosition()[2]);
			System.out.println(getDistance(nextTargetPosition, startPosition)-getDistance(startPosition, this.getPosition()));

			if (targetPosition != null){
				if (getDistance(targetPosition, startPosition)-getDistance(startPosition, this.getPosition())<0){
					setPosition(targetPosition);
					targetPosition = null;
					status = Status.DONE;
				
			}
			
			}
			else if (getDistance(nextTargetPosition, startPosition)-getDistance(startPosition, this.getPosition())<0){

				setPosition(nextTargetPosition);
				if (targetPosition != null)
					moveTo(targetPosition);
				else
					status = Status.DONE;

			}
		}

		else if (status == Status.WORKING) {
			if (workingTime < totalWorkingTime) {
				workingTime += duration;
				progressWork = workingTime / totalWorkingTime;
			} else {
				progressWork = (float) 1.0;
				status = Status.DONE;
			}
		} 
		else if (status == Status.INITIAL_RESTING) {

			if (recoveredHitpoints >= 1.0 || this.getHitpoints() >= max_nbPoints()) {
				this.setHitPoints(max_nbPoints());
				status = Status.RESTING;
			} else {
				this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints());
				recoveredHitpoints += (getToughness() / 200.0) * 5 * duration;
			}
		}

		else if (status == Status.RESTING) {
			if (this.getHitpoints() < max_nbPoints())
				this.setHitPoints((getToughness() / 200.0) * 5 * duration + getHitpoints());
			else if (this.getStaminaPoints() < max_nbPoints()) {
				this.setHitPoints(max_nbPoints());
				this.setStaminaPoints((getToughness() / 100.0) * 5 * duration + getStaminaPoints());
			} else {
				this.setStaminaPoints(max_nbPoints());
				status = Status.DONE;
			}
		} else if (status == Status.ATTACKING) {
			attackTimer += duration;
			if (attackTimer >= 1.0)
				status = Status.DONE;
		}

	}

	/**
	 * Move a unit to the center of a neighboring cube.
	 * @param dx
	 * 		The amount of cubes to move in the x-direction; should be -1,
	 *            0 or 1. 		
	 * @param dy
	 * 		The amount of cubes to move in the y-direction; should be -1,
	 *            0 or 1.
	 * @param dz
	 * 		The amount of cubes to move in the z-direction; should be -1,
	 *            0 or 1.
	 * @effect The walkingspeed of this unit is set to the given flag. 
	 * 			| setWalkingspeed(dz)
	 * @throws IllegalArgumentException
	 * 			If the unit needs to move more than one cube in the x-, y-, z-direction.
	 * 			| !((dx >= -1 || dx <= 1) && (dy >= -1 || dy <= 1) && (dz >= -1 || dz <= 1))
	 * @throws IllegalArgumentException
	 * 			If the unit needs to move in a direction outside the game world.
	 * 			| !isValidPosition(new double[] { getPosition()[0] + (double) dx, getPosition()[1] + (double) dy,
					getPosition()[2] + (double) dz })
	 */

	public void moveToAdjacent(int dx, int dy, int dz) throws IllegalArgumentException {
		if (!((dx >= -1 || dx <= 1) && (dy >= -1 || dy <= 1) && (dz >= -1 || dz <= 1)))
			throw new IllegalArgumentException();
		if (!isValidPosition(new double[] { getPosition()[0] + (double) dx, getPosition()[1] + (double) dy,
				getPosition()[2] + (double) dz }))
			throw new IllegalArgumentException();
		if (canMove()) {
			status = Status.MOVING;
			startPosition = new double[] {this.getPosition()[0],this.getPosition()[1],this.getPosition()[2]};

			nextTargetPosition = getCubeCenter(new double[] {this.getCubePosition()[0] + (double) dx , this.getCubePosition()[1] 
				+ (double) dy , this.getCubePosition()[2] + (double) dz });

			setWalkingSpeed(dz);
		}

	}
	/**
	 * A variable registering the amount of cubes the unit needs to move in the x-direction.
	 */
	private int dx = 0;
	/**
	 * A variable registering the amount of cubes the unit needs to move in the y-direction.
	 */
	private int dy = 0;
	/**
	 * A variable registering the amount of cubes the unit needs to move in the z-direction.
	 */
	private int dz = 0;
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
	 * A variable registering the status of this unit.
	 */
	public Status status = Status.DONE;

	/**
	 * Return the distance between two positions.
	 * @param targetPosition
	 * 		The first position.
	 * @param startPosition
	 * 		The second position.
	 * @return the distance between the first and the second position.
	 * 		| result == Math.sqrt(Math.pow(targetPosition[0] - startPosition[0], 2.0)
	 *		|	+ Math.pow(targetPosition[1] - startPosition[1], 2.0)
	 *		|	+ Math.pow(targetPosition[2] - startPosition[2], 2.0))
	 */
	public double getDistance(double[] targetPosition, double[] startPosition) {
		return Math.sqrt(Math.pow(targetPosition[0] - startPosition[0], 2.0)
				+ Math.pow(targetPosition[1] - startPosition[1], 2.0)
				+ Math.pow(targetPosition[2] - startPosition[2], 2.0));
	}
	
	/**
	 * Return the basespeed of the unit.
	 * @return the basespeed of the unit.
	 * 		| result == 1.5 * (this.getStrength() + this.getAgility()) / (200.0 * (this.getWeight() / 100.0))
	 */
	public double getBaseSpeed() {
		return 1.5 * (this.getStrength() + this.getAgility()) / (200.0 * (this.getWeight() / 100.0));
	}
	
	/**
	 * Check whether the given cube is a neighboring cube of the unit's cubePosition.
	 * @param cubePosition
	 * 		The position of a cube. 
	 * @return True only if the difference between the coordinates of the cubeCenters is equal to 1 or -1. 
	 * 		   | result == (Util.fuzzyEquals(Math.abs(getCubeCentre(cubePosition)[0] - this.getCubePosition()[0]),1.0)
	 *		   |	&& (Util.fuzzyEquals(Math.abs(getCubeCentre(cubePosition)[1] - this.getCubePosition()[1]),1.0))
	 *		   |	&& (Util.fuzzyEquals(Math.abs(getCubeCentre(cubePosition)[1] - this.getCubePosition()[1]),1.0)))
	 */
	public boolean isNeighbouringCube(double[] cubePosition) {
		return (Util.fuzzyEquals(Math.abs(getCubeCenter(cubePosition)[0] - this.getCubePosition()[0]),1.0)
				&& (Util.fuzzyEquals(Math.abs(getCubeCenter(cubePosition)[1] - this.getCubePosition()[1]),1.0))
				&& (Util.fuzzyEquals(Math.abs(getCubeCenter(cubePosition)[1] - this.getCubePosition()[1]),1.0)));
	}

	/**
	 * Return the center of a cube.
	 * 
	 * @param cubePosition
	 *            The position of the cube.
	 * @return The center of the given cube. 
	 * 		   | result == {cubePosition[0]+0.5,cubePosition[1]+0.5,cubePosition[2]+0.5}
	 */
	public double[] getCubeCenter(double[] cubePosition) {
		return new double[] { cubePosition[0] + 0.5, cubePosition[1] + 0.5, cubePosition[2] + 0.5 };

	}

	/**
	 * Set the walkingspeed of this unit to the given walkingspeed.
	 * 
	 * @param dz
	 *            The amount of cubes to move in the z-direction; should be -1,
	 *            0 or 1.
	 * @effect If the unit is moving to a higher cube, the walkingspeed will be
	 *         1.2 times the unit's basespeed.
	 *         | if (dz == -1)
	 *         | 	then walkingSpeed = 1.2 * getBaseSpeed()
	 * @effect If the unit is moving to a lower cube, the walkingspeed will be
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
	 * Return the walkingspeed of this unit.
	 * @return the walkingspeed of this unit.
	 * 		   | result == this.walkingSpeed
	 */

	public double getWalkingSpeed() {
		return this.walkingSpeed;
	}
	
	/**
	 * A variable registering the walkingspeed of this unit.
	 */
	private double walkingSpeed = 0;
	/**
	 * A boolean to register if the unit is sprinting.
	 */
	public boolean isSprinting = false;

	/**
	 * Enable sprinting mode for the given unit.
	 * @post If the unit is currently moving and the unit's stamina points are greater then zero,
	 * 		 	isSprinting will be set to true. Otherwise isSprinting will be set to false.
	 * 		 | if (status == Status.MOVING && getStaminaPoints() > 0)
	 * 		 | 	then isSprinting = true
	 * 		 |	else isSprinting = false
	 */
	public void startSprinting() {
		if (status == Status.MOVING && getStaminaPoints() > 0)
			isSprinting = true;
		else
			isSprinting = false;
	}

	/**
	 * Disable sprinting mode for the given unit.
	 * @post isSprinting will be set to false.
	 * 		 | isSprinting = false
	 */
	public void stopSprinting() {
		isSprinting = false;
	}

	/**
	 * Return the current speed of the given unit.
	 * 
	 * @return The speed at which the unit is moving. | result == | if (status
	 *         != Status.MOVING){ | then 0.0 | else | if (isSprinting) | then
	 *         2.0*getWalkingSpeed() | else getWalkingSpeed();
	 */
	public double getCurrentSpeed() {
		if (status != Status.MOVING) {
			return 0.0;
		} else {
			if (isSprinting)
				return 2.0 * getWalkingSpeed();
			return getWalkingSpeed();
		}
	}

	/**
	 * Start moving the given unit to the given cube.
	 * 
	 * @param targetPosition
	 *            The coordinate of the cube to move to.
	 * @effect If the unit can move, |if
	 *         (Util.fuzzyEquals(targetPosition[0]-this.getPosition()[0],0)) |
	 *         x= 0; | else if
	 *         (Math.signum(targetPosition[0]-this.getPosition()[0])==-1) | x=
	 *         -1; | else | x = 1; | if
	 *         (Util.fuzzyEquals(targetPosition[1]-this.getPosition()[1],0)) |
	 *         y=0; | else if
	 *         (Math.signum(targetPosition[1]-this.getPosition()[1])==-1) | y=
	 *         -1; | else | y= 1; | if
	 *         (Util.fuzzyEquals(targetPosition[2]-this.getPosition()[2],0)) |
	 *         z= 0; | else if
	 *         (Math.signum(targetPosition[2]-this.getPosition()[2])==-1) | z=
	 *         -1; | else | z= 1; | moveToAdjacent(x,y,z);
	 * @throws IllegalArgumentException
	 *             The given targetPosition is not a valid targetPosition for a
	 *             unit. | ! isValidPosition(targetPosition)
	 */
	public void moveTo(double[] targetPosition) throws IllegalArgumentException {
		if (!isValidPosition(targetPosition))
			throw new IllegalArgumentException();

		if (canMove()) {
			status = Status.IN_CENTER;
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

	public void moveTo(int[] cubePosition) throws IllegalArgumentException {
		moveTo(new double[] { (double) cubePosition[0] + 0.5, (double) cubePosition[1] + 0.5,
				(double) cubePosition[2] + 0.5 });
	}
	
	/**
	 * Check whether it's possible for a unit to move.
	 * @return True only if the unit is currently resting or doing nothing or 
	 * 			is moving and is currently in the center of a cube.
	 * 		   | result == (status == Status.RESTING || status == Status.DONE || status == Status.IN_CENTER)
	 */
	public boolean canMove() {
		if (status == Status.RESTING || status == Status.DONE || status == Status.IN_CENTER)
			return true;
		return false;
	}
	
	/**
	 * A variable registering the passed game time since the last rest.
	 */
	private double restTimer;
	/**
	 * A variable registering the duration that the unit is working.
	 */
	private float workingTime;
	/**
	 * A variable registering the duration that the unit must work to complete a task.
	 */
	private float totalWorkingTime;
	/**
	 * A variable registering the progress of a unit's work.
	 */
	private float progressWork;
	
	/**
	 * Makes the unit work.
	 * @post If a unit can work, his status will be updated to working.
	 * 		 | if (canWork())
	 *		 |	then status = Status.WORKING
	 * @post The workingTime and the progressWork of the unit will be set on zero
	 * 			and the totalWorkingTime will be calculated as 500 divided by the unit's strength.
	 * 		 | workingTime = (float) 0.0
	 *		 | totalWorkingTime = (float) 500.0 / this.getStrength()
	 *	 	 | progressWork = (float) 0.0
	 */
	public void work() {
		if (canWork())
			status = Status.WORKING;
		workingTime = (float) 0.0;
		totalWorkingTime = (float) 500.0 / this.getStrength();
		progressWork = (float) 0.0;

	}
	
	/**
	 * Makes the unit attack an other unit.
	 * @param other
	 * 		The unit that will be attacked.
	 * @post If the unit can attack an other unit, his status will be updated to attacking
	 * 			and the attackTimer will be set to zero.
	 * 		 | if (canAttack())
	 * 		 |	then status = Status.ATTACKING && attackTimer = 0.0
	 * @effect If the unit can attack an other unit, 
	 * 			the orientation of the attacking will be changed so that the units face eachother
	 * 			and the other unit will try to defend himself.
	 * 		 | if (canAttack())
	 * 		 |	then this.setOrientation((float) Math.atan2(other.getPosition()[1] - this.getPosition()[1],
			 |		other.getPosition()[0] - this.getPosition()[0])) && other.defend(this).
	 * @throws IllegalArgumentException
	 * 			If the unit that will be attacked isn't positioned in a neighboring cube.
	 * 			| (!isNeighbouringCube(other.getCubePosition()))
	 */
	public void attack(Unit other) throws IllegalArgumentException {
		if (!isNeighbouringCube(other.getCubePosition()))
			throw new IllegalArgumentException();
		if (canAttack()) {
			status = Status.ATTACKING;
			this.setOrientation((float) Math.atan2(other.getPosition()[1] - this.getPosition()[1],
					other.getPosition()[0] - this.getPosition()[0]));
			other.defend(this);
			attackTimer = 0.0;
		}
	}
	
	/**
	 * Check whether it's possible for a unit to attack.
	 * @return True if the unit is not currently moving.
	 * 		   | result == (status != Status.MOVING)
	 */
	public boolean canAttack() {
		if (status != Status.MOVING)
			return true;
		return false;

	}
	/**
	 * A variable registering the duration of the attack.
	 */
	private double attackTimer;

	/**
	 * Makes the unit defend itself against an attack of an other unit.
	 * @param unit
	 * 		The attacking unit.
	 * @effect Change the orientation of the defending unit so that the two units are facing each other.
	 * 		   | setOrientation((float) Math.atan2(unit.getPosition()[1] - this.getPosition()[1],
	 *		   |	unit.getPosition()[0] - this.getPosition()[0]))
	 * @effect If the unit is able to dodge, he will jump to a random neighboring cube.
	 * 		   | if (new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility()))
	 * 		   |	then moveToAdjacent(-1 + (new Random().nextInt(3)), -1 + (new Random().nextInt(3)),
	 * 		   |		-1 + (new Random().nextInt(3)))
	 * @post If the unit wasn't able to dodge but he is able to block the attack,
	 * 			the unit's status will be updated to done. 
	 * 		 | if (new Random().nextDouble() <= 0.25*
	 *	 	 |	((this.getStrength() + this.getAgility()) / (unit.getStrength() + unit.getAgility())))
	 *		 |		then status = Status.DONE
	 * @effect If the unit wasn't able to dodge or to block the attack, the unit will lose hitpoints equal to 
	 * 			to the attacking unit's strength divided by 10. If the hitpoints would go under zero,
	 * 			they will be set to zero. Afterwards the unit's status will be set to done.
	 * 		   | double newHitPoints = this.getHitpoints() - unit.getStrength() / 10.0
	 * 		   | if (newHitPoints > 0)
	 * 		   |	then this.setHitPoints(newHitPoints)
	 * 		   |	else this.setHitPoints(0.0)
	 * 		   | status = Status.DONE;
	 */
	public void defend(Unit unit) {
		Status previousStatus = this.status;
		status = Status.DEFENDING;
		setOrientation((float) Math.atan2(unit.getPosition()[1] - this.getPosition()[1],
				unit.getPosition()[0] - this.getPosition()[0]));
		if (new Random().nextDouble() <= 0.20 * (this.getAgility() / unit.getAgility())) {
			try {
				moveToAdjacent(-1 + (new Random().nextInt(3)), -1 + (new Random().nextInt(3)),
						-1 + (new Random().nextInt(3)));
			} catch (IllegalArgumentException exc) {
				defend(unit);
			}
			status = Status.DONE;
		}

		if (new Random().nextDouble() <= 0.25
				* ((this.getStrength() + this.getAgility()) / (unit.getStrength() + unit.getAgility())))
			status = Status.DONE;
		else {
			double newHitPoints = this.getHitpoints() - unit.getStrength() / 10.0;
			if (newHitPoints > 0)
				this.setHitPoints(newHitPoints);
			else
				this.setHitPoints(0.0);
		}

		status = Status.DONE;
	}
	
	/**
	 * Makes the unit rest.
	 * @post If a unit needs or can rest, the restTimer and recoveredHitpoints will be set to zero. 
	 * 		 If a unit needs or can rest and his hitpoints are less then the maximum value of hitpoints,
	 * 			the unit's status will be set to initial resting, otherwise the status will be set to resting.
	 * 		 | if (mustRest() || canRest())
	 * 		 |	then restTimer = 0.0 && recoveredHitpoints = 0.0
	 * 		 |		if (this.getHitpoints() < max_nbPoints())
	 * 	 	 |			then status = Status.INITIAL_RESTING
	 * 		 |			else status = Status.RESTING
	 */
	public void rest() {
		if (mustRest() || canRest()) {
			restTimer = 0.0;
			recoveredHitpoints = 0.0;
			if (this.getHitpoints() < max_nbPoints())
				status = Status.INITIAL_RESTING;
			else
				status = Status.RESTING;
		}
	}
	
	/**
	 * Checks whether the unit needs to rest.
	 * @return True only if 3 minutes of game time have past or the stamina points or hitpoints or equal to zero.
	 * 		   | result == (restTimer >= 180 || this.getStaminaPoints() <= 0 || this.getHitpoints() <= 0)
	 */
	public boolean mustRest() {

		if (restTimer >= 180 || this.getStaminaPoints() <= 0 || this.getHitpoints() <= 0)
			return true;
		return false;
	}
	/**
	 * Check whether it's possible for a unit to rest.
	 * @return True only if the unit is currently doing nothing or is resting or 
	 * 			working or is moving and is currently in the center of a cube.
	 * 		   | result == (status == Status.DONE || status == Status.RESTING || 
	 * 				status == Status.WORKING || status == Status.IN_CENTER)
	 */
	public boolean canRest() {
		if (status == Status.DONE || status == Status.RESTING || status == Status.WORKING || status == Status.IN_CENTER)
			return true;
		return false;
	}
	/**
	 * Symbolic constant registering the recovered hitpoints of a unit.
	 */
	private double recoveredHitpoints = 0.0;
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
	private static int Z = 50; // p727
	
	/**
	 * Check whether it's possible for a unit to work.
	 * @return True only if the unit wasn't currently moving or 
	 * 			wasn't initial resting or wasn't attacking an other unit.
	 * 		   | result == (status != Status.MOVING && status != Status.INITIAL_RESTING && status != Status.ATTACKING)
	 */
	public boolean canWork() {
		if (status != Status.MOVING && status != Status.INITIAL_RESTING && status != Status.ATTACKING)
			return true;
		return false;
	}
	
	/**
	 * Makes the unit start with his default behaviour.
	 * @post If a unit's status is done, enableDefaultBehaviour becomes true and 
	 * 		 a random integer from 0 to 4 will be generated. Otherwise the enableDefaultBehaviour becomes false.
	 * 		 | if (status == Status.DONE)
	 * 		 |	then enableDefaultBehaviour = true && int i = new Random().nextInt(4)
	 * 		 |	else enableDefaultBehaviour = false
	 * @effect If the random integer is equal to zero, the unit's status will be updated to moving and
	 * 		   he will start sprinting to a random position.
	 * 		   If the random integer is equal to one, the unit's status will be updated to moving and
	 * 		   he will start moving(walking) to a random position.
	 * 		   If the random integer is equal to two, the unit will start working.
	 * 		   If the random integer is equal to three, the unit will start resting.
	 * 		   | if (i == 0)
	 * 		   |	then status = Status.MOVING && startSprinting() &&
	 * 		   |		moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
	 *		   |		 (new Random().nextDouble()) * 50 })
	 *		   | if (i == 1)
	 *		   |	then status = Status.MOVING && stopSprinting() &&
	 *		   |		moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
	 *		   |		 (new Random().nextDouble()) * 50 })
	 *		   | if (i == 2)
	 *		   |	then work()
	 *		   | if (i == 3)
	 *	  	   |	then rest()
	 */
	public void startDefaultBehaviour() {
		if (status == Status.DONE) {
			enableDefaultBehaviour = true;
			int i = new Random().nextInt(4);
			if (i == 0) {
				status = Status.MOVING;
				startSprinting();
				moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
						(new Random().nextDouble()) * 50 });
			}
			if (i == 1) {
				status = Status.MOVING;
				stopSprinting();
				moveTo(new double[] { (new Random().nextDouble()) * 50, (new Random().nextDouble()) * 50,
						(new Random().nextDouble()) * 50 });
			}
			if (i == 2)
				work();
			if (i == 3)
				rest();
		} else
			enableDefaultBehaviour = false;

	}

	/**
	 * Makes the unit stop with his default behaviour.
	 * @post enableDefaultBehaviour will be set to false and the unit's status will be set to done.
	 * 		 | enableDefaultBehaviour = false && status = Status.DONE
	 */
	public void stopDefaultBehaviour() {
		enableDefaultBehaviour = false;
		status = Status.DONE;
	}
	/**
	 * A boolean to check if the default behaviour is enabled.
	 */
	private boolean enableDefaultBehaviour;

	/**
	 * Check whether the default behaviour is enabled.
	 * @return True if the default behaviour is enabled.
	 * 		   | result == enableDefaultBehaviour
	 */
	public boolean isEnableDefaultBehaviour() {
		return enableDefaultBehaviour;
	}

	/**
	 * Set the enableDefaultBehaviour of this unit to the given enableDefaultBehaviour.
	 * @param enableDefaultBehaviour
	 *          The new enableDefaultBehaviour for this unit.
	 * @post The enableDefaultBehaviour of this new unit is equal to the given enableDefaultBehaviour.
	 * 		 | this.enableDefaultBehaviour = enableDefaultBehaviour
	 */
	public void setEnableDefaultBehaviour(boolean enableDefaultBehaviour) {
		this.enableDefaultBehaviour = enableDefaultBehaviour;
	}

}