package org.cyclomatic_number;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class CyclomaticNumberModule implements GraphCharacteristic
{

    void DFS(Integer v, boolean[] visited, List<Edge> edges) {
        visited[v] = true;
        for (Integer adj : edges.get(v))
        {
            if (!visited[adj])
            {
                DFS(adj, visited, edges);
            }
        }
    }
    Integer Cyclomatic_Number(Integer vertexCount, Integer edgeCount, List<Edge> edges) {
        int count_connectivity_components = 0;
        boolean[] visited = new boolean[vertexCount];
        for (int i = 0; i < vertexCount; ++i)
        {
            if (!visited[i])
            {
                DFS(i, visited, edges);
                ++count_connectivity_components;
            }
        }
        return (edgeCount - vertexCount + count_connectivity_components);
    }

    @Override
    public Integer execute(Graph graph) {
        List<Edge> edges = graph.getEdges();
        return Cyclomatic_Number(graph.getVertexCount(), graph.getEdgeCount(), graph.getEdges());
    }
}
