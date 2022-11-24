package org.cyclomatic_number;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class CyclomaticNumberModule implements GraphCharacteristic {
    // Функция обхода в глубину по матрице смежности
    // int start - вершина, с которой начнется обхода, int[][] adj - матрица смежности
    void DFS(int start, boolean[] visited, int[][] adj) {
        // помечаем вершину как просмотренную
        visited[start] = true;

        // идем по всем вершинам, если i-ая вершина смежная и непросмотренная, то сделать обход в глубину, начиная с этой вершины
        for (int i = 0; i < adj[start].length; i++) {
            if (adj[start][i] == 1 && (!visited[i])) {
                DFS(i, visited, adj);
            }
        }

    }

    // Функция нахождения цикломатического числа графа
    // vertexCount - количество вершин, edgeCount - количество рёбер, adj - матрица смежности
    Integer Cyclomatic_Number(Integer vertexCount, Integer edgeCount, int[][] adj) {
        int count_connectivity_components = 0;
        boolean[] visited = new boolean[vertexCount];

        // проходимся по всем вершинам, если она непросмотренная, то запускаем для нее обход в глубину и увеличиваем счетчик
        for (int i = 0; i < vertexCount; ++i)
        {
            if (!visited[i]) {
                DFS(i, visited, adj);
                ++count_connectivity_components;
            }
        }
        return (edgeCount - vertexCount + count_connectivity_components);
    }

    // Функция нахождения позиции вершины в массиве вершин
    int find(UUID[] vertex, UUID num){
        int r = 0;
        for (int j=0; j < vertex.length; j++){
            if (vertex[j].equals(num)) r = j;
        }
        return r;
    }

    // Функция составления матрицы смежности графа
    // g - список вершин, Vertex_count - количество вершин, edges - список ребер
    int[][] get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges){
        // массив вершин
        UUID[] vert = new UUID[Vertex_count];
        int [][] adj = new int[Vertex_count][Vertex_count];

        // Добавляем все id вершин графа в массив вершин графа
        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();

        // Проходимся по всем ребрам и заполняем матрицу смежности(вершина откуда начинается ребро, куда идет ребро)
        for (Edge tmp : edges){
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            adj[from][to] = 1;
            adj[to][from] = 1;
        }

        return adj;
    }

    @Override
    public Integer execute(Graph graph) {
        return Cyclomatic_Number(graph.getVertexCount(), graph.getEdgeCount(), get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges()));
    }
}
