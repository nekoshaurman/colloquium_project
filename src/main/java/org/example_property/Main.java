package org.example_property;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ExampleModuleProp exampleModuleProp = new ExampleModuleProp();
        File graph = new File("Ex_Test_Prop/Test1_False.txt");
        System.out.println(exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Prop/Test2_True.txt");
        System.out.println(exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Prop/Test3_False.txt");
        System.out.println(exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Prop/Test4_False.txt");
        System.out.println(exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Prop/Test5_True.txt");
        System.out.println(exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}