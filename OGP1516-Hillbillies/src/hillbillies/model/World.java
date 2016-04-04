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
	}
	
	
	
	private final TerrainChangeListener listener;

	/**
	 * Return the number of units of this world.
	 */
	@Basic
	@Raw
	public int getNumberUnits() {
		return this.units.size();
	}

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
	 * Return the number of boulders of this world.
	 */
	@Basic @Raw
	public int getNumberBoulders() {
		return this.boulders.size();
	}
	

	/**
	 * Return the number of logs of this world.
	 */
	@Basic @Raw
	public int getNumberLogs() {
		return this.logs.size();
	}
	
	/**
	 * Return the terraintype of a cube.
	 * @param position
	 * 		The position of the cube.
	 */
	public TerrainType getTerrain(int[] position) throws IllegalArgumentException {
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		return TerrainType.getTerrain(terrainTypes[position[0]][position[1]][position[2]]);
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
		listener.notifyTerrainChanged(position[0], position[1], position[2]);
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
			for (int[] j: terrainTypes[i])
				for (int k: j)
					if (terrainTypes[i][j][k] != 0 ||terrainTypes[i][j][k] != 1
					||terrainTypes[i][j][k] != 2||terrainTypes[i][j][k] != 3)
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
		Unit spawnUnit = new Unit(randomName(), new double[] { (new Random().nextDouble()) * getxDimension()*L, 
				(new Random().nextDouble()) * this.getyDimension()*L,(new Random().nextDouble()) * getzDimension()*L }, 
				new Random().nextInt(201)+1,new Random().nextInt(201)+1, new Random().nextInt(201)+1
				,new Random().nextInt(201)+1,enableDefaultBehavior,(double) new Random().nextInt(),
				(double)new Random().nextInt(),new Random().nextDouble()*360);
		 addAsUnit(spawnUnit);
		 spawnUnit.setWorld(this);
		 addToFaction(spawnUnit);
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
		for(Faction faction: this.factions){
			if(faction.canHaveAsUnit(unit) && unit.getFaction()==null && faction.canAddAsUnit(unit))
				unit.setFaction(faction);
			// in addAsUnit moet getFaction al verschillend zijn van nul.
				faction.addAsUnit(unit);
		}
		if(unit.getFaction()== null && getNbActiveFactions()<5)
			 newFaction = new Faction();
			 unit.setFaction(newFaction);
			 newFaction.addAsUnit(unit);
	}
	// we moeten misschien ook eens nadenken hier hoe we die request stilzwijgend gaan beeindigen. Misschien gewoon een nullwaarde teruggeven? 
	// ik kon ook geen locale variabele maken van newFaction en ik vraag mij af of we die dan altijd ook terug op null moeten zetten 
	private Faction newFaction;
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
			for (int j =-1; i<2; i++){
				for (int k =-1; i<2; i++){
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


	
	@Basic @Raw
	public int getNbFactions(){
		return factions.size();
	}
	
	@Raw
	public int getNbActiveFactions(){
		return getActiveFactions().size();
	}
	
	public Set<Faction> getActiveFactions(){
		Set<Faction> activeFactions = new HashSet<Faction>();
		for(Faction faction: this.factions){
			if (faction.isActive())
				activeFactions.add(faction);
		}
		return activeFactions;
	}
	
	@Basic @Raw
	public boolean hasAsFaction(Faction faction) throws IllegalArgumentException{
		return factions.contains(faction);
	}
	
	@Raw
	public boolean canHaveAsFaction(Faction faction){
		return (faction != null && (!this.isTerminated() || faction.isTerminated()) &&faction.getNbUnits() <=50 && faction.getNbUnits() > 0);
	}
	
	
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
	
	public void addAsFaction(Faction faction)throws IllegalArgumentException{
		if (! canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		if (getNbActiveFactions()!=5)
			factions.add( faction);
//		if (faction.getWorld() != null)
//			throw new IllegalArgumentException();
		//faction.setWorld(this);
		
	}
	
	public void removeAsFaction(Faction faction){
			
		if( faction == null)
			throw new IllegalArgumentException();
		if (hasAsFaction(faction))
			this.factions.remove(faction);
	}
	
	private final Set<Faction> factions = new HashSet<Faction>();
	
	@Basic
	@Raw
	public boolean hasAsBoulder(Boulder boulder){
		return this.boulders.contains(boulder);
	}
	@Raw
	public boolean canHaveAsBoulder(Boulder boulder){
		return (boulder != null) && (! this.isTerminated() || boulder.isTerminated());
	}
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
	
	public void addAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if(! canHaveAsBoulder(boulder))
			throw new IllegalArgumentException();	
		
		this.boulders.add(boulder);
		this.addBoulderToBouldersAtCubeMap(boulder);
		boulder.setWorld(this);
	}
	public void removeAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if( boulder == null)
			throw new IllegalArgumentException();
		if (hasAsBoulder(boulder)){
			this.boulders.remove(boulder);
			this.removeBoulderFromBouldersAtCubeMap(boulder);
			boulder.setWorld(null);
		}
	}
	
	private final Set<Boulder> boulders = new HashSet<Boulder>();
	
	@Basic
	@Raw
	public boolean hasAsLog(Log log){
		return this.logs.contains(log);
	}
	@Raw
	public boolean canHaveAsLog(Log log){
		return (log != null) && (! this.isTerminated() || log.isTerminated());
	}
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
	
	public void addAsLog(Log log) throws IllegalArgumentException{
		if(! canHaveAsLog(log))
			throw new IllegalArgumentException();	
		this.logs.add(log);
		this.addLogToLogsAtCubeMap(log);
		log.setWorld(this);
	}
	
	
	public void removeAsLog(Log log) throws IllegalArgumentException{
		if( log == null)
			throw new IllegalArgumentException();
		if (hasAsLog(log)){
			this.logs.remove(log);
			this.removeLogFromLogsAtCubeMap(log);
			log.setWorld(null);
		}
	}
	
	private final Set<Log> logs = new HashSet<Log>();
	
	@Basic
	@Raw
	public boolean hasAsUnit(Unit unit){
		return this.units.contains(unit);
	}
	@Raw
	public boolean canHaveAsUnit(Unit unit){
		return (unit != null) && (! this.isTerminated() || unit.isTerminated());
	}
	@Raw
	public boolean hasProperUnits(){
		for (Unit unit: this.units){
			if (! canHaveAsUnit(unit))
				return false;
			if (unit.getWorld() != this)
				return false;
			if (this.getNumberUnits() >100)
				return false;
		}
		return true;
	}
	
	public void addAsUnit(Unit unit) throws IllegalArgumentException{
		if(! canHaveAsUnit(unit))
			throw new IllegalArgumentException();	
		if (getNumberUnits() <100 && !hasAsUnit(unit)){
			this.units.add(unit);
			this.addUnitToUnitsAtCubeMap(unit);
			unit.setWorld(this);
			
			if (getNbActiveFactions()<5){
				Faction faction = new Faction();
				faction.addAsUnit(unit);
			}
			else{
				int minNbUnits = 50;
				Faction minNbUnitsFaction = null;
				for (Faction faction: factions){
					if (faction.isActive() && faction.getNbUnits()< minNbUnits){
						minNbUnits = faction.getNbUnits();
						minNbUnitsFaction = faction;
					}
					
				}
				if (minNbUnitsFaction != null){
					minNbUnitsFaction.addAsUnit(unit);
				}
			}
		}
	}
	public void removeAsUnit(Unit unit) throws IllegalArgumentException{
		if( unit == null)
			throw new IllegalArgumentException();
		if (hasAsUnit(unit)){
			this.units.remove(unit);
			this.removeUnitFromUnitsAtCubeMap(unit);
			unit.setWorld(null);
			unit.getFaction().removeAsUnit(unit);
			unit.setFaction(null);
		}
	}
	
	public List<Unit> listAllUnits(){
		List<Unit> unitList= new ArrayList<Unit>();
		for (Unit unit:units){
			unitList.add(unit);
		}
		return unitList;
	}
	
	public List<List<Unit>> listAllUnitsPerFaction(){
		List<List<Unit>> list = new ArrayList<List<Unit>>();
		for (Faction faction:factions){
			if (faction.isActive()){
				List<Unit> listUnits = new ArrayList<Unit>();
				for (Unit unit:faction.getUnits()){
					listUnits.add(unit);
				}
				list.add(listUnits);
			}
		}
		return list;
	}
	
	public List<Boulder> listAllBoulders(){
		List<Boulder> boulderList= new ArrayList<Boulder>();
		for (Boulder boulder:boulders){
			boulderList.add(boulder);
		}
		return boulderList;
	}
	
	public List<Log> listAllLogs(){
		List<Log> logList= new ArrayList<Log>();
		for (Log log:logs){
			logList.add(log);
		}
		return logList;
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
	
	public Set<Unit> getUnits(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		if (unitsAtCubeMap.get(position)==null)
			return new HashSet<>();
		else{
			return unitsAtCubeMap.get(position);
		}
	}
	
	public Set<Log> getLogs(int[] position)throws IllegalArgumentException{
		if (!this.isCubeInWorld(position))
			throw new IllegalArgumentException();
		if (logsAtCubeMap.get(position)==null)
			return new HashSet<>();
		else{
			return logsAtCubeMap.get(position);
		}
	}
	
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
	
	private ConnectedToBorder connectedToBorder = new ConnectedToBorder(this.getxDimension(),this.getyDimension(),this.getzDimension());

	
	/**
	 * @return the connectedToBorder
	 */
	public ConnectedToBorder getConnectedToBorder() {
		return connectedToBorder;
	}

	public void advanceTime(double duration){
		for (int x=0; x < getxDimension(); x ++){
			for (int y=0; y< getyDimension(); y++){
				for (int z=0; z < getzDimension() ; z ++){
					if (! connectedToBorder.isSolidConnectedToBorder(x, y, z)){
						List<int[]> positionsToChange = new ArrayList<int[]>(); connectedToBorder.changeSolidToPassable(x,y,z);
						for (int[] position: positionsToChange){
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
							setTerrain(position,TerrainType.AIR);

							}
						}
					}
				}
			}
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


	

