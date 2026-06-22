class Solution {
    int convert(String time) {
        String[] arr = time.split(":");
        
        int hour = Integer.parseInt(arr[0]);
        int minute = Integer.parseInt(arr[1]);
        int second = Integer.parseInt(arr[2]);
        
        return hour * 3600 + minute * 60 + second;
    }
    
    String convert(int time) {
        int hour = time / 3600;
        time %= 3600;
        
        int minute = time / 60;
        int second = time % 60;
        
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convert(play_time);
        int advTime = convert(adv_time);
        
        long[] count = new long[playTime + 2]; 
        
        for(String log : logs) {
            String[] arr = log.split("-");
            int from = convert(arr[0]);
            int to = convert(arr[1]);
            
            count[from] += 1;
            count[to] -= 1; 
        }
        
        for(int idx = 1; idx <= playTime; idx++) { 
            count[idx] += count[idx - 1];
        }
        
        long sum = 0;
        for(int idx = 0; idx < advTime; idx++) {
            sum += count[idx];
        }
        
        long maxSum = sum;
        int answer = 0;
        
        for(int right = advTime; right <= playTime; right++) {
            sum += count[right];
            sum -= count[right - advTime];
            
            if(sum > maxSum) {
                maxSum = sum;
                answer = right - advTime + 1;
            }
        }
        
        return convert(answer);
    }
}