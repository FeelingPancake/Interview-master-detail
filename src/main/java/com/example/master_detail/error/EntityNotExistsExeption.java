package com.example.master_detail.error;

public class EntityNotExistsExeption extends RuntimeException {
    public EntityNotExistsExeption(String mes) {
        super(mes);
    }
}
