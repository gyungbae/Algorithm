import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] arr = s.substring(2, s.length() - 2).split("\\},\\{");
        
        Map<Integer, Integer> map = new HashMap<>();
        for(String str : arr) {
            String[] tuple = str.split(",");
            for(String num : tuple) {
                int key = Integer.parseInt(num);
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        
        int max = 0;
        for(int count : map.values()) {
            max = Math.max(max, count);
        }
        
        int[] answer = new int[max];
        int idx = 0;
        while(max > 0) {
            for(int key : map.keySet()) {
                if(map.get(key) == max) {
                    answer[idx++] = key;
                    break;
                }
            }
            
            max--;
        }
        
        return answer;
    }
}