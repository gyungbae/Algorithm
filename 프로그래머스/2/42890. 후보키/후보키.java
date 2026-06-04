import java.util.*;

class Solution {
    String[][] relation;
    int rowSize, colSize;
    
    Map<String, Integer> map = new HashMap<>();
    boolean[] selected;
    int answer;
    
    void makeCombination(String current, int columnIdx, int size) {
        if(current.length() == size) {
            if(isMinimal(current) && isUnique(current)) {
                map.put(current, 1);
                answer++;
            }
            
            return;
        }
        
        for(int idx = columnIdx; idx < colSize; idx++) {
            makeCombination(current + idx, idx + 1, size);
        }
    }
    
    boolean isMinimal(String current) {
        for(String key : map.keySet()) {
            if(map.get(key) != 1)
                continue;
            
            int count = 0;
            
            for(int idx = 0; idx < key.length(); idx++) {
                if(current.indexOf(key.charAt(idx)) != -1)
                    count++;
            }
            
            if(count == key.length())
                return false;
        }
        
        return true;
    } 
    
    boolean isUnique (String key) {
        Set<String> set = new HashSet<>();
    
        for(String[] info : relation) {
            StringBuilder sb = new StringBuilder();
            
            for(int idx = 0; idx < key.length(); idx++) {
                int column = key.charAt(idx) - '0';
                sb.append(info[column]);
            }   
            
            if(set.contains(sb.toString()))
                return false;
            
            set.add(sb.toString());
        }
        
        return true;
    }
    
    public int solution(String[][] relation) {
        this.relation = relation;
        rowSize = relation.length;
        colSize = relation[0].length;
        
        selected = new boolean[colSize];
        for(int size = 1; size <= colSize; size++) {
            makeCombination("", 0, size);
        }
        
        return answer;
    }
}