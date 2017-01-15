package com.remirobert;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by remirobert on 09/01/2017.
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND)
    public class TokenNotFoundException extends RuntimeException {}
