package org.cyclomatic_number;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;

class CyclomaticNumberModuleTest {

    @Test
    void TestCyclomaticNumberModule() throws FileNotFoundException {
        CyclomaticNumberModule cyclomaticNumberModule = new CyclomaticNumberModule();

        File graph = new File("Test_cyclomatic_number/Test1_6.txt");
        Assertions.assertEquals(6,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}