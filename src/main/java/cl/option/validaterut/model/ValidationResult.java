package cl.option.validaterut.model;

import lombok.Data;

@Data
public class ValidationResult {
    private String value;
    private String formatedValue;
    private boolean isValid;
    private boolean isValidated;
}