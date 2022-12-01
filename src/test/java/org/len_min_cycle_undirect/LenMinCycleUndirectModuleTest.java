package org.len_min_cycle_undirect;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;

class LenMinCycleUndirectModuleTest {

    @Test
    void TestLenMinCycleUndirectedModule() throws FileNotFoundException
    {
        LenMinCycleUndirectModule lenMinCycleUndirectModule = new LenMinCycleUndirectModule();

        File graph = new File("Test_len_min_undirect/Test1_3.txt");
        Assertions.assertEquals(3,
                lenMinCycleUndirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_undirect/Test2_4.txt");
        Assertions.assertEquals(4,
                lenMinCycleUndirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_undirect/Test3_6.txt");
        Assertions.assertEquals(6,
                lenMinCycleUndirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_undirect/Test4_3.txt");
        Assertions.assertEquals(3,
                lenMinCycleUndirectModule.execute(GraphFactory.loadGraphFromFile(graph)));

        graph = new File("Test_len_min_undirect/Test5_0.txt");
        Assertions.assertEquals(0,
                lenMinCycleUndirectModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}