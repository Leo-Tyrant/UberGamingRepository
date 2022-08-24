package com.example.pruebavariableglobales;


public class VariablesGlobales {
    String s;
    private static final VariablesGlobales ourInstance = new VariablesGlobales();
    public static VariablesGlobales getInstance() {
        return ourInstance;
    }
    private VariablesGlobales() {
    }
    public void setData(String s) {
        this.s = s;
    }
    public String getData() {
        return s;
    }
}