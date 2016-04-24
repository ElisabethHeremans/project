package hillbillies.model;

import hillbillies.part3.programs.SourceLocation;

public class Expression {
	
	public void setValue(Object object){
		value = object;
	}
	
	
	private Object value;

	/**
	 * @return the value
	 */
	private final Object getValue() {
		return value;
	}

	public void setSourceLocation(SourceLocation sourceLocation) {
		this.sourceLocation = sourceLocation;
		
	}
	
	private SourceLocation sourceLocation;
	

}
