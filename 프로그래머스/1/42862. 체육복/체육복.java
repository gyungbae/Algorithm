class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] count = new int[n + 1];

        for (int idx = 1; idx <= n; idx++) {
            count[idx] = 1;
        }

        for (int idx : lost) {
            count[idx]--;
        }

        for (int idx : reserve) {
            count[idx]++;
        }

        for (int idx = 1; idx <= n; idx++) {
            if (count[idx] != 0) {
                continue;
            }

            if (idx > 1 && count[idx - 1] == 2) {
                count[idx - 1]--;
                count[idx]++;
                continue;
            }

            if (idx < n && count[idx + 1] == 2) {
                count[idx + 1]--;
                count[idx]++;
            }
        }

        int answer = 0;

        for (int idx = 1; idx <= n; idx++) {
            if (count[idx] >= 1) {
                answer++;
            }
        }

        return answer;
    }
}