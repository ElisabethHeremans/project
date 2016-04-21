package hillbillies.model;

public class Statement {
	
	public boolean hasExpression(Expression e){
		if (this.toString().contains(e.toString()))
			return true;
		return false;
	}

}
