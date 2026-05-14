class Solution {
    int distLimit, splitLimit;
    int answer;

    void search(long currentDist, long leaf, long split, long dist) {
        if (dist > distLimit) return;

        answer = (int) Math.max(answer, currentDist + leaf);

        for (int child = 2; child <= 3; child++) {
            long nextSplit = split * child;
            if (nextSplit > splitLimit) continue;

            long nextNodes = currentDist * child;
            long remain = distLimit - dist;

            long nextDist = Math.min(remain, nextNodes);
            long nextLeaf = nextNodes - nextDist;

            search(nextDist, leaf + nextLeaf, nextSplit, dist + nextDist);
        }
    }

    public int solution(int dist_limit, int split_limit) {
        this.distLimit = dist_limit;
        this.splitLimit = split_limit;

        answer = 1;

        search(1, 0, 1, 1);

        return answer;
    }
}