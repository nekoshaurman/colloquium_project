package org.cyclomatic_number;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.graph.repository.GraphType;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class CyclomaticNumberModule implements GraphCharacteristic
{

    void DFS(int v, boolean[] visited, List<Edge> edges) {
        visited[v] = true;
        LinkedList<Integer>[] g = new LinkedList[10];
        for (Integer adj : g[9])
        {
            if (!visited[adj])
                DFS(adj, visited, edges);
        }
    }

    Integer Cyclomatic_Number(Integer vertexCount, Integer edgeCount, List<Edge> edges) {
        int count_connectivity_components = 0;
        boolean[] visited = new boolean[vertexCount];
        for (int i = 0; i < vertexCount; ++i)
        {
            if (!visited[i])            {
                DFS(i, visited, edges);
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

    @Override
    public Integer execute(Graph graph) {




        List<Edge> edges = graph.getEdges(); // list of edges
        GraphType type = graph.getDirectType(); // graph type: DIRECTED or UNDIRECTED

        UUID[] vert = new UUID[graph.getVertexCount()];
        boolean [][] matrix = new boolean[graph.getVertexCount()][graph.getVertexCount()];

        int i=-1;
        Map<UUID, Vertex> g = graph.getVertices();
        for (Map.Entry<UUID, Vertex> f : g.entrySet() ) {
            vert[++i] = f.getKey();
        }


        for (int z = 0; z < edges.size(); z++) {
            Edge tmp = edges.get(z);
            UUID from = tmp.getFromV();
            UUID to = tmp.getToV();
            System.out.println(from + "    0000000000000000000000000   " + to);
            System.out.println( find(vert, from)+ "    tttttttttttttttttt   " + find(vert, to));
            matrix[find(vert, from)][find(vert, to)] = true;

        }





        for (int j=0; j < graph.getVertexCount(); j++){
            System.out.println(vert[j]);
        }
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);





        return 0;//Cyclomatic_Number(graph.getVertexCount(), graph.getEdgeCount(), graph.getEdges());
    }
}
