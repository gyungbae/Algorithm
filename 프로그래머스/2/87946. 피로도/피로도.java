class Solution {
    int k;
    int[][] dungeons;
    int size;
    
    int[] order;
    boolean[] selected;
    int answer;
    
    void makeOrder(int depth) {
        if(depth == size) {
            int fatigue = k;
            int count = 0;
            for(int idx : order) {
                int[] info = dungeons[idx];
                
                if(fatigue < info[0])
                    continue;
                
                if(fatigue < info[1])
                    break;
                
                fatigue -= info[1];
                count++;
            }
            
            answer = Math.max(answer, count);
            
            return;    
        }
        
        for(int idx = 0; idx < size; idx++) {
            if(selected[idx])
                continue;
            
            selected[idx] = true;
            order[depth] = idx;
            makeOrder(depth + 1);
            order[depth] = 0;
            selected[idx] = false;
        }
    }
    
    public int solution(int k, int[][] dungeons) {
        this.k = k;
        this.dungeons = dungeons;
        size = dungeons.length;
        
        order = new int[size];
        selected = new boolean[size];
        
        makeOrder(0);
        
        return answer;
    }
}