package org.is_matching_bipartite_max;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.Map;
import java.util.UUID;

public class MatchingBipartiteMaxModule implements GraphProperty {
    Graph graph;

    public boolean dfs(int x){
        boolean[] visited_vertices = new boolean[graph.getVertexCount()];
        if(visited_vertices[x]){
            return false;
        }
        visited_vertices[x] = true;

        return false;
    }
    @Override
    public boolean execute(Graph graph) {

        return true;
    }
}
