package org.finding_smallest_element;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FindingSmallestElement implements GraphProperty {

    private int V;
    UUID[] vert;
    private ArrayList<ArrayList<Integer>> adj;

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean[] visited,
                             Stack<Integer> stack)
    {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent
        // to thisvertex
        for (Integer integer : adj.get(v)) {
            i = integer;
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        }

        // Push current vertex to stack
        // which stores result
        stack.push(v);
    }

    // The function to do Topological Sort.
    // It uses recursive topologicalSortUtil()
    UUID topologicalSort()
    {
        Stack<Integer> stack = new Stack<>();

        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper
        // function to store
        // Topological Sort starting
        // from all vertices one by one
        for (int i = 0; i < V; i++)
            if (!visited[i])
                topologicalSortUtil(i, visited, stack);
        UUID min_elem = null;
        // Print contents of stack
        while (!stack.empty())
        {
            min_elem = convert(stack.pop());
        }

        return min_elem;

    }

    void Add_edge(int x, int y)
    {
        adj.get(x).add(y);
    }

    // Функция нахождения позиции вершины в массиве вершин
    int find(UUID num){
        int r = 0;
        for (int j=0; j < vert.length; j++){

            if (vert[j].equals(num))
                r = j;
        }
        return r;
    }

    UUID convert(int num){
        UUID r = null;
        for (int j=0; j < vert.length; j++){

            if (j == num)
                r = vert[j];
        }
        return r;
    }

    // Функция составления матрицы смежности графа
    // g - список вершин, Vertex_count - количество вершин, edges - список ребер
    void get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges) {
        // gr - список смежности
        adj = new ArrayList<>(Vertex_count);

        V = Vertex_count;

        for (int i = 0; i < Vertex_count; i++)
            adj.add(new ArrayList<>());
        // массив вершин графа
        vert = new UUID[Vertex_count];

        // Добавляем все id вершин графа в массив вершин графа
        int i = -1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet()) {
            vert[++i] = f.getKey();
        }

        // Проходимся по всем ребрам и заполняем список смежности(вершина откуда начинается ребро, куда идет ребро)
        for (Edge tmp : edges) {
            int from = find(tmp.getFromV());
            int to = find(tmp.getToV());
            Add_edge(from, to);
        }
    }



    @Override
    public boolean execute(Graph graph) {
        get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges());
        Color red_color = Color.valueOf("red");

        int flag = 0;

        for (Map.Entry<UUID, Vertex> f : graph.getVertices().entrySet()) {
            if (f.getValue().getColor() == red_color) {
                ++flag;
                if (flag > 1) {
                    break;
                }
            }
        }

        return (flag == 1) & (graph.getVertices().get(topologicalSort()).getColor() == red_color);

    }
}
