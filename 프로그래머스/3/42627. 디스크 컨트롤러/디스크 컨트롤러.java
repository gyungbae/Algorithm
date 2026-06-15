import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
           if(o1[1] == o2[1])
               return o1[0] - o2[0];
            
            return o1[1] - o2[1];
        });
        
        int size = jobs.length;
        int time = 0;
        int sum = 0;
        int jobIdx = 0;
        int complete = 0;
        
        while(complete < size) {
            while(jobIdx < size && jobs[jobIdx][0] <= time) {
                queue.offer(jobs[jobIdx]);
                jobIdx++;
            }
            
            if(queue.isEmpty()) {
                time = jobs[jobIdx][0];
                continue;
            }
            
            int[] info = queue.poll();
            
            time += info[1];
            sum += time - info[0];
            complete++;
        }
        
        return sum / size;
    }
}