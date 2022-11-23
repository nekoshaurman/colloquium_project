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
        graph = new File("Test_cyclomatic_number/Test2_2.txt");
        Assertions.assertEquals(2,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test3_4.txt");
        Assertions.assertEquals(4,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test4_0.txt");
        Assertions.assertEquals(0,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test5_4.txt");
        Assertions.assertEquals(5,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_cyclomatic_number/Test6_8.txt");
        Assertions.assertEquals(8,
                cyclomaticNumberModule.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}