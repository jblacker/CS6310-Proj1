package edu.gatech.omscs.cs6310.Interfaces;

public abstract class BaseHeatedPlate implements HeatedPlate {
	
	protected final int MAXIMUM_ITERATIONS = 150000;	
	
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
	
	/**
	 * Initialize the dimension and initial temperatures of the heated plate
	 * @param args
	 */
	public void initialize(String[] args) {
		for(int i = 0; i < args.length; i += 2) {
			int temp;
			
			try {
				temp = Integer.parseInt(args[i+1]);
			}
			catch(NumberFormatException ex) {
				System.out.println("Expected an integer every other argument, invalid input.");
				return;
			}	
			
			switch(args[i]) {
				case "-d":
				case "-D":
					if(temp >= 1) {
						this.dimension = temp;
						break;
					}
					else {
						System.out.println("Dimension argument must be greater than or equal to 1");
						System.exit(1);
						return;
					}
				case "-l":
				case "-L":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						this.initLeftEdgeTemp = temp;
						break;
						
					}
				case "-r":
				case "-R":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						this.initRightEdgeTemp = temp;
						break;
						
					}
				case "-b":
				case "-B":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						this.initBottomEdgeTemp = temp;
						break;
						
					}
				case "-t":
				case "-T":
					if(temp > 100 || temp < 0) {
						System.out.println("Temperature agruments must be between 0 - 100");
						System.exit(1);
						return;
					}
					else {
						this.initTopEdgeTemp = temp;
						break;
						
					}	
			}
		}
	}
}
