package hillbillies.part2.facade;

import java.util.HashSet;
import java.util.Set;

import hillbillies.model.Boulder;
import hillbillies.model.Faction;
import hillbillies.model.Log;
import hillbillies.model.Status;
import hillbillies.model.TerrainType;
import hillbillies.model.Unit;
import hillbillies.model.World;
import hillbillies.part2.listener.TerrainChangeListener;
import ogp.framework.util.ModelException;

public class Facade implements IFacade{
	
	public Facade(){
		
	}

	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		try{
			double[] initPosition = new double[] {((double) initialPosition[0]) +0.5,((double) initialPosition[1]) +0.5,((double) initialPosition[2]) +0.5};
			return new Unit(name, initPosition , weight,  agility, strength,toughness,enableDefaultBehavior);
		}
		catch (NullPointerException exc) {
			throw new ModelException();
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
			
	}

	@Override
	public double[] getPosition(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getPosition();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getCubeCoordinate();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getName();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setName(newName);
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
			
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getWeight(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getWeight();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setWeight(newValue);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getStrength();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setStrength(newValue);
		}
		
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getAgility();
		}
		
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setAgility(newValue);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getToughness();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.setToughness(newValue);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (int) unit.getMaxPoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (int) unit.getHitpoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (int) unit.getMaxPoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (int) unit.getStaminaPoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.advanceTime((float)dt); 
		}
		catch (NullPointerException exc){
			
			//exc.printStackTrace();
			throw new ModelException();

		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.moveToAdjacent(dx, dy, dz);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public double getCurrentSpeed(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getCurrentSpeed();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getStatus() == Status.MOVING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.startSprinting();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.stopSprinting();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isSprinting();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (double) unit.getOrientation();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.moveTo(cube);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}	
	}

	@Override
	public void work(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.work(unit.getCubeCoordinate());
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getStatus() == Status.WORKING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		// TODO Auto-generated method stub
		try{
			attacker.attack(defender);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}		
	}

	@Override
	public boolean isAttacking(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getStatus() == Status.ATTACKING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			unit.rest();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getStatus() == Status.RESTING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		// TODO Auto-generated method stub
		try{
			if (value){
				unit.startDefaultBehaviour();
			}
			else
				unit.stopDefaultBehaviour();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}	
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.isEnableDefaultBehaviour();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public World createWorld(int[][][] terrainTypes, TerrainChangeListener modelListener) throws ModelException {
		try{
			return new World(terrainTypes,modelListener);
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesX(World world) throws ModelException {
		try{
			return world.getxDimension();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesY(World world) throws ModelException {
		try{
			return world.getyDimension();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getNbCubesZ(World world) throws ModelException {
		try{
			return world.getzDimension();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void advanceTime(World world, double dt) throws ModelException {
		try{
			world.advanceTime(dt);
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getCubeType(World world, int x, int y, int z) throws ModelException {
		
		try{
			return world.getTerrain(new int[] {x,y,z}).getType();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setCubeType(World world, int x, int y, int z, int value) throws ModelException {
		try{
			world.setTerrain(new int[] {x,y,z},TerrainType.getTerrain(value));
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isSolidConnectedToBorder(World world, int x, int y, int z) throws ModelException {
		try{
			if (!world.isCubeInWorld((new int[] {x,y,z})))
					throw new IllegalArgumentException();
			return world.getConnectedToBorder().isSolidConnectedToBorder(x, y, z);
			}
		catch(NullPointerException exc){
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public Unit spawnUnit(World world, boolean enableDefaultBehavior) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return world.spawnUnit(enableDefaultBehavior);
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public void addUnit(Unit unit, World world) throws ModelException {
		// TODO Auto-generated method stub
		try{
			world.addAsUnit(unit);
		}
		catch(NullPointerException exc) {
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public Set<Unit> getUnits(World world) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (Set<Unit>) world.listAllUnits();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isCarryingLog(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (unit.getLog() != null);
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isCarryingBoulder(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (unit.getBoulder() != null);
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public boolean isAlive(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return !unit.isTerminated();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getExperiencePoints(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getExperiencePoints();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void workAt(Unit unit, int x, int y, int z) throws ModelException {
		// TODO Auto-generated method stub
		try{
			int[] position = new int[] {x,y,z};
			unit.work(position);
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
		catch(IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public Faction getFaction(Unit unit) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return unit.getFaction();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public Set<Unit> getUnitsOfFaction(Faction faction) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return faction.getUnits();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public Set<Faction> getActiveFactions(World world) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return world.getActiveFactions();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public double[] getPosition(Boulder boulder) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return boulder.getPosition();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public Set<Boulder> getBoulders(World world) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (Set<Boulder>) world.listAllBoulders();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public double[] getPosition(Log log) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return log.getPosition();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public Set<Log> getLogs(World world) throws ModelException {
		// TODO Auto-generated method stub
		try{
			return (Set<Log>) world.listAllLogs();
		}
		catch(NullPointerException exc){
			throw new ModelException();
		}
	}
	

}
