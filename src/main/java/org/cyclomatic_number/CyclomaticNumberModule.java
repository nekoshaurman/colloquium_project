package org.cyclomatic_number;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class CyclomaticNumberModule implements GraphCharacteristic
{
    ArrayList<int> g = new ArrayList<>(3);


    void DFS(int v, boolean[] visited) {
        visited[v] = true;
        for (Integer adj : g.get(v))
        {
            if (!visited[adj])
            {
                DFS(adj, visited);
            }
        }
    }
    Integer Cyclomatic_Number(Integer vertexCount, Integer edgeCount) {
        int count_connectivity_components = 0;
        boolean[] visited = new boolean[vertexCount];
        for (int i = 0; i < vertexCount; ++i)
        {
            if (!visited[i])
            {
                DFS(i, visited);
                ++count_connectivity_components;
            }
        }
        return (edgeCount - vertexCount + count_connectivity_components);
    }

    @Override
    public Integer execute(Graph graph) {
        return Cyclomatic_Number(graph.getVertexCount(), graph.getEdgeCount());
    }
}
