package hillbillies.model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;
import hillbillies.part2.listener.TerrainChangeListener;

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
	public World(int[] dimensions, int Units, int[][][] terrainTypes, TerrainChangeListener Listener) {
		if (!isValidNumberUnits(Units))
			Units = 0;
		setNumberUnits(Units);
		if (! isValidNumberBoulders(Boulders))
			Boulders = 0;
		setNumberBoulders(Boulders);
		if (! isValidNumberLogs(Logs))
			Logs = 0;
		setNumberLogs(Logs);
		this.terrainType = terrainTypes;
	}

	/**
	 * Return the number of units of this world.
	 */
	@Basic
	@Raw
	public int getNumberUnits() {
		return this.Units;
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
	 * Set the number of units of this world to the given number of units.
	 * 
	 * @param Units
	 *            The new number of units for this world.
	 * @post If the given number of units is a valid number of units for any
	 *       world, the number of units of this new world is equal to the given
	 *       number of units.
	 */
	@Raw
	public void setNumberUnits(int Units) {
		if (isValidNumberUnits(Units))
			this.Units = Units;
	}

	/**
	 * Variable registering the number of units of this world.
	 */
	private int Units;


	/**
	 * Return the number of boulders of this world.
	 */
	@Basic @Raw
	public int getNumberBoulders() {
		return this.Boulders;
	}
	
	/**
	 * Check whether the given number of boulders is a valid number of boulders for
	 * any world.
	 *  
	 * @param  Boulders
	 *         The number of boulders to check.
	 * @return True if and only if the number of bounders is greater than or equal 0.
	*/
	public static boolean isValidNumberBoulders(int Boulders) {
		return Boulders >=0;
	}
	
	/**
	 * Set the number of boulders of this world to the given number of boulders.
	 * 
	 * @param  Boulders
	 *         The new number of boulders for this world.
	 * @post   If the given number of boulders is a valid number of boulders for any world,
	 *         the number of boulders of this new world is equal to the given
	 *         number of boulders.
	 */
	@Raw
	public void setNumberBoulders(int Boulders) {
		if (isValidNumberBoulders(Boulders))
			this.Boulders = Boulders;
	}
	
	/**
	 * Variable registering the number of boulders of this world.
	 */
	private int Boulders;
	
	/**
	 * Return the number of logs of this world.
	 */
	@Basic @Raw
	public int getNumberLogs() {
		return this.Logs;
	}
	
	/**
	 * Check whether the given number of logs is a valid number of logs for
	 * any world.
	 *  
	 * @param  Logs
	 *         The number of logs to check.
	 * @return True if and only if the number of logs is greater than or equal to zero.
	*/
	public static boolean isValidNumberLogs(int Logs) {
		return Logs>=0;
	}
	
	/**
	 * Set the number of logs of this world to the given number of logs.
	 * 
	 * @param  Logs
	 *         The new number of logs for this world.
	 * @post   If the given number of logs is a valid number of logs for any world,
	 *         the number of logs of this new world is equal to the given
	 *         number of logs.
	 */
	@Raw
	public void setNumberLogs(int Logs) {
		if (isValidNumberLogs(Logs))
			this.Logs = Logs;
	}
	
	/**
	 * Variable registering the number of logs of this world.
	 */
	private int Logs;

	public int getTerrain (double[] position){
		int[] cube = new int[] {(int) Math.floor(position[0]), 
				(int) Math.floor(position[1]), (int) Math.floor(position[2])};
		return getTerrain(cube);
	}
	public int getTerrain(int[] position) {
		return this.terrainType[position[0]][position[1]][position[2]];
	}
	private int[][][] terrainType;
	
	public Unit spawnUnit(){
		int 
		return new Unit(randomName(), new double[] { (new Random().nextDouble()) * X*L, 
				(new Random().nextDouble()) * Y*L,(new Random().nextDouble()) * Z*L }, 
				new Random().nextInt(201)+1,new Random().nextInt(201)+1, new Random().nextInt(201)+1
				,new Random().nextInt(201)+1,new Random().nextBoolean(),(double) new Random().nextInt(),
				(double)new Random().nextInt(),new Random().nextDouble()*360);
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
	
	@Basic @Raw
	public int getNbFactions(){
		return factions.size();
	}
	
	@Basic @Raw
	public Faction getFactionAt(int index) throws IllegalArgumentException{
		if (index > getNbFactions() || index <1)
			throw new IllegalArgumentException();
		return factions.get(index);
	}
	
	@Raw
	public boolean canHaveAsFaction(Faction faction){
		return (faction != null && (!this.isTerminated || faction.isTerminated) &&faction.getNbUnits() <=50 && faction.getNbUnits() > 0);
	}
	
	@Raw
	public boolean canHaveAsFactionAt(Faction faction, int index){
		if (!canHaveAsFaction(faction))
			return false;
		if (index <1 || index > getNbFactions()+1)
			return false;
		for (int i = 1; i<=getNbFactions(); i++)
			if (i != index && getFactionAt(i) == faction)
				return false;
		return true;
	}
	
	@Raw
	public boolean hasProperFactions(){
		for (int i = 1; i<=getNbFactions(); i++){
			if (!canHaveAsFactionAt(getFactionAt(i),i))
				return false;
			if (getFactionAt(i).getWorld() != this)
				return false;
		}
		return true;
	}
	
	public void addAsFaction(Faction faction)throws IllegalArgumentException{
		if (! canHaveAsFactionAt(faction, getNbFactions()+1))
			throw new IllegalArgumentException();
		if (faction.getWorld() != null)
			throw new IllegalArgumentException();
		factions.add(getNbFactions()+1, faction);
		faction.setWorld(this);
	}
	
	public void removeAsFaction(Faction faction){
		if (hasAsFaction())
	}
	
	
	private final List<Faction> factions = new ArrayList<Faction>();
	
	private final Set<Boulder> boulders = new HashSet<Boulder>();
	
	private final Set<Log> logs = new HashSet<Log>();

	
	private String Char;
	private StringBuilder Name;
	private int X;
	private int Y;
	private int Z;
	private int L;
	private int[] dimensions = new int[] { X, Y, Z };
	private int maxUnits = 100;
	private int maxFactions = 5;
}
