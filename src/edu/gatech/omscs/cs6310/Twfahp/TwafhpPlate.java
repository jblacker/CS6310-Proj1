package edu.gatech.omscs.cs6310.Twfahp;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;

public class TwafhpPlate implements HeatedPlate {

	private int dimension;
	private int leftEdgeTemp;
	private int rightEdgeTemp;
	private int topEdgeTemp;
	private int bottomEdgeTemp;
	
	public TwafhpPlate(int dimension, int leftEdgeTemp, int rightEdgeTemp, int topEdgeTemp, int bottomEdgeTemp) {
		this.dimension = dimension;
		this.leftEdgeTemp = leftEdgeTemp;
		this.rightEdgeTemp = rightEdgeTemp;
		this.topEdgeTemp = topEdgeTemp;
		this.bottomEdgeTemp = bottomEdgeTemp;		
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#getDimension()
	 */
	@Override
	public int getDimension() {
		return this.dimension;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#setDimension(int)
	 */
	@Override
	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#getLeftEdgeTemp()
	 */
	@Override
	public int getLeftEdgeTemp() {
		return this.leftEdgeTemp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#setLeftEdgeTemp(int)
	 */
	@Override
	public void setLeftEdgeTemp(int temp) {
		this.leftEdgeTemp = temp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#getRightEdgeTemp()
	 */
	@Override
	public int getRightEdgeTemp() {
		return this.rightEdgeTemp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#setRightEdgeTemp(int)
	 */
	@Override
	public void setRightEdgeTemp(int temp) {
		this.rightEdgeTemp = temp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#getTopEdgeTemp()
	 */
	@Override
	public int getTopEdgeTemp() {
		return this.topEdgeTemp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#setTopEdgeTemp(int)
	 */
	@Override
	public void setTopEdgeTemp(int temp) {
		this.topEdgeTemp = temp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#getBottomEdgeTemp()
	 */
	@Override
	public int getBottomEdgeTemp() {
		return this.bottomEdgeTemp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#setBottomEdgeTemp(int)
	 */
	@Override
	public void setBottomEdgeTemp(int temp) {
		this.bottomEdgeTemp = temp;
	}

	/* (non-Javadoc)
	 * @see edu.gatech.omscs.cs6310.Interfaces.HeatedPlate#calculateLatticePoints()
	 */
	@Override
	public Float[][] calculateLatticePoints() {
		// TODO Auto-generated method stub
		return null;
	}
}
