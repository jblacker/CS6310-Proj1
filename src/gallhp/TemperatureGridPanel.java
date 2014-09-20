package gallhp;

import interfaces.HeatedPlate;
import interfaces.PlateNotInitializedException;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;



public class TemperatureGridPanel extends JPanel {

	private static final long serialVersionUID = -3502023499522607593L;

	/**
	 * Create the panel.
	 */
	public TemperatureGridPanel(HeatedPlate plate) throws PlateNotInitializedException {
		this.setLayout(new GridLayout(plate.getDimension(), plate.getDimension()));
		List<List<Number>> results = plate.calculateLatticePoints();
		System.out.println("");
		for(int x = 1; x < results.size()-1; x++) {
			for(int y = 1; y < results.get(x).size()-1; y++) {
				this.add(new TemperaturePlate(results.get(x).get(y).floatValue()));
			}
		}
	}
}