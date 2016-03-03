package hillbillies.part1.facade;

import hillbillies.model.Status;
import hillbillies.model.Unit;
import ogp.framework.util.ModelException;

public class Facade implements IFacade {
	
	
	@Override
	public Unit createUnit(String name, int[] initialPosition, int weight, int agility, int strength, int toughness,
			boolean enableDefaultBehavior) throws ModelException {
		try{
			return new Unit(name,Unit.getPosition(initialPosition), weight,  agility, strength,toughness,enableDefaultBehavior);
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
		try{
			return unit.getPosition();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int[] getCubeCoordinate(Unit unit) throws ModelException {
		try{
			return unit.getCubeCoordinate();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public String getName(Unit unit) throws ModelException {
		try{
			return unit.getName();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setName(Unit unit, String newName) throws ModelException {
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
		try{
			return unit.getWeight();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setWeight(Unit unit, int newValue) throws ModelException {
		try{
			unit.setWeight(newValue);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public int getStrength(Unit unit) throws ModelException {
		try{
			return unit.getStrength();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setStrength(Unit unit, int newValue) throws ModelException {
		try{
			unit.setStrength(newValue);
		}
		
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getAgility(Unit unit) throws ModelException {
		try{
			return unit.getAgility();
		}
		
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setAgility(Unit unit, int newValue) throws ModelException {
		try{
			unit.setAgility(newValue);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getToughness(Unit unit) throws ModelException {
		try{
			return unit.getToughness();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setToughness(Unit unit, int newValue) throws ModelException {
		try{
			unit.setToughness(newValue);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getMaxHitPoints(Unit unit) throws ModelException {
		try{
			return (int) unit.max_nbPoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getCurrentHitPoints(Unit unit) throws ModelException {
		try{
			return (int) unit.getHitpoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getMaxStaminaPoints(Unit unit) throws ModelException {
		try{
			return (int) unit.max_nbPoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public int getCurrentStaminaPoints(Unit unit) throws ModelException {
		try{
			return (int) unit.getStaminaPoints();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void advanceTime(Unit unit, double dt) throws ModelException {
		try{
			unit.advanceTime((float)dt); 
		}
		catch (NullPointerException exc){
			
			exc.printStackTrace();
			throw new ModelException();

		}
		catch (IllegalArgumentException exc){
			throw new ModelException();
		}
	}

	@Override
	public void moveToAdjacent(Unit unit, int dx, int dy, int dz) throws ModelException {
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
		try{
			return unit.getCurrentSpeed();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public boolean isMoving(Unit unit) throws ModelException {
		try{
			return unit.status == Status.MOVING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public void startSprinting(Unit unit) throws ModelException {
		try{
			unit.startSprinting();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
			
	}

	@Override
	public void stopSprinting(Unit unit) throws ModelException {
		try{
			unit.stopSprinting();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
				
	}

	@Override
	public boolean isSprinting(Unit unit) throws ModelException {
		try{
			return unit.isSprinting;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
			}

	@Override
	public double getOrientation(Unit unit) throws ModelException {
		try{
			return (double) unit.getOrientation();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public void moveTo(Unit unit, int[] cube) throws ModelException {
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
		try{
			unit.work();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
			
		}

	@Override
	public boolean isWorking(Unit unit) throws ModelException {
		try{
			return unit.status == Status.WORKING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public void fight(Unit attacker, Unit defender) throws ModelException {
		try{
			attacker.attack(defender);
			//defender.defend(attacker);
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
		try{
			return unit.status == Status.ATTACKING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
		
	}

	@Override
	public void rest(Unit unit) throws ModelException {
		try{
			unit.rest();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
			
		
	}

	@Override
	public boolean isResting(Unit unit) throws ModelException {
		try{
			return unit.status == Status.RESTING;
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}

	@Override
	public void setDefaultBehaviorEnabled(Unit unit, boolean value) throws ModelException {
		try{
			unit.setEnableDefaultBehaviour(value);
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}		
	}

	@Override
	public boolean isDefaultBehaviorEnabled(Unit unit) throws ModelException {
		try{
			return unit.isEnableDefaultBehaviour();
		}
		catch (NullPointerException exc){
			throw new ModelException();
		}
	}
	
}
