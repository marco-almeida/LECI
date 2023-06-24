package lab12.Ex2.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lab12.Ex2.model.Thermometer;
import lab12.Ex2.model.ThermometerListener;

/**
 * A thermometer that displays as a digital thermometer.
 */
public class DigitalDisplay extends JPanel implements ThermometerListener {
	
	// The label displaying the temperature
	private JLabel tempLabel;
	
	// The Unicode symbol for degrees
	private static final char DEGREE_SYMBOL = '\u00B0';
	
	/** The thermometer whose temperature is being displayed */
	protected Thermometer thermometer;
	
	/**
	 * Creates a digital thermometer
	 * @param t the thermometer whose temperature is displayed
	 */
	public DigitalDisplay(Thermometer t) {
		thermometer = t;

		JPanel tempPanel = new JPanel();
		tempLabel = new JLabel(getDisplayString());
		tempLabel.setFont(tempLabel.getFont().deriveFont(20.0f));
		tempPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
		tempPanel.add(tempLabel);
		add(tempPanel);
	}

	/**
	 * Create the string to display in the thermometer
	 * @return the string to display in the thermometer
	 */
	private String getDisplayString() {
		return "" + thermometer.getTemperature() + DEGREE_SYMBOL + "F";
	}
	
	/**
	 * Change the temperature displayed
	 */
	@Override
	public void temperatureChanged() {
		tempLabel.setText(getDisplayString());
		repaint();
	}
}
