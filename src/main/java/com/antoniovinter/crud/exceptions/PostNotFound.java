package com.antoniovinter.crud.exceptions;

public class PostNotFound extends RuntimeException {
    public PostNotFound(String s) {
        super(s);
    }
}
