package OA2;

import java.util.ArrayList;
import java.util.List;

public class criticalConnection {
    private int time = 0;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer>[] graph = (List<Integer>[]) new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection: connections) {
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }
        boolean[] visited = new boolean[n];
        //记录当前点的父节点
        int[] parent = new int[n];
        //记录节点的访问时间
        int[] visitedTime = new int[n];
        //记录当前节点能达到的最早visited的节点
        int[] low = new int[n];
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(parent, visitedTime, low, visited, i, graph, ret);
            }
        }
        return ret;
    }
    private void dfs(int[] parent, int[] visitedTime, int[] low, boolean[] visited, int cur, List<Integer>[] graph, List<List<Integer>> list) {
        visited[cur] = true;
        visitedTime[cur] = time;
        low[cur] = time;
        time++;
        for (int ne: graph[cur]) {
            if (!visited[ne]) {
                parent[ne] = cur;
                dfs(parent, visitedTime, low, visited, ne, graph, list);
                low[cur] = Math.min(low[cur], low[ne]);
                if (visitedTime[cur] < low[ne]) {
                    List<Integer> tmp = new ArrayList<>();
                    //添加亚麻限制条件,经过leetcode test 可以过。
                    if (cur < ne) {
                        tmp.add(cur);
                        tmp.add(ne);
                        list.add(tmp);
                    }
                    /*
                    tmp.add(cur);
                    tmp.add(ne);
                    list.add(tmp);

                     */
                }
            } else if (ne != parent[cur]) {
                low[cur] = Math.min(low[cur], visitedTime[ne]);
            }
        }
    }
}
