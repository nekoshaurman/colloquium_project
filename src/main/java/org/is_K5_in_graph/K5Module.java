package org.is_K5_in_graph;
import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphProperty;

import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import java.util.*;

public class K5Module implements GraphProperty {
    static final int N = 100000;
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] graph = new Vector[N];
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] cycles = new Vector[N];
    @SuppressWarnings("unchecked")
    static Vector<Integer>[] cycleslen5 = new Vector[N];
    static int cyclenumber = 0;
    static void dfs_cycle(int u, int p, int[] color,int[] par)
    {

        if (color[u]==2){
            return;
        }
        if (color[u] == 1)
        {

            Vector<Integer> v = new Vector<>();
            int cur = p;
            v.add(cur);

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
        color[u]=1;
        for (int v : graph[u])
        {
            if (v == par[u])
            {
                continue;
            }
            dfs_cycle(v, u, color, par);
        }
        color[u]=2;
    }

    static int printCycles()
    {
        int countlen5=0;
        for (int i = 0; i < cyclenumber; i++)
        {
            int len=cycles[i].size();
            if (len==5){
                for (int ignored : cycles[i])
                cycleslen5[countlen5]=cycles[i];
                countlen5++;
            }
        }
        return countlen5;
    }

    int find(UUID[] vertex, UUID num){
        int r = 0;
        for (int j=0; j < vertex.length; j++){
            if (vertex[j].equals(num)) r = j;
        }
        return r;
    }
    void get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges){
        UUID[] vert = new UUID[Vertex_count];
        for (int i = 0; i < Vertex_count; i++)
        {
            graph[i] = new Vector<>();
            cycles[i] = new Vector<>();
            cycleslen5[i] = new Vector<>();
        }
        int i=-1;
        for (Map.Entry<UUID, Vertex> f : g.entrySet())
            vert[++i] = f.getKey();
        for (Edge tmp : edges) {
            int from = find(vert, tmp.getFromV());
            int to = find(vert, tmp.getToV());
            graph[from].add(to);
            graph[to].add(from);
        }
    }

    boolean trycatchK5(Integer Vertex_count,Integer countlen5,Integer Edges_count){
        int[][] graphmatrix=new int[Vertex_count][Vertex_count];
        int[] degs = new int[Vertex_count];
        int[] nabor =new int[Vertex_count];
        int p=0;
        if ((Vertex_count<5)||(Edges_count<10)){
            return false;
        }
        for (int i = 0; i < Vertex_count; i++){
            for (int j = 0;  j< Vertex_count; j++){
                graphmatrix[i][j]=0;
            }
        }
        for (int j = 0; j < Vertex_count; j++)
        {
            degs[j]=graph[j].size();
        }
        for (int h=0; h<degs.length ;h++)
        {
            if(degs[h]>=4){
                nabor[p]=h;
                p++;
            }
        }
        if((Vertex_count==5)&(Edges_count==10)&(p==5)){
            return true;
        }
        for (int l = 0; l < Vertex_count; l++){
            for (int j = 0;  j< Vertex_count; j++){
                if (graph[l].contains(j)){
                    graphmatrix[l][j]=1;
                }
            }
        }
        Integer[] naborwoutzero=new Integer[p];
        Integer[] naborwotzeroint=new Integer[p];
        for (int l=0;l<p;l++){
            naborwoutzero[l]=nabor[l];
            naborwotzeroint[l]=nabor[l];
        }
        Vector<Integer> intListofnabor = new Vector<>(Arrays.asList(naborwoutzero));
        Integer string;
        Integer column1;
        Integer column2;
        Integer column3;
        Integer column4;
        Integer column5;
        int counter=0;
        for (int k=0;k<countlen5;k++){
            if (new HashSet<>(intListofnabor).containsAll(cycleslen5[k])){
                column1=cycleslen5[k].get(0);
                column2=cycleslen5[k].get(1);
                column3=cycleslen5[k].get(2);
                column4=cycleslen5[k].get(3);
                column5=cycleslen5[k].get(4);
                for (int y=0;y<5;y++){
                    string=cycleslen5[k].get(y);
                    if (graphmatrix[string][column1]+graphmatrix[string][column2]+graphmatrix[string][column3]+graphmatrix[string][column4]+graphmatrix[string][column5]==4){
                        counter++;
                    }
                }
            }
            if (counter==5){
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean execute(Graph graph) {
        get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges());
        int[] color = new int[N];
        int[] par = new int[N];
        boolean result;
        int countlen5;
        for (int i=0;i< graph.getVertexCount();i++){
            dfs_cycle(i,i,color,par);
        }
        countlen5=printCycles();
        result=trycatchK5(graph.getVertexCount(),countlen5,graph.getEdgeCount());
        return result;
    }
}
