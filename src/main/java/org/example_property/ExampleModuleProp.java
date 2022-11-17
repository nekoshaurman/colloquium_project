package org.example_property;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

public class ExampleModuleProp implements GraphProperty {
    @Override
    public boolean execute(Graph graph) {
        GraphType type = graph.getDirectType();

        GraphType direct = GraphType.DIRECTED;

        return type == direct;
    }
}