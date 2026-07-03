import java.util.*;

class Solution {
    int getGCD(int num1, int num2) {
        while(num2 > 0) {
            int remain = num1 % num2;
            num1 = num2;
            num2 = remain;
        }
        
        return num1;
    }
    
    int getLCM(int num1, int num2) {
        return num1 * num2 / getGCD(num1, num2);
    }
    
    public int solution(int[] arr) {
        Arrays.sort(arr);
        
        int answer = 1;
        for(int idx = arr.length - 1; idx >= 0; idx--) {
            answer = getLCM(answer, arr[idx]);
        }
        
        return answer;
    }
}