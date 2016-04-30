package hillbillies.model.expression;

public class SelectedExpression extends BasicPExpression {
	public SelectedExpression(){
		this.setValue(this.getStatement().getTask().getSelectedCube());
	}
}
