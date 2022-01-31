package com.revature.p2_lfg.service.session.exception;

import lombok.experimental.StandardException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@StandardException
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid HostUser")
public class InvalidHostUserException extends RuntimeException {
}
