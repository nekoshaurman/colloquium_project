package org.len_min_cycle_direct;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class LenMinCycleDirectModule implements GraphCharacteristic {
    Graph graph;
    ArrayList<Vector<Integer>> adj;
    int vertexCount;
    int max_cycle_len;
    UUID[] vertices;

    @Override
    public Integer execute(Graph graph) {
        this.graph = graph;

        if (graph.getDirectType() != GraphType.DIRECTED) {
            return -1;
        }
        initAdj();

        return getMinCycleLen();
    }

    private int getMinCycleLen() {
        this.max_cycle_len = this.vertexCount + 1;
        int min_len = this.max_cycle_len;
        for (int i = 0; i < vertexCount; i++) {
            min_len = Math.min(getMinCycleLenFromVert(i), min_len);
        }

        if (min_len == this.max_cycle_len) {
            min_len = 0;
        }

        return min_len;
    }

    private int getMinCycleLenFromVert(int start) {
        int[] dist = new int[this.vertexCount];
        Arrays.fill(dist, this.vertexCount + 1);
        dist[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int v = q.poll();
            for (int u : this.adj.get(v)) {
                if (dist[u] > dist[v] + 1) {
                    dist[u] = dist[v] + 1;
                    q.add(u);
                }
                if (u == start && dist[start] == 0) {
                    dist[start] = dist[v] + 1;
                }
            }
        }

        return dist[start];
    }

    private void initAdj() {
        this.vertexCount = this.graph.getVertexCount();
        this.adj = new ArrayList<>();
        this.vertices = new UUID[vertexCount];

        for (int i = 0; i < vertexCount; i++)
            this.adj.add(new Vector<>());

        // Добавляем все id вершин графа в массив вершин графа
        int i = 0;
        for (Map.Entry<UUID, Vertex> f : this.graph.getVertices().entrySet())
            this.vertices[i++] = f.getKey();

        // Проходимся по всем ребрам и заполняем список смежности(вершина откуда начинается ребро, куда идет ребро)
        for (Edge edge : this.graph.getEdges()){
            int from = findInVertices(edge.getFromV());
            int to = findInVertices(edge.getToV());
            this.adj.get(from).add(to);
        }
    }

    private int findInVertices(UUID vertId) {
        for (int i = 0; i < this.vertexCount; i++) {
            if (this.vertices[i].equals(vertId))
                return i;
        }
        return -1;
    }
}