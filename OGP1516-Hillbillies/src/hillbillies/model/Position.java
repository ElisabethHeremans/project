package hillbillies.model;

import java.util.Arrays;

import be.kuleuven.cs.som.annotate.Value;

/**
 * A value class of positions, as three-dimensional arrays of coordinates.
 * 
 * @author adminheremans
 *
 */
@Value
public class Position {
	
		/**
		 * Initialize this new position with a given integer array of coordinates.
		 * @param coords
		 * 			The coordinates that define the position.
		 */
		public Position(int[] coords){
			this.coords = coords;
		}
		
		/**
		 * A variable registering the coordinates of this position.
		 */
		private int[] coords;
		
		public int[] getCoords(){
			return this.coords;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		/**
		 * Return the hashcode of this position.
		 * 
		 * @return the hash code of this position
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(coords);
			return result;
		}



		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		/**
		 * Return whether this position equals an other position.
		 * 
		 * @return True if and only if this position's coordinates are the same as the compared position's coordinates.
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Position other = (Position) obj;
			if (!Arrays.equals(coords, other.coords))
				return false;
			return true;
		}
		
		public String toString(){
			return Arrays.toString(getCoords());
		}


}
