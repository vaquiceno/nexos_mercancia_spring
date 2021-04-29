package com.nexos.mercancia.exception;

public class ProductoNotFoundException extends RuntimeException{
    public ProductoNotFoundException(String s) {
        super(s);
    }
}
