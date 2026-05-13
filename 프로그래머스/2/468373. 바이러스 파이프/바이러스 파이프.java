import java.util.*;

class Solution {
    int n, k;
    int[][] edges;

    boolean[] infected;
    int answer;

    void DFS(int depth) {
        if (depth == k) {
            int count = 0;

            for (int idx = 1; idx <= n; idx++) {
                if (infected[idx]) {
                    count++;
                }
            }

            answer = Math.max(answer, count);
            return;
        }

        for (int pipe = 1; pipe <= 3; pipe++) {
            List<Integer> list = infect(pipe);

            DFS(depth + 1);

            for (int idx : list) {
                infected[idx] = false;
            }
        }
    }

    List<Integer> infect(int openType) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        List<Integer> newList = new ArrayList<>();

        for (int idx = 1; idx <= n; idx++) {
            if (infected[idx]) {
                visited[idx] = true;
                queue.offer(idx);
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                int type = edge[2];

                if (type != openType) continue;

                int next;

                if (from == current) {
                    next = to;
                } else if (to == current) {
                    next = from;
                } else {
                    continue;
                }

                if (visited[next]) continue;

                infected[next] = true;
                visited[next] = true;
                newList.add(next);
                queue.offer(next);
            }
        }

        return newList;
    }

    public int solution(int n, int infection, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        this.edges = edges;

        infected = new boolean[n + 1];
        infected[infection] = true;

        DFS(0);

        return answer;
    }
}