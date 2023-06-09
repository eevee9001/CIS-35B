package com.matanalbert.assignment4.scale;

import com.matanalbert.assignment4.model.Automobile;

public class EditOptionHelper {
    public EditOptionHelper(Automobile automobile) {
    }

    public void updateOption0(Automobile automobile, String[] args) {
        automobile.updateOptName(args[0], args[1], args[2]);
    }
    public void updateOption1(Automobile automobile, String[] args) {
        automobile.updateOptName(args[0], args[1], args[2]);
    }
    public void updateOption2(Automobile automobile, String[] args) {
        synchronized (automobile) {
            automobile.updateOptName(args[0], args[1], args[2]);
        }
    }
    public void updateOption3(Automobile automobile, String[] args) {
        synchronized (automobile) {
            automobile.updateOptName(args[0], args[1], args[2]);
        }
    }
}
