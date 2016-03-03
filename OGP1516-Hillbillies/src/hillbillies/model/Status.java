package hillbillies.model;

import be.kuleuven.cs.som.annotate.Value;

@Value
public enum Status {
	MOVING{
	},
	ATTACKING,
	WORKING,
	DEFENDING,
	INTERRUPTED_MOVING,
	INITIAL_RESTING,
	RESTING,
	IN_CENTER,
	DONE
	
	
	

}
