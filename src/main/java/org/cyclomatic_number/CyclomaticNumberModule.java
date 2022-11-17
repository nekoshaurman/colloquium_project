package org.cyclomatic_number;

import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;
import java.util.*;

public class CyclomaticNumberModule implements GraphCharacteristic
{
    /*@Override*/
    public Integer n = 10;
    public Integer e = 9;
    public LinkedList<Integer>[] g;
    public boolean[] visited;

    public void DFS(int v)
    {
        visited[v] = true;
        for (int adj : g[v])
        {
            if (!visited[adj])
            {
                DFS(adj);
            }
        }
    }
    public int Cyclomatic_Number()
    {
        int c = 0;
        for (int i = 0; i< n; ++i)
        {
            visited[i] = false;
        }
        for (int i = 0; i< n; ++i)
        {
            if (!visited[i])
            {
                DFS(i);
                c++;
            }
        }
        return (e - n + c);
    }

    public Integer execute(Graph graph)
    {
        return Cyclomatic_Number();
    }
}
