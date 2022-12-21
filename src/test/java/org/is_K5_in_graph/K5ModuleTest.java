package org.is_K5_in_graph;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

class K5ModuleTest {

    @Test
    void TestK5Module() throws FileNotFoundException {
        K5Module k5Module = new K5Module();

        File graph = new File("Test_K5/TEST8_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST2_False.txt");
        Assertions.assertFalse(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST3_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST4_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST5_False.txt");
        Assertions.assertFalse(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST6_False.txt");
        Assertions.assertFalse(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST7_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST1_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST9_False.txt");
        Assertions.assertFalse(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_K5/TEST10_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}