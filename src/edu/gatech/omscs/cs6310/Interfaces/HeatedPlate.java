package edu.gatech.omscs.cs6310.Interfaces;

public interface HeatedPlate {
	
	/**
	 * Gets the dimensions of a heated plate
	 * @return the dimensions of a heated plate
	 */
	public int getDimension();
	
	/**
	 * Sets the dimensions of a heated plate 
	 * @param the size of sides of the square heated plate
	 */
	public void setDimension(int dimension);
	
	/**
	 * Gets the temperature of the left edge of the heated plate
	 * @return the temperature of the left edge of the heated plate
	 */
	public int getLeftEdgeTemp();
	
	/**
	 * Sets the temperature of the left edge of the heated plate
	 * @param temp The temperature of the left edge
	 */
	public void setLeftEdgeTemp(int temp);
	
	/**
	 * Gets the temperature of the right edge of the heated plate
	 * @return the temperature of the right edge of the plate
	 */
	public int getRightEdgeTemp();
	
	/**
	 * Sets the temperature of the right edge of the heated plate
	 * @param temp the temperature of the right edge
	 */
	public void setRightEdgeTemp(int temp);
	
	/**
	 * Gets the temperature of the top edge of the heated plate
	 * @return the temperature of the top edge
	 */
	public int getTopEdgeTemp();
	
	/**
	 * Sets the temperature of the top edge of the heated plate
	 * @param temp the temperature of the top edge
	 */
	public void setTopEdgeTemp(int temp);
	
	/**
	 * Gets the temperature of the bottom edge of the heated plate
	 * @return the temperature of the bottom edge
	 */
	public int getBottomEdgeTemp();
	
	/**
	 * Sets the temperature of the bottom edge of the heated plate
	 * @param temp the temperature of the bottom edge
	 */
	public void setBottomEdgeTemp(int temp);
	
	/**
	 * Runs the algorithm to calculate the lattice points of the diffusion
	 * differential equation.
	 * <br />
	 * Please note that <code>Float</code> is used so the edges can be set to <code>null</code>
	 * 
	 * @exception PlateNotInitializedException Thrown if one or more edges are not initialized
	 * @return Two-Dimensional Array of boxed <code>Floats</floats> representing the lattice points of the diffusion equation 
	 */
	public Float[][] calculateLatticePoints();
	
	/**
	 * Gets the amount of time in nanoseconds that the diffusion calculation took
	 * @return Amount of time in nanoseconds
	 */
	public long getCalculationTime();
}
