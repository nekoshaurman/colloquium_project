package org.example;

import java.util.*;

public class CyclomaticNumber {
    static int n = 10;
    static int e = 9;
    static LinkedList<Integer>[] g;
    static boolean[] visited;
    static void DFS(int v)
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
    static int Cyclomatic_Number()
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
    public static void main(String[] args)
    {
        System.out.println(CyclomaticNumber());
    }
}