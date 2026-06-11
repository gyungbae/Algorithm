class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200_000_000;
        int answer = 0;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            int count = 0;
            boolean possible = true;
            for(int idx = 0; idx < stones.length; idx++) {
                if(stones[idx] < mid)
                    count++;
                
                if(count == k) {
                    possible = false;
                    break;
                }
                
                if(stones[idx] >= mid)
                    count = 0;
            }
            
            if(possible) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return answer;
    }
}