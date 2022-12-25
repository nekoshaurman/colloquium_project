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
        graph = new File("Test_Random/Test2_4.txt");
        Assertions.assertEquals(4,
                matchingRandomMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_Random/Test3_2.txt");
        Assertions.assertEquals(2,
                matchingRandomMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_Random/Test4_3.txt");
        Assertions.assertEquals(3,
                matchingRandomMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_Random/Test5_6.txt");
        Assertions.assertEquals(6,
                matchingRandomMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_Random/Test6_3.txt");
        Assertions.assertEquals(3,
                matchingRandomMaxModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}