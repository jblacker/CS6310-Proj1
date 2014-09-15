package edu.gatech.omscs.cs6310.Twfahp;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;

public class TwafhpDiffusion implements HeatedPlate {

	private int dimension;
	private int leftEdgeTemp;
	private int rightEdgeTemp;
	private int topEdgeTemp;
	private int bottomEdgeTemp;
	
	public TwafhpDiffusion() {
		this.dimension = 0;
		this.leftEdgeTemp = 0;
		this.rightEdgeTemp = 0;
		this.topEdgeTemp = 0;
		this.bottomEdgeTemp = 0;
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
		return this.leftEdgeTemp;
	}

	@Override
	public void setLeftEdgeTemp(int temp) {
		this.leftEdgeTemp = temp;
	}

	@Override
	public int getRightEdgeTemp() {
		return this.rightEdgeTemp;
	}

	@Override
	public void setRightEdgeTemp(int temp) {
		this.rightEdgeTemp = temp;
	}

	@Override
	public int getTopEdgeTemp() {
		return this.topEdgeTemp;
	}

	@Override
	public void setTopEdgeTemp(int temp) {
		this.topEdgeTemp = temp;
	}

	@Override
	public int getBottomEdgeTemp() {
		return this.bottomEdgeTemp;
	}

	@Override
	public void setBottomEdgeTemp(int temp) {
		this.bottomEdgeTemp = temp;
	}

	@Override
	public Float[][] calculateLatticePoints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getCalculationTime() {
		// TODO Auto-generated method stub
		return 0;
	}
}
