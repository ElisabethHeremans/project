package hillbillies.model.types;

public abstract class Type {
	
	public Type(Object value){
		setValue(value);
	}
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
