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

        File graph = new File("Test_K5/Test1_True.txt");
        Assertions.assertTrue(k5Module.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}