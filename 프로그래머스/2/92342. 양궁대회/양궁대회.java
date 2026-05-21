import java.util.*;

class Solution {
    int n;
    int[] info;
    
    int[] answer;
    int[] tmpInfo;
    int maxDif;
    
    void search(int searchIdx, int currentShoot) {
        if(searchIdx == 11 || currentShoot == n) {
            int remainShoot = n - currentShoot;
            tmpInfo[10] += remainShoot;
            
            int lion = 0;
            int peach = 0;
            
            for(int idx = 0; idx <= 10; idx++) {
                if(info[idx] == 0 && tmpInfo[idx] == 0)
                    continue;
                
                int score = 10 - idx;
                
                if(info[idx] >= tmpInfo[idx])
                    peach += score;
                else
                    lion += score;
            }
            
            int dif = lion - peach;
            
            if(dif > 0) {
                if(dif > maxDif) {
                    maxDif = dif;
                    answer = Arrays.copyOf(tmpInfo, 11);
                } else if(dif == maxDif && answer != null) {
                    for(int idx = 10; idx >= 0; idx--) {
                        if(tmpInfo[idx] > answer[idx]) {
                            answer = Arrays.copyOf(tmpInfo, 11);
                            break;
                        } else if(tmpInfo[idx] < answer[idx]) {
                            break;
                        }
                    }
                }
            }
            
            tmpInfo[10] -= remainShoot;
            return;
        }
        
        int nextShoot = info[searchIdx] + 1;
        
        if(currentShoot + nextShoot <= n) {
            tmpInfo[searchIdx] = nextShoot;
            search(searchIdx + 1, currentShoot + nextShoot);
            tmpInfo[searchIdx] = 0;
        }
        
        search(searchIdx + 1, currentShoot);
    }
    
    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        
        tmpInfo = new int[11];
        maxDif = 0;
        
        search(0, 0);
        
        return answer == null ? new int[]{-1} : answer;
    }
}