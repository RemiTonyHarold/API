package com.remirobert;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by remirobert on 14/01/2017.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class SourceIdNotFoundException extends RuntimeException {
}
