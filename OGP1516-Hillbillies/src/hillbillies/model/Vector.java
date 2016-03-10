package hillbillies.model;

public class Vector {
	
public static double getDistance(double[] vector1, double[] vector2){
	return Math.sqrt(Math.pow(vector1[0] - vector2[0], 2.0)
			+ Math.pow(vector1[1] - vector2[1], 2.0)
			+ Math.pow(vector1[2] - vector2[2], 2.0));
}

public static double[] scalarMultiplication(double[] vector, double scalar){
	return new double[] {vector[0]*scalar, vector[1]*scalar, vector[2]*scalar};
}

public static double[] vectorAdd(double[] vector1, double[] vector2){
	return new double[] {vector1[0]+vector2[0],vector1[1]+vector2[1],vector1[2]+vector2[2]};
}

}
