package hillbillies.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.part2.listener.TerrainChangeListener;
import hillbillies.util.ConnectedToBorder;
import ogp.framework.util.Util;

public class World {


	
	/**
	 * 
	 * @param Units
	 *            The number of units for this new world.
	 * @param  Boulders
	 *         The number of boulders for this new world.
	 * @param  Logs
	 *         The number of logs for this new world.
	 * @post If the given number of units is a valid number of units for any
	 *       world, the number of units of this new world is equal to the given
	 *       number of units. Otherwise, the number of units of this new world
	 *       is equal to 0.
	 * @post If the given number of boulders is a valid number of boulders for any world,
	 *       the number of boulders of this new world is equal to the given
	 *       number of boulders. Otherwise, the number of boulders of this new world is equal
	 *       to 0.
	 * @post If the given number of logs is a valid number of logs for any world,
	 *       the number of logs of this new world is equal to the given
	 *       number of logs. Otherwise, the number of logs of this new world is equal
	 *       to 0.
	 */
	
	/**
	 * Initialize this new world with given terrain types and terrain change listener.
	 * 
	 * @param terrainTypes
	 * 			A matrix of the terrain types of the cubes in this new world.
	 * @param listener
	 * 			The terrain change listener of this new world.
	 * @effect Set the terrain types of this new world to the given terrain types.
	 * @post The x-dimension of this new world is equal to the length of terrain types in the x-direction.
	 * @post The y-dimension of this new world is equal to the length of terrain types in the y-direction.
	 * @post The z-dimension of this new world is equal to the length of terrain types in the z-direction.
	 * @post The terrain change listener of this new world is equal to the given listener.
	 * @post The variable connectedToBorder of this new world is initialized as a new instance of ConnectedToBorder, 
	 * 			with the dimensions of this new world as its dimensions.
	 * @effect Initialize the cube terrains of this new world.
	 * 
	 */
	public World(int[][][] terrainTypes, TerrainChangeListener listener) {
		this.setTerrainTypes(terrainTypes);
		this.xDimension = terrainTypes.length;
		this.yDimension = terrainTypes[0].length;
		this.zDimension = terrainTypes[0][0].length;
		this.listener = listener;
		this.connectedToBorder = new ConnectedToBorder(this.getxDimension(),this.getyDimension(),this.getzDimension());
		initializeCubeTerrains();
	}
	
	
	

	/**
	 * Return the listener of this world.
	 */
	@Basic @Raw
	private final TerrainChangeListener getListener() {
		return listener;
	}
	
	/**
	 * A variable registrating the terrain change listener of this world.
	 */
	private final TerrainChangeListener listener;


	/**
	 * Return the terrain of a cube with the given position in this world.
	 * @param position
	 * 		The position of the cube to check.
	 * @effect Get the terrain type of the terrain at the given position in getTerrainTypes() of this world.
	 * @throws IllegalArgumentException
	 * 			If the given position is not a position inside this world.
	 * 
	 */
	public TerrainType getTerrain(int[] position) throws IllegalArgumentException {
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		return TerrainType.getTerrain(getTerrainTypes()[position[0]][position[1]][position[2]]);
	}
	/**
	 * Return the terrain type of the cube at the given double position.
	 * @param position
	 * 		The position to check.
	 * @effect Get the terrain of the cube coordinate of the given position.
	 * 
	 */
	public TerrainType getTerrain (double[] position) throws IllegalArgumentException{
		int[] cube = getCubePosition(position);
		return getTerrain(cube);
	}

