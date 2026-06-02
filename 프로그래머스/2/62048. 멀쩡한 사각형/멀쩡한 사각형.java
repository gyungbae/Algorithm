class Solution {
    int getGCD(int num1, int num2) {
        while(num2 > 0) {
            int remainder = num1 % num2;
            num1 = num2;
            num2 = remainder;
        }
        
        return num1;
    }
    
    public long solution(int w, int h) {
        return (long) w * h - (w + h - getGCD(w, h));
    }
}