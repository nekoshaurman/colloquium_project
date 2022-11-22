package org.is_matching_bipartite_max;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MatchingBipartiteMaxModule implements GraphProperty {

//    public boolean dfs(int x){
//        boolean[] visited_vertices = new boolean[graph.getVertexCount()];
//        if(visited_vertices[x]){
//            return false;
//        }
//        visited_vertices[x] = true;
//        List<Edge> edge_list = graph.getEdges();

//        return false;
//    }
    @Override
    public boolean execute(Graph graph) {
        int x = graph.getVertexCount()-1;
        boolean[] visited_vertices = new boolean[graph.getVertexCount()];
        if(visited_vertices[x]){
            return false;
        }

        visited_vertices[x] = true;
        List<Edge> edge_list = graph.getEdges();
        HashMap<UUID, List<UUID>> incidence_array = new HashMap<>();
        for(int i = 0; i < graph.getEdgeCount(); i++){
            ArrayList<UUID> values = new ArrayList<>();
            UUID start_V = edge_list.get(i).getFromV();
            incidence_array.put(start_V, values);
        }

        for(int i = 0; i < graph.getEdgeCount(); i++){
            UUID start_V = edge_list.get(i).getFromV();
            UUID end_V = edge_list.get(i).getToV();
            ArrayList<UUID> values = (ArrayList<UUID>) incidence_array.get(start_V);
            values.add(end_V);
            incidence_array.put(start_V, values);
        }

        for(int i = 0; i < graph.getEdgeCount(); i++){
            UUID temp = edge_list.get(i).getFromV();
            System.out.println("\n" + temp + " вершина, массив - " + incidence_array.get(temp));
        }

        for(int i = 0; i < graph.getEdgeCount(); i++){
            if(incidence_array.get(edge_list.get(i).getToV()) == null){
                //incidence_array.get(edge_list.get())
            }
        }
        return false;

    }
}
