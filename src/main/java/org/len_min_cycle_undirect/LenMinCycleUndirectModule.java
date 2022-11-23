package org.len_min_cycle_undirect;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class LenMinCycleUndirectModule implements GraphCharacteristic {

    void Add_edge(Vector<Integer>[] gr, int x, int y)
    {
        gr[x].add(y);
        gr[y].add(x);
    }
    int find(UUID[] vertex, UUID num){
        int r = 0;
        for (int j=0; j < vertex.length; j++){
            if (vertex[j].equals(num)) r = j;
        }
        return r;
    }
    Vector<Integer>[] get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges){
        @SuppressWarnings("unchecked")
        Vector<Integer>[] gr = new Vector[Vertex_count];

        for (int i = 0; i < Vertex_count; i++)
            gr[i] = new Vector<>();
        UUID[] vert = new UUID[Vertex_count];

        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();

        for (Edge tmp : edges){
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            Add_edge(gr, from, to);
        }

        return gr;
    }

    int shortest_cycle(Vector<Integer>[] gr, int n)
    {

        // To store length of the shortest cycle
        int ans = Integer.MAX_VALUE;

        // For all vertices
        for (int i = 0; i < n; i++)
        {

            // Make distance maximum
            int[] dist = new int[n];
            Arrays.fill(dist, (int) 1e9);

            // Take a imaginary parent
            int[] par = new int[n];
            Arrays.fill(par, -1);

            // Distance of source to source is 0
            dist[i] = 0;
            Queue<Integer> q = new LinkedList<>();

            // Push the source element
            q.add(i);

            // Continue until queue is not empty
            while (!q.isEmpty())
            {

                // Take the first element
                int x = q.poll();

                // Traverse for all it's childs
                for (int child : gr[x])
                {
                    // If it is not visited yet
                    if (dist[child] == (int) (1e9))
                    {

                        // Increase distance by 1
                        dist[child] = 1 + dist[x];

                        // Change parent
                        par[child] = x;

                        // Push into the queue
                        q.add(child);
                    } else if (par[x] != child && par[child] != x)
                        ans = Math.min(ans, dist[x] + dist[child] + 1);
                }
            }
        }

        // If graph contains no cycle
        if (ans == Integer.MAX_VALUE)
            return -1;

            // If graph contains cycle
        else
            return ans;
    }



    @Override
    public Integer execute(Graph graph) {
        return shortest_cycle(get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges()), graph.getVertexCount());
    }
}