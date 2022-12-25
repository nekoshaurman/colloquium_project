package org.finding_smallest_element;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class FindingSmallestElement implements GraphProperty {


    UUID[] vert;
    private ArrayList<ArrayList<Integer>> adj;

    // A recursive function used by topologicalSort


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

    // Функция составления списка графа
    // g - список вершин, Vertex_count - количество вершин, edges - список ребер
    boolean get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges, UUID ver) {
        // gr - список смежности
        adj = new ArrayList<>(Vertex_count);
        int[] out = new int[Vertex_count];



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
            out[from] += 1;
            Add_edge(from, to);
        }
        int min = Integer.MAX_VALUE;
        for (int elem : out)
        {
            if (min > elem)
                min = elem;
        }
        for (int jj = 0; jj<Vertex_count; jj++)
        {
            if ((out[jj] == min) & (convert(jj) == ver))
            {
                return true;
            }
        }
        return false;
    }




    @Override
    public boolean execute(Graph graph) {
        Color red_color = Color.valueOf("red");
        UUID ver = null;

        int flag = 0;

        for (Map.Entry<UUID, Vertex> f : graph.getVertices().entrySet()) {
            if (f.getValue().getColor() == red_color) {
                ver = f.getValue().getId();
                ++flag;
                if (flag > 1) {
                    break;
                }
            }
        }

        return (flag == 1) & get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges(), ver);
    }
}
