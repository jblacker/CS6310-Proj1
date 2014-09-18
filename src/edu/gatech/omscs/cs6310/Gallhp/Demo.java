package edu.gatech.omscs.cs6310.Gallhp;

import java.awt.EventQueue;

import javax.management.InvalidApplicationException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Dialog.ModalityType;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;

import edu.gatech.omscs.cs6310.Interfaces.HeatedPlate;
import edu.gatech.omscs.cs6310.Interfaces.MemInfo;
import edu.gatech.omscs.cs6310.Interfaces.PlateNotInitializedException;
import edu.gatech.omscs.cs6310.Tpdahp.TpdahpDiffusion;
import edu.gatech.omscs.cs6310.Tpdohp.TpdohpDiffusion;
import edu.gatech.omscs.cs6310.Tpfahp.TpfahpDiffusion;
import edu.gatech.omscs.cs6310.Twfahp.TwfahpDiffusion;

public class Demo implements ActionListener {

	private JFrame frame;
	private JTextField tfDimensions;
	private JComboBox<ComputationType> cbCompType;
	private JSpinner spinRight;
	private JSpinner spinLeft;
	private JSpinner spinTop;
	private JSpinner spinBottom;
	private TemperatureGridPanel display;
	private JLabel lblCompletionTime;
	private JLabel lblIterationCount;
	private JLabel lblMemoryUsage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Demo window = new Demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Demo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		BorderLayout borderLayout = (BorderLayout) frame.getContentPane().getLayout();
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.setActionCommand("Clear");
		mntmClear.addActionListener(this);
		mnFile.add(mntmClear);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		
		mntmQuit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				AboutDialog about = new AboutDialog();
				about.setModalityType(ModalityType.APPLICATION_MODAL);
				about.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		
		JPanel settingPanel = new JPanel();
		frame.getContentPane().add(settingPanel, BorderLayout.NORTH);
		GridBagLayout gbl_settingPanel = new GridBagLayout();
		gbl_settingPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_settingPanel.rowHeights = new int[] {0, 0};
		gbl_settingPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_settingPanel.rowWeights = new double[]{0.0, 0.0};
		settingPanel.setLayout(gbl_settingPanel);
		
		JLabel lblComputationType = new JLabel("Computation Type:");
		GridBagConstraints gbc_lblComputationType = new GridBagConstraints();
		gbc_lblComputationType.gridwidth = 2;
		gbc_lblComputationType.insets = new Insets(5, 5, 5, 5);
		gbc_lblComputationType.gridx = 0;
		gbc_lblComputationType.gridy = 0;
		settingPanel.add(lblComputationType, gbc_lblComputationType);
		
		cbCompType = new JComboBox<ComputationType>(ComputationType.values());
		GridBagConstraints gbc_cbCompType = new GridBagConstraints();
		gbc_cbCompType.gridwidth = 2;
		gbc_cbCompType.insets = new Insets(5, 5, 5, 5);
		gbc_cbCompType.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCompType.gridx = 2;
		gbc_cbCompType.gridy = 0;
		cbCompType.setSelectedIndex(-1);
		settingPanel.add(cbCompType, gbc_cbCompType);
		
		JLabel lblDimensions = new JLabel("Dimensions");
		GridBagConstraints gbc_lblDimensions = new GridBagConstraints();
		gbc_lblDimensions.gridwidth = 2;
		gbc_lblDimensions.insets = new Insets(5, 5, 5, 5);
		gbc_lblDimensions.gridx = 4;
		gbc_lblDimensions.gridy = 0;
		settingPanel.add(lblDimensions, gbc_lblDimensions);
		
