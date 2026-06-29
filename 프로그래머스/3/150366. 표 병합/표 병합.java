import java.util.*;

class Solution {
    int[] parentArr;
    String[] valueArr;
    
    int find(int idx) {
        if(parentArr[idx] != idx)
            parentArr[idx] = find(parentArr[idx]);
        
        return parentArr[idx];
    }
    
    public String[] solution(String[] commands) {
        int size = 50 * 50;
        
        parentArr = new int[size];
        for(int node = 0; node < size; node++) {
            parentArr[node] = node;
        }
        
        valueArr = new String[size];
        List<String> printList = new ArrayList<>();
        
        for(String command : commands) {
            String[] info = command.split(" ");
            String cmd = info[0];
            
            if(cmd.equals("UPDATE")) {
                if(info.length == 4) {
                    int row = Integer.parseInt(info[1]) - 1;
                    int col = Integer.parseInt(info[2]) - 1;
                    int idx = row * 50 + col;
                    
                    valueArr[find(idx)] = info[3];
                } else {
                    for(int idx = 0; idx < size; idx++) {
                        if(info[1].equals(valueArr[idx]))
                            valueArr[idx] = info[2];
                    }
                }
            } else if(cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(info[1]) - 1;
                int c1 = Integer.parseInt(info[2]) - 1;
                int r2 = Integer.parseInt(info[3]) - 1;
                int c2 = Integer.parseInt(info[4]) - 1;
                
                int idx1 = r1 * 50 + c1;
                int idx2 = r2 * 50 + c2;
                
                int parent1 = find(idx1);
                int parent2 = find(idx2);
                
                if(parent1 != parent2) {
                    String value1 = valueArr[parent1];
                    String value2 = valueArr[parent2];
                    
                    String newValue = value1;
                    if(value1 == null && value2 != null)
                        newValue = value2;
                    
                    parentArr[parent2] = parent1;
                    
                    valueArr[parent1] = newValue;
                    valueArr[parent2] = null;
                }                
            } else if(cmd.equals("UNMERGE")) {
                int row = Integer.parseInt(info[1]) - 1;
                int col = Integer.parseInt(info[2]) - 1;
                
                int idx = row * 50 + col;
                int parent = find(idx);
                String value = valueArr[parent];
                
                List<Integer> mergedCellList = new ArrayList<>(); 
                
                for(int cellIdx = 0; cellIdx < size; cellIdx++) {
                    if(find(cellIdx) == parent) {
                        mergedCellList.add(cellIdx); 
                    }
                }
                
                for(int cellIdx : mergedCellList) {
                    parentArr[cellIdx] = cellIdx;
                    valueArr[cellIdx] = null; 
                }
                
                valueArr[idx] = value;
            } else {
                int row = Integer.parseInt(info[1]) - 1;
                int col = Integer.parseInt(info[2]) - 1;
                int idx = row * 50 + col;
                int parent = find(idx);
                
                printList.add(valueArr[parent] == null ? "EMPTY" : valueArr[parent]);
            }
        }
        
        String[] answer = new String[printList.size()];
        for(int idx = 0; idx < answer.length; idx++) {
            answer[idx] = printList.get(idx);
        }
        
        return answer;
    }
}