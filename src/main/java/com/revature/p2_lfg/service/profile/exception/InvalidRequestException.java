package com.revature.p2_lfg.service.profile.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Bad request")
public class InvalidRequestException extends RuntimeException{
}
