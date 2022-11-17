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

        File graph = new File("Test_bipartite/Test1_False.txt");
        Assertions.assertEquals(false,
                matchingBipartiteMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}