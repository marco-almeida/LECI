package lab12.Ex2.view;

import lab12.Ex2.model.Thermometer;
import lab12.Ex2.model.ThermometerListener;

public class TerminalDisplay implements ThermometerListener{
	/** The thermometer whose temperature is being displayed */
	protected Thermometer thermometer;

    public TerminalDisplay(Thermometer thermometer){
        this.thermometer = thermometer;
        reprint(thermometer.getTemperature());
    }

    /**
	 * React to changes to the temperature.
	 */
	@Override
	public void temperatureChanged() {
		reprint(thermometer.getTemperature());
	}


    protected void reprint(int temperature){
        System.out.println("Temperature: " + temperature + "ºF");
    }


}
