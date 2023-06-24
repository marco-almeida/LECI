package lab12.Ex2.model;
import java.util.ArrayList;


/**
 * This class manages the data of a thermometer, specifically the current temperature.
 * This is a simple example of a model class.
 */
public class Thermometer {
	/** The current temperature */
	protected int current;
	
	/** The views listening to this thermometer */
	protected ArrayList<ThermometerListener> listeners = new ArrayList<ThermometerListener>();
	
	/**
	 * Create a new thermometer.
	 * @param cur temperature to display
	 */
	public Thermometer (int cur)  {
		current = cur;
	}
	
	/**
	 * Displays the temperature passed in.
	 * @param cur the temperature to display.
	 */
	public void setCurrent (int cur) {
		current = cur;
		notifyListeners();
	}

	/**
	 * Add a listener
	 * @param l the listener to add
	 */
	public void addThermometerListener(ThermometerListener l) {
		listeners.add(l);
	}
	
	/**
	 * Remove a listener
	 * @param l the listener to remove
	 */
	public void removeThermometerListener(ThermometerListener l) {
		listeners.remove(l);
	} 
	
	/**
	 * Notify the listeners of a change to the temperature
	 */
	protected void notifyListeners() {
		for (ThermometerListener l : listeners) {
			l.temperatureChanged();
		}
	}

	/**
	 * @return the temperature used by the thermometer
	 */
	public int getTemperature() {
		return current;
	}
}
