package hillbillies.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

public abstract class RawMaterial {

	public RawMaterial(double[] position) {
	}
	
	public abstract double[] getPosition();
	
	public abstract void setPosition(double[] position);
	
	public abstract boolean isValidPosition(double[] position);
	
	public abstract int getWeight();
	
	protected abstract void setWeight(int weight);
	
	public abstract void advanceTime(float duration);
	
	@Basic @Raw
	public World getWorld(){
		return this.world;
	}
	
	public abstract void setWorld(@Raw World world);
	
	@Raw
	public abstract boolean hasProperWorld();
	private World world;
	
	public abstract void terminate();
	
	public abstract boolean isTerminated();

	
}
