package org.cyclomatic_number;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class CyclomaticNumberModule implements GraphCharacteristic {
    void DFS(int start, boolean[] visited, int[][] adj) {
        visited[start] = true;

        for (int i = 0; i < adj[start].length; i++) {
            if (adj[start][i] == 1 && (!visited[i])) {
                DFS(i, visited, adj);
            }
        }
    }

    Integer Cyclomatic_Number(Integer vertexCount, Integer edgeCount, int[][] adj) {
        int count_connectivity_components = 0;
        boolean[] visited = new boolean[vertexCount];
        for (int i = 0; i < vertexCount; ++i)
        {
            if (!visited[i]) {
                DFS(i, visited, adj);
                ++count_connectivity_components;
            }
        }
        return (edgeCount - vertexCount + count_connectivity_components);
    }

    int find(UUID[] vertex, UUID num){
        int r = 0;
        for (int j=0; j < vertex.length; j++){
            if (vertex[j].equals(num)) r = j;
        }
        return r;
    }

    int[][] get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges, GraphType type){
        UUID[] vert = new UUID[Vertex_count];
        int [][] adj = new int[Vertex_count][Vertex_count];

        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();

        for (Edge tmp : edges){
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            adj[from][to] = 1;
            if (type == GraphType.UNDIRECTED)
                adj[to][from] = 1;
        }

        return adj;
    }

    @Override
    public Integer execute(Graph graph) {
        return Cyclomatic_Number(graph.getVertexCount(), graph.getEdgeCount(), get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges(), graph.getDirectType()));
    }
}
