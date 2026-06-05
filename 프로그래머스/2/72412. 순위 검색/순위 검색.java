import java.util.*;

class Solution {
    Map<String, List<Integer>> map = new HashMap<>();
    
    void makeKey(String[] input, int depth, String key) {
        if(depth == 4) {
            int score = Integer.parseInt(input[4]);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(score);
            return;
        }
        
        makeKey(input, depth + 1, key + input[depth]);
        makeKey(input, depth + 1, key + "-");
    }
    
    int search(List<Integer> scoreList, int targetScore) {
        int left = 0;
        int right = scoreList.size();
        
        while(left < right) {
            int mid = left + (right - left) / 2;
            
            if(scoreList.get(mid) < targetScore) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    public int[] solution(String[] info, String[] query) {
        for(String str : info) {
            makeKey(str.split(" "), 0, "");
        }
        
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }
        
        int[] answer = new int[query.length];
        for(int idx = 0; idx < query.length; idx++) {
            String[] arr = query[idx].split(" ");
            
            StringBuilder key = new StringBuilder();
            key.append(arr[0]).append(arr[2]).append(arr[4]).append(arr[6]);

            int score = Integer.parseInt(arr[7]);
            List<Integer> list = map.get(key.toString());            
            
            if(list == null) {
                answer[idx] = 0;
            } else {
                answer[idx] = list.size() - search(list, score);
            }
        }
        
        return answer;
    }
}