package utils.validation;

import com.gerg2008.app.model.Component;
import customizedVaadinComponents.CustomNotification;
import utils.InputValidationException;

import java.util.List;

import static com.gerg2008.app.Constants.R;


public class TemperaturePressureValidation extends Validator {
    private final double temperature;
    private final double pressure;
    private final List<Component> components;

    public TemperaturePressureValidation(double temperature, double pressure, List<Component> components) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.components = components;
    }

    @Override
    public void validate() throws InputValidationException {
        double vaporGuess=(pressure * 100000.0) / (R * temperature);
        double liquidGuess = 0d;
        boolean overTemperature = true;
        boolean overPressure = true;

        if (temperature <= 0 || pressure <= 0) {
            String msg = "Invalid input. No negative values allowed for pressure or temperature";
            throw new InputValidationException(msg);

        }

        for (Component c : components) {
            if (temperature <= c.getT_ci())
                overTemperature = false;

            if (vaporGuess <= c.getRho_ci())
                overPressure = false;

            liquidGuess = 10 * c.getComposition() * c.getRho_ci() + liquidGuess;
        }

        if (overTemperature && overPressure) {
            String msg = "System might operate over critical point. Please review properties and design a system within eos range";
            throw new InputValidationException(msg);
        }

        validateNext();
    }


}
