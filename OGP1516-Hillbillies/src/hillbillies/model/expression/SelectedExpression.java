package hillbillies.model.expression;

import hillbillies.model.Position;
import hillbillies.model.expression.vuilbak.BasicPExpression;

public class SelectedExpression<E> extends PositionExpression<E> {
	public SelectedExpression(){
		this.setValue(new Position(this.getStatement().getTask().getSelectedCube()));
	}

	@Override
	public Position evaluateExpression() {
		// TODO Auto-generated method stub
		return null;
	}
}
