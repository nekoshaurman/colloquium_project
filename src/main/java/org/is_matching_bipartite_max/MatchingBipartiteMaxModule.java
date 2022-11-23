package org.is_matching_bipartite_max;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.HashMap;

import java.util.List;
import java.util.UUID;

//TODO: Is a matching in a bipartite graph maximal?
public class MatchingBipartiteMaxModule implements GraphProperty {
    public boolean dfs(Graph graph, UUID vertex, HashMap<UUID, UUID> incedence_array, HashMap<UUID, Boolean> visited_vertices) {
        UUID empty = new UUID(0, 0);

        if (visited_vertices.get(vertex)) return false;
        visited_vertices.replace(vertex, true);

        for (UUID tmp_vert : incedence_array.keySet()) {
            if (incedence_array.get(tmp_vert).equals(empty) || dfs(graph, tmp_vert, incedence_array, visited_vertices)) {
                incedence_array.replace(tmp_vert, vertex);
                return true;
            }
        }

        return false;
    }


    public void fill_arrays(HashMap<UUID, Boolean> visited_vertices) {

        for (UUID vertex : visited_vertices.keySet()) {
            visited_vertices.put(vertex, false);

        }
    }

    @Override
    public boolean execute(Graph graph) {
        UUID empty = new UUID(0, 0);

        HashMap<UUID, UUID> incedence_array = new HashMap<>(graph.getVertexCount());
        System.out.println(incedence_array);
        HashMap<UUID, Boolean> visited_vertices = new HashMap<>();
        HashMap<UUID, Vertex> vertices = (HashMap<UUID, Vertex>) graph.getVertices();



        for (UUID vertex : vertices.keySet()) {
            incedence_array.put(vertex, empty);
            visited_vertices.put(vertex, false);

        }
        for (UUID vertex : vertices.keySet()) {
            fill_arrays(visited_vertices);
            dfs(graph, vertex, incedence_array, visited_vertices);
        }


        System.out.println(incedence_array);
        System.out.println(visited_vertices);

        return false;
    }

}
