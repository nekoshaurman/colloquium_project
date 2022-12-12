package org.is_matching_random_max;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.*;

public class MatchingRandomMaxModule implements GraphProperty {

    int lca(int[] match, int[] base, int[] p, int a, int b)
    {
        boolean[] used = new boolean[match.length];
        while (true)
        {
            a = base[a];
            used[a] = true;
            if (match[a] == -1)
                break;
            a = p[match[a]];
        }
        while (true)
        {
            b = base[b];
            if (used[b])
                return b;
            b = p[match[b]];
        }
    }

    void markPath(int[] match, int[] base, boolean[] blossom, int[] p,
                         int v, int b, int children)
    {
        for (; base[v] != b; v = p[match[v]])
        {
            blossom[base[v]] = blossom[base[match[v]]] = true;
            p[v] = children;
            children = match[v];
        }
    }

    int findPath(List<Integer>[] graph, int[] match, int[] p, int root)
    {
        int n = graph.length;
        boolean[] used = new boolean[n];
        Arrays.fill(p, -1);
        int[] base = new int[n];
        for (int i = 0; i < n; ++i)
            base[i] = i;
        used[root] = true;
        int qh = 0;
        int qt = 0;
        int[] q = new int[n];
        q[qt++] = root;
        while (qh < qt)
        {
            int v = q[qh++];
            for (int to : graph[v])
            {
                if (base[v] == base[to] || match[v] == to)
                    continue;
                if (to == root || match[to] != -1 && p[match[to]] != -1)
                {
                    int curbase = lca(match, base, p, v, to);
                    boolean[] blossom = new boolean[n];
                    markPath(match, base, blossom, p, v, curbase, to);
                    markPath(match, base, blossom, p, to, curbase, v);
                    for (int i = 0; i < n; ++i)
                        if (blossom[base[i]])
                        {
                            base[i] = curbase;
                            if (!used[i])
                            {
                                used[i] = true;
                                q[qt++] = i;
                            }
                        }
                }
                else if (p[to] == -1)
                {
                    p[to] = v;
                    if (match[to] == -1)
                        return to;
                    to = match[to];
                    used[to] = true;
                    q[qt++] = to;
                }
            }
        }
        return -1;
    }

    int maxMatching(List<Integer>[] graph)
    {
        int n = graph.length;
        int[] match = new int[n];
        Arrays.fill(match, -1);
        int[] p = new int[n];
        for (int i = 0; i < n; ++i)
        {
            if (match[i] == -1)
            {
                int v = findPath(graph, match, p, i);
                while (v != -1)
                {
                    int pv = p[v];
                    int ppv = match[pv];
                    match[v] = pv;
                    match[pv] = v;
                    v = ppv;
                }
            }
        }
        int matches = 0;
        for (int i = 0; i < n; ++i)
            if (match[i] != -1)
            {
                System.out.println(match[i]);
                ++matches;
            }

        return matches / 2;
    }

    void Add_edge(Vector<Integer>[] gr, int x, int y, GraphType directType)
    {
        gr[x].add(y);
        System.out.println(x + " " + y);
        if (directType == GraphType.UNDIRECTED)
            gr[y].add(x);
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
    Vector<Integer>[] get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges, GraphType directType){
        @SuppressWarnings("unchecked")
        // gr - список смежности
        Vector<Integer>[] gr = new Vector[Vertex_count];

        for (int i = 0; i < Vertex_count; i++)
            gr[i] = new Vector<>();
        // массив вершин графа
        UUID[] vert = new UUID[Vertex_count];

        // Добавляем все id вершин графа в массив вершин графа
        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();

        // Проходимся по всем ребрам и заполняем список смежности(вершина откуда начинается ребро, куда идет ребро)
        for (Edge tmp : edges){
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            Add_edge(gr, from, to, directType);
        }
        return gr;
    }





    @Override
    public boolean execute(Graph graph) {

        System.out.println("Maximum matching for the given graph is: "
        + maxMatching(get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges(), graph.getDirectType())));



        return true;
    }
}