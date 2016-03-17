package hillbillies.model;

import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

public class Faction {

	public Faction(String name, boolean Active) {
		this.Name = name;
		this.Active = Active;
	}


	/**
	 * Return the number of units of this factions.
	 */
	@Basic
	@Raw
	public int getNbUnits() {
		return NbUnits;
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

	/**
	 * Set the number of units of this factions to the given number of units.
	 * 
	 * @param Units
	 *            The new number of units for this factions.
	 * @post If the given number of units is a valid number of units for any
	 *       factions, the number of units of this new factions is equal to the
	 *       given number of units. | if (isValidNumberUnits(Units)) | then
	 *       new.getNumberUnits() == Units
	 */
	@Raw
	public void setNumberUnits(int Units) {
		if (isValidNumberUnits(Units))
			NbUnits = Units;
	}

	private String Name;
	private int NbUnits;
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
			if (this.getNbUnits() >50)
				return false;
		}
		return true;
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
	
}
