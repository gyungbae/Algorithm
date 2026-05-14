class Solution {
    public int[] solution(int[][] edges) {
        int size = 0;

        for(int[] edge : edges) {
            size = Math.max(Math.max(edge[0], edge[1]), size);
        }

        int[] inDegree = new int[size + 1];
        int[] outDegree = new int[size + 1];
        boolean[] exist = new boolean[size + 1];

        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            outDegree[from]++;
            inDegree[to]++;

            exist[from] = true;
            exist[to] = true;
        }

        int newNode = 0;
        int total = 0;
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;

        for(int node = 1; node <= size; node++) {
            if(!exist[node]) continue;

            int in = inDegree[node];
            int out = outDegree[node];

            if(in == 0 && out >= 2) {
                newNode = node;
                total = out;
            } else if(out == 0) {
                count2++;
            } else if(in >= 2 && out == 2) {
                count3++;
            }
        }

        count1 = total - count2 - count3;

        return new int[]{newNode, count1, count2, count3};
    }
}