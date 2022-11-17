package org.example_characteristic;

import com.mathsystem.api.graph.GraphFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ExampleModuleCharact exampleModuleCharact = new ExampleModuleCharact();
        File graph = new File("Ex_Test_Charact/Test1_6.txt");
        System.out.println(exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Charact/Test2_4.txt");
        System.out.println(exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Charact/Test3_8.txt");
        System.out.println(exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Charact/Test4_5.txt");
        System.out.println(exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Ex_Test_Charact/Test5_12.txt");
        System.out.println(exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}