		tfDimensions = new JTextField();
		tfDimensions.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_DELETE) {
					
					if(!(c == '0' || c == '1'|| c == '2'|| c == '3'|| c == '4'|| c == '5'
							|| c == '6'|| c == '7'|| c == '8'|| c == '9') ) {
						
						e.consume();
					}
				}
			}

			@Override
			public void keyPressed(KeyEvent e) { }

			@Override
			public void keyReleased(KeyEvent e) { }
		});
		GridBagConstraints gbc_tfDimensions = new GridBagConstraints();
		gbc_tfDimensions.gridwidth = 2;
		gbc_tfDimensions.insets = new Insets(5, 5, 5, 5);
		gbc_tfDimensions.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDimensions.gridx = 6;
		gbc_tfDimensions.gridy = 0;
		settingPanel.add(tfDimensions, gbc_tfDimensions);
		tfDimensions.setColumns(10);
		
		JLabel lblLeft = new JLabel("Left");
		lblLeft.setLabelFor(lblLeft);
		GridBagConstraints gbc_lblLeft = new GridBagConstraints();
		gbc_lblLeft.insets = new Insets(0, 5, 5, 5);
		gbc_lblLeft.gridx = 0;
		gbc_lblLeft.gridy = 1;
		settingPanel.add(lblLeft, gbc_lblLeft);
		
		spinLeft = new JSpinner();
		spinLeft.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		GridBagConstraints gbc_spinLeft = new GridBagConstraints();
		gbc_spinLeft.insets = new Insets(0, 5, 5, 5);
		gbc_spinLeft.gridx = 1;
		gbc_spinLeft.gridy = 1;
		settingPanel.add(spinLeft, gbc_spinLeft);
		
		JLabel lblRight = new JLabel("Right");
		lblRight.setLabelFor(lblRight);
		GridBagConstraints gbc_lblRight = new GridBagConstraints();
		gbc_lblRight.insets = new Insets(0, 5, 5, 5);
		gbc_lblRight.gridx = 2;
		gbc_lblRight.gridy = 1;
		settingPanel.add(lblRight, gbc_lblRight);
		
		spinRight = new JSpinner();
		spinRight.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		GridBagConstraints gbc_spinRight = new GridBagConstraints();
		gbc_spinRight.insets = new Insets(0, 5, 5, 5);
		gbc_spinRight.gridx = 3;
		gbc_spinRight.gridy = 1;
		settingPanel.add(spinRight, gbc_spinRight);
		
		JLabel lblTop = new JLabel("Top");
		GridBagConstraints gbc_lblTop = new GridBagConstraints();
		gbc_lblTop.insets = new Insets(0, 5, 5, 5);
		gbc_lblTop.gridx = 4;
		gbc_lblTop.gridy = 1;
		settingPanel.add(lblTop, gbc_lblTop);
		
		spinTop = new JSpinner();
		lblTop.setLabelFor(spinTop);
		spinTop.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		GridBagConstraints gbc_spinTop = new GridBagConstraints();
		gbc_spinTop.insets = new Insets(0, 5, 5, 5);
		gbc_spinTop.gridx = 5;
		gbc_spinTop.gridy = 1;
		settingPanel.add(spinTop, gbc_spinTop);
		
		JLabel lblBottom = new JLabel("Bottom");
		GridBagConstraints gbc_lblBottom = new GridBagConstraints();
		gbc_lblBottom.insets = new Insets(0, 5, 5, 5);
		gbc_lblBottom.gridx = 6;
		gbc_lblBottom.gridy = 1;
		settingPanel.add(lblBottom, gbc_lblBottom);
		
		spinBottom = new JSpinner();
		spinBottom.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		lblBottom.setLabelFor(spinBottom);
		GridBagConstraints gbc_spinBottom = new GridBagConstraints();
		gbc_spinBottom.insets = new Insets(0, 5, 5, 0);
		gbc_spinBottom.gridx = 7;
		gbc_spinBottom.gridy = 1;
		settingPanel.add(spinBottom, gbc_spinBottom);
		
		JButton btnRun = new JButton("RUN");
		btnRun.setActionCommand("Run");
		btnRun.addActionListener(this); 
		GridBagConstraints gbc_btnRun = new GridBagConstraints();
		gbc_btnRun.gridheight = 2;
		gbc_btnRun.insets = new Insets(5, 15, 5, 5);
		gbc_btnRun.gridx = 8;
		gbc_btnRun.gridy = 0;
		settingPanel.add(btnRun, gbc_btnRun);
		
		JPanel metricsPanel = new JPanel();
		metricsPanel.setLayout(new GridLayout(1,3));
		frame.getContentPane().add(metricsPanel, BorderLayout.SOUTH);
		
		lblCompletionTime = new JLabel("Calculation Time: - ns");
		metricsPanel.add(lblCompletionTime);
		
		lblIterationCount = new JLabel("Iterations Used: -");
		lblIterationCount.setHorizontalAlignment(SwingConstants.CENTER);
		lblIterationCount.setHorizontalTextPosition(SwingConstants.CENTER);
		metricsPanel.add(lblIterationCount);
		
		lblMemoryUsage = new JLabel("Memory Usage: - MB");
		metricsPanel.add(lblMemoryUsage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Clear") {
			resetOptions();
		}
		else if(e.getActionCommand() == "Run") {
			//add validation
			try {
				runSimulation();
			} catch (InvalidApplicationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void resetOptions() {
		this.cbCompType.setSelectedIndex(-1);
		this.tfDimensions.setText("");
		this.spinRight.setValue(new Integer(0));
		this.spinLeft.setValue(new Integer(0));
		this.spinTop.setValue(new Integer(0));
		this.spinBottom.setValue(new Integer(0));
		this.lblCompletionTime.setText("Calculation Time: - ns");
		this.lblIterationCount.setText("Iterations Used: -");
		this.lblMemoryUsage.setText("Memory Usage: - MB");
		
		if(display != null) {
			this.frame.getContentPane().remove(display);
			frame.revalidate();
			frame.repaint();
		}
	}

	private void runSimulation() throws InvalidApplicationException {
		if (display != null)
			frame.getContentPane().remove(display);
		
		HeatedPlate plate;
		switch((ComputationType)this.cbCompType.getSelectedItem()) {		
			case TPDAHP:
				plate = new TpdahpDiffusion();
				break;
			case TPDOHP:
				plate = new TpdohpDiffusion();
				break;
			case TPFAHP:
				plate = new TpfahpDiffusion();
				break;
			case TWFAHP:
				plate = new TwfahpDiffusion();
				break;
			default:
				throw new InvalidApplicationException("Unknown Computation Type");			
		}
		
		try {
			int dim = Integer.parseInt(this.tfDimensions.getText().trim());
			plate.setDimension(dim);
		}
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Invalid dimensions entered",
					"Gallhp",JOptionPane.WARNING_MESSAGE);
		}
		
		plate.setBottomEdgeTemp((Integer)this.spinBottom.getValue());
		plate.setTopEdgeTemp((Integer)this.spinTop.getValue());
		plate.setRightEdgeTemp((Integer)this.spinRight.getValue());
		plate.setLeftEdgeTemp((Integer)this.spinLeft.getValue());
		try {
			display = new TemperatureGridPanel(plate);
			this.lblIterationCount.setText(String.format("Iterations Used: %d", plate.getIterationsUsed()));
			this.lblCompletionTime.setText(String.format("Calculation Time: %d ns", plate.getCalculationTime()));
			this.lblMemoryUsage.setText(String.format("Memory Usage: %d MB", MemInfo.getCurrentMemoryUsage()));
			frame.getContentPane().add(display, BorderLayout.CENTER);
			frame.revalidate();
			frame.repaint();
		}
		catch (PlateNotInitializedException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Gallhp", JOptionPane.ERROR_MESSAGE);
		}
	}
}