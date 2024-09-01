package utils.validation;

import utils.InputValidationException;


/**
 * responsable for modeling the shape of validators
 */

public abstract class Validator {

    private Validator next;

    public Validator linkWith(Validator next) {
        this.next = next;
        return next;
    }

    public abstract void validate() throws InputValidationException;

    protected void validateNext() {
        if (next != null) {
            next.validate();
        }
    }
}
