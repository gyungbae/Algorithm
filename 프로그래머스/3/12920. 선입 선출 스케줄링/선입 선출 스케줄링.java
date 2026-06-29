class Solution {
    public int solution(int n, int[] cores) {
        int size = cores.length;
        
        if(n <= size)
            return n;
        
        int remain = n - size;
        
        int left = 1;
        int right = n * 10000;
        int totalTime = -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            
            int complete = 0;
            for(int time : cores) {
                complete += mid / time;
            }
            
            if(complete >= remain) {
                totalTime = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        int complete = 0;
        for(int time : cores) {
            complete += (totalTime - 1) / time;
        }
        
        int lastRemain = remain - complete;
        
        for(int core = 0; core < size; core++) {
            if(totalTime % cores[core] == 0) {
                lastRemain--;
                
                if(lastRemain == 0)
                    return core + 1;
            }
        }
        
        return -1;
    }
}