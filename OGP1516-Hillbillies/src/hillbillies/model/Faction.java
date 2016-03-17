package hillbillies.model;

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
		return (Units <= 50);
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
}
