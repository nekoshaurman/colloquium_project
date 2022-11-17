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

        File graph = new File("Test_len_min_direct/Test1_6.txt");
        Assertions.assertEquals(6,
                lenMinCycleDirectModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}