package edu.gatech.omscs.cs6310.Gallhp;


import java.awt.GridLayout;

import javax.swing.JPanel;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;

public class TemperatureGridPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3502023499522607593L;

	/**
	 * Create the panel.
	 */
	public TemperatureGridPanel(HeatedPlate plate) {
		this.setLayout(new GridLayout(plate.getDimension(), plate.getDimension()));
		
		//TODO:  Once decided on interface signatures generate plates & display in grid
	}

}
