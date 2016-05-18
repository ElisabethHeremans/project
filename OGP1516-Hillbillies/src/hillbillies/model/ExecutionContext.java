package hillbillies.model;

import java.util.HashMap;
import java.util.Map;

import hillbillies.model.expression.Expression;

public class ExecutionContext {
	public ExecutionContext(Unit executingUnit, int[] selectedCube, HashMap<String,Expression<?>> variables){
		setExecutingUnit(executingUnit);
		setSelectedCube(selectedCube);
		setVariables(variables);
	}
	
	private Unit executingUnit;
	
	private int[] selectedCube;
	
	private Map<String,Expression<?>> variables = new HashMap<String,Expression<?>>();

	public void addVariable(String variableName,Expression<?> expr){
		if (getVariables()==null)
			setVariables(new HashMap<String,Expression<?>>());
//		System.out.println("add variable");
//		System.out.println(variableName);
//		System.out.println(variables);
//		System.out.println(expr);
		try{
			variables.put(variableName, expr);
		}
		catch(ClassCastException exc){
			System.out.println(exc);
		}
		catch(UnsupportedOperationException exc2){
			System.out.println(exc2);
		}
		catch(IllegalArgumentException exc3){
			System.out.println(exc3);
		}
		catch(NullPointerException exc4){
			System.out.println(exc4);
		
		}
		
			
	}


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
	public Map<String, Expression<?>> getVariables() {
		return variables;
	}

	/**
	 * @param variables2 the variables to set
	 */
	public void setVariables(HashMap<String, Expression<?>> variables2) {
		this.variables = variables2;
	}}
