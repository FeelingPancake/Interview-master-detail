package com.example.master_detail.error;

import javax.swing.text.html.parser.Entity;

public class EntityNotExistsExeption extends RuntimeException {
    public EntityNotExistsExeption(String mes) {
        super(mes);
    }
}
