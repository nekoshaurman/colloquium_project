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

        //UNDIRECTED TESTS

        File graph = new File("Test_cyclomatic_number/Test1_3.txt");
        Assertions.assertEquals(3,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test2_7.txt");
        Assertions.assertEquals(7,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test3_0.txt");
        Assertions.assertEquals(0,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test3_0.txt");
        Assertions.assertEquals(0,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test4_0.txt");
        Assertions.assertEquals(0,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test5_13.txt");
        Assertions.assertEquals(13,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}