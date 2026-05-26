class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        for(long idx = left; idx <= right; idx++) {
            long row = idx / n;
            long col = idx % n;
            
            answer[(int)(idx - left)] = (int)Math.max(row, col) + 1;
        }
        
        return answer;
    }
}