class Solution {
    public int solution(int n) {
        int target = countOne(n);

        int answer = n + 1;
        while (countOne(answer) != target) {
            answer++;
        }

        return answer;
    }

    private int countOne(int num) {
        int count = 0;

        while (num > 0) {
            if (num % 2 == 1) count++;
            num /= 2;
        }

        return count;
    }
}