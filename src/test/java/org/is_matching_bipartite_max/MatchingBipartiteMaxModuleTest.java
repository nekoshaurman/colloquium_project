package org.is_matching_bipartite_max;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

class MatchingBipartiteMaxModuleTest {

    @Test
    void TestMatchingBipartiteMaxModule() throws FileNotFoundException {
        MatchingBipartiteMaxModule matchingBipartiteMaxModule = new MatchingBipartiteMaxModule();

        File graph = new File("Test_bipartite/Test1_True.txt");
        Assertions.assertEquals(true,
                matchingBipartiteMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_bipartite/Test2_True.txt");
        Assertions.assertEquals(true,
                matchingBipartiteMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_bipartite/Test3_False.txt");
        Assertions.assertEquals(false,
                matchingBipartiteMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_bipartite/Test4_False.txt");
        Assertions.assertEquals(false,
                matchingBipartiteMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_bipartite/Test5_True.txt");
        Assertions.assertEquals(true,
                matchingBipartiteMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}