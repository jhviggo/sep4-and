package com.example.sep4androidapp;

public class TemperatureResponse {
    private String value;

    public Temperature getData()
    {
        return new Temperature(value);
    }
}
