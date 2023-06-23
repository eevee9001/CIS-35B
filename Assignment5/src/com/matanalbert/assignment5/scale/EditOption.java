package com.matanalbert.assignment5.scale;

import com.matanalbert.assignment5.adapter.*;
import com.matanalbert.assignment5.model.Automobile;
import com.matanalbert.assignment5.model.LHMAuto;
import com.matanalbert.assignment5.adapter.ProxyAutomobile;

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
        LHMAuto<String, Automobile> lhm = getProxyAutomobiles();
        this.automobile = lhm.get(modelName); // use function in LHM to find auto based on model name (methods in helper class)
        thread.start();

    }


    @Override
    public void run() {
        ops();
    }
    public void ops() {

        EditOptionHelper helper = new EditOptionHelper(automobile); //add synchronized/non sychronized methods here

        switch(operation) {
            case 0:
                helper.updateOption0(automobile, args);
                //Call method in helper class
                //Update the option name from blue to dark blue - non synchronized operation
                break;
            case 1:
                helper.updateOption1(automobile, args);
                //Call method in helper class
                //Update the option name from blue to Red	- non synchronized operation
                break;
            case 2:
                helper.updateOption2(automobile, args);
                //Call method in helper class
                //Update the option name from blue to dark blue - synchronized operation
                break;
            case 3:
                helper.updateOption3(automobile, args);
                //Call method in helper class
                //Update the option name from blue to red - synchronized operation
                break;
        }
    }
}
