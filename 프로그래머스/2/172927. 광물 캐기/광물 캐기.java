import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int pickCount = picks[0] + picks[1] + picks[2];
        int maxMineralIdx = Math.min(pickCount * 5, minerals.length) - 1;
        
        List<int[]> infoList = new ArrayList<>();
        for(int headIdx = 0; headIdx <= maxMineralIdx; headIdx += 5) {
            int diamond = 0;
            int iron = 0;
            int stone = 0;
            
            for(int idx = headIdx; idx < headIdx + 5 && idx <= maxMineralIdx; idx++) {
                String mineral = minerals[idx];
                
                if(mineral.equals("diamond"))
                    diamond++;
                else if(mineral.equals("iron"))
                    iron++;
                else
                    stone++;
            }
            
            int fatigue = diamond * 25 + iron * 5 + stone;
            infoList.add(new int[]{diamond, iron, stone, fatigue});
        }
        
        Collections.sort(infoList, (o1, o2) -> o2[3] - o1[3]);
        
        for(int[] info : infoList) {
            if(picks[0] > 0) {
                answer += (info[0] + info[1] + info[2]);
                picks[0]--;
            } else if(picks[1] > 0) {
                answer += (info[0] * 5 + info[1] + info[2]);
                picks[1]--;
            } else if(picks[2] > 0) {
                answer += info[3];
                picks[2]--;
            } else {
                break;
            }
        }
        
        return answer;
    }
}
