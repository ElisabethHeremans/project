package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Faction {

	public Faction() {
		//this.Name = name;
		//this.Active = Active;
	}


	/**
	 * Return the number of units of this factions.
	 */
	@Basic
	@Raw
	public int getNbUnits() {
		return this.getUnits().size();
	}
	
	public boolean isActive(){
		return getNbUnits() > 0;
	}

	/**
	 * Check whether the given number of units is a valid number of units for
	 * any factions.
	 * 
	 * @param Units
	 *            The number of units to check.
	 * @return | result ==
	 */
	public static boolean isValidNumberUnits(int Units) {
		return (0<= Units && Units<= 50);
	}


	private String Name;
	private boolean Active;
	private Set<Unit> units = new HashSet<Unit>();
	
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
			if (unit.getFaction() != this)
				return false;
		}
		if (this.getNbUnits() >50)
			return false;
		
		return true;
	}
	
	public boolean canAddAsUnit(Unit unit){
		return canHaveAsUnit(unit) && getNbUnits()<50;
	}
	
	public void addAsUnit(Unit unit) throws IllegalArgumentException{
		if(! canHaveAsUnit(unit) || getNbUnits()>50)
			throw new IllegalArgumentException();	
		if( unit.getFaction() != null )
			// check of unit van faction kan veranderen.
			this.units.add(unit);
			unit.setFaction(this);
	}
	public void removeAsUnit(Unit unit) throws IllegalArgumentException{
		if( unit == null)
			throw new IllegalArgumentException();
		if (hasAsUnit(unit))
			this.units.remove(unit);
			unit.setFaction(null);
			// Wat als... geen units meer.
	}
	
	public Set<Unit> getUnits(){
		return units;
	}
	
	/**
	 * Terminate this faction.
	 *
	 * @post   This faction  is terminated.
	 *       | new.isTerminated()
	 * @post   ...
	 *       | ...
	 */
	 public void terminate() {
		 this.isTerminated = true;
	 }
	 
	 /**
	  * Return a boolean indicating whether or not this faction
	  * is terminated.
	  */
	 @Basic @Raw
	 public boolean isTerminated() {
		 return this.isTerminated;
	 }
	 
	 /**
	  * Variable registering whether this faction is terminated.
	  */
	 private boolean isTerminated = false;
	 
}
