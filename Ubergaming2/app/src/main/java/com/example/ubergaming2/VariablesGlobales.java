package com.example.ubergaming2;


public class VariablesGlobales {

    String Direccion ;
    String CurrentUser;
    private static final VariablesGlobales ourInstance = new VariablesGlobales();
    public static VariablesGlobales getInstance() {
        return ourInstance;
    }
    private VariablesGlobales() {
    }
    public void setDireccion(String d) {
        this.Direccion = d;
    }
    public String getDireccion() {
        return Direccion;
    }
    public void setCurrentUser(String cu) {
        this.CurrentUser = cu;
    }
    public String getCurrentUser() {
        return CurrentUser;
    }
}