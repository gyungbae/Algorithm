import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] record = new int[y + 1];

        Arrays.fill(record, -1);

        record[x] = 0;
        queue.offer(x);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == y) 
                return record[current];

            int[] nexts = {current + n, current * 2, current * 3};

            for (int next : nexts) {
                if (next > y) continue;
                if (record[next] != -1) continue;

                record[next] = record[current] + 1;
                queue.offer(next);
            }
        }

        return -1;
    }
}
