package interfaces;

import tpdahp.TpdahpDiffusion;
import tpdohp.TpdohpDiffusion;
import tpfahp.TpfahpDiffusion;
import twfahp.TwfahpDiffusion;

public class Secret {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try
		{
			HeatedPlate plate = new TpdahpDiffusion();
			
			plate.setBottomEdgeTemp(25);
			plate.setTopEdgeTemp(53);
			plate.setRightEdgeTemp(33);
			plate.setLeftEdgeTemp(77);
		
			System.out.println("TPDAHP:");
			for (int i = 10; i <= 100; i = i + 10)
			{
				plate.setDimension(i);
				plate.calculateLatticePoints();
				System.out.println("Dimension: " + i);
				System.out.print("Iterations:\t");
				System.out.print("Time:\t");
				System.out.print("Memory:\n");
				System.out.print(plate.getIterationsUsed() + "\t\t");
				System.out.print(plate.getCalculationTime() + "\t\t");
				System.out.print(MemInfo.getCurrentMemoryUsage() + "\n");
			}
			
			plate = new TpdohpDiffusion();
			plate.setBottomEdgeTemp(25);
			plate.setTopEdgeTemp(53);
			plate.setRightEdgeTemp(33);
			plate.setLeftEdgeTemp(77);
			
			System.out.println("TPDOHP:");
			for (int i = 10; i <= 100; i = i + 10)
			{
				plate.setDimension(i);
				plate.calculateLatticePoints();
				System.out.println("Dimension: " + i);
				System.out.print("Iterations:\t");
				System.out.print("Time:\t");
				System.out.print("Memory:\n");
				System.out.print(plate.getIterationsUsed() + "\t\t");
				System.out.print(plate.getCalculationTime() + "\t\t");
				System.out.print(MemInfo.getCurrentMemoryUsage() + "\n");
			}
			
			plate = new TpfahpDiffusion();
			plate.setBottomEdgeTemp(25);
			plate.setTopEdgeTemp(53);
			plate.setRightEdgeTemp(33);
			plate.setLeftEdgeTemp(77);
			
			System.out.println("TPFAHP:");
			for (int i = 10; i <= 100; i = i + 10)
			{
				plate.setDimension(i);
				plate.calculateLatticePoints();
				System.out.println("Dimension: " + i);
				System.out.print("Iterations:\t");
				System.out.print("Time:\t");
				System.out.print("Memory:\n");
				System.out.print(plate.getIterationsUsed() + "\t\t");
				System.out.print(plate.getCalculationTime() + "\t\t");
				System.out.print(MemInfo.getCurrentMemoryUsage() + "\n");
			}
			
			plate = new TwfahpDiffusion();
			plate.setBottomEdgeTemp(25);
			plate.setTopEdgeTemp(53);
			plate.setRightEdgeTemp(33);
			plate.setLeftEdgeTemp(77);
			
			System.out.println("TWFAHP:");
			for (int i = 10; i <= 100; i = i + 10)
			{
				plate.setDimension(i);
				plate.calculateLatticePoints();
				System.out.println("Dimension: " + i);
				System.out.print("Iterations:\t\t");
				System.out.print("Time:\t\t");
				System.out.print("Memory:\n");
				System.out.print(plate.getIterationsUsed() + "\t\t");
				System.out.print(plate.getCalculationTime() + "\t\t");
				System.out.print(MemInfo.getCurrentMemoryUsage() + "\n");
			}
			
			System.out.println("DONE. Press a button or whatever.");
			//System.in.read();
		}
		catch (Exception e)
		{
			
		}
	}

}
