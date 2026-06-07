class Solution {
    public long solution(int n, int[] works) {
        int left = 0;
        int right = 0;
        
        long total = 0;
        for(int value : works) {
            total += value;
            right = Math.max(right, value);
        }
        
        if(total <= n)
            return 0;
        
        int target = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            
            long sum = 0;
            for(int value : works) {
                if(value > mid)
                    sum += value - mid;
            }
            
            if(sum <= n) {
                target = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        int[] result = new int[works.length];
        for(int idx = 0; idx < works.length; idx++) {
            if(works[idx] > target) {
                result[idx] = target;
                n -= works[idx] - target;
            } else {
                result[idx] = works[idx];
            }
        }
            
        for(int idx = 0; idx < works.length; idx++) {
            if(n <= 0)
                break;
            
            if(result[idx] == target) {
                result[idx]--;
                n--;
            }
        }
        
        long answer = 0;
        for(int idx = 0; idx < works.length; idx++) {
            answer += (long) result[idx] * result[idx];
        }
        
        return answer;
    }
}