package hillbillies.model;

import java.util.Map;

import hillbillies.model.expression.Expression;

public class ExecutionContext {
	public ExecutionContext(Unit executingUnit, int[] selectedCube, HashMap<String,Expression> variables){
		setExecutingUnit(executingUnit);
		setSelectedCube(selectedCube);
		setVariables(variables);
	}
	
	private Unit executingUnit;
	
	private int[] selectedCube;
	
	private Map<String,Expression> variables = new HashMap<String,Expression>;

	public addVariable()

	/**
	 * @return the executingUnit
	 */
	public Unit getExecutingUnit() {
		return executingUnit;
	}

	/**
	 * @param executingUnit the executingUnit to set
	 */
	public void setExecutingUnit(Unit executingUnit) {
		this.executingUnit = executingUnit;
	}

	/**
	 * @return the selectedCube
	 */
	public int[] getSelectedCube() {
		return selectedCube;
	}

	/**
	 * @param selectedCube the selectedCube to set
	 */
	public void setSelectedCube(int[] selectedCube) {
		this.selectedCube = selectedCube;
	}

	/**
	 * @return the variables
	 */
	public Map<String, Expression> getVariables() {
		return variables;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(Map<String, Expression> variables) {
		this.variables = variables;
	}}
