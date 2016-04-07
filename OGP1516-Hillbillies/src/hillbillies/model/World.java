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

public class World {


	
	/**
	 * Initialize this new world with given number of units.
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
	 * @return the listener
	 */
	private final TerrainChangeListener getListener() {
		return listener;
	}
	
	private final TerrainChangeListener listener;


	/**
	 * Return the terraintype of a cube.
	 * @param position
	 * 		The position of the cube.
	 */
	public TerrainType getTerrain(int[] position) throws IllegalArgumentException {
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		return TerrainType.getTerrain(getTerrainTypes()[position[0]][position[1]][position[2]]);
	}
	/**
	 * Return the terraintype of a cube.
	 * @param position
	 * 		The position of the cube.
	 * @effect Returns the terraintype of a cube.
	 */
	public TerrainType getTerrain (double[] position) throws IllegalArgumentException{
		int[] cube = getCubePosition(position);
		return getTerrain(cube);
	}

	/**
	 * Set the terraintype of the given cube to the given terraintype.
	 * @param position
	 * 		The position of the cube.
	 * @param terrain
	 * 		The new terraintype for this cube.
	 */
	public void setTerrain(int[] position, TerrainType terrain) throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		terrainTypes[position[0]][position[1]][position[2]] = terrain.getType();
		getListener().notifyTerrainChanged(position[0], position[1], position[2]);
	}
	
	
	/**
	 * @return the terrainTypes
	 */
	public int[][][] getTerrainTypes() {
		return terrainTypes;
	}

	/**
	 * 
	 * @param terrainTypes
	 * @throws IllegalArgumentException
	 */
	public final void setTerrainTypes(int[][][] terrainTypes) throws IllegalArgumentException {
		for (int[][] i : terrainTypes)
			for (int[] j: i)
				for (int k: j)
					if (k != 0 &&k != 1 &&k != 2&&k!= 3)
						throw new IllegalArgumentException();
		this.terrainTypes = terrainTypes;
	}

	private int[][][] terrainTypes;
	
	
	/**
	 * Initialize this new unit with a random name, position, weight, strength,
	 * agility, toughness, state of default behaviour, hitpoints, stamina points
	 * and an orientation.
	 * @return
	 */
	public Unit spawnUnit(boolean enableDefaultBehavior){
		int randomToughness = new Random().nextInt(201)+1;
		int randomAgility = new Random().nextInt(201)+1;
		int randomStrength = new Random().nextInt(201)+1;
		int randomWeight = new Random().nextInt(201-((randomAgility+randomStrength)/2))+1+((randomAgility+randomStrength-2)/2);
		double randomHitpoints = (double) new Random().nextInt(((int) Math.ceil(200.0*(randomWeight/100.0)*(randomToughness/100.0)))+1);
		double randomStaminaPoints = (double) new Random().nextInt(((int) Math.ceil(200.0*(randomWeight/100.0)*(randomToughness/100.0)))+1);
		boolean validPosFound = false;
		double[] pos = new double[]{};
		while (!validPosFound){
			pos = new double[] { (new Random().nextDouble()) * getxDimension()*L, 
					(new Random().nextDouble()) * this.getyDimension()*L,(new Random().nextDouble()) * getzDimension()*L };
			if (this.isCubeInWorld(this.getCubeCoordinate(pos)) && this.getPassable(this.getCubeCoordinate(pos))
					&& (!this.getPassable(getCubeCoordinate(new double[] {pos[0],pos[1],pos[2]-1.0}))
							||(int) Math.floor(pos[2]-1.0) == 0))
				validPosFound = true;
		}
		Unit spawnUnit = new Unit(randomName(), pos, 
				randomWeight,randomStrength, randomAgility,randomToughness,enableDefaultBehavior,
				randomHitpoints,randomStaminaPoints,new Random().nextDouble()*360);
		if (this.listAllUnits().size()<100)
			addAsUnit(spawnUnit);
		return spawnUnit;
	}
	// ik denk foutje : 201-> 200 & nieuwe faction starten en zo: hoe?
	/**
	 * Creates a random name.
	 * @return A random name that is at least two characters long,
	 * 		starts with an uppercase letter and only contains letters (uppercase and lowercase),
	 * 		quotes (single and double) and spaces.
	 */
	private String randomName(){
		Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \'\"";
		int Length = new Random().nextInt(9)+2;
		Name.append(Char.charAt(new Random().nextInt(26)));
		for( int i = 1; i < Length; i++ ) 
		      Name.append( Char.charAt(new Random().nextInt(Char.length()) ) );
		   return Name.toString();
	}
	/**
	 * Adds a given unit to a faction and creates a new faction in which the unit is added
	 * if no legal faction is available.
	 * @param unit
	 * 		The given unit.
	 */
	public void addToFaction(Unit unit){
		if(getNbActiveFactions()<5){
			 Faction newFaction = new Faction();
			 this.addAsFaction(newFaction);
			 newFaction.addAsUnit(unit);
		}
		else{
			Faction leastUnitsFaction = null;
			for(Faction faction: this.getActiveFactions()){
				if (faction.getUnits().size()<50 && (leastUnitsFaction == null 
						||faction.getUnits().size()<leastUnitsFaction.getUnits().size())) 
					leastUnitsFaction = faction;
			}
			if (leastUnitsFaction != null)
				leastUnitsFaction.addAsUnit(unit);
		
		}
	}

	/**
	 * Returns the coordinates of the cube in which the given position is located.
	 * @param position
	 * 		A position in the gameworld.
	 * @return the coordinates of the cube in which the given position is located.
	 */
	int[] getCubePosition(double[] position) {
		return  new int[] { (int) Math.floor(position[0]), (int) Math.floor(position[1]),
				(int) Math.floor(position[2]) };
		
	}
	
	/**
	 * Return the position of the center of the cube with given integer coordinates.
	 * 
	 * @param cubePosition
	 * 			The position of the cube.
	 * @return the position of the center of the given cube, is each coordinate increased with the length of one cube /2.
	 * 		   | result == new double [] { (double) cubePosition[0] + L/2, 
	 * 		   | (double) cubePosition[1] + L/2,(double) cubePosition[2] + L/2 }
	 */
	double[] getCubeCenter(int[] cubePosition) {
		return new double[] { (double) cubePosition[0] + L/2, (double) cubePosition[1] + L/2,
				(double) cubePosition[2] + L/2 };
	}
	
	/**
	 * Return the center of a cube.
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
	 * Checks whether a given cube is passable for a unit.
	 * @param cubePosition
	 * 		the position of the cube.
	 * @return True if and only if the terraintype is air or workshop.
	 */
	// misschien hier ipv return effect gebruiken?
	boolean getPassable(int[] cubePosition) {
		return this.getTerrain(cubePosition).isPassable();
	}
	/**
	 * Find all neighboring cubes of a given position.
	 * @param position
	 * 		A position in the gameworld.
	 * @return a list with all the neighboring cubes of the given position.
	 */
	List<int[]> getNeighboringCubes( int[] position){
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
	 * Checks if all the neighboring cubes of a position are solid.
	 * @param position
	 * 		A position in the gameworld.
	 * @return True if and only if there is at least one neigboring cube which 
	 * 		terraintype is rock or tree.
	 */
	boolean isNeighboringSolidTerrain( int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		List<int[]> neighboringCubes = getNeighboringCubes(position);
		for(int index=0; index<=neighboringCubes.size(); index++){
			if(this.getTerrain(neighboringCubes.get(index)) == TerrainType.ROCK 
					|| this.getTerrain(neighboringCubes.get(index)) == TerrainType.TREE)
				return true;
		}
		return false;
	}
	

	
	int[] getCubeCoordinate(double[] position){
		return new int[] { (int) Math.floor(position[0]), (int) Math.floor(position[1]),
				(int) Math.floor(position[2]) };
	}
	/**
	 * Checks whether the cube is located inside the gameworld.
	 * @param cubePosition
	 * 		The position of the cube.
	 * @return True if and only if the given x-, y-, z-coordinate is between 0 and the x-, y-,z-dimension of the gameworld. 
	 */
	public boolean isCubeInWorld(int[] cubePosition){
		return ((0 <= cubePosition[0]) && (cubePosition[0] < getxDimension()*L) && (0 <= cubePosition[1]) 
				&& (cubePosition[1] < getyDimension()*L ) && (0 <= cubePosition[2]) && (cubePosition[2] < getzDimension()*L));
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
	@Raw
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
	 */
	@Basic @Raw
	public boolean hasAsFaction(Faction faction) throws IllegalArgumentException{
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
		return (faction != null && (!this.isTerminated() || faction.isTerminated()) &&faction.getNbUnits() <=50);
	}
	
	/**
	 * Check whether this world has proper factions associated with it.
	 * @return True if and only if this world can have each of its factions as a faction.
	 */
	@Raw
	public boolean hasProperFactions(){
		
		for (Faction faction: this.factions){
			if (! canHaveAsFaction(faction))
				return false;
//			if (faction.getWorld() != this)
//				return false;
			if (getNbActiveFactions()>5)
				return false;
		}
		return true;
	}
	/**
	 * Add the given faction as a faction for this world.
	 * @param faction
	 * 		The faction to become a faction for this world.
	 * @post If this world contains not more than 5 active factions,
	 * 		this world has the given faction as one if its factions.
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given faction as a faction.
	 */
	private void addAsFaction(Faction faction)throws IllegalArgumentException{
		if (! canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		if (getNbActiveFactions()!=5)
			factions.add( faction);
//		if (faction.getWorld() != null)
//			throw new IllegalArgumentException();
		//faction.setWorld(this);
		
	}
	/**
	 * Remove the given faction as a faction for this world.
	 * @param faction
	 * 		The faction to be removed.
	 * @post The given faction is not a faction of this world.
	 * @throws IllegalArgumentException
	 * 		The given faction is not effective.
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
	 * Add the given boulder to the set of boulders attached to this world.
	 * @param boulder
	 * 		The boulder to be added.
	 * @post This world has the given boulder as one of its boulders.
	 * @post The given boulder references this world as the world to which it is attached.
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given boulder as one of its boulders.
	 * @throws IllegalArgumentException
	 * 		The given boulder is already attached to some world.
	 */
	public void addAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if(! canHaveAsBoulder(boulder))
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
	 * @throws IllegalArgumentException
	 */
	public void removeAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if( boulder == null)
			throw new IllegalArgumentException();
		if (hasAsBoulder(boulder)){
			this.boulders.remove(boulder);
			this.removeBoulderFromBouldersAtCubeMap(boulder);
			boulder.setWorld(null);
		}
	}
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
	 * Add the given log to the set of logs attached to this world.
	 * @param log
	 * 		The log to be added.
	 * @post This world has the given log as one of its logs.
	 * @post The given log references this world as the world to which it is attached.
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given log as one of its logs.
	 * @throws IllegalArgumentException
	 * 		The given log is already attached to some world.
	 */
	void addAsLog(Log log) throws IllegalArgumentException{
		if(! canHaveAsLog(log))
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
	 * Set collecting references to logs attached to this world.
	 * @invar The set of logs is effective.
	 * @invar Each element in the set of logs references a log that
	 * 		is an acceptable log for this world.
	 * @invar Each log in the set of logs references this world as the world
	 * 		to which it is attached.
	 */
	private Set<Log> logs = new HashSet<Log>();
	/**
	 * Check whether the given number of units is a valid number of units for
	 * any world.
	 * 
	 * @param Units
	 *            The number of units to check.
	 * @return True if and only if the number of units is less than or equal to
	 *         100 and greater than or equal to zero.
	 */
	public static boolean isValidNumberUnits(int Units) {
		return (Units >= 0 && Units <= 100);
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
	 */
	@Basic
	@Raw
	public boolean hasAsUnit(Unit unit){
		return this.units.contains(unit);
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
	 * @return False if the total number of units is greater than hundred.
	 * 		Otherwise, true if and only if this world can have each of its units as
	 * 		a unit attached to it, and if each of these units references this world
	 * 		as their world.
	 */
	@Raw
	public boolean hasProperUnits(){
		// Uit forloop gehaald, anders ga je dit elke keer opnieuw controleren.
		if (this.getNumberUnits() >100)
			return false;
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
	 * @post This world has the given unit as one of its units.
	 * @post The given unit references this world as the world to which it is attached.
	 * @effect The given unit is added to a faction.
	 * @throws IllegalArgumentException
	 * 		This world cannot have the given unit as one of its units or 
	 * 		the total number of units in this world is not less than 100.
	 * @throws IllegalArgumentException
	 * 		The given unit is positioned outside the gameworld or inside a cube that is not passable.
	 * @throws IllegalArgumentException
	 * 		The given unit is already attached to some world.
	 */
	public void addAsUnit(Unit unit) throws IllegalArgumentException{

		if(! canHaveAsUnit(unit)|| !(getNumberUnits() <100)){
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
	 * @post This world does not have the given unit as one of its units.
	 * @post If this world has the given unit as one of its units,
	 * 		the given unit is no longer attached to any world.
	 * @effect If this world has the given unit as one of its units,
	 * 		the given unit is removed from the set of units attached to its faction.
	 * @throws IllegalArgumentException
	 */
	void removeAsUnit(Unit unit) throws IllegalArgumentException{
		if( unit == null)
			throw new IllegalArgumentException();
		if (hasAsUnit(unit)){
			this.units.remove(unit);
			this.removeUnitFromUnitsAtCubeMap(unit);
			unit.setWorld(null);
			unit.getFaction().removeAsUnit(unit);
			// overbodig, gebeurd al in removeAsUnit
			//unit.setFaction(null);
		}
	}
	/**
	 * Return the set collecting references to units attached to this world.
	 * 	@return
	 */
	public Set<Unit> listAllUnits(){
		return units;
	}
	/**
	 * Return the set collecting references to units attached 
	 * to this world and the given faction.
	 * @param faction
	 * 		The faction units need to belong to.
	 * @throws IllegalArgumentException
	 * 		The given faction is not attached to this world.
	 * @return
	 */
	// Wat als de gegeven faction niet bestaat of zich niet in de gameworld bevindt? 
	
	public Set<Unit> listAllUnitsOfFaction(Faction faction) throws IllegalArgumentException{
		if( !hasAsFaction(faction))
			throw new IllegalArgumentException();
		return faction.getUnits();
	}
	/**
	 * Return the set collecting references to boulders attached to this world.
	 * 	@return
	 */
	public Set<Boulder> listAllBoulders(){
		return boulders;
	}
	/**
	 * Return the set collecting references to logs attached to this world.
	 * 	@return
	 */
	public Set<Log> listAllLogs(){
		return logs;
	}
	
	
	public List<List<?>> inspectCube(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		List<List<?>> list = new ArrayList<>();
		List<TerrainType> terrainType= new ArrayList<TerrainType>();
		terrainType.add(this.getTerrain(position));
		list.add(terrainType);
		
		List<Unit> unitList= new ArrayList<Unit>();
		if(unitsAtCubeMap.get(position) !=null){
			for (Unit unit:unitsAtCubeMap.get(position)){
				unitList.add(unit);
			}
		}
		list.add(unitList);
		
		List<Log> logList= new ArrayList<Log>();
		if(logsAtCubeMap.get(position) !=null){

		for (Log log:logsAtCubeMap.get(position)){
			logList.add(log);
		}
		}
		
		list.add(logList);

		
		List<Boulder> boulderList= new ArrayList<Boulder>();
		if(bouldersAtCubeMap.get(position) !=null){

		for (Boulder boulder:bouldersAtCubeMap.get(position)){
			boulderList.add(boulder);
		}
		}
		list.add(boulderList);
	
		return list;
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
		if (unitsAtCubeMap.get(position)==null)
			return new HashSet<>();
		else{
			return unitsAtCubeMap.get(position);
		}
	}
	/**
	 * Return a set of logs in this world that are located at the given position in this world.
	 * @param position
	 * 		The position of the logs.
	 * @return
	 * @throws IllegalArgumentException
	 * 	The given position is not located inside this world.
	 */
	public Set<Log> getLogs(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		if (logsAtCubeMap.get(position)==null)
			return new HashSet<>();
		else{
			return logsAtCubeMap.get(position);
		}
	}
	/**
	 * Return a set of boulders in this world that are located at the given position in this world.
	 * @param position
	 * 		The position of the boulders.
	 * @return
	 * @throws IllegalArgumentException
	 * 	The given position is not located inside this world.
	 */
	public Set<Boulder> getBoulders(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		if (bouldersAtCubeMap.get(position)==null)
			return new HashSet<>();
		else{
			return bouldersAtCubeMap.get(position);
		}
	}
	
	
	private void addUnitToUnitsAtCubeMap(Unit unit){
		Set<Unit> unitsAtCube = this.unitsAtCubeMap.get(unit.getCubeCoordinate());
		if ( unitsAtCube != null){
			unitsAtCube.add(unit);
			this.unitsAtCubeMap.put(unit.getCubeCoordinate(),unitsAtCube);
		}
		else{
			unitsAtCube = new HashSet<Unit>();
			unitsAtCube.add(unit);
			this.unitsAtCubeMap.put(unit.getCubeCoordinate(),unitsAtCube);
		}
	}
	
	private void removeUnitFromUnitsAtCubeMap(Unit unit){
		Set<Unit> unitsAtCube = this.unitsAtCubeMap.get(unit.getCubeCoordinate());
		if (unitsAtCube.contains(unit)){
			unitsAtCube.remove(unit);
			if (unitsAtCube.isEmpty())
				unitsAtCubeMap.remove(unit.getCubeCoordinate());
			else{
				unitsAtCubeMap.replace(unit.getCubeCoordinate(), unitsAtCube);
			}
		}
	}
	
	private Map<int[],Set<Unit>> unitsAtCubeMap = new HashMap<int[], Set<Unit>>();

	
	
	private void addBoulderToBouldersAtCubeMap(Boulder boulder){
		Set<Boulder> bouldersAtCube = this.bouldersAtCubeMap.get(boulder.getCubeCoordinate());
		if ( bouldersAtCube != null){
			bouldersAtCube.add(boulder);
			this.bouldersAtCubeMap.put(boulder.getCubeCoordinate(),bouldersAtCube);
		}
		else{
			bouldersAtCube = new HashSet<Boulder>();
			bouldersAtCube.add(boulder);
			this.bouldersAtCubeMap.put(boulder.getCubeCoordinate(),bouldersAtCube);
		}
	}
	private void removeBoulderFromBouldersAtCubeMap(Boulder boulder){
		Set<Boulder> bouldersAtCube = this.bouldersAtCubeMap.get(boulder.getCubeCoordinate());
		if (bouldersAtCube.contains(boulder)){
			bouldersAtCube.remove(boulder);
			if (bouldersAtCube.isEmpty())
				bouldersAtCubeMap.remove(boulder.getCubeCoordinate());
			else{
				bouldersAtCubeMap.replace(boulder.getCubeCoordinate(), bouldersAtCube);
			}
		}
	}
	
	private Map<int[],Set<Boulder>> bouldersAtCubeMap = new HashMap<int[],Set<Boulder>>();

	
	private void addLogToLogsAtCubeMap(Log log){
		Set<Log> logsAtCube = this.logsAtCubeMap.get(log.getCubeCoordinate());
		if ( logsAtCube != null){
			logsAtCube.add(log);
			this.logsAtCubeMap.put(log.getCubeCoordinate(),logsAtCube);
		}
		else{
			logsAtCube = new HashSet<Log>();
			logsAtCube.add(log);
			this.logsAtCubeMap.put(log.getCubeCoordinate(),logsAtCube);
		}
	}
	
	private void removeLogFromLogsAtCubeMap(Log log){
		Set<Log> logsAtCube = this.logsAtCubeMap.get(log.getCubeCoordinate());
		if (logsAtCube.contains(log)){
			logsAtCube.remove(log);
			if (logsAtCube.isEmpty())
				logsAtCubeMap.remove(log.getCubeCoordinate());
			else{
				logsAtCubeMap.replace(log.getCubeCoordinate(), logsAtCube);
			}
		}
	}
	
	private Map<int[],Set<Log>> logsAtCubeMap = new HashMap<int[],Set<Log>>();

	private final Set<Unit> units = new HashSet<Unit>();
	
	private String Char;
	private StringBuilder Name;
	private int maxUnits = 100;
	private int maxFactions = 5;
	
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

	
	/**
	 * @return the connectedToBorder
	 */
	public ConnectedToBorder getConnectedToBorder() {
		return connectedToBorder;
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
		setTerrain(position,TerrainType.AIR);
		if (new Random().nextDouble() <= 0.25){
			if (getTerrain(position) == TerrainType.ROCK){
				Boulder boulder = new Boulder(position);
				addAsBoulder(boulder);
			}
			if (getTerrain(position) == TerrainType.TREE){
				Log log = new Log(position);
				addAsLog(log);
			}
		}
		for (int[] positionToChange: toChange){
			solidToPassableUpdate(positionToChange);
			
		}
		
	}

	public void advanceTime(double duration){
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


	

