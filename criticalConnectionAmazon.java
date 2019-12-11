package OA2;

import java.util.ArrayList;
import java.util.List;

public class criticalConnectionAmazon {

    class pairInt {
        int first, second;

        pairInt() {
        }

        pairInt(int first, int second) {
            this.first = first;
            this.second = second;
        }

        //定义可能又变化，zhu变化。

        public int getFirst() {
            return this.first;
        }

        public int getSecond() {
            return this.second;
        }
    }

    private int time = 0;
    @SuppressWarnings("unchecked")
    public List<pairInt> criticalConnections(int numberOfhouse, int numberofRoads, List<pairInt> connections) {
        /**
         *
         * @Parameter
         * numberOfHouse, start from 1. -> n + 1;
         * numberOfRoad,
         * List
         *
         */
        int n = numberOfhouse + 1;
        List<pairInt> ret = new ArrayList<>();
        List<Integer>[] graph = (List<Integer>[]) new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }
        for (pairInt connection: connections) {
            /*
            graph[connection.getFirst()].add(connection.getSecond());
            graph[connection.getSecond()].add(connection.getFirst());

             */
            //TODO 看定义
            graph[connection.first].add(connection.second);
            graph[connection.second].add(connection.first);
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
    private void dfs(int[] parent, int[] visitedTime, int[] low, boolean[] visited, int cur, List<Integer>[] graph, List<pairInt> list) {
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
                    //List<Integer> tmp = new ArrayList<>();

                    if (cur < ne) {
                        pairInt element = new pairInt(cur,ne);
                        list.add(element);
                    } else {
                        pairInt element = new pairInt(ne,cur);
                        list.add(element);
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