	/**
	 * Set the terraintype of the given cube to the given terraintype.
	 * @param position
	 * 		The position of the cube to change the terrain type.
	 * @param terrain
	 * 		The new terraintype for the given cube.
	 * @post The terrain type of the given cube in this new world is equal to the given terrain type.
	 * @effect The listener of this new world is notified about the terrain change.
	 * @throws IllegalArgumentException
	 * 			If the given position is not a position inside this world.
	 * 
	 */
	public void setTerrain(int[] position, TerrainType terrain) throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		terrainTypes[position[0]][position[1]][position[2]] = terrain.getType();
		getListener().notifyTerrainChanged(position[0], position[1], position[2]);
	}
	
	
	/**
	 * Return the integer matrix of terrain types of this world.
	 */
	public int[][][] getTerrainTypes() {
		return terrainTypes;
	}

	/**
	 * Set the integer matrix of terrain types of this world to the given integer matrix.
	 * @param terrainTypes
	 * 			The terrain types for this new world.
	 * @post This new world has the given matrix of terrain types as its terrain types.
	 * @throws IllegalArgumentException
	 * 			If the matrix of terrain types contains invalid integers
	 * 			that do not reference terrain types (integers other than 0,1,2 and 3)
	 */
	public final void setTerrainTypes(int[][][] terrainTypes) throws IllegalArgumentException {
		for (int[][] i : terrainTypes)
			for (int[] j: i)
				for (int k: j)
					if (k != 0 &&k != 1 &&k != 2&&k!= 3)
						throw new IllegalArgumentException();
		this.terrainTypes = terrainTypes;
	}
	
	/**
	 * An integer matrix as variable registering the terrain types of this world.
	 */
	private int[][][] terrainTypes;
	
	
	/**
	 * Return the coordinates of the cube in which the given position is located.
	 * @param position
	 * 		A position in the gameworld.
	 * @return the coordinates of the cube in which the given position is located.
	 * 
	 */
	int[] getCubePosition(double[] position){
		return  new int[] { (int) Math.floor(position[0]/L), (int) Math.floor(position[1]/L),
				(int) Math.floor(position[2]/L) };
	}


	/**
	 * Return the position of the center of the cube with given integer coordinates.
	 * 
	 * @param cubePosition
	 * 			The position of the cube.
	 * @return The center of the given cube, a double array with 
	 * 		   the x,y and z-coordinate of the cube position increased by half of the length of a cube. 
	 * 		   | result == {cubePosition[0]+L/2,cubePosition[1]+L/2,cubePosition[2]+L/2}
	 */
	double[] getCubeCenter(int[] cubePosition) {
		return new double[] { (double) cubePosition[0] + L/2, (double) cubePosition[1] + L/2,
				(double) cubePosition[2] + L/2 };
	}


	/**
	 * Return position the center of the cube with the given coordinates.
	 * 
	 * @param cubePosition
	 *            The position of the cube.
	 * @return The center of the given cube, a double array with 
	 * 		   the x,y and z-coordinate of the cube position increased by half of the length of a cube. 
	 * 		   | result == {cubePosition[0]+L/2,cubePosition[1]+L/2,cubePosition[2]+L/2}
	 */
	
	double[] getCubeCenter(double[] cubePosition) {
		return new double[] { cubePosition[0] + L/2, cubePosition[1] + L/2, cubePosition[2] + L/2 };
	}



	/**
	 * Return the coordinates of the cube in which the given position is located.
	 * @param position
	 * 		A position in the gameworld.
	 * @return the coordinates of the cube in which the given position is located.
	 * 
	 */
	int[] getCubeCoordinate(double[] position){
		return new int[] { (int) Math.floor(position[0]/L), (int) Math.floor(position[1]/L),
				(int) Math.floor(position[2]/L) };
	}




	/**
	 * Check whether a given cube is of a passable terrain type.
	 * 
	 * @param cubePosition
	 * 		the position of the cube.
	 * @return True if and only if the terraintype of the given position is air or workshop.
	 * @throws IllegalArgumentException
	 * 			If the given position is not inside this world.
	 */
	boolean getPassable(int[] cubePosition) throws IllegalArgumentException {
		return this.getTerrain(cubePosition).isPassable();
	}




	/**
	 * Find all neighboring cubes of a given position.
	 * 
	 * @param position
	 * 			The position of the cube to find the neighboring cubes of.
	 * @return A list with all the neighboring cubes of the given position.
	 * @throws IllegalArgumentException
	 * 			If the given position is not inside this world.
	 * 
	 */
	List<int[]> getNeighboringCubes( int[] position) throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		List<int[]> neighboringCubes = new ArrayList<int[]>();
		for(int i =-1; i < 2; i++){
			for (int j =-1; j<2; j++){
				for (int k =-1; k<2; k++){
					int[] newPosition = {position[0]+i, position[1]+j, position[2]+k};
					if(( i!= 0 || j!=0 || k!=0)){
						if (isCubeInWorld(newPosition))
							neighboringCubes.add(newPosition);
					}
				}
			}
		}
		return neighboringCubes;
	}




	/**
	 * Checks if the given cube is neighboring any solid terrain cubes.
	 * 
	 * @param position
	 * 		The position to check.
	 * @return True if and only if at least one neighboring cube of the given cube has rock or tree as its terrain type.
	 * @throws IllegalArgumentException
	 * 			If the given position is not inside this world.
	 */
	boolean isNeighboringSolidTerrain( int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		List<int[]> neighboringCubes = getNeighboringCubes(position);
		for(int index=0; index<neighboringCubes.size(); index++){
			if(this.getTerrain(neighboringCubes.get(index)) == TerrainType.ROCK 
					|| this.getTerrain(neighboringCubes.get(index)) == TerrainType.TREE)
				return true;
		}
		return false;
	}




	/**
	 * Checks whether the given cube is located inside this gameworld.
	 * @param cubePosition
	 * 		The position of the cube.
	 * @return True if and only if the given x-, y-, z-coordinate are each between 0 and the x-, y-,z-dimension of this gameworld. 
	 */
	public boolean isCubeInWorld(int[] cubePosition){
		return ((0 <= cubePosition[0]) && (cubePosition[0] < getxDimension()) && (0 <= cubePosition[1]) 
				&& (cubePosition[1] < getyDimension()) && (0 <= cubePosition[2]) && (cubePosition[2] < getzDimension()));
	}




	/**
	 * Initialize a new unit with random name, position, weight, strength,
	 * agility, toughness, hitpoints, stamina points
	 * and orientation, and with the given state of default behavior.
	 * @return The new random unit.
	 * @effect Initialize the new random unit, 
	 * @effect add the new unit to this world if the maximum number of units is not reached for this world.
	 */
	public Unit spawnUnit(boolean enableDefaultBehavior){
		int randomToughness = new Random().nextInt(76)+25;
		int randomAgility = new Random().nextInt(76)+25;
		int randomStrength = new Random().nextInt(76)+25;
		int randomWeight = new Random().nextInt(101-((randomAgility+randomStrength)/2))+((randomAgility+randomStrength)/2);
		double randomHitpoints = (double) new Random().nextInt(((int) Math.ceil(200.0*(randomWeight/100.0)*(randomToughness/100.0))))+1;
		double randomStaminaPoints = (double) new Random().nextInt(((int) Math.ceil(200.0*(randomWeight/100.0)*(randomToughness/100.0))))+1;
		boolean validPosFound = false;
		double[] pos = new double[]{};
		while (!validPosFound){
			pos = new double[] { (new Random().nextDouble()) * getxDimension()*L, 
					(new Random().nextDouble()) * this.getyDimension()*L,(new Random().nextDouble()) * getzDimension()*L };
			//System.out.println(pos[0]+ " "+pos[1]+" "+pos[2]);
			if (this.isCubeInWorld(this.getCubeCoordinate(pos)) && this.getPassable(this.getCubeCoordinate(pos))
					&& ((int) Math.floor(pos[2]) == 0 ||
					!this.getPassable(getCubeCoordinate(new double[] {pos[0],pos[1],pos[2]-1.0}))))
				validPosFound = true;
		}
		Unit spawnUnit = new Unit(randomName(), pos, 
				randomWeight,randomStrength, randomAgility,randomToughness,enableDefaultBehavior,
				randomHitpoints,randomStaminaPoints,new Random().nextDouble()*360);
		if (this.getNumberUnits()<MAX_UNITS)
			addAsUnit(spawnUnit);
		return spawnUnit;
	}

	/**
	 * Create a random name for a unit.
	 * 
	 * @return A random name that is at least two characters long,
	 * 		starts with an uppercase letter and only contains letters (uppercase and lowercase),
	 * 		quotes (single and double) and spaces.
	 */
	private String randomName(){
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \'\"";
		StringBuilder name = new StringBuilder();
		int Length = new Random().nextInt(9)+2;
		//System.out.println(characters.charAt(new Random().nextInt(27)));
		//System.out.println(name.toString());
		char character = characters.charAt(new Random().nextInt(26));
		name.append(character);
		for( int i = 0; i < Length; i++ ){ 
		      name.append( characters.charAt(new Random().nextInt(characters.length()) ) );
		}
		return name.toString();
	}
	


	/**
	 * Return the number of units of this world.
	 */
	@Basic
	@Raw
	public int getNumberUnits() {
		return this.units.size();
	}




	/**
	 * Check whether this world has the given unit as one of the units attached to it.
	 * @param unit
	 * 		The unit to check.
	 * @throws IllegalArgumentException
	 * 			If the given unit is null.
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(Unit unit) throws IllegalArgumentException{
		if (unit==null)
			throw new IllegalArgumentException();
		return this.listAllUnits().contains(unit);
	}




	/**
	 * Check whether this world can have the given unit as one of its units.
	 * @param unit
	 * 		The unit to check.
	 * @return False if the given unit is not effective. Otherwise true if and only if
	 * 		this world is not yet terminated or the given unit is also terminated. 
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit){
		return (unit != null) 
				&&(! this.isTerminated() || unit.isTerminated());
	}




	/**
	 * Check whether this world has proper units attached to it.
	 * @return False if the total number of units is greater than the maximum number of units.
	 * 		Otherwise, true if and only if this world can have each of its units as
	 * 		a unit attached to it, and if each of these units references this world
	 * 		as their world.
	 */
	@Raw
	public boolean hasProperUnits(){
	
		if (this.getNumberUnits() >MAX_UNITS){
			
			return false;
		}
		for (Unit unit: this.units){
			if (! canHaveAsUnit(unit))
				return false;
			if (unit.getWorld() != this)
				return false;
			}
		return true;
	}




	/**
	 * Add the given unit to the set of units attached to this world.
	 * @param unit
	 * 		The unit to be added.
	 * @post This new world has the given unit as one of its units.
	 * @post The given new unit has this world as its world.
	 * @effect The given unit is added to a faction.
	 * @throws IllegalArgumentException
	 * 		If this world cannot have the given unit as one of its units or 
	 * 		the total number of units in this world is not less than 100.
	 * @throws IllegalArgumentException
	 * 		If the given unit is positioned outside the gameworld or in a cube that is not passable.
	 * @throws IllegalArgumentException
	 * 		If the given unit is already attached to some world.
	 */
	public void addAsUnit(Unit unit) throws IllegalArgumentException{
	
		if(! canHaveAsUnit(unit)|| !(getNumberUnits() <MAX_UNITS)){
			throw new IllegalArgumentException();
		}
		if( !(this.isCubeInWorld(unit.getCubeCoordinate())) || !(this.getPassable(unit.getCubeCoordinate()))){
			throw new IllegalArgumentException();
		}
		if( unit.getWorld()!=null){
			throw new IllegalArgumentException();
		}
		this.units.add(unit);
		this.addUnitToUnitsAtCubeMap(unit);
		unit.setWorld(this);
		addToFaction(unit);
	
	}




	/**
	 * Remove the given unit from the set of units attached to this world.
	 * @param unit
	 * 		The unit to be removed.
	 * @post This new world does not have the given unit as one of its units.
	 * @post If this world has the given unit as one of its units,
	 * 		the given unit is no longer attached to any world.
	 * @effect If this world has the given unit as one of its units, 
	 * 			the unit is removed from this new worlds unitmap at the units position.
	 * @effect If this world has the given unit as one of its units, the unit is removed from its faction.
	 * @effect If this world has the given unit as one of its units,
	 * 		the given unit is removed from the set of units attached to its faction.
	 * @throws IllegalArgumentException
	 * 			If the given unit is not effective
	 */
	void removeAsUnit(Unit unit) throws IllegalArgumentException{
		if( unit == null)
			throw new IllegalArgumentException();
		if (hasAsUnit(unit)){
			this.units.remove(unit);
			this.removeUnitFromUnitsAtCubeMap(unit);
			unit.setWorld(null);
			unit.getFaction().removeAsUnit(unit);
		}
	}


	/**
	 * Return the set collecting all units attached to this world.
	 * 	@return The set of units of this world.
	 */
	public Set<Unit> listAllUnits(){
		return units;
	}




	/**
	 * Return the set of units attached 
	 * to this world and the given faction.
	 * @param faction
	 * 		The faction units need to belong to.
	 * @effect Get the units attached to the given faction.
	 * @throws IllegalArgumentException
	 * 		The given faction is not attached to this world.
	 */
	public Set<Unit> listAllUnitsOfFaction(Faction faction) throws IllegalArgumentException{
		if( !hasAsFaction(faction))
			throw new IllegalArgumentException();
		return faction.getUnits();
	}

	/**
	 * Return a set of units in this world that are located at the given position in this world.
	 * @param position
	 * 		The position of the units.
	 * @return
	 * @throws IllegalArgumentException
	 * 	The given position is not located inside this world.
	 */
	public Set<Unit> getUnits(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		if (unitsAtCubeMap.get(new Position(position))==null){
			return new HashSet<>();
		}
		else{
			return unitsAtCubeMap.get(new Position(position));
		}
	}



	/**
	 * Add the given unit to the unitmap at the cube position of the given unit.
	 * 
	 * @param unit
	 * 			The unit to add to the unit map.
	 * @post The given unit is added to the unitmap of this world, with its cube position as its key in the map
	 * @throws IllegalArgumentException
	 * 			If the given unit is not attached to this world.
	 */
	void addUnitToUnitsAtCubeMap(Unit unit) throws IllegalArgumentException{
		if (!this.hasAsUnit(unit))
			throw new IllegalArgumentException();
		Set<Unit> unitsAtCube = this.unitsAtCubeMap.get(new Position(unit.getCubeCoordinate()));
		//System.out.println("f");
		if ( unitsAtCube != null){
			unitsAtCube.add(unit);
			this.unitsAtCubeMap.put(new Position(unit.getCubeCoordinate()),unitsAtCube);
		}
		else{
			unitsAtCube = new HashSet<Unit>();
			unitsAtCube.add(unit);
			this.unitsAtCubeMap.put(new Position(unit.getCubeCoordinate()),unitsAtCube);
			//System.out.println("g");
			//System.out.println(unitsAtCubeMap.get(new Position(unit.getCubeCoordinate())).contains(unit));
	
		}
	}



	/**
	 * Remove the given unit from the unitmap at the cube position of the given unit.
	 * 
	 * @param unit
	 * 			The unit to remove from the unit map.
	 * @post If the given unit is present in the he given unit is removed from the unitmap of this world.
	 * @post If there are no units anymore on the previous position of the given unit, the position is removed from the map
	 * @throws IllegalArgumentException
	 * 			If the given unit is not attached to this world.
	 */
	void removeUnitFromUnitsAtCubeMap(Unit unit){
		Set<Unit> unitsAtCube = this.unitsAtCubeMap.get(new Position(unit.getCubeCoordinate()));
		if (unitsAtCube.contains(unit)){
			unitsAtCube.remove(unit);
			if (unitsAtCube.isEmpty())
				unitsAtCubeMap.remove(new Position(unit.getCubeCoordinate()));
			else{
				unitsAtCubeMap.replace(new Position(unit.getCubeCoordinate()), unitsAtCube);
			}
		}
	}
	
	/**
	 * A variable which is a map of positions of the world as keys and sets of the units occupying those cubes as values.
	 */
	private Map<Position,Set<Unit>> unitsAtCubeMap = new HashMap<Position, Set<Unit>>();


	/**
	 * Adds a given unit to a faction and creates a new faction in which the unit is added
	 * if no legal faction is available.
	 * @param unit
	 * 		The given unit.
	 * @effect If The number of active factions are less than the maximum number, a new faction is created and 
	 * 			the given unit is added to this new faction.
	 * @effect Else the unit is added to the faction with the least number of units, 
	 * 			if some faction has less than the maximum number of units
	 * @throws illegalArgumentException
	 * 			If the given unit is not attached to this world.
	 */
	private void addToFaction(Unit unit) throws IllegalArgumentException{
		if (!this.hasAsUnit(unit))
			throw new IllegalArgumentException();
		if(getNbActiveFactions()<MAX_FACTIONS){
			 Faction newFaction = new Faction();
			 this.addAsFaction(newFaction);
			 newFaction.addAsUnit(unit);
		}
		else{
			Faction leastUnitsFaction = null;
			for(Faction faction: this.getActiveFactions()){
				if (faction.getUnits().size()<MAX_UNITS_PER_FACTION && (leastUnitsFaction == null 
						||faction.getUnits().size()<leastUnitsFaction.getUnits().size())) 
					leastUnitsFaction = faction;
			}
			if (leastUnitsFaction != null)
				leastUnitsFaction.addAsUnit(unit);
		
		}
	}

	/**
	 * Return the number of factions in the world.
	 */
	@Basic @Raw
	public int getNbFactions(){
		return factions.size();
	}
	
	
	/**
	 * Return the number of active factions in the world.
	 */
	public int getNbActiveFactions(){
		return getActiveFactions().size();
	}
	
	/**
	 * Return a set of all the active factions in the world.
	 */
	public Set<Faction> getActiveFactions(){
		Set<Faction> activeFactions = new HashSet<Faction>();
		for(Faction faction: this.factions){
			if (faction.isActive())
				activeFactions.add(faction);
		}
		return activeFactions;
	}
	
	/**
	 * Checks whether the given faction is one of the factions 
	 * 	associated with this world.
	 * @param faction
	 * 		The faction to check.
	 * @return True if and only if this world has the given faction
	 * 		as one of its factions.
	 * @throws IllegalArgumentException
	 * 			If the given faction is not effective
	 */
	@Basic @Raw
	public boolean hasAsFaction(Faction faction) throws IllegalArgumentException {
		if (faction== null){
			throw new IllegalArgumentException();
		}
		else
			return factions.contains(faction);
	}
	
	/**
	 * Checks whether this world can have the given faction as one of his factions.
	 * @param faction
	 * 		The faction to check.
	 * @return True if and only if the given faction is effective and neither the world or the faction is terminated
	 * 		and the number of units in the faction is a number between 0 and 50 (0 not included, 50 included).
	 */
	@Raw
	public boolean canHaveAsFaction(Faction faction){
		return (faction != null && (!this.isTerminated() || faction.isTerminated()) &&faction.getNbUnits() <=MAX_UNITS_PER_FACTION);
	}
	
	/**
	 * Check whether this world has proper factions associated with it.
	 * @return True if and only if this world can have each of its factions as a faction, 
	 * 			and the number of active factions is not greater than the maximum number of active factions.
	 */
	@Raw
	public boolean hasProperFactions(){
		for (Faction faction: this.factions){
			if (! canHaveAsFaction(faction))
				return false;
			if (getNbActiveFactions()>MAX_FACTIONS)
				return false;
		}
		return true;
	}
	
	/**
	 * Add the given faction as a faction for this world.
	 * @param faction
	 * 		The faction to become a faction for this world.
	 * @post If this world not already contains the max number of active factions,
	 * 		this world has the given faction as one if its factions.
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given faction as a faction.
	 */
	private void addAsFaction(Faction faction)throws IllegalArgumentException{
		if (! canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		if (getNbActiveFactions()!=MAX_FACTIONS)
			factions.add( faction);
	}
	
	/**
	 * Remove the given faction as a faction for this world.
	 * @param faction
	 * 		The faction to be removed.
	 * @post The given faction is not a faction of this world.
	 * @throws IllegalArgumentException
	 * 		If the given faction is not effective.
	 */
	private void removeAsFaction(Faction faction) throws IllegalArgumentException{
			
		if( faction == null)
			throw new IllegalArgumentException();
		if (hasAsFaction(faction))
			this.factions.remove(faction);
	}
	/**
	 * Set collecting references to the factions of this world.
	 * @invar The set of factions is effective.
	 * @invar Each element in the set of factions references a faction that is an acceptable faction for this world.
	 */
	private Set<Faction> factions = new HashSet<Faction>();
	/**
	 * Return the number of boulders of this world.
	 */
	@Basic @Raw
	public int getNumberBoulders() {
		return this.boulders.size();
	}


	/**
	 * Check whether this world has the given boulder as one of the boulders attached to it.
	 * @param boulder
	 * 		The boulder to check.
	 */
	@Basic
	@Raw
	public boolean hasAsBoulder(Boulder boulder){
		return this.boulders.contains(boulder);
	}
	/**
	 * Check whether this world can have the given boulder as one of its boulders.
	 * @param boulder
	 * 		The boulder to check.
	 * @return False if the given boulder is not effective. Otherwise true if and only if
	 * 		this world is not yet terminated or the given boulder is also terminated. 
	 */
	@Raw
	public boolean canHaveAsBoulder(Boulder boulder){
		return (boulder != null) && (! this.isTerminated() || boulder.isTerminated());
	}
	/**
	 * Check whether this world has proper boulders attached to it.
	 * @return True if and only if this world can have each of its boulders as
	 * 		a boulder attached to it, and if each of these boulders references this world
	 * 		as their world.
	 */
	@Raw
	public boolean hasProperBoulders(){
		for (Boulder boulder: this.boulders){
			if (! canHaveAsBoulder(boulder))
				return false;
			if (boulder.getWorld() != this)
				return false;
			
		}
		return true;
	}
	/**
	 * Return the set collecting references to boulders attached to this world.
	 * 	@return the set of boulders attached to this world
	 */
	public Set<Boulder> listAllBoulders(){
		return boulders;
	}




	/**
	 * Add the given boulder to the set of boulders attached to this world.
	 * @param boulder
	 * 		The boulder to be added.
	 * @post This world has the given boulder as one of its boulders.
	 * @post The given boulder references this world as the world to which it is attached.
	 * @effect the given boulder is added to the map of boulders at its position
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given boulder as one of its boulders.
	 * @throws IllegalArgumentException
	 * 		The given boulder is already attached to some world.
	 */
	public void addAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if(! canHaveAsBoulder(boulder))
			throw new IllegalArgumentException();
		if( !(this.isCubeInWorld(boulder.getCubeCoordinate())) || !(this.getPassable(boulder.getCubeCoordinate())))
			throw new IllegalArgumentException();
		if( boulder.getWorld()!=null)
			throw new IllegalArgumentException();
		this.boulders.add(boulder);
		this.addBoulderToBouldersAtCubeMap(boulder);
		boulder.setWorld(this);
	}
	/**
	 * Remove the given boulder from the set of boulders attached to this world.
	 * @param boulder
	 * 		The boulder to be removed.
	 * @post This world does not have the given boulder as one of its boulders.
	 * @post If this world has the given boulder as one of its boulders,
	 * 		the given boulder is no longer attached to any world.
	 * @effect the given boulder is removed from the map of boulders at its position
	 * @throws IllegalArgumentException
	 */
	void removeAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if( boulder == null)
			throw new IllegalArgumentException();
		if (hasAsBoulder(boulder)){
			this.boulders.remove(boulder);
			this.removeBoulderFromBouldersAtCubeMap(boulder);
			boulder.setWorld(null);
		}
	}
	/**
	 * Return a set of boulders in this world that are located at the given position in this world.
	 * @param position
	 * 		The position of the boulders.
	 * @return the boulders at the given position
	 * @throws IllegalArgumentException
	 * 			if the given position is not inside this world
	 */
	public Set<Boulder> getBoulders(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		if (bouldersAtCubeMap.get(new Position(position))==null)
			return new HashSet<>();
		else{
			return bouldersAtCubeMap.get(new Position(position));
		}
	}
	
	/**
	 * Add the given boulder to the boulder map at the cube position of the given boulder.
	 * 
	 * @param boulder
	 * 			The boulder to add to the unit map.
	 * @post The given boulder is added to the bouldermap of this world, with its cube position as its key in the map
	 * @throws IllegalArgumentException
	 * 			If the given boulder is not attached to this world.
	 */
	protected void addBoulderToBouldersAtCubeMap(Boulder boulder)throws IllegalArgumentException{
		if(!this.hasAsBoulder(boulder))
			throw new IllegalArgumentException();

		Set<Boulder> bouldersAtCube = this.bouldersAtCubeMap.get(new Position(boulder.getCubeCoordinate()));
		if ( bouldersAtCube != null){
			bouldersAtCube.add(boulder);
			this.bouldersAtCubeMap.put(new Position(boulder.getCubeCoordinate()),bouldersAtCube);
		}
		else{
			bouldersAtCube = new HashSet<Boulder>();
			bouldersAtCube.add(boulder);
			this.bouldersAtCubeMap.put(new Position(boulder.getCubeCoordinate()),bouldersAtCube);
		}
	}



	/**
	 * Remove the given boulder from the bouldermap at the cube position of the given boulder.
	 * 
	 * @param boulder
	 * 			The boulder to remove from the boulder map.
	 * @post If the given boulder is present in its position in the map, the given boulder is removed from the bouldermap of this world.
	 * @post If there are no boulders anymore on the previous position of the given boulder, the position is removed from the map
	 * @throws IllegalArgumentException
	 * 			If the given boulder is not attached to this world.
	 */
	protected void removeBoulderFromBouldersAtCubeMap(Boulder boulder){
		Set<Boulder> bouldersAtCube = this.bouldersAtCubeMap.get(new Position(boulder.getCubeCoordinate()));
		if (bouldersAtCube.contains(boulder)){
			bouldersAtCube.remove(boulder);
			if (bouldersAtCube.isEmpty())
				bouldersAtCubeMap.remove(new Position(boulder.getCubeCoordinate()));
			else{
				bouldersAtCubeMap.replace(new Position(boulder.getCubeCoordinate()), bouldersAtCube);
			}
		}
	}
	/**
	 * The map registering the sets of boulders of this world with their cube position as keys.
	 */
	private Map<Position,Set<Boulder>> bouldersAtCubeMap = new HashMap<Position,Set<Boulder>>();

	/**
	 * Set collecting references to boulders attached to this world.
	 * @invar The set of boulders is effective.
	 * @invar Each element in the set of boulders references a boulder that
	 * 		is an acceptable boulder for this world.
	 * @invar Each boulder in the set of boulders references this world as the world
	 * 		to which it is attached.
	 */
	private Set<Boulder> boulders = new HashSet<Boulder>();
	
	/**
	 * Return the number of logs of this world.
	 */
	@Basic @Raw
	public int getNumberLogs() {
		return this.logs.size();
	}


	/**
	 * Check whether this world has the given log as one of the logs attached to it.
	 * @param log
	 * 		The log to check.
	 */
	@Basic
	@Raw
	public boolean hasAsLog(Log log){
		return this.logs.contains(log);
	}
	/**
	 * Check whether this world can have the given log as one of its logs.
	 * @param log
	 * 		The log to check.
	 * @return False if the given log is not effective. Otherwise true if and only if
	 * 		this world is not yet terminated or the given log is also terminated. 
	 */
	@Raw
	public boolean canHaveAsLog(Log log){
		return (log != null) && (! this.isTerminated() || log.isTerminated());
	}
	/**
	 * Check whether this world has proper logs attached to it.
	 * @return True if and only if this world can have each of its logs as
	 * 		a log attached to it, and if each of these logs references this world
	 * 		as their world.
	 */
	@Raw
	public boolean hasProperLogs(){
		for (Log log: this.logs){
			if (! canHaveAsLog(log))
				return false;
			if (log.getWorld() != this)
				return false;
			
		}
		return true;
	}
	/**
	 * Return the set collecting references to logs attached to this world.
	 * 	@return the set of logs of this world
	 */
	public Set<Log> listAllLogs(){
		return logs;
	}




	/**
	 * Add the given log to the set of logs attached to this world.
	 * @param log
	 * 		The log to be added.
	 * @post This world has the given log as one of its logs.
	 * @post The given log references this world as the world to which it is attached.
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given log as one of its logs.
	 * @throws IllegalArgumentException
	 * 		The given log is already attached to some world.
	 * @throws IllegalArgumentException
	 * 		the given log does not have a valid position for this world.
	 */
	public void addAsLog(Log log) throws IllegalArgumentException{
		if(! canHaveAsLog(log))
			throw new IllegalArgumentException();
		if( !(this.isCubeInWorld(log.getCubeCoordinate())) || !(this.getPassable(log.getCubeCoordinate())))
			throw new IllegalArgumentException();
		if(log.getWorld()!= null)
			throw new IllegalArgumentException();
		this.logs.add(log);
		this.addLogToLogsAtCubeMap(log);
		log.setWorld(this);
	}
	
	/**
	 * Remove the given log from the set of logs attached to this world.
	 * @param log
	 * 		The log to be removed.
	 * @post This world does not have the given log as one of its logs.
	 * @post If this world has the given log as one of its logs,
	 * 		the given log is no longer attached to any world.
	 * @throws IllegalArgumentException
	 * 		the log is not effective
	 */
	void removeAsLog(Log log) throws IllegalArgumentException{
		if( log == null)
			throw new IllegalArgumentException();
		if (hasAsLog(log)){
			this.logs.remove(log);
			this.removeLogFromLogsAtCubeMap(log);
			log.setWorld(null);
		}
	}
	/**
	 * Return a set of logs in this world that are located at the given position in this world.
	 * @param position
	 * 		The position of the logs.
	 * @return the set of logs in this world
	 * @throws IllegalArgumentException
	 * 	The given position is not located inside this world.
	 */
	public Set<Log> getLogs(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position)){
			//System.out.println(position[0]+" "+position[1]+" "+position[2]);
			throw new IllegalArgumentException();
		}
		if (logsAtCubeMap.get(new Position(position))==null)
			return new HashSet<>();
		else{
			return logsAtCubeMap.get(new Position(position));
		}
	}
	/**
	 * Add the given log to the logmap at the cube position of the given log.
	 * 
	 * @param log
	 * 			The log to add to the log map.
	 * @post The given log is added to the log map of this world, with its cube position as its key in the map
	 * @throws IllegalArgumentException
	 * 			If the given log is not attached to this world.
	 */
	protected void addLogToLogsAtCubeMap(Log log) throws IllegalArgumentException{
		if(!this.hasAsLog(log))
			throw new IllegalArgumentException();
		Set<Log> logsAtCube = this.logsAtCubeMap.get(new Position(log.getCubeCoordinate()));
		if ( logsAtCube != null){
			logsAtCube.add(log);
			this.logsAtCubeMap.put(new Position(log.getCubeCoordinate()),logsAtCube);
		}
		else{
			logsAtCube = new HashSet<Log>();
			logsAtCube.add(log);
			this.logsAtCubeMap.put(new Position(log.getCubeCoordinate()),logsAtCube);
		}
	}



	/**
	 * Remove the given log from the log map at the cube position of the given log.
	 * 
	 * @param log
	 * 			The log to remove from the log map.
	 * @post If the given log is present in its position in the map, the given log is removed from the log map of this world.
	 * @post If there are no logs anymore on the previous position of the given log, the position is removed from the map
	 * @throws IllegalArgumentException
	 * 			If the given log is not attached to this world.
	 */
	protected void removeLogFromLogsAtCubeMap(Log log){
		Set<Log> logsAtCube = this.logsAtCubeMap.get(new Position(log.getCubeCoordinate()));
		if (logsAtCube.contains(log)){
			logsAtCube.remove(log);
			if (logsAtCube.isEmpty())
				logsAtCubeMap.remove(new Position(log.getCubeCoordinate()));
			else{
				logsAtCubeMap.replace(new Position(log.getCubeCoordinate()), logsAtCube);
			}
		}
	}
	
	/**
	 * The map registering the sets of logs of this world with their cube position as keys.
	 */
	private Map<Position,Set<Log>> logsAtCubeMap = new HashMap<Position,Set<Log>>();

	/**
	 * Set collecting references to logs attached to this world.
	 * @invar The set of logs is effective.
	 * @invar Each element in the set of logs references a log that
	 * 		is an acceptable log for this world.
	 * @invar Each log in the set of logs references this world as the world
	 * 		to which it is attached.
	 */
	private Set<Log> logs = new HashSet<Log>();
	
	
	List<List<?>> inspectCube(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		List<List<?>> list = new ArrayList<>();
		List<TerrainType> terrainType= new ArrayList<TerrainType>();
		terrainType.add(this.getTerrain(position));
		list.add(terrainType);
		
		List<Unit> unitList= new ArrayList<Unit>();
		if(unitsAtCubeMap.get(new Position(position)) !=null){
			for (Unit unit:unitsAtCubeMap.get(new Position(position))){
				unitList.add(unit);
			}
		}
		list.add(unitList);
		
		List<Log> logList= new ArrayList<Log>();
		if(logsAtCubeMap.get(new Position(position)) !=null){

		for (Log log:logsAtCubeMap.get(new Position(position))){
			logList.add(log);
		}
		}
		
		list.add(logList);

		
		List<Boulder> boulderList= new ArrayList<Boulder>();
		if(bouldersAtCubeMap.get(new Position(position)) !=null){

		for (Boulder boulder:bouldersAtCubeMap.get(new Position(position))){
			boulderList.add(boulder);
		}
		}
		list.add(boulderList);
	
		return list;
	}
	private final Set<Unit> units = new HashSet<Unit>();
	
	private final static int MAX_UNITS = 100;
	private final static int MAX_FACTIONS = 5;
	private final static int MAX_UNITS_PER_FACTION = 50;
	
	/**
	 * @return the xDimension
	 */
	public final int getxDimension() {
		return xDimension;
	}

	/**
	 * @return the yDimension
	 */
	public final int getyDimension() {
		return yDimension;
	}

	/**
	 * @return the zDimension
	 */
	public final int getzDimension() {
		return zDimension;
	}

	
	private final int xDimension;

	private final int yDimension;

	private final int zDimension;
	
	protected ConnectedToBorder connectedToBorder;

	
