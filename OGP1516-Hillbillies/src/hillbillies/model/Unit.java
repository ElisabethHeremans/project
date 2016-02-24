package hillbillies.model;

public class Unit { 
	
	public Unit(String name, int weight, int strength, int agility, int toughness, double position, double orientation, int hitpoint, int staminapoints){
		
	}
	private double orientation = Math.PI/2.0;
	
	/**
	 * Update the position and activity status.
	 * @param duration
	 * @post the new position and activity status of a unit 
	 * @throws IllegalArgumentException
	 * 		If the duration is less than zero or exceeds or equals 0.2 s.
	 */
	public void advanceTime(float duration) throws IllegalArgumentException{
		if(duration<0 || duration>= 0.2)
			throw new IllegalArgumentException(); 
	}
	
	public int getMaxStaminaPoints(){
		return Math.ceil(200.0*(this.getWeight/100.0)*(this.getToughness/100.0));
	}
	
	public int getMaxHitPoints(){
		return Math.ceil(200.0*(this.getWeight/100.0)*(this.getToughness/100.0));
	}

	public double getOrientation() {
		return orientation;
	}

	public void setOrientation(double orientation) {
		this.orientation = orientation;
	}
	
	public float getBaseSpeed(){
		return 1.5*(this.getStrength+this.getAgility)/(200.0*(this.getWeight/100));
	}

}
