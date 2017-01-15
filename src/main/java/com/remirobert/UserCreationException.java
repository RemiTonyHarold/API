package com.remirobert;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by remirobert on 15/01/2017.
 */
@ResponseStatus(value= HttpStatus.UNAUTHORIZED)
public class UserCreationException extends RuntimeException {}
