package org.len_min_cycle_direct;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;

class LenMinCycleDirectModuleTest {

    @Test
    void TestLenMinCycleDirectModule() throws FileNotFoundException {
        LenMinCycleDirectModule lenMinCycleDirectModule = new LenMinCycleDirectModule();

        File graph = new File("Test_len_min_direct/test1_answer_3.txt");
        Assertions.assertEquals(3,
                lenMinCycleDirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_direct/test2_answer_4.txt");
        Assertions.assertEquals(4,
                lenMinCycleDirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_direct/test3_answer_7.txt");
        Assertions.assertEquals(7,
                lenMinCycleDirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_direct/test4_answer_0.txt");
        Assertions.assertEquals(0,
                lenMinCycleDirectModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}