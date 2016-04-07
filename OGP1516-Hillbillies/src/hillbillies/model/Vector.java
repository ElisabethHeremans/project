package hillbillies.model;
/**
 * A class of vector manipulation.
 * @author MoranGybels
 *
 */
public class Vector {
	/**
	 * Initiate a vector.
	 * @param vector
	 * 		The given double array for this vector.
	 */
	public Vector(double[] vector){
		// zomaar leeg laten?
	}
	/**
	 * Return the distance between two vectors.
	 * @param vector1
	 * 		The first given array.
	 * @param vector2
	 * 		The second given array.
	 * @return The distance between the two vectors.
	 */
	public static double getDistance(double[] vector1, double[] vector2){
		return Math.sqrt(Math.pow(vector1[0] - vector2[0], 2.0)
				+ Math.pow(vector1[1] - vector2[1], 2.0)
				+ Math.pow(vector1[2] - vector2[2], 2.0));
	}
	/**
	 * Multiply a vector with a scalar.
	 * @param vector
	 * 		The given vector to be multiplied.
	 * @param scalar
	 * 		The given scalar to multiply with.
	 * @return The multiplication of the vector with the scalar.
	 */
	public static double[] scalarMultiplication(double[] vector, double scalar){
		return new double[] {vector[0]*scalar, vector[1]*scalar, vector[2]*scalar};
	}
	/**
	 * Add two vectors.
	 * @param vector1
	 * 		The first vector to be added.
	 * @param vector2
	 * 		The second vector to add.
	 * @return the sum of the two vectors
	 */
	public static double[] vectorAdd(double[] vector1, double[] vector2){
		return new double[] {vector1[0]+vector2[0],vector1[1]+vector2[1],vector1[2]+vector2[2]};
	}
	/**
	 * Substract a vector from another vector.
	 * @param vector1
	 * 		The minuend.
	 * @param vector2
	 * 		The subtrahend.
	 * @return The difference of the two vectors.
	 */
	public static double[] vectorReduction(double[] vector1, double[] vector2){
		return new double[] {vector1[0]-vector2[0],vector1[1]-vector2[1],vector1[2]-vector2[2]};
	}

}
