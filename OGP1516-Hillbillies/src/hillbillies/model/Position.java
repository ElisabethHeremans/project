package hillbillies.model;

import java.util.Arrays;

import be.kuleuven.cs.som.annotate.Value;

@Value
public class Position {
	
		
		public Position(int[] coords){
			this.coords = coords;
		}
		
		private int[] coords;

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
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


}
