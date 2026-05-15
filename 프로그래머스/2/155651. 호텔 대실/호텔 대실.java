import java.util.*;

class Solution {
    int size;
    
    int[][] convert(String[][] arr) {
        int[][] result = new int[size][2];
        
        for(int idx = 0; idx < size; idx++) {
            String[] from = arr[idx][0].split(":");
            String[] to = arr[idx][1].split(":");
            
            int fromMin = Integer.parseInt(from[0]) * 60 + Integer.parseInt(from[1]);
            int toMin = Integer.parseInt(to[0]) * 60 + Integer.parseInt(to[1]);
            
            result[idx][0] = fromMin;
            result[idx][1] = toMin;
        }    
        
        return result;
    }
    
    public int solution(String[][] book_time) {
        size = book_time.length;
        
        int[][] bookTime = convert(book_time);    
        
        Arrays.sort(bookTime, (o1, o2) -> o1[0] - o2[0]);
        
        List<Integer> list = new ArrayList<>();
        list.add(bookTime[0][1] + 10);
        
        for(int idx = 1; idx < size; idx++) {
            int from = bookTime[idx][0];
            int to = bookTime[idx][1];
            
            boolean booked = false;
            for(int listIdx = 0; listIdx < list.size(); listIdx++) {
                int prevTo = list.get(listIdx);
                
                if(prevTo > from)
                    continue;
                
                list.set(listIdx, to + 10);
                booked = true;
                break;
            }
            
            if(!booked)
                list.add(to + 10);
        }
        
        return list.size();
    }
}