package org.example_characteristic;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

public class ExampleModuleCharact implements GraphCharacteristic {
    @Override
    public Integer execute(Graph graph) {
        Integer count = graph.getVertexCount();
        return count;
    }
}
