package com.example.ubergaming2;

import android.app.Application;

public class GlobalVariable extends Application {

    private int someVariable;

    public int getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(int someVariable) {
        this.someVariable = someVariable;
    }
}