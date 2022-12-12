package org.is_matching_random_max;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

class MatchingRandomMaxModuleTest {

    @Test
    void TestMatchingRandomMaxModule() throws FileNotFoundException {
        MatchingRandomMaxModule matchingRandomMaxModule = new MatchingRandomMaxModule();

        File graph = new File("Test_Random/Test1_3.txt");
        Assertions.assertEquals(3,
                matchingRandomMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}