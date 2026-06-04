import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> bridge = new ArrayDeque<>();
        
        int answer = 0;
        int bridgeWeight = 0;
        int truckIdx = 0;
        int complete = 0;
        while(complete < truck_weights.length) {
            answer++;
            
            while(!bridge.isEmpty() && answer - bridge.peek()[1] >= bridge_length) {
                int[] truck = bridge.poll();
                bridgeWeight -= truck_weights[truck[0]];
                complete++;
            }
            
            
            if(truckIdx >= truck_weights.length)
                continue;

            int nextWeight = bridgeWeight + truck_weights[truckIdx];

            if(nextWeight > weight)
                continue;

            bridgeWeight = nextWeight;
            bridge.offer(new int[]{truckIdx, answer});
            truckIdx++;
        }
        
        return answer;
    }
}