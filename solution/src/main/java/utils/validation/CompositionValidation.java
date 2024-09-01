package utils.validation;

import utils.InputValidationException;

import java.util.Map;

public class CompositionValidation extends Validator {
    private final Map<String, Double> map;

    public CompositionValidation(Map<String, Double> map) {
        this.map = map;
    }

    @Override
    public void validate()  throws InputValidationException {
        double sum = map.values().stream().mapToDouble(Double::doubleValue).sum();
        if (sum != 1.0) {
            throw new InputValidationException("Wrong input. The sum of all compositions must be equal to 1");
        }

        validateNext();
    }
}
