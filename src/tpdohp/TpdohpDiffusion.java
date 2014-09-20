package tpdohp;

import interfaces.BaseHeatedPlate;
import interfaces.PlateNotInitializedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;



public class TpdohpDiffusion extends BaseHeatedPlate {
	private ArrayList<ArrayList<LatticePoint>> plate;

	@SuppressWarnings("unchecked")
	@Override
	public List<List<Double>> calculateLatticePoints() throws PlateNotInitializedException {
		this.calculateDiffusion();
		
		List<List<Double>> points = new ArrayList<List<Double>>();
		
		for(int i = 0; i <= this.dimension + 1; i++) {
			List<Double> innerPoints = new ArrayList<Double>();
			for (int j = 0; j <= this.dimension + 1; j++) {
				innerPoints.add(this.plate.get(i).get(j).temperature);
			}
			points.add(innerPoints);
		}
		
		return points;
	}

	public void calculateDiffusion() throws PlateNotInitializedException {
		if(this.dimension == 0)
		{
			throw new PlateNotInitializedException("Dimensions must be set");
		}
		
		long startTime = System.nanoTime();
		
		createAndInitializePlate();
		
		int iterations = 0;
		double difference = 0;
		LatticePoint point;
		
		do {
			for (int x = 1; x <= this.dimension; x++) {
				for (int y = 1; y <= this.dimension; y++) {
					point = this.plate.get(x).get(y);
					point.newTemperature = (this.plate.get(x + 1).get(y).temperature +
							this.plate.get(x - 1).get(y).temperature +
							this.plate.get(x).get(y + 1).temperature +
							this.plate.get(x).get(y - 1).temperature) / 4d;
				}
			}
			difference = getTempDifference();
			setPlateTemperature();
			iterations++;
		}
		while(difference >= MAX_DIFF_PERCENT && iterations < MAXIMUM_ITERATIONS);
		
		this.lastIterationCount = iterations;
		this.lastRunTime = System.nanoTime() - startTime;
	}	
	
	private void createAndInitializePlate()
	{
		this.plate = new ArrayList<ArrayList<LatticePoint>>();

		ArrayList<LatticePoint> innerList;
		for(int i = 0; i <= this.dimension + 1; i++) 
		{
			innerList = new ArrayList<LatticePoint>();
			if (i >= 1 && i <= this.dimension)
			{
				for (int j = 0; j <= this.dimension + 1; j++) {
					if (j == 0)
					{
						//Set left points
						innerList.add(j, new LatticePoint(this.initLeftEdgeTemp));
					}
					else if (j == this.dimension + 1)
					{
						//Set right points
						innerList.add(j, new LatticePoint(this.initRightEdgeTemp));
					}
					else
					{
						//Set inner points
						innerList.add(j, new LatticePoint());
					}
				}
			}
			else if (i == 0)
			{
				//Set top points
				for (int j = 0; j <= this.dimension + 1; j++)
				{
					innerList.add(j, new LatticePoint(this.initTopEdgeTemp));
				}
			}
			else if (i == this.dimension + 1)
			{
				//Set bottom points
				for (int j = 0; j <= this.dimension + 1; j++)
				{
					innerList.add(new LatticePoint(this.initBottomEdgeTemp));
				}
			}
			this.plate.add(i, innerList);
		}
	}
	
	private void setPlateTemperature()
	{
		for(int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				this.plate.get(i).get(j).temperature =
						this.plate.get(i).get(j).newTemperature;
			}
		}
	}
	
	private double getTempDifference() {
		double totalNewTemp = 0d, totalOldTemp = 0d;
		// Calculate total temperature of plates
		LatticePoint point;
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				point = this.plate.get(i).get(j);
				totalNewTemp += point.newTemperature;
				totalOldTemp += point.temperature;
	        }
		}
		
		if (totalOldTemp == 0)
		{
			return 100;
		}
		else
		{
			return ((totalNewTemp - totalOldTemp) / totalOldTemp) * 100;
		}
	}
}
