package org.len_min_cycle_undirect;

import com.mathsystem.api.graph.model.Edge;
import com.mathsystem.api.graph.model.Graph;
import com.mathsystem.api.graph.model.Vertex;
import com.mathsystem.domain.plugin.plugintype.GraphCharacteristic;

import java.util.*;

public class LenMinCycleUndirectModule implements GraphCharacteristic {

    // Функция добавления ребра в список смежности
    void Add_edge(Vector<Integer>[] gr, int x, int y)
    {
        gr[x].add(y);
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

    // Функция составления списка смежности графа
    // g - список вершин, Vertex_count - количество вершин, edges - список ребер
    Vector<Integer>[] get_adj(Map<UUID, Vertex> g, Integer Vertex_count, List<Edge> edges){
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
            Add_edge(gr, from, to);
        }
        return gr;
    }

    // Функция нахождения минимального цикла
    int shortest_cycle(Vector<Integer>[] gr, int n)
    {
        // Для хранения длины наименьшего цикла
        int ans = Integer.MAX_VALUE;

        // Для всех вершин
        for (int i = 0; i < n; i++)
        {

            // Сделать расстояние максимальным
            int[] dist = new int[n];
            Arrays.fill(dist, (int) 1e9);

            // Создадим родительскую вершину
            int[] par = new int[n];
            Arrays.fill(par, -1);

            // Расстояние для элемента сделаем 0
            dist[i] = 0;
            Queue<Integer> q = new LinkedList<>();

            // Добавим элемент в очередь
            q.add(i);

           // Выполняем до тех пор пока очередь не пустая
            while (!q.isEmpty())
            {

                // Берем первый элемент
                int x = q.poll();

                // Пройтись по всем смежным вершинам
                for (int child : gr[x])
                {
                    // Если еще не посещали
                    if (dist[child] == (int) (1e9))
                    {

                        // Увеличиваем расстояние на 1
                        dist[child] = 1 + dist[x];

                        // Меняем родителя
                        par[child] = x;

                        // Добавляем в очередь
                        q.add(child);
                    } else if (par[x] != child && par[child] != x)
                        ans = Math.min(ans, dist[x] + dist[child] + 1);
                }
            }
        }

        // Если в графе нет циклов
        if (ans == Integer.MAX_VALUE)
            return 0;

        // Если есть цикл, вернуть размер минимального цикла
        else
            return ans;
    }



    @Override
    public Integer execute(Graph graph) {
        return shortest_cycle(get_adj(graph.getVertices(), graph.getVertexCount(), graph.getEdges()), graph.getVertexCount());
    }
}