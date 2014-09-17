package edu.gatech.omscs.cs6310.Interfaces;

public abstract class BaseHeatedPlate implements HeatedPlate {
	
	protected final int MAXIMUM_ITERATIONS = 150000;
	protected final double MAX_DIFF_PERCENT = 0.01;
	
	/* Number of dimensions to split the plate into */
	protected int dimension;
	
	/* Initial temperature of the left edge of the plate */
	protected int initLeftEdgeTemp;
	
	/* Initial temperature of the right edge of the plate */
	protected int initRightEdgeTemp;
	
	/* Initial temperature of the top edge of the plate */
	protected int initTopEdgeTemp;
	
	/* Initial temperature of the bottom edge of the plate */
	protected int initBottomEdgeTemp;
	
	/* Length of time required for plate temperature to stabilize */
	protected long lastRunTime;
	
	/* Number of iterations required for plate temperature to stabilize */
	protected int lastIterationCount;
	
	/**
	 * Default constructor
	 * Sets initial dimension and temperature values to 0
	 */
	public BaseHeatedPlate() {
		this.dimension = 0;
		this.initLeftEdgeTemp = 0;
		this.initRightEdgeTemp = 0;
		this.initTopEdgeTemp = 0;
		this.initBottomEdgeTemp = 0;
	}
	
	@Override
	public int getDimension() {
		return this.dimension;
	}
	
	@Override
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}
	
	@Override
	public int getLeftEdgeTemp() {
		return this.initLeftEdgeTemp;
	}
	
	@Override
	public void setLeftEdgeTemp(int temp) {
		this.initLeftEdgeTemp = temp;
	}
	
	@Override
	public int getRightEdgeTemp() {
		return this.initRightEdgeTemp;
	}
	
	@Override
	public void setRightEdgeTemp(int temp) {
		this.initRightEdgeTemp = temp;
	}
	
	@Override
	public int getTopEdgeTemp() {
		return this.initTopEdgeTemp;
	}
	
	@Override
	public void setTopEdgeTemp(int temp) {
		this.initTopEdgeTemp = temp;
	}
	
	@Override
	public int getBottomEdgeTemp() {
		return this.initBottomEdgeTemp;
	}
	
	@Override
	public void setBottomEdgeTemp(int temp) {
		this.initBottomEdgeTemp = temp;
	}

	@Override
	public long getCalculationTime() {
		return this.lastRunTime;
	}
	
	@Override
	public int getIterationsUsed() {
		return this.lastIterationCount;
	}	
}