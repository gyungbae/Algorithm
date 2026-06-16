import java.util.*;

class Solution {
    int convert(String time) {
        String[] arr = time.split(":");
        
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
    
    String convert(int time) {
        int hour = time / 60;
        int min = time % 60;
        
        return String.format("%02d:%02d", hour, min);
    }
    
    public String solution(int n, int t, int m, String[] timetable) {
        int[] timeTable = new int[timetable.length];
        for(int idx = 0; idx < timetable.length; idx++) {
            timeTable[idx] = convert(timetable[idx]);
        }
        
        Arrays.sort(timeTable);
        
        int lastTime = convert("09:00") + (n - 1) * t;
        int time = convert("09:00");
        int idx = 0;
        
        int answer = 0;
        while(time <= lastTime) {
            int count = m;
            int lastCrewTime = -1;
            
            while(idx < timetable.length && timeTable[idx] <= time && count > 0) {
                lastCrewTime = timeTable[idx];
                count--;
                idx++;
            }
            
            if(time == lastTime) {
                if(count > 0) {
                    answer = time;
                } else {
                    answer = lastCrewTime - 1;
                }
            }
            
            time += t;
        }
        
        return convert(answer);
    }
}