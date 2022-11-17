package org.example_property;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

class ExampleModulePropTest {

    @Test
    void TestExampleModuleProp() throws FileNotFoundException {
        ExampleModuleProp exampleModuleProp = new ExampleModuleProp();

        File graph = new File("Test_Prop/Test1_False.txt");
        Assertions.assertEquals(false,
                exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Prop/Test2_True.txt");
        Assertions.assertEquals(true,
                exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Prop/Test3_False.txt");
        Assertions.assertEquals(false,
                exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Prop/Test4_False.txt");
        Assertions.assertEquals(false,
                exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Prop/Test5_True.txt");
        Assertions.assertEquals(true,
                exampleModuleProp.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}