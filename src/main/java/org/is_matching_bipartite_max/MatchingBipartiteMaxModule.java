package org.is_matching_bipartite_max;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.graph.repository.Color;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.ArrayList;


import java.util.List;
import java.util.UUID;

//TODO: Is a matching in a bipartite graph maximal?
public class MatchingBipartiteMaxModule implements GraphProperty {

    @Override
    public boolean execute(Graph graph) {
        List<Edge> edges_list = graph.getEdges();
        Color red_color = Color.valueOf("red");
        List<UUID> red_vertices = new ArrayList<>();
        for(Edge edge : edges_list){
            if(edge.getColor() == red_color) {
                red_vertices.add(edge.getToV());
                red_vertices.add(edge.getFromV());
            }
        }

        if(red_vertices.size() == 0) return false;
        else {
            for(Edge edge : edges_list){
                int count = 0;
                if(edge.getColor() != red_color){
                    for(UUID vertex : red_vertices){
                        if(!(vertex.equals(edge.getFromV())) && !(vertex.equals(edge.getToV()))){
                            count++;
                        }
                    }
                    if(count == red_vertices.size()) return false;
                }
            }
        }
        return true;
    }

}
