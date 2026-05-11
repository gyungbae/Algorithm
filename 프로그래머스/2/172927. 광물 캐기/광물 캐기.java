import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int pickCount = 0;
        
        for(int pick : picks) {
            pickCount += pick;
        }
        
        int mineralCount = Math.min(minerals.length, pickCount * 5);
        
        List<int[]> infoList = new ArrayList<>();
        
        for(int headIdx = 0; headIdx < mineralCount; headIdx += 5) {
            int diamondCount = 0;
            int ironCount = 0;
            int stoneCount = 0;
            
            for(int idx = headIdx; idx < headIdx + 5 && idx < mineralCount; idx++) {
                String mineral = minerals[idx];
                
                if(mineral.equals("diamond")) {
                    diamondCount++;
                } else if(mineral.equals("iron")) {
                    ironCount++;
                } else {
                    stoneCount++;
                }
            }
            
            int score = 25 * diamondCount + 5 * ironCount + stoneCount;
            infoList.add(new int[]{diamondCount, ironCount, stoneCount, score});
        }
        
        Collections.sort(infoList, (o1, o2) -> o2[3] - o1[3]);
        
        for(int[] info : infoList) {
            if(picks[0] > 0) {
                answer += info[0];
                answer += info[1];
                answer += info[2];
                picks[0]--;
            } else if(picks[1] > 0) {
                answer += info[0] * 5;
                answer += info[1];
                answer += info[2];
                picks[1]--;
            } else if(picks[2] > 0){
                answer += info[3];
                picks[2]--;
            }
        }
        
        return answer;
    }
}