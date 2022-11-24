package org.len_min_cycle_undirect;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;

class LenMinCycleUndirectModuleTest {

    @Test
    void TestLenMinCycleUndirectModule() throws FileNotFoundException {
        LenMinCycleUndirectModule lenMinCycleUndirectModule = new LenMinCycleUndirectModule();

        File graph = new File("Test_len_min_undirect/Test1_3.txt");
        Assertions.assertEquals(3,
                lenMinCycleUndirectModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}