//	/**
//	 * @return the connectedToBorder
//	 */
//	public ConnectedToBorder getConnectedToBorder() {
//		return connectedToBorder;
//	}
	
	public boolean isSolidConnectedToBorder(int[] pos) throws IllegalArgumentException{
		if (!isCubeInWorld(pos))
			throw new IllegalArgumentException();
		else
			return this.connectedToBorder.isSolidConnectedToBorder(pos[0], pos[1], pos[2]);
	}
	
	private void initializeCubeTerrains(){
		for (int x=0; x < getxDimension(); x ++){
			for (int y=0; y< getyDimension(); y++){
				for (int z=0; z < getzDimension() ; z ++){
					if (this.getPassable(new int[] {x,y,z}))
						connectedToBorder.changeSolidToPassable(x, y, z);
					}
				}
			}
	}
	
	void updateCubeTerrains(){
		for (int x=0; x < getxDimension(); x ++){
			for (int y=0; y< getyDimension(); y++){
				for (int z=0; z < getzDimension() ; z ++){
					if (! connectedToBorder.isSolidConnectedToBorder(x, y, z)){
						solidToPassableUpdate(new int[] {x,y,z});
						}
					}
				}
			}
	}
	void solidToPassableUpdate(int[] position){
		List<int[]> toChange = connectedToBorder.changeSolidToPassable(position[0],position[1],position[2]);
		
		if (new Random().nextDouble() <= 0.25){
			if (getTerrain(position) == TerrainType.ROCK){
				Boulder boulder = new Boulder(position);
				setTerrain(position,TerrainType.AIR);
				addAsBoulder(boulder);
			}
			if (getTerrain(position) == TerrainType.TREE){
				Log log = new Log(position);
				setTerrain(position,TerrainType.AIR);
				addAsLog(log);
			}
		}
		for (int[] positionToChange: toChange){
			solidToPassableUpdate(positionToChange);
			
		}
		
	}

	public void advanceTime(double duration) throws IllegalArgumentException{
		if (!(Util.fuzzyGreaterThanOrEqualTo(duration, 0.0-Util.DEFAULT_EPSILON )&& Util.fuzzyLessThanOrEqualTo((double)duration, 0.2+Util.DEFAULT_EPSILON))){
			System.out.println(duration);
			throw new IllegalArgumentException();
		}
		updateCubeTerrains();
		for (Unit unit : this.listAllUnits()){
			unit.advanceTime((float)duration);
			if (unit.getLog() != null)
				unit.getLog().setPosition(unit.getPosition());
			if (unit.getBoulder() != null)
				unit.getBoulder().setPosition(unit.getPosition());
		}
		for (Boulder boulder: boulders){
			boulder.advanceTime((float) duration);
		}
		for (Log log: logs){
			log.advanceTime((float) duration);
		}
		updateCubeTerrains();
	}

	
	/**
	 * Symbolic constant registering the side length of cubes, expressed in meters.
	 */
	private final double L = 1.0;
	
	/**
 	 * Terminate this world.
 	 *
 	 * @post   This world  is terminated.
 	 *       | new.isTerminated()
 	 * @post   ...
 	 *       | ...
 	 */
 	 public void terminate() {
 		 this.isTerminated = true;
 		 
 	 }
 	 
 	 /**
 	  * Return a boolean indicating whether or not this world
 	  * is terminated.
 	  */
 	 @Basic @Raw
 	 public boolean isTerminated() {
 		 return this.isTerminated;
 	 }
 	 
 	 /**
 	  * Variable registering whether this world is terminated.
 	  */
 	 private boolean isTerminated = false;
 	 
  }


	

