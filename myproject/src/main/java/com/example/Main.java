package com.example;
import spoon.Launcher;
import spoon.reflect.CtModel;
import com.example.Reversing;

public class Main {

    public static void main(String[] args) {
        // Initialize Spoon launcher
        String path_to_code = "./src/main/java/com/example/Code.java";
        Reversing reverse = new Reversing();
        Launcher launcher = new Launcher();
        launcher.addInputResource(path_to_code);
        launcher.buildModel();
        CtModel model = launcher.getModel();
        reverse.transformModel(model);
        reverse.printAST(model,launcher);
    }


}