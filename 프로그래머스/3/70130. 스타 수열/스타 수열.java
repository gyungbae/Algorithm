class Solution {
    public int solution(int[] a) {
        int size = a.length;
        int[] count = new int[size];
        
        for(int num : a) {
            count[num]++;
        }
        
        int answer = 0;
        
        for(int num = 0; num < size; num++) {
            if(count[num] == 0)
                continue;
            
            if(count[num] * 2 <= answer)
                continue;
            
            int pairCount = 0;
            
            for(int idx = 0; idx < size - 1; idx++) {
                if(a[idx] == a[idx + 1])
                    continue;
                
                if(a[idx] != num && a[idx + 1] != num)
                    continue;
                
                pairCount++;
                idx++;
            }
            
            answer = Math.max(answer, pairCount * 2);
        }
        
        return answer;
    }
}