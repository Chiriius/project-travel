package com.miguel.project_travel.util.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE= "User no exist in %s";

    public UserNotFoundException(String tableName){
            super(String.format(ERROR_MESSAGE,tableName));

    }

}
