package lab12.Ex2.model;

/**
 * The interface used to notify views of a change to a temperature
 * 
 */
public interface ThermometerListener {
	/**
	 * Notification that the temperature changed
	 */
	public void temperatureChanged();
}
