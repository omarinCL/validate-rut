package cl.option.validaterut.controller;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.option.validaterut.model.ValidationResult;
import cl.option.validaterut.service.RutValidation;

@RestController
@RequestMapping("/api/v1/rut")
@Validated
public class RutValidateController {
    @Autowired
    private RutValidation service;

    @GetMapping("/validate")
    public ResponseEntity<ValidationResult> getValidation(
        @RequestParam(value = "rut") @NotEmpty String rut
    ) {
        return new ResponseEntity<ValidationResult>(service.validate(rut), HttpStatus.OK);
    }
}