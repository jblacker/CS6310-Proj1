package edu.gatech.omscs.cs6310.Tpdohp;

public class LatticePoint {
	public double temperature;
	public double newTemperature;
	
	public LatticePoint()
	{
		this.temperature = 0;
		this.newTemperature = 0;
	}
	
	public LatticePoint(double temperature)
	{
		this.temperature = temperature;
	}
}
