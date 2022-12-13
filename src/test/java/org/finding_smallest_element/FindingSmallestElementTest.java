package org.finding_smallest_element;

import com.mathsystem.api.graph.GraphFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class FindingSmallestElementTest {

    @Test
    void TestFindingSmallestElementModule() throws FileNotFoundException {
        FindingSmallestElement findingSmallestElement = new FindingSmallestElement();

        File graph = new File("Test_smallest_element/Test1_True.txt");
        Assertions.assertTrue(findingSmallestElement.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_smallest_element/Test2_False.txt");
        Assertions.assertFalse(findingSmallestElement.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_smallest_element/Test3_True.txt");
        Assertions.assertTrue(findingSmallestElement.execute(GraphFactory.loadGraphFromFile(graph)));
        graph = new File("Test_smallest_element/Test4_False.txt");
        Assertions.assertFalse(findingSmallestElement.execute(GraphFactory.loadGraphFromFile(graph)));
    }
}
