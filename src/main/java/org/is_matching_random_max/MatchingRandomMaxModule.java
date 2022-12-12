package org.is_matching_random_max;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MatchingRandomMaxModule implements GraphProperty {
    int find(UUID[] vertex, UUID num){
        int r = 0;
        for (int j=0; j < vertex.length; j++){
            if (vertex[j].equals(num)) r = j;
        }
        return r;
    }

    boolean[][] get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges, GraphType type){
        // массив вершин
        UUID[] vert = new UUID[Vertex_count];
        boolean [][] adj = new boolean[Vertex_count][Vertex_count];

        // Добавляем все id вершин графа в массив вершин графа
        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();

        // Проходимся по всем ребрам и заполняем матрицу смежности(вершина откуда начинается ребро, куда идет ребро)
        for (Edge tmp : edges){
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            adj[from][to] = true;
            if (type == GraphType.UNDIRECTED)
                adj[to][from] = true;
        }

        return adj;
    }





    @Override
    public boolean execute(Graph graph) {

        return true;
    }
}