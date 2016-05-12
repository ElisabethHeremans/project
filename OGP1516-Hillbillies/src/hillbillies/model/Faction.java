 package hillbillies.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import be.kuleuven.cs.som.annotate.*;
/**
 * A class of factions.
 * 
 * @invar Each faction has proper units for that faction
 */
public class Faction {
	/**
	 * Initialize this new faction.
	 */
	public Faction() {
		setScheduler(new Scheduler());
	}


	/**
	 * Return the number of units of this factions.
	 */
	@Basic
	@Raw
	protected int getNbUnits() {
		return this.getUnits().size();
	}
	/**
	 * Check whether this faction is active.
	 * @return True if and only if the number of units in this faction is greater than zero.
	 */
	protected boolean isActive(){
		return getNbUnits() > 0;
	}

	/**
	 * Check whether the given number of units is a valid number of units for
	 * any factions.
	 * 
	 * @param Units
	 *            The number of units to check.
	 * @return True if and only if the number of units is greater or equal to zero, 
	 * 		but less than or equal to fifty.
	 */
	protected static boolean isValidNumberUnits(int Units) {
		return (0<= Units && Units<= 50);
	}
	/**
	 * Check whether this faction has the given unit as one of the units attached to it.
	 * @param unit
	 * 		The unit to check.
	 */
	@Basic
	@Raw
	protected boolean hasAsUnit(Unit unit){
		return this.units.contains(unit);
	}
	/**
	 * Check whether this faction can have the given unit as one of its units.
	 * @param unit
	 * 		The unit to check.
	 * @return False if the given unit is not effective. Otherwise true if and only if
	 * 		this faction is not yet terminated or the given unit is also terminated. 
	 */
	@Raw
	public boolean canHaveAsUnit(Unit unit){
		if (this.isTerminated())
			return unit == null;
		return (unit != null && !unit.isTerminated()); 
	}
	
	/**
	 * Check whether this faction has proper units attached to it.
	 * @return False if the total number of units is greater than fifty.
	 * 		Otherwise, true if and only if this faction can have each of its units as
	 * 		a unit attached to it, and if each of these units references this faction
	 * 		as their faction.
	 */
	@Raw
	public boolean hasProperUnits(){
		if (this.getNbUnits() >50)
			return false;
		for (Unit unit: this.units){
			if (! canHaveAsUnit(unit))
				return false;
			if (unit.getFaction() != this)
				return false;
		}
		return true;
	}
	/**
	 * Check whether the given unit can be added to this faction.
	 * @param unit
	 * 		The unit to check.
	 * @effect False if the given unit is not effective. Otherwise true if and only if
	 * 		this faction is not yet terminated or the given unit is also terminated and 
	 * 		the total number of units in this faction is less than fifty.
	 */
	protected boolean canAddAsUnit(Unit unit){
		return canHaveAsUnit(unit) && getNbUnits()<50;
	}
	/**
	 * Add the given unit to the set of units attached to this faction.
	 * @param unit
	 * 		The unit to be added.
	 * @post This faction has the given unit as one of its units.
	 * @post The given unit references this faction as the faction to which it is attached.
	 * @throws IllegalArgumentException
	 * 		This faction cannot have the given unit as one of its units.
	 * @throws IllegalArgumentException
	 * 		The given unit is already attached to some faction.
	 */
	public void addAsUnit(Unit unit) throws IllegalArgumentException{
		if(! canAddAsUnit(unit))
			throw new IllegalArgumentException();	
		if( unit.getFaction() != null )
			throw new IllegalArgumentException();
		this.units.add(unit);
		unit.setFaction(this);
	}
	/**
	 * Remove the given unit from the set of units attached to this faction.
	 * @param unit
	 * 		The unit to be removed.
	 * @post This faction does not have the given unit as one of its units.
	 * @post If this faction has the given unit as one of its units,
	 * 		the given unit is no longer attached to any faction.
	 * @throws IllegalArgumentException
	 */
	protected void removeAsUnit(Unit unit) throws IllegalArgumentException{
		if( unit == null)
			throw new IllegalArgumentException();
		if (hasAsUnit(unit))
			this.units.remove(unit);
			unit.setFaction(null);
	}
	/**
	 * Return the set collecting references to units attached to this faction.
	 * 	@return
	 */
	public Set<Unit> getUnits(){
		return units;
	}
	
	/**
	 * Set collecting references to units attached to this faction.
	 * @invar The set of units is effective.
	 * @invar Each element in the set of units references a unit that
	 * 		is an acceptable unit for this faction.
	 * @invar Each unit in the set of units references this faction as the faction
	 * 		to which it is attached.
	 */
	protected Set<Unit> units =  Collections.synchronizedSet(new HashSet<Unit>());
	
	/**
	 * Terminate this faction.
	 * @post   The units attached to this faction no longer references
	 * 			any faction.
	 * @post   This faction  is terminated.
	 */
	 public void terminate() {
		 for (Unit unit:this.units){
			 unit.setFaction(null);
		 }
		 units  =  Collections.synchronizedSet(new HashSet<Unit>());
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
	 
	 
	 /**
	 * Return the scheduler attached to this faction.
	 */
	 @Basic @Raw
	 public final Scheduler getScheduler() {
		return scheduler;
	 }


	 /**
	 * Set the scheduler for this faction to the given scheduler. 
	 * @param scheduler 
	 * 			the scheduler for this new faction
	 * @post The new scheduler for this faction is the same as the given scheduler.
	 * 		| new.getScheduler() == scheduler
	 * @throws IllegalArgumentException()
	 * 		The given scheduler is not a valid scheduler for any faction.
	 * 		| ! isValidScheduler(scheduler)		
	 * 
	 */
	 @Raw
	 public final void setScheduler(Scheduler scheduler) throws IllegalArgumentException {
			if(! isValidScheduler(scheduler))
	 			throw new IllegalArgumentException();
		this.scheduler = scheduler;
		scheduler.setFaction(this);
	 }
	 
	 /**
	  * Check whether the given scheduler is a valid scheduler for any faction.
	  * @param scheduler
	  * 		The scheduler to check.
	  * @return If this faction is terminated, true if and only if the scheduler is null. Else, true.
	  * 		|if (this.isTerminated())
	  *			|	result == ( scheduler == null)
	  *			|else
	  *			|	result == true
	  */
	  private boolean isValidScheduler(Scheduler scheduler) {
			if (this.isTerminated())
				return scheduler == null;
	 		return true;
	  }
	  
	/**
	 * Check whether this faction has a proper scheduler.
	 * 
	 * @return True if and only if this faction can have its scheduler as its
	 *         scheduler.
	 */
 	@Raw
	public boolean hasProperScheduler() {
		return isValidScheduler(getScheduler());
	}


	/**
	 * Variable registering the scheduler of this faction
	 */
	 private Scheduler scheduler;
	 
}
