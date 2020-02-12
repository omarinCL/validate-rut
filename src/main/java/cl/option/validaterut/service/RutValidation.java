package cl.option.validaterut.service;

import org.springframework.stereotype.Service;

import cl.option.validaterut.model.ValidationResult;

@Service
public class RutValidation {
    public ValidationResult validate(String rut) {
        ValidationResult res = new ValidationResult();
        res.setValue(rut);
        rut = rut.trim();
        rut = clearFormat(rut);
        int rutBody = getRutBody(rut);
        char rutDv = getRutDv(rut);
        res.setValid(validateRut(rutBody, rutDv));
        res.setValidated(true);
        res.setFormatedValue(rutFormat(rutBody, rutDv));
        return res;
    }

    private String rutFormat(int body, char dv) {
        String formatedRut = "" + body;

        switch (formatedRut.length()) {
            case 1:
                formatedRut = "0000000" + formatedRut;
                break;
            case 2:
                formatedRut = "000000" + formatedRut;
                break;
            case 3:
                formatedRut = "00000" + formatedRut;
                break;
            case 4:
                formatedRut = "0000" + formatedRut;
                break;
            case 5:
                formatedRut = "000" + formatedRut;
                break;
            case 6:
                formatedRut = "00" + formatedRut;
                break;
            case 7:
                formatedRut = "0" + formatedRut;
                break;
        }

        formatedRut = String.join(
            ".",
            formatedRut.substring(0, formatedRut.length() - 6),
            formatedRut.substring(formatedRut.length() - 6, formatedRut.length() - 3),
            formatedRut.substring(formatedRut.length() - 3, formatedRut.length())
            );

        formatedRut = String.join("-", formatedRut, "" + dv);

        return formatedRut.toUpperCase();
    }

    private boolean validateRut(int rut, char dv)
	{
		int m = 0, s = 1;
		for (; rut != 0; rut /= 10)
		{
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;
		}
		return dv == (char) (s != 0 ? s + 47 : 75); 
	}

    private int getRutBody(String rut) {
        String body = rut.substring(0, rut.length() - 1);
        if (body.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(body);
        }
    }

    private char getRutDv(String rut) {
        return rut.charAt(rut.length() - 1);
    }

    private String clearFormat(String rut) {
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        return rut;
    }
}