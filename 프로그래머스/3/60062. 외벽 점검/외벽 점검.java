import java.util.*;

class Solution {
    int weakSize;
    int[] expandedWeak;
    int[] dist;
    boolean[] selected;
    int answer = Integer.MAX_VALUE;
    
    void search(List<Integer> selectedList) {
        if(selectedList.size() == dist.length) {
            check(selectedList);
            return;
        }
        
        for(int distIdx = 0; distIdx < dist.length; distIdx++) {
            if(selected[distIdx])
                continue;
            
            selected[distIdx] = true;
            selectedList.add(dist[distIdx]);
            
            search(selectedList);
            
            selectedList.remove(selectedList.size() - 1);
            selected[distIdx] = false;
        }
    }
    
    void check(List<Integer> selectedList) {
        for(int startIdx = 0; startIdx < weakSize; startIdx++) {
            int listIdx = 0;
            int coverPoint = expandedWeak[startIdx] + selectedList.get(listIdx);
            
            for(int weakIdx = startIdx; weakIdx < startIdx + weakSize; weakIdx++) {
                if(expandedWeak[weakIdx] <= coverPoint)
                    continue;

                listIdx++;

                if(listIdx == selectedList.size())
                    break;

                coverPoint = expandedWeak[weakIdx] + selectedList.get(listIdx);
            }

            if(listIdx < selectedList.size()) 
                answer = Math.min(answer, listIdx + 1);
        }
    }
    
    public int solution(int n, int[] weak, int[] dist) {
        this.weakSize = weak.length;
        this.dist = dist;
        selected = new boolean[dist.length];
        
        expandedWeak = new int[weakSize * 2];
        for(int weakIdx = 0; weakIdx < weakSize; weakIdx++) {
            int point = weak[weakIdx];
            expandedWeak[weakIdx] = point;
            expandedWeak[weakIdx + weakSize] = point + n;
        }
        
        search(new ArrayList<>());
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}