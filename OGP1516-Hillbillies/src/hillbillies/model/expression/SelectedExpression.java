package hillbillies.model.expression;

import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class SelectedExpression extends PositionExpression {
	public SelectedExpression(){
		this.setValue(new Position(this.getStatement().getTask().getSelectedCube()));
	}

	
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
