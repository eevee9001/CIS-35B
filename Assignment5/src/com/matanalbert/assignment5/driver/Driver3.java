package com.matanalbert.assignment5.driver;

import com.matanalbert.assignment5.adapter.*;
import com.matanalbert.assignment5.adapter.BuildAuto;
import com.matanalbert.assignment5.adapter.ChoiceAuto;
import com.matanalbert.assignment5.adapter.CreateAuto;

import java.io.IOException;

public class Driver3 {
    public static void main(String[] args) throws IOException {

        CreateAuto create = new BuildAuto();
        ChoiceAuto choice = new BuildAuto();

        create.buildAuto("Car-1.txt");
        String model1 = "Focus Wagon ZTW";
        create.printAuto(model1);
        choice.setOptionChoice(model1, "Color", "Fort Knox Gold Clearcoat Metallic");
        choice.setOptionChoice(model1, "Transmission", "manual");
        choice.setOptionChoice(model1, "Brakes/Traction Control", "Standard");
        choice.setOptionChoice(model1, "Side Impact Air Bags", "none");
        choice.setOptionChoice(model1, "Power Moonroof", "none");
        System.out.println("Total price: " + choice.getTotalPrice(model1));
        // 18445 + 0 + -815 + 0 + 0 + 0 = 17630

        create.buildAuto("Car-2.txt");
        String model2 = "Mustang";
        create.printAuto(model2);
        choice.setOptionChoice(model2, "Color", "Infra-Red Clearcoat");
        choice.setOptionChoice(model2, "Transmission", "automatic");
        choice.setOptionChoice(model2, "Brakes/Traction Control", "ABS with Advance Trac");
        choice.setOptionChoice(model2, "Side Impact Air Bags", "present");
        choice.setOptionChoice(model2, "Power Moonroof", "present");
        System.out.println("Total price: " + choice.getTotalPrice(model2));
        // 18445 + 0 + 0 + 1625 + 350 + 595 = 21015
    }
}
