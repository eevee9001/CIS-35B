package com.matanalbert.assignment4.scale;

import com.matanalbert.assignment4.adapter.*;
import com.matanalbert.assignment4.model.Automobile;

public class EditOption extends ProxyAutomobile implements Runnable{

    private Thread thread;
    private Automobile automobile;
    private String modelName;
    private String[] args;
    private int operation;

    public EditOption(String modelName, int operation, String[] args) {
        this.modelName = modelName;
        this.operation = operation;
        this.args = args;
        this.thread = new Thread(this);
        this.automobile = null; // use function in LHM to find auto based on model name (methods in helper class)
    }


    @Override
    public void run() {

    }
    public void ops() {

        EditOptionHelper h = new EditOptionHelper(); //add synchronized/non sychronized methods here

        switch(operation) {
            case 0:
                //Call method in helper class
                //Update the option name from blue to dark blue - non synchronized operation
                break;
            case 1:
                //Call method in helper class
                //Update the option name from blue to Red	- non synchronized operation
                break;
            case 2:
                //Call method in helper class
                //Update the option name from blue to dark blue - synchronized operation
                break;
            case 3:
                //Call method in helper class
                //Update the option name from blue to red - synchronized operation
                break;
        }
    }
}
