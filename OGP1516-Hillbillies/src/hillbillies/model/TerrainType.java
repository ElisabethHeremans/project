package hillbillies.model;

public enum TerrainType {
	AIR{
		
		boolean getPassable(){
			return true;
		}
		
	},
	WORKSHOP{
		boolean getPassable(){
			return true;
		}
		},
	ROCK{
			boolean getPassable(){
				return false;
			}
			},
	WOOD{
				boolean getPassable(){
					return false;
				}
				}
	
	

}
