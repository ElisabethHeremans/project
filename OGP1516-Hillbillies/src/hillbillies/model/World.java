package hillbillies.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
	public World(int[] dimensions,int[][][] terrainTypes, TerrainChangeListener Listener) {
		this.xDimension = dimensions[0];
		this.yDimension = dimensions[1];
		this.zDimension = dimensions[2];
		this.terrainTypes = terrainTypes;
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
	

	public TerrainType getTerrain (double[] position){
		int[] cube = getCubePosition(position);
		return getTerrain(cube);
	}
	public TerrainType getTerrain(int[] position) {
		return TerrainType.getTerrain(this.terrainTypes[position[0]][position[1]][position[2]]);
	}
	
	private void setTerrain(int[] position, TerrainType terrain){
		terrainTypes[position[0]][position[1]][position[2]] = terrain.getType();
	}
	
	private int[][][] terrainTypes;
	
	public Unit spawnUnit(){
	 
		return new Unit(randomName(), new double[] { (new Random().nextDouble()) * X*L, 
				(new Random().nextDouble()) * Y*L,(new Random().nextDouble()) * Z*L }, 
				new Random().nextInt(201)+1,new Random().nextInt(201)+1, new Random().nextInt(201)+1
				,new Random().nextInt(201)+1,new Random().nextBoolean(),(double) new Random().nextInt(),
				(double)new Random().nextInt(),new Random().nextDouble()*360, addToFaction());
	}
	// ik denk foutje : 201-> 200 & nieuwe faction starten en zo: hoe?
	
	private String randomName(){
		Char = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz \'\"";
		int Length = new Random().nextInt(9)+2;
		Name.append(Char.charAt(new Random().nextInt(26)));
		for( int i = 1; i < Length; i++ ) 
		      Name.append( Char.charAt(new Random().nextInt(Char.length()) ) );
		   return Name.toString();
	}
	
	public static int[] getCubePosition(double[] position){
		return new int[] { (int) Math.floor(position[0]), (int) Math.floor(position[1]),
				(int) Math.floor(position[2]) };
	}
	
	public boolean isCubeInWorld(int[] cubePosition){
		return (0 <= cubePosition[0]) && (cubePosition[0] < getxDimension()*L) && (0 <= cubePosition[1]) 
				&& (cubePosition[1] < getyDimension()*L ) && (0 <= cubePosition[2]) && (cubePosition[2] < getzDimension()*L);
	}
	
	public boolean getPassable(int[] cubePosition){
		return this.getTerrain(cubePosition).isPassable();
	}
	
	@Basic @Raw
	public int getNbFactions(){
		return factions.size();
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
			if (faction.getWorld() != this)
				return false;
			if (getNbFactions()>50)
				return false;
			
		}
		return true;
	}
	
	public void addAsFaction(Faction faction)throws IllegalArgumentException{
		if (! canHaveAsFaction(faction))
			throw new IllegalArgumentException();
		if (getNbFactions()!=5)
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
		return (boulder != null) && (! this.isTerminated() || unit.isTerminated());
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
		boulder.setWorld(this);
	}
	public void removeAsBoulder(Boulder boulder) throws IllegalArgumentException{
		if( boulder == null)
			throw new IllegalArgumentException();
		if (hasAsBoulder(boulder))
			this.boulders.remove(boulder);
			boulder.setWorld(null);
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
			if (! canHaveAsLog(logs))
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
		log.setWorld(this);
	}
	
	
	public void removeAsLog(Log log) throws IllegalArgumentException{
		if( log == null)
			throw new IllegalArgumentException();
		if (hasAsLog(log))
			this.logs.remove(log);
			log.setWorld(null);
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
		if(! canHaveAsUnit(unit) || getNumberUnits()>1)
			throw new IllegalArgumentException();	
		if (getNumberUnits() <100 && !hasAsUnit(unit)){
		//if( unit.getFaction() != null )
			// check of unit van faction kan veranderen.
			this.units.add(unit);
			unit.setWorld(this);
			if (getNbFactions()<5){
				Faction faction = new Faction();
				faction.addAsUnit(unit);
				unit.setFaction(faction);
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
		if (hasAsUnit(unit))
			this.units.remove(unit);
			unit.setWorld(null);
			// Wat als... geen units meer.
	}
	
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

	
	public void advanceTime(){
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
		}
		

	
	
	


	
	/**
	 * Symbolic constant registering the side length of cubes, expressed in meters.
	 */
	private static final double L = 1.0;



	
}
