package org.is_K5_in_graph;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

public class K5Module implements GraphProperty {
    static final int N = 100000;

    // variables to be used
    // in both functions
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] graph = new Vector[N];
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] cycles = new Vector[N];
    static int cyclenumber;

    // Function to mark the vertex with
    // different colors for different cycles
    static void dfs_cycle(int u, int p, int[] color,int[] par)
    {

        // already (completely) visited vertex.
        if (color[u] == 2)
        {
            return;
        }

        // seen vertex, but was not completely visited -> cycle detected.
        // backtrack based on parents to find the complete cycle.
        if (color[u] == 1)
        {

            Vector<Integer> v = new Vector<Integer>();
            int cur = p;
            v.add(cur);

            // backtrack the vertex which are
            // in the current cycle thats found
            while (cur != u)
            {
                cur = par[cur];
                v.add(cur);
            }
            cycles[cyclenumber] = v;
            cyclenumber++;
            return;
        }
        par[u] = p;

        // partially visited.
        color[u] = 1;

        // simple dfs on graph
        for (int v : graph[u])
        {

            // if it has not been visited previously
            if (v == par[u])
            {
                continue;
            }
            dfs_cycle(v, u, color, par);
        }

        // completely visited.
        color[u] = 2;
    }


    // Function to print the cycles
    static void printCycles()
    {

        // print all the vertex with same cycle
        for (int i = 0; i < cyclenumber; i++)
        {
            // Print the i-th cycle
            System.out.printf("Cycle Number %d: ", i + 1);
            for (int x : cycles[i])
                System.out.printf("%d ", x);
            System.out.println();
        }
    }

    int find(UUID[] vertex, UUID num){
        int r = 0;
        for (int j=0; j < vertex.length; j++){
            if (vertex[j].equals(num)) r = j;
        }
        return r;
    }
    void get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges){
        // массив вершин
        UUID[] vert = new UUID[Vertex_count];
        for (int i = 0; i < Vertex_count; i++)
        {
            graph[i] = new Vector<>();
            cycles[i] = new Vector<>();
        }

        // Добавляем все id вершин графа в массив вершин графа
        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();

        // Проходимся по всем ребрам и заполняем матрицу смежности(вершина откуда начинается ребро, куда идет ребро)
        for (Edge tmp : edges){
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            graph[from].add(to);
            graph[to].add(to);
        }
    }




    @Override
    public boolean execute(Graph graph) {


        get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges());
        int[] color = new int[N];
        int[] par = new int[N];

        // call DFS to mark the cycles
        dfs_cycle(1, 0, color, par);

        // function to print the cycles
        printCycles();
        return true;
    }
}