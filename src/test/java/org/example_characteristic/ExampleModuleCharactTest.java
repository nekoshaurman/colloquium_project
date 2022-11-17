package org.example_characteristic;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;

class ExampleModuleCharactTest {

    @Test
    void TestExampleModuleCharact() throws FileNotFoundException {
        ExampleModuleCharact exampleModuleCharact = new ExampleModuleCharact();

        File graph = new File("Test_Charact/Test1_6.txt");
        Assertions.assertEquals(6,
                exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Charact/Test2_4.txt");
        Assertions.assertEquals(4, exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Charact/Test3_8.txt");
        Assertions.assertEquals(8, exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Charact/Test4_5.txt");
        Assertions.assertEquals(5, exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_Charact/Test5_12.txt");
        Assertions.assertEquals(12, exampleModuleCharact.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}