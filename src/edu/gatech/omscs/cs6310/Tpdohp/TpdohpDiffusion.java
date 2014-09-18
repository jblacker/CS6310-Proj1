package edu.gatech.omscs.cs6310.Tpdohp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.gatech.omscs.cs6310.Interfaces.BaseHeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;

public class TpdohpDiffusion extends BaseHeatedPlate {
	private HashMap<LatticeCoordinate, LatticePoint> plate;

	@SuppressWarnings("unchecked")
	@Override
	public List<List<Double>> calculateLatticePoints() throws PlateNotInitializedException {
		this.calculateDiffusion();
		
		List<List<Double>> points = new ArrayList<List<Double>>();
		LatticeCoordinate coordinate;
		for(int i = 1; i <= this.dimension; i++) {
			List<Double> innerPoints = new ArrayList<Double>();
			for (int j = 1; j <= this.dimension; j++) {
				coordinate = new LatticeCoordinate(i, j);
				if (this.plate.get(coordinate) != null)
				{
					innerPoints.add(this.plate.get(coordinate).temperature);
				}
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
		LatticePoint point1;
		LatticePoint point2;
		LatticePoint point3;
		LatticePoint point4;
		LatticeCoordinate coordinate;
		
		do {
			for (int x = 1; x <= this.dimension; x++) {
				for (int y = 1; y <= this.dimension; y++) {
					coordinate = new LatticeCoordinate(x, y);
					point = this.plate.get(coordinate);
					point1 = this.plate.get(new LatticeCoordinate
							(x + 1, y));
					point2 = this.plate.get(new LatticeCoordinate
							(x - 1, y));
					point3 = this.plate.get(new LatticeCoordinate
							(x, y + 1));
					point4 = this.plate.get(new LatticeCoordinate
							(x, y - 1));
					
					if (point != null && point1 != null && point2 != null && point3 != null && point4 != null)
					{
						point.newTemperature = (point1.temperature + point2.temperature +
							point3.temperature + point4.temperature) / 4d;
					}
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
		this.plate = new HashMap<LatticeCoordinate, LatticePoint>();
		for(int i = 1; i <= this.dimension; i++) {
			this.plate.put(new LatticeCoordinate(0, i),
					new LatticePoint(this.initTopEdgeTemp));
			this.plate.put(new LatticeCoordinate(this.dimension + 1, i),
					new LatticePoint(this.initBottomEdgeTemp));
		}
		
		for(int i = 1; i <= this.dimension; i++) {
			this.plate.put(new LatticeCoordinate(i, 0),
					new LatticePoint(this.initLeftEdgeTemp));
			this.plate.put(new LatticeCoordinate(i, this.dimension + 1),
					new LatticePoint(this.initLeftEdgeTemp));
		}
		
		for(int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				this.plate.put(new LatticeCoordinate(i, j),
						new LatticePoint());
			}
		}
	}
	
	private void setPlateTemperature()
	{
		LatticeCoordinate coordinate;
		for(int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				coordinate = new LatticeCoordinate(i, j);
				if (this.plate.get(coordinate) != null)
				{
					this.plate.get(coordinate).temperature = 
						this.plate.get(coordinate).newTemperature;
				}
			}
		}
	}
	
	private double getTempDifference() {
		double totalNewTemp = 0d, totalOldTemp = 0d;
		// Calculate total temperature of plates
		LatticePoint point;
		for (int i = 1; i <= this.dimension; i++) {
			for (int j = 1; j <= this.dimension; j++) {
				point = this.plate.get(new LatticeCoordinate(i, j));
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
