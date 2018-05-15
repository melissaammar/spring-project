package com.example.springgradle.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

abstract class BaseController {

    @Autowired
    protected DataBaseService dataBaseService = DataBaseService.getInstance();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    protected Timestamp getDateTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }
}
