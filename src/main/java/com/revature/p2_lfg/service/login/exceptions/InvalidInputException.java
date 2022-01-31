package com.revature.p2_lfg.service.login.exceptions;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid input")
public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String empty_new_username_input) {
        
    }
}
